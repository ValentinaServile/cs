package datastructures.queue;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    @Nested
    class Enqueue {

        @Test
        void addsElementsInQueue() {
            Queue queue = new Queue();

            queue.enqueue("first");
            queue.enqueue("second");
            queue.enqueue("third");

            assertEquals(queue.peek(), "first");
        }
    }

    @Nested
    class Dequeue {

        @Test
        void returnsErrorIfEmptyQueue() {
            Queue queue = new Queue();

            assertThrows(NoSuchElementException.class, queue::dequeue);
        }

        @Test
        void removesAndReturnsElementAtTheFrontOfTheQueue() {
            Queue queue = new Queue();

            queue.enqueue("first");
            queue.enqueue("second");

            String dequeued = queue.dequeue();
            assertEquals(dequeued, "first");
            assertEquals(queue.peek(), "second");
        }
    }

}