package algorithms.dynamicprogramming.sum.howmanysum;

import java.util.HashMap;
import java.util.List;

public class HowManySumMemoized {

    //Complexity: O(possibleFactors.length * n)
    public static Integer forNumber(Integer n, List<Integer> possibleFactors) {
        return forNumber(n, possibleFactors, new HashMap<>());
    }

    private static Integer forNumber(Integer n, List<Integer> possibleFactors, HashMap<Integer, Integer> memo) {
        if (memo.containsKey(n)) return memo.get(n);
        if (n < 0) return 0;
        if (n == 0) return 1;

        Integer howManyCombinations = 0;

        for (Integer possibleFactor : possibleFactors) {
            howManyCombinations += forNumber(n - possibleFactor, possibleFactors, memo);
        }

        memo.put(n, howManyCombinations);
        return howManyCombinations;
    }
}
