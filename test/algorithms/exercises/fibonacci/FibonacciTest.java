package algorithms.exercises.fibonacci;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciTest {

    @Test
    void shouldReturnNthFibonacciNumber() {
        Long result = Fibonacci.at(7L);

        assertEquals(13L, result);
    }


    @Disabled("This computation takes too long")
    @Test
    void shouldReturnNthFibonacciNumberSlowlyWithLargeValuesOfN() {
        Long result = Fibonacci.at(50L);

        assertEquals(12_586_269_025L, result);
    }
}