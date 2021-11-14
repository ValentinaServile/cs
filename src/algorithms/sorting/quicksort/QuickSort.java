package algorithms.sorting.quicksort;

import java.math.BigInteger;
import java.util.List;

public class QuickSort {

    //Complexity: O(n log(n))
    public void sort(Integer[] input) {
        sort(input, 0, input.length -1);
    }

    private void sort(Integer[] input, int startIndex, int endIndex) {
        if (endIndex - startIndex < 1) {
            return;
        }

        int pivotIndex = partition(input, startIndex, endIndex);

        sort(input, startIndex, pivotIndex - 1);
        sort(input, pivotIndex + 1, endIndex);
    }

    //Lomuto partitioning scheme
    private int partition(Integer[] input, int startIndex, int endIndex) {
        Integer pivot = input[endIndex];

        int leftIndex = startIndex - 1;
        int rightIndex = startIndex;

        while (rightIndex <= endIndex - 1) {

            if (input[rightIndex] < pivot) {
                leftIndex++;
                swap(input, rightIndex, leftIndex);
            }

            rightIndex++;
        }

        swap(input, leftIndex + 1, endIndex);
        return leftIndex + 1;
    }


    private void swap(Integer[] input, int firstIndex, int secondIndex) {
        int tmp = input[firstIndex];
        input[firstIndex] = input[secondIndex];
        input[secondIndex] = tmp;
    }

}
