package algorithms.dynamicprogramming.sum.howsum;

import java.util.ArrayList;
import java.util.List;

public class HowSum {

    //Complexity: O(possibleFactors.length^n)
    public static List<Integer> forNumber(Integer n, List<Integer> possibleFactors) {
        if (n < 0) return null;
        if (n == 0) return new ArrayList<>();

        for (Integer possibleFactor : possibleFactors) {
            List<Integer> result = forNumber(n - possibleFactor, possibleFactors);
            if (result != null) {
                result.add(possibleFactor);
                return result;
            }
        }

        return null;
    }
}
