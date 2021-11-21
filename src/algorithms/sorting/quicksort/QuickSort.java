package algorithms.sorting.quicksort;

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

        int lastElementSmallerThanPivotIndex = startIndex - 1;

        for (int i = startIndex; i <= endIndex - 1; i++) {

            if (input[i] < pivot) {
                lastElementSmallerThanPivotIndex++;
                swap(input, i, lastElementSmallerThanPivotIndex);
            }

        }

        swap(input, lastElementSmallerThanPivotIndex + 1, endIndex);
        return lastElementSmallerThanPivotIndex + 1;
    }


    private void swap(Integer[] input, int firstIndex, int secondIndex) {
        int tmp = input[firstIndex];
        input[firstIndex] = input[secondIndex];
        input[secondIndex] = tmp;
    }

}
