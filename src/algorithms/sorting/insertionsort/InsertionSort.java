package algorithms.sorting.insertionsort;

public class InsertionSort {

    //Complexity: O(n^2)
    public void sort(Integer[] input) {

        for (int firstUnsortedIndex = 1; firstUnsortedIndex < input.length; firstUnsortedIndex++) {

            Integer elementToInsert = input[firstUnsortedIndex];

            int currentIndex;

            for (currentIndex = firstUnsortedIndex; currentIndex > 0;  currentIndex--) {

                if (elementToInsert <= input[currentIndex - 1]) {
                    input[currentIndex] = input[currentIndex - 1];
                } else {
                    break;
                }
            }

            input[currentIndex] = elementToInsert;
        }
    }

}
