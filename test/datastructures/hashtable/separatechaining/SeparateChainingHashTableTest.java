package datastructures.hashtable.separatechaining;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SeparateChainingHashTableTest {
    
    @Test
    void allowsInsertingMultipleElements() {
        SeparateChainingHashTable hashTable = new SeparateChainingHashTable();
        hashTable.insert("key1", "apples");
        hashTable.insert("key2", "pears");
        hashTable.insert("key3", "oranges");

        assertEquals(hashTable.get("key1"), "apples");
        assertEquals(hashTable.get("key2"), "pears");
        assertEquals(hashTable.get("key3"), "oranges");
    }

    @Test
    void capacityWillExpandWhenGoingOverMaxLoadFactor() {
        SeparateChainingHashTable hashTable = new SeparateChainingHashTable(2, 0.75);
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
        SeparateChainingHashTable hashTable = new SeparateChainingHashTable();
        hashTable.insert("key1", "oranges");
        hashTable.insert("key2", "oranges");
        hashTable.insert("key3", "oranges");

        assertEquals(hashTable.get("key1"), "oranges");
        assertEquals(hashTable.get("key2"), "oranges");
        assertEquals(hashTable.get("key3"), "oranges");
    }

    @Test
    void allowsInsertingElementsWithConflictingHash() {
        SeparateChainingHashTable hashTable = new SeparateChainingHashTable();
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
        SeparateChainingHashTable hashTable = new SeparateChainingHashTable();

        assertThrows(NoSuchElementException.class, () -> hashTable.get("NONEXISTENT"));
    }

    @Test
    void allowsUpdatingElementWithSameKey() {
        SeparateChainingHashTable hashTable = new SeparateChainingHashTable();
        hashTable.insert("key1", "apples");
        hashTable.insert("key1", "oranges");

        assertEquals(hashTable.get("key1"), "oranges");
    }

    @Test
    void allowsRemovingElement() {
        SeparateChainingHashTable hashTable = new SeparateChainingHashTable();
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
        SeparateChainingHashTable hashTable = new SeparateChainingHashTable();
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