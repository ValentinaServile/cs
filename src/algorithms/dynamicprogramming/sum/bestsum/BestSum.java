package algorithms.dynamicprogramming.sum.bestsum;

import java.util.ArrayList;
import java.util.List;

public class BestSum {

    //Complexity: O(n^possibleFactors.length * possibleFactors.length)
    public static List<Integer> forNumber(Integer n, List<Integer> possibleFactors) {
        if (n < 0) return null;
        if (n == 0) return new ArrayList<>();

        List<Integer> shortestCombination = null;

        for (Integer possibleFactor : possibleFactors) {
            List<Integer> result = forNumber(n - possibleFactor, possibleFactors);

            if (result != null && (shortestCombination == null || shortestCombination.size() > result.size() + 1)) {
                ArrayList<Integer> newShortestCombination = new ArrayList<>(result);
                newShortestCombination.add(possibleFactor);
                shortestCombination = newShortestCombination;
            }
        }

        return shortestCombination;
    }
}
