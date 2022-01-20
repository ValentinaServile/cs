package algorithms.dynamicprogramming.sum.cansum;

import java.util.List;

public class CanSum {

    //Complexity: O(possibleFactors.length^n)
    public static boolean forNumber(Integer n, List<Integer> possibleFactors) {
        if (n < 0) return false;
        if (n == 0) return true;

        for(Integer possibleFactor : possibleFactors) {
            if (forNumber(n - possibleFactor, possibleFactors)) {
                return true;
            }
        }
        return false;
    }

}
