package algorithms.sorting.bubblesort;

public class BubbleSort {

    //Complexity: O(n^2)
    public void sort(Integer[] input) {

        for (int lastUnsortedIndex = input.length - 1; lastUnsortedIndex > 0; lastUnsortedIndex--) {

            for (int currentIndex = 0; currentIndex < lastUnsortedIndex; currentIndex++) {
               if (input[currentIndex] > input[currentIndex + 1]) {
                   swap(input, currentIndex, currentIndex + 1);
               }
            }
        }

    }

    private void swap(Integer[] input, int firstIndex, int secondIndex) {
        int tmp = input[firstIndex];
        input[firstIndex] = input[secondIndex];
        input[secondIndex] = tmp;
    }
}
