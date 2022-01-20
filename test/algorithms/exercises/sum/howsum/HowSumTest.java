package algorithms.exercises.sum.howsum;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class HowSumTest {

    @Test
    void shouldReturnNumbersWhenThereIsACombinationOfNumbersThatSumToN() {
        List<Integer> result = HowSum.forNumber(7, List.of(2, 4, 1, 8));

        assertEquals(List.of(1, 2, 2, 2), result);
    }

    @Test
    void shouldReturnNullWhenThereIsNoCombinationOfNumbersThatSumToN() {
        List<Integer> result = HowSum.forNumber(7, List.of(3, 5, 6));

        assertNull(result);
    }


    @Disabled("This computation takes too long")
    @Test
    void shouldTakeALongTimeWhenNIsABigNumber() {
        List<Integer> result = HowSum.forNumber(500, List.of(3, 6));

        assertNull(result);
    }
}