package datastructures.tree.heap.binaryheap;

import java.util.*;

public class BinaryHeap {

    private final ArrayList<Integer> backingArrayList;
    private final HashMap<Integer, Set<Integer>> backingHashMap;
    private int size;


    BinaryHeap() {
        backingArrayList = new ArrayList<>();
        backingHashMap = new HashMap<>();
        size = 0;
    }

    //Complexity: O(1)
    public Integer peek() {
        return backingArrayList.get(0);
    }

    //Complexity: O(log(n))
    public void add(Integer element) {
        backingArrayList.add(element);
        addToBackingHashmap(element, size);
        size++;
        bubbleUp(size - 1);
    }

    //Complexity: O(log(n))
    public Integer poll() {
        Integer result = backingArrayList.get(0);
        removeAt(0);
        return result;
    }

    //Complexity: O(log(n))
    public void remove(Integer element) {
        int elementIndex = indexOf(element);
        if (elementIndex == -1) {
            throw new NoSuchElementException("Element not in heap");
        }
        removeAt(elementIndex);
    }

    //Complexity: O(1)
    public boolean contains(Integer element) {
        return indexOf(element) != -1;
    }

    private int indexOf(Integer element) {
        if (!backingHashMap.containsKey(element)) {
            return -1;
        } else {
            return backingHashMap.get(element).stream().findFirst().orElse(-1);
        }
    }

    private void removeAt(int indexToRemove) {
        swap(size - 1, indexToRemove);
        removeFromBackingHashmap(backingArrayList.get(size - 1), size - 1);
        size--;

        if (!smallerThanParent(indexToRemove, parentIndexOf(indexToRemove))) {
            bubbleUp(indexToRemove);
        } else {
            bubbleDown(indexToRemove);
        }
    }

    private void bubbleUp(int elementIndex) {
        int currentIndex = elementIndex;
        int parentIndex = parentIndexOf(elementIndex);

        while (!smallerThanParent(currentIndex, parentIndex)) {
            swap(currentIndex, parentIndex);
            currentIndex = parentIndex;
            parentIndex = parentIndexOf(parentIndex);
        }
    }

    private void bubbleDown(int elementIndex) {
        int currentIndex = elementIndex;
        int leftChildIndex = leftChildIndexOf(elementIndex);
        int rightChildIndex = rightChildIndexOf(elementIndex);

        while (!greaterThanBothChildren(currentIndex, leftChildIndex, rightChildIndex)) {
            int smallestChildIndex = indexOfGreater(leftChildIndex, rightChildIndex);
            swap(currentIndex, smallestChildIndex);

            currentIndex = smallestChildIndex;
            leftChildIndex = leftChildIndexOf(currentIndex);
            rightChildIndex = rightChildIndexOf(currentIndex);
        }
    }

    private int indexOfGreater(int leftChildIndex, int rightChildIndex) {
        if (leftChildIndex == -1 && rightChildIndex == -1) {
            return -1;
        } else if (leftChildIndex == -1) {
            return rightChildIndex;
        } else if (rightChildIndex == -1) {
            return leftChildIndex;
        } else {
            return backingArrayList.get(leftChildIndex) >= backingArrayList.get(rightChildIndex)
                    ? leftChildIndex
                    : rightChildIndex;
        }
    }

    private boolean smallerThanParent(int elementIndex, int parentIndex) {
        return parentIndex == -1 || backingArrayList.get(elementIndex) < backingArrayList.get(parentIndex);
    }

    private boolean greaterThanBothChildren(int elementIndex, int leftChildIndex, int rightChildIndex) {
        if (leftChildIndex == -1 && rightChildIndex == -1) {
            return true;
        } else if (leftChildIndex == -1) {
            return backingArrayList.get(elementIndex) > backingArrayList.get(rightChildIndex);
        } else if (rightChildIndex == -1) {
            return backingArrayList.get(elementIndex) > backingArrayList.get(leftChildIndex);
        } else {
            return backingArrayList.get(elementIndex) > backingArrayList.get(leftChildIndex) &&
                    backingArrayList.get(elementIndex) > backingArrayList.get(rightChildIndex);
        }
    }

    private int parentIndexOf(int childIndex) {
        return Math.floorDiv(childIndex - 1, 2);
    }

    private int leftChildIndexOf(int parentIndex) {
        int result = parentIndex * 2 + 1;

        if (result >= size) {
            return -1;
        }

        return result;
    }

    private int rightChildIndexOf(int parentIndex) {
        int result = parentIndex * 2 + 2;

        if (result >= size) {
            return -1;
        }

        return result;
    }

    private void swap(int firstIndex, int secondIndex) {
        Integer firstElement = backingArrayList.get(firstIndex);
        Integer secondElement = backingArrayList.get(secondIndex);

        removeFromBackingHashmap(firstElement, firstIndex);
        addToBackingHashmap(firstElement, secondIndex);

        removeFromBackingHashmap(secondElement, secondIndex);
        addToBackingHashmap(secondElement, firstIndex);

        backingArrayList.set(firstIndex, secondElement);
        backingArrayList.set(secondIndex, firstElement);
    }

    private void addToBackingHashmap(Integer element, int index) {
        if (backingHashMap.containsKey(element)) {
            backingHashMap.get(element).add(index);
        } else {
            backingHashMap.put(element, new HashSet<>(List.of(index)));
        }
    }

    private void removeFromBackingHashmap(Integer element, int index) {
        backingHashMap.get(element).remove(index);
    }

    @Override
    public String toString() {
        return "BinaryHeap{" +
                "backingArrayList=" + backingArrayList +
                ", backingHashMap=" + backingHashMap +
                ", size=" + size +
                '}';
    }
}
