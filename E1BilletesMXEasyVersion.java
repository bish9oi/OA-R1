import java.util.*;

public class E1BilletesMXEasyVersion {
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt(); // number of test cases

        while (t-- > 0) {
            int n = sc.nextInt(); // rows
            int m = sc.nextInt(); // cols
            int k = sc.nextInt(); // number of fixed cells
            int q = sc.nextInt(); // q = 0 (easy version)

            // Track which rows and columns are fixed
            Set<Integer> usedRows = new HashSet<>();
            Set<Integer> usedCols = new HashSet<>();

            // Read k fixed cells
            for (int i = 0; i < k; i++) {
                int r = sc.nextInt();
                int c = sc.nextInt();
                int v = sc.nextInt();

                usedRows.add(r);
                usedCols.add(c);
            }
            // The number of independent constraints equals the number of pairs (row +
            // column) affected
            // The degrees of freedom = total size - (used rows + used columns) + overlaps
            // However, in the reduced XOR condition, we only care about independent
            // row/column decisions
            // The number of degrees of freedom is the number of distinct rows + columns
            // used
            int total = n + m - usedRows.size() - usedCols.size();
            long res = modPow(2, total, MOD);
            System.out.println(res);
        }
    }

    // Fast exponentiation modulo MOD
    static long modPow(long base, long exp, int mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1)
                result = (result * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }
}
