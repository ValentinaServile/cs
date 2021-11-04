package datastructures.tree.heap.binaryheap;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BinaryHeapTest {

    @Nested
    class Constructor {

        @Test
        void allowsToCreateHeapFromArrayInLinearTimeByHeapifyingOnlyInternalNodes() {
            ArrayList<Integer> arrayList = new ArrayList<>();
            arrayList.add(1);
            arrayList.add(2);
            arrayList.add(5);
            arrayList.add(3);
            arrayList.add(7);
            arrayList.add(4);
            arrayList.add(9);
            arrayList.add(6);

            BinaryHeap heap = new BinaryHeap(arrayList);

            assertTrue(respectsMaxHeapInvariant(heap));
        }

        @Test
        void allowsToCreateHeapFromCollectionInNLogNTimeByHeapifyingAllNodes() {
            Collection<Integer> collection = new ArrayList<>();
            collection.add(1);
            collection.add(2);
            collection.add(5);
            collection.add(3);
            collection.add(7);
            collection.add(4);
            collection.add(9);
            collection.add(6);

            BinaryHeap heap = new BinaryHeap(collection);

            assertTrue(respectsMaxHeapInvariant(heap));
        }

    }

    @Nested
    class Add {

        @Test
        void preservesHeapInvariantByBubblingElementUpIfBiggerThanOthers() {
            BinaryHeap heap = new BinaryHeap();

            heap.add(1);
            assertEquals(heap.peek(), 1);

            heap.add(2);
            assertEquals(heap.peek(), 2);

            heap.add(3);
            assertEquals(heap.peek(), 3);

            assertTrue(respectsMaxHeapInvariant(heap));
        }

        @Test
        void preservesHeapInvariantByNotBubblingElementsUpIfSmallerThanOthers() {
            BinaryHeap heap = new BinaryHeap();

            heap.add(3);
            heap.add(1);
            heap.add(2);

            assertEquals(heap.peek(), 3);
            assertTrue(respectsMaxHeapInvariant(heap));
        }
    }

    @Nested
    class Poll {

        @Test
        void returnsCurrentMaxElementAndPreservesHeapInvariantByBubblingUpTheNewMaxElement() {
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

            assertTrue(respectsMaxHeapInvariant(heap));
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
        void preservesHeapInvariantByBubblingUpNewMaxElement() {
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
            assertTrue(respectsMaxHeapInvariant(heap));
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


    private boolean respectsMaxHeapInvariant(BinaryHeap heap) {
        return respectsMaxHeapInvariant(0, heap.asList());
    }

    private boolean respectsMaxHeapInvariant(int nodeIndex, List<Integer> heapifiedNodes) {
        if (nodeIndex >= heapifiedNodes.size())
            return true;

        int leftChildIndex = nodeIndex * 2 + 1;
        int rightChildIndex = nodeIndex * 2 + 2;

        if (leftChildIndex < heapifiedNodes.size() && heapifiedNodes.get(leftChildIndex) >  heapifiedNodes.get(nodeIndex) )
            return false;

        if (rightChildIndex < heapifiedNodes.size() && heapifiedNodes.get(rightChildIndex) >  heapifiedNodes.get(nodeIndex) )
            return false;

        return respectsMaxHeapInvariant(leftChildIndex, heapifiedNodes) && respectsMaxHeapInvariant(rightChildIndex, heapifiedNodes);
    }
}