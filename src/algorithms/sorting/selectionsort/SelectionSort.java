package algorithms.sorting.selectionsort;

public class SelectionSort {

    //Complexity: O(n^2)
    public void sort(Integer[] input) {

        for (int lastUnsortedIndex = input.length - 1; lastUnsortedIndex > 0; lastUnsortedIndex--) {

            int maximumIndex = 0;

            for (int i = 1; i <= lastUnsortedIndex; i++) {
                if (input[i] > input[maximumIndex]) {
                    maximumIndex = i;
                }
            }

            swap(input, lastUnsortedIndex, maximumIndex);
        }

    }

    private void swap(Integer[] input, int firstIndex, int secondIndex) {
        Integer tmp = input[firstIndex];
        input[firstIndex] = input[secondIndex];
        input[secondIndex] = tmp;
    }
}
