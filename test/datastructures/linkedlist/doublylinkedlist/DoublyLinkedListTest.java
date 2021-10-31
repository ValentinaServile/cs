package datastructures.linkedlist.doublylinkedlist;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {

    @Nested
    class Add {

        @Test
        void addsElementsAtTheEnd() {
            DoublyLinkedList list = new DoublyLinkedList();

            list.add("first");
            list.add("second");
            list.add("third");

            assertEquals(list.get(0), "first");
            assertEquals(list.get(1), "second");
            assertEquals(list.get(2), "third");
        }
    }

    @Nested
    class AddAtIndex {

        @Test
        void allowsInsertingAtEnd() {
            DoublyLinkedList list = new DoublyLinkedList();

            list.add("first");
            list.add("second");
            list.add("third");
            list.add("fourth", 3);

            assertEquals(list.get(0), "first");
            assertEquals(list.get(1), "second");
            assertEquals(list.get(2), "third");
            assertEquals(list.get(3), "fourth");
        }

        @Test
        void allowsInsertingAtBeginning() {
            DoublyLinkedList list = new DoublyLinkedList();

            list.add("first");
            list.add("second");
            list.add("third");
            list.add("zeroeth", 0);

            assertEquals(list.get(0), "zeroeth");
            assertEquals(list.get(1), "first");
            assertEquals(list.get(2), "second");
            assertEquals(list.get(3), "third");
        }

        @Test
        void doesNotAllowToInsertPastEnd() {
            DoublyLinkedList list = new DoublyLinkedList();

            list.add("first");
            list.add("second");
            list.add("third");

            assertThrows(IndexOutOfBoundsException.class, () -> list.add("fourth", 4));
        }

        @Test
        void shiftsAllElementsToMakeRoomForNewOne() {
            DoublyLinkedList list = new DoublyLinkedList();

            list.add("first");
            list.add("second");
            list.add("third");
            list.add("firstAndAHalf", 1);

            assertEquals(list.get(0), "first");
            assertEquals(list.get(1), "firstAndAHalf");
            assertEquals(list.get(2), "second");
            assertEquals(list.get(3), "third");
        }
    }

    @Nested
    class Size {

        @Test
        void returnsCurrentSize() {
            DoublyLinkedList list = new DoublyLinkedList();

            list.add("first");
            list.add("second");
            list.add("third");

            assertEquals(list.size(), 3);
        }
    }

    @Nested
    class Get {

        @Test
        void doesNotAllowToAccessNegativeIndexes() {
            DoublyLinkedList list = new DoublyLinkedList();

            list.add("first");
            list.add("second");
            list.add("third");

            assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        }

        @Test
        void doesNotAllowAccessToIndexesPastTheEnd() {
            DoublyLinkedList list = new DoublyLinkedList();

            list.add("first");
            list.add("second");
            list.add("third");

            assertThrows(IndexOutOfBoundsException.class, () -> list.get(99));
        }

        @Test
        void returnsRequestedElementWhenIndexExists() {
            DoublyLinkedList list = new DoublyLinkedList();

            list.add("first");
            list.add("second");
            list.add("third");

            assertEquals(list.get(2), "third");
        }
    }

    @Nested
    class Remove {

        @Test
        void doesNothingWhenElementToRemoveDoesNotExist() {
            DoublyLinkedList list = new DoublyLinkedList();

            list.add("first");
            list.add("second");
            list.add("third");

            list.remove("NONEXISTENT");

            assertEquals(list.get(0), "first");
            assertEquals(list.get(1), "second");
            assertEquals(list.get(2), "third");
        }

        @Test
        void allowsRemovingFirstElement() {
            DoublyLinkedList list = new DoublyLinkedList();

            list.add("removeMe");
            list.add("first");
            list.add("second");
            list.add("third");

            list.remove("removeMe");

            assertEquals(list.get(0), "first");
            assertEquals(list.get(1), "second");
            assertEquals(list.get(2), "third");
        }

        @Test
        void allowsRemovingLastElement() {
            DoublyLinkedList list = new DoublyLinkedList();

            list.add("first");
            list.add("second");
            list.add("third");
            list.add("removeMe");

            list.remove("removeMe");

            assertEquals(list.get(0), "first");
            assertEquals(list.get(1), "second");
            assertEquals(list.get(2), "third");
            assertEquals(list.size(), 3);
        }


        @Test
        void removesFirstOccurrenceOfElementShiftingAllOthersToCoverGap() {
            DoublyLinkedList list = new DoublyLinkedList();

            list.add("first");
            list.add("removeMe");
            list.add("second");
            list.add("removeMe");
            list.add("third");

            list.remove("removeMe");

            assertEquals(list.get(0), "first");
            assertEquals(list.get(1), "second");
            assertEquals(list.get(2), "removeMe");
            assertEquals(list.get(3), "third");
        }
    }

    @Nested
    class RemoveAtIndex {

        @Test
        void doesNotAllowRemovingNegativeIndexes() {
            DoublyLinkedList list = new DoublyLinkedList();

            list.add("first");
            list.add("second");
            list.add("third");

            assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        }

        @Test
        void doesNotAllowRemovingIndexesPastTheEnd() {
            DoublyLinkedList list = new DoublyLinkedList();

            list.add("first");
            list.add("second");
            list.add("third");

            assertThrows(IndexOutOfBoundsException.class, () -> list.remove(99));
        }

        @Test
        void allowsRemovingElementAtBeginning() {
            DoublyLinkedList list = new DoublyLinkedList();

            list.add("removeMe");
            list.add("first");
            list.add("second");
            list.add("third");
            list.remove(0);

            assertEquals(list.get(0), "first");
            assertEquals(list.get(1), "second");
            assertEquals(list.get(2), "third");
        }

        @Test
        void allowsRemovingElementAtEnd() {
            DoublyLinkedList list = new DoublyLinkedList();

            list.add("first");
            list.add("second");
            list.add("third");
            list.add("removeMe");
            list.remove(3);

            assertEquals(list.get(0), "first");
            assertEquals(list.get(1), "second");
            assertEquals(list.get(2), "third");
            assertEquals(list.size(), 3);
        }

        @Test
        void removesElementShiftingAllOthersToCoverGap() {
            DoublyLinkedList list = new DoublyLinkedList();

            list.add("first");
            list.add("removeMe");
            list.add("second");
            list.add("third");
            list.remove(1);

            assertEquals(list.get(0), "first");
            assertEquals(list.get(1), "second");
            assertEquals(list.get(2), "third");
        }
    }

    @Nested
    class IndexOf {

        @Test
        void returnsNegativeOneWhenElementDoesNotExist() {
            DoublyLinkedList list = new DoublyLinkedList();

            list.add("first");
            list.add("second");
            list.add("third");

            assertEquals(list.indexOf("fourth"), -1);
        }

        @Test
        void returnsIndexOfFirstOccurrenceOfElementWhenItExist() {
            DoublyLinkedList list = new DoublyLinkedList();

            list.add("first");
            list.add("second");
            list.add("third");
            list.add("second");

            assertEquals(list.indexOf("second"), 1);
        }
    }

    @Nested
    class Contains {

        @Test
        void returnsFalseWhenElementDoesNotExist() {
            DoublyLinkedList list = new DoublyLinkedList();

            list.add("first");
            list.add("second");
            list.add("third");

            assertFalse(list.contains("fourth"));
        }

        @Test
        void returnsTrueWhenElementExists() {
            DoublyLinkedList list = new DoublyLinkedList();

            list.add("first");
            list.add("second");
            list.add("third");

            assertTrue(list.contains("second"));
        }
    }

}