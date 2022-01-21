package algorithms.dynamicprogramming.sum.howsum;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class HowSumTabulatedTest {

    @Test
    void shouldReturnNumbersWhenThereIsACombinationOfNumbersThatSumToN() {
        List<Integer> result = HowSumTabulated.forNumber(7, List.of(5, 3, 4));

        assertEquals(List.of(4, 3), result);
    }

    @Test
    void shouldReturnNullWhenThereIsNoCombinationOfNumbersThatSumToN() {
        List<Integer> result = HowSumTabulated.forNumber(7, List.of(3, 5, 6));

        assertNull(result);
    }

    @Test
    void shouldNotTakeALongTimeEvenWhenNIsABigNumber() {
        List<Integer> result = HowSumTabulated.forNumber(500, List.of(3, 6));

        assertNull(result);
    }
}