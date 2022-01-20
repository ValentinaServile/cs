package algorithms.exercises.sum.howmanysum;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HowManySumMemoizedTest {

    @Test
    void shouldReturnHowManyCombinationsOfNumbersInListCanSumToN() {
        Integer result = HowManySumMemoized.forNumber(7, List.of(5, 3, 4, 7));

        assertEquals(3, result);
    }

    @Test
    void shouldReturnZeroWhenThereIsNoCombinationOfNumbersThatSumToN() {
        Integer result = HowManySumMemoized.forNumber(7, List.of(3, 5, 6));

        assertEquals(0, result);
    }

    @Test
    void shouldNotTakeALongTimeEvenWhenNIsABigNumber() {
        Integer result = HowManySumMemoized.forNumber(500, List.of(3, 6));

        assertEquals(0, result);
    }

}