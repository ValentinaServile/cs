package algorithms.dynamicprogramming.sum.bestsum;

import java.util.ArrayList;
import java.util.List;

public class BestSumTabulated {

    //Complexity: O(possibleFactors.length^2 * n)
    public static List<Integer> forNumber(Integer n, List<Integer> possibleFactors) {
        List<Integer>[] table = new List[n + 1];

        table[0] = new ArrayList<>();

        for (int i = 0; i < table.length; i++) {

            if (table[i] == null) continue;

            for (Integer possibleFactor : possibleFactors) {
                int indexOfFactor = i + possibleFactor;

                if (indexOfFactor > table.length - 1)
                    continue;
                if (table[indexOfFactor] != null && table[indexOfFactor].size() < table[i].size() + 1)
                    continue;

                table[indexOfFactor] = new ArrayList<>(table[i]);
                table[indexOfFactor].add(possibleFactor);
            }
        }

        return table[n];
    }
}
