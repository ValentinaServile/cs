package algorithms.dynamicprogramming.sum.howsum;

import java.util.ArrayList;
import java.util.List;

public class HowSumTabulated {

    //Complexity: O(possibleFactors.length^2 * n)
    public static List<Integer> forNumber(Integer n, List<Integer> possibleFactors) {
        List<Integer>[] table = new List[n + 1];

        table[0] = new ArrayList<>();

        for (int i = 0; i < table.length; i++) {

            if (table[i] == null) continue;

            for (Integer possibleFactor : possibleFactors) {
                int indexOfFactor = i + possibleFactor;

                if (indexOfFactor <= table.length - 1) {

                    table[indexOfFactor] = new ArrayList<>(table[i]);
                    table[indexOfFactor].add(possibleFactor);
                }
            }
        }

        return table[n];
    }
}
