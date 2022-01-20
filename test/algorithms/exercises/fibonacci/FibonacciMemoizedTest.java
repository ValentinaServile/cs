package algorithms.exercises.fibonacci;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FibonacciMemoizedTest {

    @Test
    void shouldReturnNthFibonacciNumber() {
        Long result = FibonacciMemoized.at(7L);

        assertEquals(13L, result);
    }


    @Test
    void shouldReturnNthFibonacciNumberQuicklyEvenWithLargeValuesOfN() {
        Long result = FibonacciMemoized.at(50L);

        assertEquals(12_586_269_025L, result);
    }
}