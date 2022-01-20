package algorithms.dynamicprogramming.gridtraveler;

public class GridTraveler {

    //Complexity: O(2^(m+n))
    public static Long waysToTravelGridWithDimensions(Long m, Long n) {
        if (m == 0 || n == 0) return 0L;
        if (m == 1 && n == 1) return 1L;

        return waysToTravelGridWithDimensions(m - 1, n)
                + waysToTravelGridWithDimensions(m, n - 1);
    }

}
