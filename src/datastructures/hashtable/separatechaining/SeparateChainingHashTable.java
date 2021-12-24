package datastructures.hashtable.separatechaining;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

public class SeparateChainingHashTable {

    private LinkedList<Entry>[] backingArray;
    private int capacity;
    private final double maxLoadFactor;
    private int size = 0;

    public SeparateChainingHashTable() {
        this(50);
    }

    public SeparateChainingHashTable(int capacity) {
        this(capacity, 0.75);
    }

    public SeparateChainingHashTable(int capacity, double maxLoadFactor) {
        this.capacity = capacity;
        this.maxLoadFactor = maxLoadFactor;
        this.backingArray = new LinkedList[capacity];
    }

    //Complexity: O(1)
    public void insert(String key, String value) {
        resizeIfAboveThreshold();
        int hash = hash(key);
        LinkedList<Entry> bucket = backingArray[hash];

        if (bucket == null) {
            bucket = new LinkedList<>();
            backingArray[hash] = bucket;
        }

        if (bucketContainsKey(bucket, key)) {
            bucket = removeKeyFromBucket(bucket, key);
            backingArray[hash] = bucket;
        } else {
            size++;
        }

        bucket.add(new Entry(key, value));
    }

    //Complexity: O(1)
    public String get(String key) {
        int hash = hash(key);
        LinkedList<Entry> bucket = backingArray[hash];
        if (bucket == null) {
            throw new NoSuchElementException("not found");
        }

        return getFromBucket(key, bucket)
                .orElseThrow(() -> new NoSuchElementException("not found"))
                .getValue();
    }
    //Complexity: O(1)
    public void remove(String key) {
        int hash = hash(key);
        LinkedList<Entry> bucket = backingArray[hash];
        if (bucket == null) {
            return;
        }

        LinkedList<Entry> bucketWithoutTarget = removeKeyFromBucket(bucket, key);

        backingArray[hash] = bucketWithoutTarget;
        size--;
    }

    public int size() {
        return size;
    }

    private Optional<Entry> getFromBucket(String key, LinkedList<Entry> bucket) {
        return bucket.stream()
                .filter(e -> e.keyMatches(key))
                .findFirst();
    }

    private boolean bucketContainsKey(LinkedList<Entry> bucket, String key) {
        return bucket.stream().anyMatch(e -> e.keyMatches(key));
    }

    private LinkedList<Entry> removeKeyFromBucket(LinkedList<Entry> bucket, String key) {
        return bucket.stream()
                .filter(not(e -> e.keyMatches(key)))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    private void resizeIfAboveThreshold() {
        if (size < (capacity * maxLoadFactor)) {
            return;
        }

        capacity *= 2;
        LinkedList<Entry>[] newBackingArray = new LinkedList[capacity];

        for (LinkedList<Entry>bucket : backingArray) {

            if (bucket == null) continue;

            for (Entry entry : bucket) {
                int newIndex = hash(entry.key);

                if (newBackingArray[newIndex] == null) {
                    newBackingArray[newIndex] = new LinkedList<>();
                }
                newBackingArray[newIndex].add(entry);
            }
        }

        backingArray = newBackingArray;
    }

    //silly hashing function, sums ascii value of each char in the string
    private int hash(String key) {
        return key.chars().sum() % capacity;
    }

    private static class Entry {
        private final String key;
        private final String value;

        private Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public boolean keyMatches(String otherKey) {
            return Objects.equals(key, otherKey);
        }

        public String getValue() {
            return value;
        }
    }
}
