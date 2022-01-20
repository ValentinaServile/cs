package algorithms.dynamicprogramming.sum.bestsum;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BestSumTest {

    @Test
    void shouldReturnBestCombinationOfNumbersThatSumToN() {
        List<Integer> result = BestSum.forNumber(7, List.of(2, 4, 1, 8));

        assertEquals(List.of(1, 4, 2), result);
    }

    @Test
    void shouldReturnNullWhenThereIsNoCombinationOfNumbersThatSumToN() {
        List<Integer> result = BestSum.forNumber(7, List.of(3, 5, 6));

        assertNull(result);
    }


    @Disabled("This computation takes too long")
    @Test
    void shouldTakeALongTimeWhenNIsABigNumber() {
        List<Integer> result = BestSum.forNumber(500, List.of(3, 6));

        assertNull(result);
    }
}