package algorithms.exercises.sum.cansum;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CanSumTest {

    @Test
    void shouldReturnTrueWhenThereIsACombinationOfNumbersThatSumToN() {
        boolean result = CanSum.forNumber(7, List.of(2, 4, 3, 6));

        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenThereIsNoCombinationOfNumbersThatSumToN() {
        boolean result = CanSum.forNumber(7, List.of(3, 5, 6));

        assertFalse(result);
    }


    @Disabled("This computation takes too long")
    @Test
    void shouldTakeALongTimeWhenNIsABigNumber() {
        boolean result = CanSum.forNumber(500, List.of(3, 6));

        assertFalse(result);
    }
}