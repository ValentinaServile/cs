package algorithms.exercises.sum.howsum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HowSumMemoized {

    //Complexity: O(possibleFactors.length * n)
    public static List<Integer> forNumber(Integer n, List<Integer> possibleFactors) {
        return forNumber(n, possibleFactors, new HashMap<>());
    }

    private static List<Integer> forNumber(Integer n, List<Integer> possibleFactors, HashMap<Integer, List<Integer>> memo) {
        if (memo.containsKey(n)) return memo.get(n);
        if (n < 0) return null;
        if (n == 0) return new ArrayList<>();

        for (Integer possibleFactor : possibleFactors) {
            List<Integer> result = forNumber(n - possibleFactor, possibleFactors, memo);
            if (result != null) {
                result.add(possibleFactor);
                memo.put(n, result);
                return result;
            }
        }

        memo.put(n, null);
        return null;
    }
}
