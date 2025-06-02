
import java.io.*;
import java.util.*;

public class D1AsesinoEasyVersion {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter out = new PrintWriter(System.out);

    // Ask player i if player j is a Knight
    static int ask(int i, int j) {
        out.println("? " + i + " " + j);
        out.flush();
        return sc.nextInt();
    }

    public static void main(String[] args) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] trust = new int[n + 1]; // 1-based
            Arrays.fill(trust, -1);

            int found = -1;
            // Scan in triplets to find a contradiction
            for (int i = 1; i <= n - 2; i++) {
                int a = ask(i, i + 1);
                int b = ask(i + 1, i + 2);
                int c = ask(i, i + 2);

                // If two agree and one doesn't, the different one may be the impostor
                if (a == b && b != c) {
                    found = i + 2;
                    break;
                }
                if (a == c && c != b) {
                    found = i + 1;
                    break;
                }
                if (b == c && a != b) {
                    found = i;
                    break;
                }
            }

            // Fallback, if all previous give same answers
            if (found == -1) {
                found = 1;
            }

            out.println("! " + found);
            out.flush();

            int response = sc.nextInt();
            if (response == -1) {
                // Jury signals wrong answer or invalid query
                return;
            }
        }
    }
}
