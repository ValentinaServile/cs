package algorithms.dynamicprogramming.sum.howmanysum;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HowManySumTabulatedTest {

    @Test
    void shouldReturnHowManyCombinationsOfNumbersInListCanSumToN() {
        Integer result = HowManySumTabulated.forNumber(7, List.of(5, 3, 4, 7));

        assertEquals(3, result);
    }

    @Test
    void shouldReturnZeroWhenThereIsNoCombinationOfNumbersThatSumToN() {
        Integer result = HowManySumTabulated.forNumber(7, List.of(3, 5, 6));

        assertEquals(0, result);
    }

    @Test
    void shouldNotTakeALongTimeEvenWhenNIsABigNumber() {
        Integer result = HowManySumTabulated.forNumber(500, List.of(3, 6));

        assertEquals(0, result);
    }
}