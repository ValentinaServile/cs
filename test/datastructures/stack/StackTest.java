package datastructures.stack;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @Nested
    class Push {

        @Test
        void addsElementOnTopOfTheStack() {
            Stack stack = new Stack();

            stack.push("first");
            assertEquals(stack.peek(), "first");

            stack.push("second");
            assertEquals(stack.peek(), "second");

            stack.push("third");
            assertEquals(stack.peek(), "third");
        }
    }

    @Nested
    class Pop {

        @Test
        void returnsErrorIfEmptyStack() {
            Stack stack = new Stack();

            assertThrows(NoSuchElementException.class, stack::pop);
        }

        @Test
        void removesAndReturnsElementFromTopOfTheStack() {
            Stack stack = new Stack();
            stack.push("first");
            stack.push("second");
            stack.push("third");

            String popped = stack.pop();
            assertEquals(popped, "third");
            assertEquals(stack.peek(), "second");
        }

    }

}