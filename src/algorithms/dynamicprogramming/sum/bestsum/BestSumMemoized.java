package algorithms.dynamicprogramming.sum.bestsum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BestSumMemoized {

    //Complexity: O(possibleFactors.length^2 * n)
    public static List<Integer> forNumber(Integer n, List<Integer> possibleFactors) {
        return forNumber(n, possibleFactors, new HashMap<>());
    }

    private static List<Integer> forNumber(Integer n, List<Integer> possibleFactors, HashMap<Integer, List<Integer>> memo) {
        if (memo.containsKey(n)) return memo.get(n);
        if (n < 0) return null;
        if (n == 0) return new ArrayList<>();

        List<Integer> shortestCombination = null;

        for (Integer possibleFactor : possibleFactors) {
            List<Integer> result = forNumber(n - possibleFactor, possibleFactors, memo);
            if (result != null && (shortestCombination == null || shortestCombination.size() > result.size() + 1)) {

                ArrayList<Integer> newShortestCombination = new ArrayList<>(result);
                newShortestCombination.add(possibleFactor);
                shortestCombination = newShortestCombination;
            }
        }

        memo.put(n, shortestCombination);
        return shortestCombination;
    }
}
