import java.util.*;

public class KarSalesman {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt(); // number of test cases

        while (t-- > 0) {
            int n = sc.nextInt(); // number of models
            int x = sc.nextInt(); // max cars per customer

            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }

            // Use max-heap to always get models with highest remaining cars
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

            for (int count : a) {
                pq.offer(count);
            }

            int customers = 0;

            while (!pq.isEmpty()) {
                List<Integer> temp = new ArrayList<>();

                // Pick up to x models
                for (int i = 0; i < x && !pq.isEmpty(); i++) {
                    int curr = pq.poll();
                    if (curr > 1) {
                        temp.add(curr - 1);
                    }
                    // If curr == 1, it’s sold and not re-added
                }

                // Reinsert remaining cars back into queue
                for (int left : temp) {
                    pq.offer(left);
                }

                customers++; // One customer handled
            }

            System.out.println(customers);
        }

        sc.close();
    }
}
