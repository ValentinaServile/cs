package algorithms.exercises.fibonacci;

public class Fibonacci {

    //Complexity: O(2^n)
    public static Long at(Long n) {
        if (n <= 2) return 1L;

        return at(n - 1) + at(n - 2);
    }
}
