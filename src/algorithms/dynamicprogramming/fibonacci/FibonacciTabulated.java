package algorithms.dynamicprogramming.fibonacci;

import java.util.Arrays;

public class FibonacciTabulated {

    //Complexity: O(n)
    public static Long at(Long n) {
        Long[] table = new Long[n.intValue() + 1];
        Arrays.fill(table, 0L);

        table[1] = 1L;

        for (int i = 0; i < table.length; i++) {
            if (i + 1 <= table.length - 1)
                table[i + 1] += table[i];
            if (i + 2 <= table.length - 1)
                table[i + 2] += table[i];
        }

        return table[n.intValue()];
    }
}
