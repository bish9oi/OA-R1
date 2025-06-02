import java.util.*;

public class D2AsesinoHardVersion {
    static Scanner sc = new Scanner(System.in);

    // Ask if player i thinks player j is a Knight
    static int ask(int i, int j) {
        System.out.println("? " + i + " " + j);
        System.out.flush();
        int response = sc.nextInt();
        if (response == -1) {
            System.exit(0); // exit on invalid response
        }
        return response;
    }

    public static void main(String[] args) {
        int t = sc.nextInt(); // number of test cases
        while (t-- > 0) {
            int n = sc.nextInt();
            int suspect = -1;

            // We search for a group where inconsistency is detected
            for (int i = 1; i <= n - 2; i++) {
                int a = ask(i, i + 1);
                int b = ask(i + 1, i + 2);
                int c = ask(i, i + 2);

                // Inconsistent responses imply impostor is one of the three
                if ((a == b && b != c)) {
                    suspect = i + 2;
                    break;
                }
                if ((a == c && c != b)) {
                    suspect = i + 1;
                    break;
                }
                if ((b == c && a != b)) {
                    suspect = i;
                    break;
                }
            }
            // If no inconsistency found, fallback to 1
            if (suspect == -1) {
                suspect = 1;
            }
            // Output final answer
            System.out.println("! " + suspect);
            System.out.flush();

            int verdict = sc.nextInt();
            if (verdict == -1) {
                System.exit(0); // Invalid or incorrect answer
            }
        }
    }
}
