package datastructures.tree.fenwicktree;

import static java.lang.System.arraycopy;

public class FenwickTree {

    private static final int PLACEHOLDER = 0;
    private int[] backingArray;

    //Complexity: O(n)
    public FenwickTree(int[] numbers) {
        backingArray = new int[numbers.length + 1];

        backingArray[0] = PLACEHOLDER;
        arraycopy(numbers, 0, backingArray, 1, numbers.length);

        for (int i = 1; i < numbers.length; i++) {
            int parentIndex = parentOf(i);
            backingArray[parentIndex] += backingArray[i];
        }
    }

    //Complexity: O(log(n))
    public void update(int index, int value) {
        int difference = value - rangeQuery(index, index);

        addToIndex(index, difference);
    }

    //Complexity: O(log(n))
    public void addToIndex(int index, int value) {
        int backingArrayIndex = index + 1;

        while (backingArrayIndex < backingArray.length) {
            backingArray[backingArrayIndex] += value;
            backingArrayIndex = parentOf(backingArrayIndex);
        }
    }

    //Complexity: O(log(n))
    public int rangeQuery(int firstIndex, int secondIndex) {
        return prefixSum(secondIndex) - prefixSum(firstIndex - 1);
    }

    //Complexity: O(log(n))
    public int prefixSum(int index) {
        int backingArrayIndex = index + 1;
        int sum = backingArray[backingArrayIndex];

        while (backingArrayIndex > 0) {
            int nextIndex = childOf(backingArrayIndex);
            sum += backingArray[nextIndex];
            backingArrayIndex = nextIndex;
        }

        return sum;
    }

    private int parentOf(int index) {
        return index + leastSignificantBitOf(index);
    }

    private int childOf(int index) {
        return index - leastSignificantBitOf(index);
    }

    private int leastSignificantBitOf(int index) {
        return index & -index; // Returns the value of the least significant bit which is set to 1
    }
}
