package datastructures.list.arraylist;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {

    @Nested
    class Add {

        @Test
        void addsElementsAtTheEnd() {
            ArrayList list = new ArrayList();

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
            ArrayList list = new ArrayList();

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
            ArrayList list = new ArrayList();

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
            ArrayList list = new ArrayList();

            list.add("first");
            list.add("second");
            list.add("third");

            assertThrows(IndexOutOfBoundsException.class, () -> list.add("fourth", 4));
        }

        @Test
        void shiftsAllElementsToMakeRoomForNewOne() {
            ArrayList list = new ArrayList();

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
            ArrayList list = new ArrayList();

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
            ArrayList list = new ArrayList();

            list.add("first");
            list.add("second");
            list.add("third");

            assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        }

        @Test
        void doesNotAllowAccessToIndexesPastTheEnd() {
            ArrayList list = new ArrayList();

            list.add("first");
            list.add("second");
            list.add("third");

            assertThrows(IndexOutOfBoundsException.class, () -> list.get(99));
        }

        @Test
        void returnsRequestedElementWhenIndexExists() {
            ArrayList list = new ArrayList();

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
            ArrayList list = new ArrayList();

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
            ArrayList list = new ArrayList();

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
            ArrayList list = new ArrayList();

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
            ArrayList list = new ArrayList();

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
            ArrayList list = new ArrayList();

            list.add("first");
            list.add("second");
            list.add("third");

            assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        }

        @Test
        void doesNotAllowRemovingIndexesPastTheEnd() {
            ArrayList list = new ArrayList();

            list.add("first");
            list.add("second");
            list.add("third");

            assertThrows(IndexOutOfBoundsException.class, () -> list.remove(99));
        }

        @Test
        void allowsRemovingElementAtBeginning() {
            ArrayList list = new ArrayList();

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
            ArrayList list = new ArrayList();

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
            ArrayList list = new ArrayList();

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
            ArrayList list = new ArrayList();

            list.add("first");
            list.add("second");
            list.add("third");

            assertEquals(list.indexOf("fourth"), -1);
        }

        @Test
        void returnsIndexOfFirstOccurrenceOfElementWhenItExist() {
            ArrayList list = new ArrayList();

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
            ArrayList list = new ArrayList();

            list.add("first");
            list.add("second");
            list.add("third");

            assertFalse(list.contains("fourth"));
        }

        @Test
        void returnsTrueWhenElementExists() {
            ArrayList list = new ArrayList();

            list.add("first");
            list.add("second");
            list.add("third");

            assertTrue(list.contains("second"));
        }
    }

}