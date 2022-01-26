package datastructures.suffixarray;

import java.util.*;

public class SuffixArray {

    private final Integer[] suffixArray;
    private final Integer[] lcpArray;
    private final String text;
    private final List<TextColor> colorsInText;

    public SuffixArray(String text) {
        colorsInText = List.of(new TextColor(0, text.length(), Color.RED));
        suffixArray = buildSuffixArrayFrom(text);
        lcpArray = buildLcpArrayFrom(text, suffixArray);
        this.text = text;
    }

    public SuffixArray(String ...words) {
        colorsInText = new ArrayList<>();
        int lastIndex = 0;

        StringBuilder textBuilder = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            Color currentColor = Color.values()[i];

            textBuilder.append(words[i]);
            textBuilder.append(currentColor.suffix);
            colorsInText.add(new TextColor(lastIndex, lastIndex + words[i].length(), currentColor));
            lastIndex += words[i].length() + 1;
        }

        this.text = textBuilder.toString();

        suffixArray = buildSuffixArrayFrom(text);
        lcpArray = buildLcpArrayFrom(text, suffixArray);
    }

    //Complexity: O(n^2 log(n))
    //this is a naive construction algorithm - more advanced ones
    //can create the suffix array in O(n log(n))
    //or also in O(n) time (DC3/Skew Algorithm)
    private Integer[] buildSuffixArrayFrom(String text) {
        class SuffixWithIndex {
            final String suffix;
            final Integer index;

            public SuffixWithIndex(String suffix, Integer index) {
                this.suffix = suffix;
                this.index = index;
            }
        }
        SuffixWithIndex[] suffixes = new SuffixWithIndex[text.length()];

        for (int i = 0; i < text.length(); i++) {
            suffixes[i] = new SuffixWithIndex(text.substring(i), i);
        }

        Arrays.sort(suffixes, Comparator.comparing(element -> element.suffix));

        return Arrays.stream(suffixes).map(suffix -> suffix.index).toArray(Integer[]::new);
    }

    //Complexity: O(n)
    //Kasai's algorithm
    private Integer[] buildLcpArrayFrom(String text, Integer[] suffixArray) {
        Integer[] lcpArray = new Integer[suffixArray.length];
        Integer[] inverseSuffixArray = new Integer[suffixArray.length];

        for (int i = 0; i < suffixArray.length; i++) {
            inverseSuffixArray[suffixArray[i]] = i;
        }

        int matchedCharacters = 0;

        for (int currentSuffixIndexInText = 0; currentSuffixIndexInText < text.length(); currentSuffixIndexInText++) {
            int currentSuffixIndexInSuffixArray = inverseSuffixArray[currentSuffixIndexInText];

            if (currentSuffixIndexInSuffixArray == 0) {
                matchedCharacters = 0;
                lcpArray[currentSuffixIndexInSuffixArray] = matchedCharacters;
                continue;
            }

            int suffixIndexToCompareToInText = suffixArray[currentSuffixIndexInSuffixArray - 1];

            while (currentSuffixIndexInText + matchedCharacters < text.length()
                    && suffixIndexToCompareToInText + matchedCharacters < text.length() &&
                    text.charAt(currentSuffixIndexInText + matchedCharacters) == text.charAt(suffixIndexToCompareToInText + matchedCharacters)) {
                matchedCharacters++;
            }

            lcpArray[currentSuffixIndexInSuffixArray] = matchedCharacters;

            if (matchedCharacters > 0) {
                matchedCharacters--;
            }

        }

        return lcpArray;
    }

    //Complexity: O(n)
    public int uniqueSubstrings() {
        int totalNumberOfSubstrings = (text.length() * (text.length() + 1)) / 2;
        int numberOfRepeatedSubstrings = Arrays.stream(lcpArray).mapToInt(Integer::intValue).sum();

        return totalNumberOfSubstrings - numberOfRepeatedSubstrings;
    }

    //Complexity: O(n)
    public String longestRepeatedSubstring() {
        int maxLcpValue = 0;
        int maxLcpIndex = 0;

        for (int i = 0; i < lcpArray.length; i++) {
            if (lcpArray[i] > maxLcpValue) {
                maxLcpValue = lcpArray[i];
                maxLcpIndex = i;
            }
        }

        return text.substring(suffixArray[maxLcpIndex], maxLcpValue);
    }

    //O(n1 + n2 + ... nm)
    public List<String> longestCommonSubstringsAmong(int numberOfStrings) {
        List<String> result = new ArrayList<>();
        int longestFoundSubstringLength = 0;

        SlidingWindowMinimum window = new SlidingWindowMinimum(lcpArray);

        while (window.highBound < text.length()) {
            if (numberOfColorsInWindow(window.lowBound, window.highBound) < numberOfStrings) {
                window.advance();
                continue;
            }

            window.shrink();

            String shortestCommonSuffix = suffixFrom(window.getIndexOfCurrentMinimum(), window.getCurrentMinimum());

            if (shortestCommonSuffix.length() > longestFoundSubstringLength) {
                result = new ArrayList<>();
                result.add(shortestCommonSuffix);
                longestFoundSubstringLength = shortestCommonSuffix.length();
            } else if (shortestCommonSuffix.length() == longestFoundSubstringLength) {
                result.add(shortestCommonSuffix);
            }

        }

        return result;
    }

    private String suffixFrom(Integer indexInSuffixArray, Integer length) {
        Integer startIndex = suffixArray[indexInSuffixArray];
        return text.substring(startIndex, startIndex + length);
    }

    private int numberOfColorsInWindow(int windowStartIndex, int windowEndIndex) {
        Set<Color> colorsInWindow = new HashSet<>();

        for (int i = windowStartIndex; i < windowEndIndex; i++) {
            Integer currentSuffixIndex = suffixArray[i];
            colorsInWindow.add(colorOf(currentSuffixIndex));
        }

        return colorsInWindow.size();
    }

    private Color colorOf(Integer suffixStartIndex) {
        for (TextColor textColor: colorsInText) {
            if (textColor.startIndex <= suffixStartIndex  && textColor.endIndex >= suffixStartIndex) {
                return textColor.color;
            }
        }

        throw new RuntimeException("Text color not found for suffix at: " + suffixStartIndex);
    }

    private enum Color {
        RED("#"),
        BLUE("$"),
        GREEN("%"),
        YELLOW("@"),
        WHITE("^"),
        BLACK("+");

        private final String suffix;

        Color(String suffix) {
            this.suffix = suffix;
        }
    }

    private static class TextColor {
        private final Color color;
        private final int startIndex;
        private final int endIndex;

        public TextColor(int startIndex, int endIndex, Color color) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.color = color;
        }
    }

    private static class SlidingWindowMinimum {
        Integer[] values;
        int lowBound, highBound;

        Deque<Integer> deque = new ArrayDeque<>();

        public SlidingWindowMinimum(Integer[] values) {
            if (values == null) throw new IllegalArgumentException();
            this.values = values;
        }

        public void advance() {
            while (!deque.isEmpty() && values[deque.peekLast()] > values[highBound])
                deque.removeLast();

            deque.addLast(highBound);

            highBound++;
        }

        public void shrink() {
            lowBound++;
            while (!deque.isEmpty() && deque.peekFirst() < lowBound)
                deque.removeFirst();
        }

        // Complexity: O(1)
        public Integer getCurrentMinimum() {
            return values[deque.peekFirst()];
        }

        // Complexity: O(1)
        public Integer getIndexOfCurrentMinimum() {
            return deque.peekFirst();
        }
    }
}
