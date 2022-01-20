package algorithms.exercises.sum.howallsum;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class HowAllSumTest {

    @Test
    void shouldReturnAllCombinationsOfNumbersInListCanSumToN() {
        List<List<Integer>> result = HowAllSum.forNumber(7, List.of(5, 3, 4, 7));

        assertEquals(3, result.size());
        assertEquals(List.of(3, 4), result.get(0));
        assertEquals(List.of(4, 3), result.get(1));
        assertEquals(List.of(7), result.get(2));
    }

    @Test
    void shouldReturnNullWhenThereIsNoCombinationOfNumbersThatSumToN() {
        List<List<Integer>> result = HowAllSum.forNumber(7, List.of(3, 5, 6));

        assertNull(result);
    }

    @Disabled("This computation takes too long")
    @Test
    void shouldTakeALongTimeWhenNIsABigNumber() {
        List<List<Integer>> result = HowAllSum.forNumber(500, List.of(3, 6));

        assertNull(result);
    }
}