package algorithms.exercises.sum.howallsum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HowAllSumMemoized {

    //Complexity: O(possibleFactors.length*n)
    public static List<List<Integer>> forNumber(Integer n, List<Integer> possibleFactors) {
        return forNumber(n, possibleFactors, new HashMap<>());
    }

    private static List<List<Integer>> forNumber(Integer n, List<Integer> possibleFactors, HashMap<Integer, List<List<Integer>>> memo) {
        if (memo.containsKey(n)) return memo.get(n);
        if (n < 0) return null;
        if (n == 0) {
            ArrayList<List<Integer>> combinations = new ArrayList<>();
            combinations.add(new ArrayList<>());
            return combinations;
        }

        List<List<Integer>> combinations = null;

        for (Integer possibleFactor : possibleFactors) {
            List<List<Integer>> result = forNumber(n - possibleFactor, possibleFactors, memo);
            if (result != null) {
                result.forEach(combination -> combination.add(0, possibleFactor));

                if (combinations == null) {
                    combinations = new ArrayList<>();
                }
                combinations.addAll(result);
            }
        }

        memo.put(n, combinations);
        return combinations;
    }
}
