package algorithms.dynamicprogramming.sum.bestsum;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BestSumTabulatedTest {

    @Test
    void shouldReturnBestCombinationOfNumbersThatSumToN() {
        List<Integer> result = BestSumTabulated.forNumber(7, List.of(2, 4, 1, 8));

        assertEquals(List.of(4, 2, 1), result);
    }

    @Test
    void shouldReturnNullWhenThereIsNoCombinationOfNumbersThatSumToN() {
        List<Integer> result = BestSumTabulated.forNumber(7, List.of(3, 5, 6));

        assertNull(result);
    }

    @Test
    void shouldNotTakeALongTimeEvenWhenNIsABigNumber() {
        List<Integer> result = BestSumTabulated.forNumber(500, List.of(3, 6));

        assertNull(result);
    }
}