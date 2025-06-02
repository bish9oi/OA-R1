import java.util.*;

public class Gerrymandering {
    static int[][][] districtPatterns = {
            // 2 vertical districts (each column)
            { { 0, 0 }, { 1, 0 }, { 0, 1 } },
            { { 1, 1 }, { 0, 2 }, { 1, 2 } },
            // 2 horizontal districts (each row)
            { { 0, 0 }, { 0, 1 }, { 0, 2 } },
            { { 1, 0 }, { 1, 1 }, { 1, 2 } },
            // L-shaped and S-shaped partitions
            { { 0, 0 }, { 1, 0 }, { 1, 1 } },
            { { 0, 1 }, { 1, 1 }, { 1, 2 } },
            { { 1, 0 }, { 1, 1 }, { 0, 1 } },
            { { 1, 1 }, { 1, 2 }, { 0, 2 } },
            { { 0, 0 }, { 0, 1 }, { 1, 1 } },
            { { 0, 1 }, { 0, 2 }, { 1, 1 } },
            { { 1, 0 }, { 0, 1 }, { 1, 1 } },
            { { 0, 1 }, { 1, 1 }, { 1, 2 } },
            // Zig-zag and corner combinations
            { { 0, 0 }, { 1, 0 }, { 0, 1 } },
            { { 0, 2 }, { 1, 2 }, { 0, 1 } },
            { { 1, 0 }, { 0, 1 }, { 1, 1 } },
            { { 1, 2 }, { 0, 1 }, { 1, 1 } }
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt(); // number of test cases

        while (t-- > 0) {
            int n = sc.nextInt(); // number of columns
            String[] grid = new String[2];
            grid[0] = sc.next();
            grid[1] = sc.next();

            int maxWins = 0;

            // Process every 3-column block
            for (int col = 0; col < n; col += 3) {
                // 2x3 block
                char[][] block = new char[2][3];
                for (int r = 0; r < 2; r++) {
                    for (int c = 0; c < 3; c++) {
                        block[r][c] = grid[r].charAt(col + c);
                    }
                }

                // Try all partitions and keep the best score
                int localMax = 0;

                // Generate all 20 possible splits into two districts of 3 connected cells
                List<List<int[]>> partitions = generateAllPartitions();

                for (List<int[]> partition : partitions) {
                    int win = 0;
                    for (int d = 0; d < 2; d++) {
                        int aCount = 0;
                        for (int[] pos : partition.get(d)) {
                            if (block[pos[0]][pos[1]] == 'A') {
                                aCount++;
                            }
                        }
                        if (aCount >= 2)
                            win++;
                    }
                    localMax = Math.max(localMax, win);
                }

                maxWins += localMax;
            }

            System.out.println(maxWins);
        }

        sc.close();
    }

    // Generate all possible partitions of a 2x3 grid into 2 groups of 3 connected
    // cells
    private static List<List<int[]>> generateAllPartitions() {
        // Hardcoded partitions to ensure connectivity and uniqueness
        List<List<int[]>> result = new ArrayList<>();

        // Manually define valid splits into two connected districts of 3
        // You can expand this list with known valid 2x3 partitions
        int[][][] validSplits = {
                { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 0 }, { 1, 1 }, { 1, 2 } },
                { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 }, { 0, 2 }, { 1, 2 } },
                { { 0, 0 }, { 1, 0 }, { 1, 1 }, { 0, 1 }, { 0, 2 }, { 1, 2 } },
                { { 0, 0 }, { 1, 0 }, { 1, 1 }, { 0, 1 }, { 1, 2 }, { 0, 2 } },
                { { 0, 0 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, 2 }, { 0, 2 } },
        };

        for (int[][] split : validSplits) {
            List<int[]> group1 = new ArrayList<>();
            List<int[]> group2 = new ArrayList<>();

            for (int i = 0; i < 3; i++)
                group1.add(split[i]);
            for (int i = 3; i < 6; i++)
                group2.add(split[i]);

            result.add(Arrays.asList(group1.toArray(new int[0][]), group2.toArray(new int[0][])));
        }

        return result;
    }
}
