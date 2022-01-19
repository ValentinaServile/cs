package datastructures.suffixarray;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SuffixArray {

    private final Integer[] suffixArray;
    private final Integer[] lcpArray;
    private final String text;

    public SuffixArray(String text) {
        suffixArray = buildSuffixArrayFrom(text);
        lcpArray = buildLcpArrayFrom(text, suffixArray);
        this.text = text;
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

    public List<String> longestCommonSubstrings() {
        throw new UnsupportedOperationException("todo");
    }

}
