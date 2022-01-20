package algorithms.exercises.sum.cansum;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CanSumMemoizedTest {

    @Test
    void shouldReturnTrueWhenThereIsACombinationOfNumbersThatSumToN() {
        boolean result = CanSumMemoized.forNumber(7, List.of(2, 4, 3, 6));

        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenThereIsNoCombinationOfNumbersThatSumToN() {
        boolean result = CanSumMemoized.forNumber(7, List.of(3, 5, 6));

        assertFalse(result);
    }

    @Test
    void shouldNotTakeALongTimeEvenWhenNIsABigNumber() {
        boolean result = CanSumMemoized.forNumber(500, List.of(3, 6));

        assertFalse(result);
    }

}