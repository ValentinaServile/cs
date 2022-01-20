package algorithms.dynamicprogramming.sum.cansum;

import java.util.HashMap;
import java.util.List;

public class CanSumMemoized {

    //Complexity: O(possibleFactors.length * n)
    public static boolean forNumber(Integer n, List<Integer> possibleFactors) {
        return forNumber(n, possibleFactors, new HashMap<>());
    }

    private static boolean forNumber(Integer n, List<Integer> possibleFactors, HashMap<Integer, Boolean> memo) {
        if (memo.containsKey(n)) return memo.get(n);
        if (n < 0) return false;
        if (n == 0) return true;

        for (Integer possibleFactor : possibleFactors) {
            if (forNumber(n - possibleFactor, possibleFactors, memo)) {
                memo.put(n, true);
                return true;
            }
        }

        memo.put(n, false);
        return false;
    }
}
