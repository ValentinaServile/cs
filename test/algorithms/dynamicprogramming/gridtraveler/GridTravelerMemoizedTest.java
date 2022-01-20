package algorithms.dynamicprogramming.gridtraveler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GridTravelerMemoizedTest {

    @Test
    void shouldReturnNumberOfWaysAGridCanBeTraveled() {
        Long result = GridTravelerMemoized.waysToTravelGridWithDimensions(2L, 3L);

        assertEquals(3L, result);
    }

    @Test
    void shouldReturnNumberOfWaysAGridCanBeTraveledQuicklyEvenForALargeGrid() {
        Long result = GridTravelerMemoized.waysToTravelGridWithDimensions(18L, 18L);

        assertEquals(2_333_606_220L, result);
    }

}