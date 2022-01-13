package datastructures.hashtable.openaddressing;

import java.util.NoSuchElementException;
import java.util.Objects;

public class LinearProbingHashTable {
    private int capacity;
    private final double maxLoadFactor;
    private Entry[] backingArray;
    private int size;

    public LinearProbingHashTable() {
        this(50, 0.75);
    }

    public LinearProbingHashTable(int capacity, double maxLoadFactor) {
        this.capacity = capacity;
        this.maxLoadFactor = maxLoadFactor;
        this.backingArray = new Entry[capacity];
    }

    //Complexity: O(1)
    public void insert(String key, String value) {
        resizeIfAboveThreshold();

        int index = hash(key);
        int probingSteps = 1;

        while (!canInsertAtIndex(key, index)) {
            index = (index + nextInProbingSequence(probingSteps)) % capacity;
            probingSteps++;
        }

        if (backingArray[index] == null || backingArray[index].deleted) {
            size++;
        }

        backingArray[index] = new Entry(key, value);
    }

    //Complexity: O(1)
    public String get(String key) {
        int index = hash(key);
        int probingSteps = 1;

        while (backingArray[index] != null && !Objects.equals(backingArray[index].key, key)) {
            index = (index + nextInProbingSequence(probingSteps)) % capacity;
            probingSteps++;
        }

        if (backingArray[index] == null || backingArray[index].deleted) {
            throw new NoSuchElementException("not found");
        }

        return backingArray[index].value;
    }

    //Complexity: O(1)
    public void remove(String key) {
        int index = hash(key);
        int probingSteps = 1;

        while (backingArray[index] != null && !Objects.equals(backingArray[index].key, key)) {
            index = (index + nextInProbingSequence(probingSteps)) % capacity;
            probingSteps++;
        }

        if (backingArray[index] == null || backingArray[index].deleted) {
            throw new NoSuchElementException("not found");
        }

        backingArray[index].deleted = true;
        size--;
    }

    //Complexity: O(1)
    public int size() {
        return size;
    }

    //simplest linear probing implementation: P(x) = ax + b where a is 1 and b is 0
    //creates no cycles because the GCD of 1 and any length of the backing array is always 1
    private int nextInProbingSequence(int probingSteps) {
        return probingSteps;
    }

    //silly hashing function, sums ascii value of each char in the string
    private int hash(String key) {
        return key.chars().sum() % capacity;
    }

    private boolean canInsertAtIndex(String key, int index) {
        return backingArray[index] == null || backingArray[index].deleted || Objects.equals(backingArray[index].key, key);
    }

    private void resizeIfAboveThreshold() {
        if (capacity > 2 && size < (capacity * maxLoadFactor)) {
            return;
        }

        capacity *= 2;
        Entry[] newBackingArray = new Entry[capacity];

        for (Entry entry: backingArray) {

            if (entry == null) {
                continue;
            }

            int index = hash(entry.key);
            int probingSteps = 1;

            while (newBackingArray[index] != null) {
                index = (index + nextInProbingSequence(probingSteps)) % capacity;
                probingSteps++;
            }

            newBackingArray[index] = entry;
        }

        backingArray = newBackingArray;
    }

    private static class Entry {
        private String key;
        private String value;
        private Boolean deleted;

        private Entry(String key, String value) {
            this(key, value, false);
        }

        private Entry(String key, String value, Boolean deleted) {
            this.key = key;
            this.value = value;
            this.deleted = deleted;
        }
    }
}
