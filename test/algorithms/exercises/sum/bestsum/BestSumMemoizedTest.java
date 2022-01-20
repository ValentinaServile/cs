package algorithms.exercises.sum.bestsum;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BestSumMemoizedTest {

    @Test
    void shouldReturnBestCombinationOfNumbersThatSumToN() {
        List<Integer> result = BestSumMemoized.forNumber(7, List.of(2, 4, 1, 8));

        assertEquals(List.of(1, 4, 2), result);
    }

    @Test
    void shouldReturnNullWhenThereIsNoCombinationOfNumbersThatSumToN() {
        List<Integer> result = BestSumMemoized.forNumber(7, List.of(3, 5, 6));

        assertNull(result);
    }

    @Test
    void shouldNotTakeALongTimeEvenWhenNIsABigNumber() {
        List<Integer> result = BestSumMemoized.forNumber(500, List.of(3, 6));

        assertNull(result);
    }
}