package algorithms.dynamicprogramming.fibonacci;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FibonacciTabulatedTest {

    @Test
    void shouldReturnNthFibonacciNumber() {
        Long result = FibonacciTabulated.at(7L);

        assertEquals(13L, result);
    }


    @Test
    void shouldReturnNthFibonacciNumberQuicklyEvenWithLargeValuesOfN() {
        Long result = FibonacciTabulated.at(50L);

        assertEquals(12_586_269_025L, result);
    }
}