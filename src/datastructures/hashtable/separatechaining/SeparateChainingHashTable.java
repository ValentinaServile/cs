package datastructures.hashtable.separatechaining;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

public class SeparateChainingHashTable {

    private final LinkedList<Entry>[] backingArray = new LinkedList[50];

    //Complexity: O(1)
    public void insert(String key, String value) {
        int hash = hash(key);
        LinkedList<Entry> bucket = backingArray[hash];

        if (bucket == null) {
            LinkedList<Entry> values = new LinkedList<>();
            values.add(new Entry(key, value));
            backingArray[hash] = values;
            return;
        }

        if (bucketContains(bucket, key)) {
            bucket = removeFromBucket(bucket, key);
        }

        bucket.add(new Entry(key, value));

        backingArray[hash] = bucket;
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

        LinkedList<Entry> bucketWithoutTarget = removeFromBucket(bucket, key);

        backingArray[hash] = bucketWithoutTarget;
    }


    private Optional<Entry> getFromBucket(String key, LinkedList<Entry> bucket) {
        return bucket.stream()
                .filter(e -> e.keyMatches(key))
                .findFirst();
    }

    private boolean bucketContains(LinkedList<Entry> bucket, String key) {
        return bucket.stream().anyMatch(e -> e.keyMatches(key));
    }

    private LinkedList<Entry> removeFromBucket(LinkedList<Entry> bucket, String key) {
        return bucket.stream()
                .filter(not(e -> e.keyMatches(key)))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    //silly hashing function, sums ascii value of each char in the string
    private int hash(String key) {
        return key.chars().sum() % 50;
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
