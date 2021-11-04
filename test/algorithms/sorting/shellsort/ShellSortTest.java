package algorithms.sorting.shellsort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ShellSortTest {

    ShellSort sorter = new ShellSort();

    @Test
    void doesNothingToEmptyArray() {
        Integer[] input = { };

        sorter.sort(input);

        Integer[] expected = { };
        assertArrayEquals(input, expected);
    }

    @Test
    void doesNothingToArrayOfOneElement() {
        Integer[] input = { 7 };

        sorter.sort(input);

        Integer[] expected = { 7 };
        assertArrayEquals(input, expected);
    }

    @Test
    void sortsArrayWithOddNumberOfElementsInPlace() {
        Integer[] input = { 20, 35, -15, 7, 55, 1, -22 };

        sorter.sort(input);

        Integer[] expected = { -22, -15, 1, 7, 20, 35, 55 };
        assertArrayEquals(input, expected);
    }

    @Test
    void sortsArrayWithEvenNumberOfElementsInPlace() {
        Integer[] input = { 4, -18, 22, 67, 39, -15, 2, -4 };

        sorter.sort(input);

        Integer[] expected = { -18, -15, -4, 2, 4, 22, 39, 67 };
        assertArrayEquals(input, expected);
    }

}