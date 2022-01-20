package algorithms.dynamicprogramming.gridtraveler;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTravelerTest {

    @Test
    void shouldReturnNumberOfWaysAGridCanBeTraveled() {
        Long result = GridTraveler.waysToTravelGridWithDimensions(2L, 3L);

        assertEquals(3L, result);
    }

    @Disabled("This computation takes too long")
    @Test
    void shouldReturnNumberOfWaysAGridCanBeTraveledSlowlyForALargeGrid() {
        Long result = GridTraveler.waysToTravelGridWithDimensions(18L, 18L);

        assertEquals(2_333_606_220L, result);
    }
}