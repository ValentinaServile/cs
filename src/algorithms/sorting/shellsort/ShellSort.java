package algorithms.sorting.shellsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShellSort {

    // Complexity: Worst case scenario O(n^2) but performs better
    // than that depending on the gap value
    // O(n^(3/2)) using Knuth’s sequence.
    public void sort(Integer[] input) {

        for (Integer gap : knuthSequenceGapsFrom(input)) {

            for (int firstUnsortedIndex = gap; firstUnsortedIndex < input.length; firstUnsortedIndex++) {

                Integer elementToInsert = input[firstUnsortedIndex];

                int currentIndex = firstUnsortedIndex;

                while (currentIndex - gap >= 0 && elementToInsert <= input[currentIndex - gap]) {
                    input[currentIndex] = input[currentIndex - gap];
                    currentIndex -= gap;
                }

                input[currentIndex] = elementToInsert;
            }
        }

    }

    private List<Integer> knuthSequenceGapsFrom(Integer[] input) {
        List<Integer> gaps = new ArrayList<>();
        int currentGap = 1;
        int k = 2;
        while (currentGap < input.length) {
            gaps.add(currentGap);
            currentGap = ((int) Math.round(Math.pow(3, k)) - 1) / 2;
            k++;
        }
        Collections.reverse(gaps);
        return gaps;
    }
}