package datastructures.tree.heap.binaryheap;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BinaryHeapTest {

    @Nested
    class Add {

        @Test
        void preservesHeapPropertyByBubblingElementUpIfBiggerThanOthers() {
            BinaryHeap heap = new BinaryHeap();

            heap.add(1);
            assertEquals(heap.peek(), 1);

            heap.add(2);
            assertEquals(heap.peek(), 2);

            heap.add(3);
            assertEquals(heap.peek(), 3);
        }

        @Test
        void preservesHeapPropertyByNotBubblingElementsUpIfSmallerThanOthers() {
            BinaryHeap heap = new BinaryHeap();

            heap.add(3);
            heap.add(1);
            heap.add(2);

            assertEquals(heap.peek(), 3);
        }
    }

    @Nested
    class Poll {

        @Test
        void returnsCurrentMaxElementAndPreservesHeapPropertyByBubblingUpTheNewMaxElement() {
            BinaryHeap heap = new BinaryHeap();

            heap.add(3);
            heap.add(5);
            heap.add(1);
            heap.add(2);
            heap.add(4);
            heap.add(1);

            Integer maximum = heap.poll();
            assertEquals(maximum, 5);
            assertEquals(heap.peek(), 4);

            Integer newMaximum = heap.poll();
            assertEquals(newMaximum, 4);
            assertEquals(heap.peek(), 3);
        }
    }

    @Nested
    class Remove {

        @Test
        void doesNotRemoveNonExistentElements() {
            BinaryHeap heap = new BinaryHeap();

            heap.add(3);
            heap.add(5);
            heap.add(1);
            heap.add(2);
            heap.add(4);
            heap.add(1);

            assertThrows(NoSuchElementException.class, () -> heap.remove(99));
        }

        @Test
        void preservesHeapPropertyByBubblingUpNewMaxElement() {
            BinaryHeap heap = new BinaryHeap();

            heap.add(3);
            heap.add(5);
            heap.add(1);
            heap.add(2);
            heap.add(4);
            heap.add(1);

            heap.remove(5);
            assertEquals(heap.peek(), 4);

            heap.remove(4);
            assertEquals(heap.peek(), 3);
        }
    }

    @Nested
    class Contains {

        @Test
        void returnsTrueWhenElementIsInHeap() {
            BinaryHeap heap = new BinaryHeap();

            heap.add(3);
            heap.add(5);
            heap.add(1);
            heap.add(2);
            heap.add(4);
            heap.add(1);

            assertTrue(heap.contains(2));
        }

        @Test
        void returnsFalseWhenElementIsNotInHeap() {
            BinaryHeap heap = new BinaryHeap();

            heap.add(3);
            heap.add(5);
            heap.add(1);
            heap.add(2);
            heap.add(4);
            heap.add(1);

            assertFalse(heap.contains(99));
        }
    }

}