package algorithms.dynamicprogramming.gridtraveler;

public class GridTravelerTabulated {

    //Complexity: O(m*n)
    public static Long waysToTravelGridWithDimensions(Long m, Long n) {
        long[][] table = new long[m.intValue() + 1][n.intValue() + 1];

        table[1][1] = 1;

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {

                long current = table[i][j];

                if (i + 1 <= m) table[i + 1][j] += current;
                if (j + 1 <= n) table[i][j + 1] += current;
            }
        }

        return table[m.intValue()][n.intValue()];
    }

}
