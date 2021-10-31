package algorithms.sorting.mergesort;

public class MergeSort {

    public void sort(Integer[] input) {
        sort(input, 0, input.length - 1);
    }

    private void sort(Integer[] input, Integer startIndex, Integer endIndex) {
        if (endIndex - startIndex < 1) {
            return;
        }

        Integer pivotIndex = (startIndex + endIndex) / 2;

        sort(input, startIndex, pivotIndex);
        sort(input, pivotIndex + 1, endIndex);

        merge(input, startIndex, pivotIndex, endIndex);
    }

    private void merge(Integer[] input, Integer startIndex, Integer pivotIndex, Integer endIndex) {

        Integer[] sorted = new Integer[endIndex - startIndex + 1];

        int i = startIndex;
        int j = pivotIndex + 1;
        int sortedIndex = 0;

        while (i <= pivotIndex && j <= endIndex) {
            if (input[i] <= input[j]) {
                sorted[sortedIndex] = input[i];
                i++;
            } else {
                sorted[sortedIndex] = input[j];
                j++;
            }
            sortedIndex++;
        }

        while (i <= pivotIndex) {
            sorted[sortedIndex] = input[i];
            i++;
            sortedIndex++;
        }

        while (j <= endIndex) {
            sorted[sortedIndex] = input[j];
            j++;
            sortedIndex++;
        }

        copyIntoInput(input, sorted, startIndex);
    }

    private void copyIntoInput(Integer[] input, Integer[] temporary, Integer startIndex) {
        for (Integer i = 0; i < temporary.length; i++) {
            input[startIndex + i] = temporary[i];
        }
    }

}
