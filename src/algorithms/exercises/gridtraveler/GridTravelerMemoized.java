package algorithms.exercises.gridtraveler;

import java.util.HashMap;

public class GridTravelerMemoized {

    //Complexity: O(m*n)
    public static Long waysToTravelGridWithDimensions(Long m, Long n) {
        return waysToTravelGridWithDimensions(m, n, new HashMap<>());
    }

    private static Long waysToTravelGridWithDimensions(Long m, Long n, HashMap<String, Long> memo) {
        String memoKey = String.format("%d,%d", m, n);
        if (memo.containsKey(memoKey)) return memo.get(memoKey);

        if (m == 0 || n == 0) return 0L;
        if (m == 1 && n == 1) return 1L;

        Long result = waysToTravelGridWithDimensions(m - 1, n, memo)
                + waysToTravelGridWithDimensions(m, n - 1, memo);

        memo.put(memoKey, result);

        return result;
    }
}

