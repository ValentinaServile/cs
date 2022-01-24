package algorithms.dynamicprogramming.sum.howmanysum;

import java.util.List;

public class HowManySumTabulated {

    //Complexity: O(possibleFactors.length^2 * n)
    public static Integer forNumber(Integer n, List<Integer> possibleFactors) {
        int[] table = new int[n + 1];

        table[0] = 1;

        for (int i = 0; i < table.length; i++) {

            if (table[i] == 0) continue;

            for (int possibleFactor : possibleFactors) {
                int indexOfFactor = i + possibleFactor;

                if (indexOfFactor > table.length - 1)
                    continue;

                table[indexOfFactor] = table[indexOfFactor] + table[i];
            }
        }

        return table[n];
    }
}
