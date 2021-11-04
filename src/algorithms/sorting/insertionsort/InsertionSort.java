package algorithms.sorting.insertionsort;

public class InsertionSort {

    //Complexity: O(n^2)
    public void sort(Integer[] input) {

        for (int firstUnsortedIndex = 1; firstUnsortedIndex < input.length; firstUnsortedIndex++) {

            Integer elementToInsert = input[firstUnsortedIndex];

            int currentIndex = firstUnsortedIndex;

            while (currentIndex > 0 && elementToInsert <= input[currentIndex - 1]) {
                input[currentIndex] = input[currentIndex - 1];
                currentIndex--;
            }

            input[currentIndex] = elementToInsert;
        }
    }

}
