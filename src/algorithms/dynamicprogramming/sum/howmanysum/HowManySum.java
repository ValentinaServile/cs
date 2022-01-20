package algorithms.dynamicprogramming.sum.howmanysum;

import java.util.List;

public class HowManySum {

    //Complexity: O(possibleFactors.length^n)
    public static Integer forNumber(Integer n, List<Integer> possibleFactors) {
        if (n < 0) return 0;
        if (n == 0) return 1;

        Integer howManyCombinations = 0;

        for (Integer possibleFactor : possibleFactors) {
            howManyCombinations += forNumber(n - possibleFactor, possibleFactors);
        }

        return howManyCombinations;
    }
}
