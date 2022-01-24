package algorithms.dynamicprogramming.sum.howallsum;

import java.util.ArrayList;
import java.util.List;

public class HowAllSumTabulated {

    //Complexity: O(n^possibleFactors.length)
    public static List<List<Integer>> forNumber(Integer n, List<Integer> possibleFactors) {
        List<List<Integer>>[] table = new List[n + 1];

        for (int i = 0; i < table.length; i++) {
            table[i] = new ArrayList<>();
        }

        table[0].add(new ArrayList<>());

        for (int i = 0; i < table.length; i++) {

            if (table[i].isEmpty()) continue;

            for (Integer possibleFactor : possibleFactors) {
                int indexOfFactor = i + possibleFactor;

                if (indexOfFactor > table.length - 1) continue;

                List<List<Integer>> nextCombinations = new ArrayList<>();
                table[i].forEach(combination -> nextCombinations.add(new ArrayList<>(combination)));
                nextCombinations.forEach(c -> c.add(possibleFactor));

                table[indexOfFactor].addAll(nextCombinations);
            }

        }

        return table[n];
    }
}
