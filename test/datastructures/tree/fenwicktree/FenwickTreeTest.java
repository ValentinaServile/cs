package datastructures.tree.fenwicktree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FenwickTreeTest {

    @Test
    void prefixSumShouldReturnSumOfAllValuesUpToIndex() {
        FenwickTree fenwickTree = new FenwickTree(new int[]{5, 7, 9, 12, -1, 7, 2, -4});

        Integer result = fenwickTree.prefixSum(5);

        assertEquals(39, result);
    }

    @Test
    void rangeQueryShouldReturnDifferenceBetweenTwoIndexes() {
        FenwickTree fenwickTree = new FenwickTree(new int[]{5, 7, 9, 12, -1, 7, 2, -4});

        Integer result = fenwickTree.rangeQuery(3, 5);

        assertEquals(18, result);
    }

    @Test
    void shouldAddValueToIndexAdjustingOtherValuesInTheTree() {
        FenwickTree fenwickTree = new FenwickTree(new int[]{5, 7, 9, 12, -1, 7, 2, -4});
        fenwickTree.addToIndex(5, 2);

        Integer result = fenwickTree.prefixSum(5);

        assertEquals(41, result);
    }

    @Test
    void shouldUpdateValueAtIndexAdjustingOtherValuesInTheTree() {
        FenwickTree fenwickTree = new FenwickTree(new int[]{5, 7, 9, 12, -1, 7, 2, -4});
        fenwickTree.update(5, 4);

        Integer result = fenwickTree.prefixSum(5);

        assertEquals(36, result);
    }
}