package algorithms.dynamicprogramming.sum.cansum;

import java.util.List;

public class CanSumTabulated {

    //Complexity: O(possibleFactors.length * n)
    public static boolean forNumber(Integer n, List<Integer> possibleFactors) {
        boolean[] table = new boolean[n + 1];

        table[0] = true;

        for (int i = 0; i < table.length; i++) {

            if (!table[i]) continue;

            for (Integer possibleFactor : possibleFactors) {
                int indexOfFactor = i + possibleFactor;
                if (indexOfFactor <= (table.length - 1)) {
                    table[indexOfFactor] = true;
                }
            }
        }

        return table[n];

    }
}
