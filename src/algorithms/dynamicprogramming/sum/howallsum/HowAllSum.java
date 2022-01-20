package algorithms.dynamicprogramming.sum.howallsum;

import java.util.ArrayList;
import java.util.List;

public class HowAllSum {

    //Complexity: O(possibleFactors.length^n)
    public static List<List<Integer>> forNumber(Integer n, List<Integer> possibleFactors) {
        if (n < 0) return null;
        if (n == 0) {
            ArrayList<List<Integer>> combinations = new ArrayList<>();
            combinations.add(new ArrayList<>());
            return combinations;
        }

        List<List<Integer>> combinations = null;

        for (Integer possibleFactor : possibleFactors) {
            List<List<Integer>> result = forNumber(n - possibleFactor, possibleFactors);
            if (result != null) {
                result.forEach(combination -> combination.add(0, possibleFactor));

                if (combinations == null) {
                    combinations = new ArrayList<>();
                }
                combinations.addAll(result);
            }
        }

        return combinations;
    }
}
