import java.util.*;

public class E2BilletesMXHardVersion {
    static final int MOD = 1_000_000_007;

    static long modPow(long base, long exp) {
        long result = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1)
                result = result * base % MOD;
            base = base * base % MOD;
            exp >>= 1;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt(); // number of test cases

        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int k = sc.nextInt();
            int q = sc.nextInt();

            Set<Integer> usedRows = new HashSet<>();
            Set<Integer> usedCols = new HashSet<>();
            Set<Long> assigned = new HashSet<>();

            // Process initial assignments
            for (int i = 0; i < k; i++) {
                int r = sc.nextInt();
                int c = sc.nextInt();
                sc.nextLong(); // value is not needed for count

                assigned.add(((long) r << 32) | c);
                usedRows.add(r);
                usedCols.add(c);
            }

            int usedRowCount = usedRows.size();
            int usedColCount = usedCols.size();
            int freeRows = n - usedRowCount;
            int freeCols = m - usedColCount;

            long baseAnswer = modPow(2, freeRows + freeCols);
            System.out.println(baseAnswer);

            for (int i = 0; i < q; i++) {
                int r = sc.nextInt();
                int c = sc.nextInt();
                sc.nextLong(); // again, value ignored for count

                long key = ((long) r << 32) | c;

                if (!assigned.contains(key)) {
                    assigned.add(key);
                    // If it's a new row or column, update free counters
                    if (!usedRows.contains(r)) {
                        usedRows.add(r);
                        freeRows--;
                    }
                    if (!usedCols.contains(c)) {
                        usedCols.add(c);
                        freeCols--;
                    }
                }

                long ans = modPow(2, freeRows + freeCols);
                System.out.println(ans);
            }
        }
    }
}
