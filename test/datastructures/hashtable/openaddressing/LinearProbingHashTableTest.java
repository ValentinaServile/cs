package datastructures.hashtable.openaddressing;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LinearProbingHashTableTest {

    @Test
    void allowsInsertingMultipleElements() {
        LinearProbingHashTable hashTable = new LinearProbingHashTable();
        hashTable.insert("key1", "apples");
        hashTable.insert("key2", "pears");
        hashTable.insert("key3", "oranges");

        assertEquals(hashTable.get("key1"), "apples");
        assertEquals(hashTable.get("key2"), "pears");
        assertEquals(hashTable.get("key3"), "oranges");
    }

    @Test
    void capacityWillExpandWhenGoingOverMaxLoadFactor() {
        LinearProbingHashTable hashTable = new LinearProbingHashTable(2, 0.75);
        hashTable.insert("key1", "apples");
        hashTable.insert("key2", "pears");
        hashTable.insert("key3", "oranges");
        hashTable.insert("key4", "bananas");
        hashTable.insert("key5", "mangoes");
        hashTable.insert("key6", "cherries");

        assertEquals(hashTable.size(), 6);
        assertEquals(hashTable.get("key1"), "apples");
        assertEquals(hashTable.get("key2"), "pears");
        assertEquals(hashTable.get("key3"), "oranges");
        assertEquals(hashTable.get("key4"), "bananas");
        assertEquals(hashTable.get("key5"), "mangoes");
        assertEquals(hashTable.get("key6"), "cherries");

    }

    @Test
    void allowsInsertingElementsWithSameValue() {
        LinearProbingHashTable hashTable = new LinearProbingHashTable();
        hashTable.insert("key1", "oranges");
        hashTable.insert("key2", "oranges");
        hashTable.insert("key3", "oranges");

        assertEquals(hashTable.get("key1"), "oranges");
        assertEquals(hashTable.get("key2"), "oranges");
        assertEquals(hashTable.get("key3"), "oranges");
    }

    @Test
    void allowsInsertingElementsWithConflictingHash() {
        LinearProbingHashTable hashTable = new LinearProbingHashTable();
        hashTable.insert("key1", "apples");
        hashTable.insert("1key", "pears");
        hashTable.insert("k1ey", "oranges");
        //these are the same because the hash function implementation sums
        //the ascii value of each character - therefore any string permutation
        //will have the same sum

        assertEquals(hashTable.get("key1"), "apples");
        assertEquals(hashTable.get("1key"), "pears");
        assertEquals(hashTable.get("k1ey"), "oranges");
    }

    @Test
    void throwsNoSuchElementExceptionWhenTryingToRetrieveNonExistentElement() {
        LinearProbingHashTable hashTable = new LinearProbingHashTable();

        assertThrows(NoSuchElementException.class, () -> hashTable.get("NONEXISTENT"));
    }

    @Test
    void allowsUpdatingElementWithSameKey() {
        LinearProbingHashTable hashTable = new LinearProbingHashTable();
        hashTable.insert("key1", "apples");
        hashTable.insert("key1", "oranges");

        assertEquals(hashTable.get("key1"), "oranges");
    }

    @Test
    void allowsRemovingElement() {
        LinearProbingHashTable hashTable = new LinearProbingHashTable();
        hashTable.insert("key1", "apples");
        hashTable.insert("key2", "pears");
        hashTable.insert("key3", "oranges");

        hashTable.remove("key2");

        assertEquals(hashTable.get("key1"), "apples");
        assertThrows(NoSuchElementException.class, () -> hashTable.get("key2"));
        assertEquals(hashTable.get("key3"), "oranges");
    }

    @Test
    void allowsRemovingElementEvenWithConflictingHash() {
        LinearProbingHashTable hashTable = new LinearProbingHashTable();
        hashTable.insert("key1", "apples");
        hashTable.insert("1key", "pears");
        hashTable.insert("k1ey", "oranges");
        //these are the same because the hash function implementation sums
        //the ascii value of each character - therefore any string permutation
        //will have the same sum

        hashTable.remove("1key");

        assertEquals(hashTable.get("key1"), "apples");
        assertThrows(NoSuchElementException.class, () -> hashTable.get("1key"));
        assertEquals(hashTable.get("k1ey"), "oranges");
    }
}