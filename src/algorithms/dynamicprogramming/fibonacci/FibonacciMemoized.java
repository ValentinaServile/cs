package algorithms.dynamicprogramming.fibonacci;

import java.util.HashMap;
import java.util.Map;

public class FibonacciMemoized {

    //Complexity: O(n)
    public static Long at(Long n) {
        return at(n, new HashMap<>());
    }

    private static Long at(Long n, Map<Long, Long> memo) {
        if (memo.containsKey(n)) return memo.get(n);
        if (n <= 2) return 1L;

        Long result = at(n - 1, memo) + at(n - 2, memo);
        memo.put(n, result);
        return result;
    }
}

