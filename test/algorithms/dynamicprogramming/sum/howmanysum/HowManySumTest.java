package algorithms.dynamicprogramming.sum.howmanysum;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HowManySumTest {

    @Test
    void shouldReturnHowManyCombinationsOfNumbersInListCanSumToN() {
        Integer result = HowManySum.forNumber(7, List.of(5, 3, 4, 7));

        assertEquals(3, result);
    }

    @Test
    void shouldReturnZeroWhenThereIsNoCombinationOfNumbersThatSumToN() {
        Integer result = HowManySum.forNumber(7, List.of(3, 5, 6));

        assertEquals(0, result);
    }


    @Disabled("This computation takes too long")
    @Test
    void shouldTakeALongTimeWhenNIsABigNumber() {
        Integer result = HowManySum.forNumber(500, List.of(3, 6));

        assertEquals(0, result);
    }

}