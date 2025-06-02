
import java.util.*;

public class Penjamo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt(); // number of test cases

        while (t-- > 0) {
            int n = sc.nextInt(); // number of families
            int r = sc.nextInt(); // number of rows

            int[] a = new int[n]; // members in each family
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }

            int totalSeats = 2 * r;
            int totalPeople = 0;

            for (int ai : a) {
                totalPeople += ai;
            }

            int pairs = 0;
            int leftovers = 0;

            for (int ai : a) {
                pairs += ai / 2;
                leftovers += ai % 2;
            }

            int fullRows = Math.min(pairs, r); // each full row takes 2 from same family
            int remainingRows = r - fullRows;
            int remainingPeople = totalPeople - 2 * fullRows;

            // In the remaining rows, we can seat one person alone (they will be happy)
            int happyFromSolo = Math.min(remainingPeople, remainingRows);

            int happyPeople = 2 * fullRows + happyFromSolo;

            System.out.println(happyPeople);
        }

        sc.close();
    }
}
