import java.util.*;

public class PizzaDeliveryTSP {

    static final int INF = 10000000;

    // Function to find minimum delivery time
    static int tsp(int[][] graph, int mask, int pos, int[][] dp, int n) {
        // If all locations visited
        if (mask == (1 << n) - 1)
            return graph[pos][0]; // return to start (A)

        // If already computed
        if (dp[mask][pos] != -1)
            return dp[mask][pos];

        int ans = INF;

        // Try going to every unvisited city
        for (int city = 0; city < n; city++) {
            if ((mask & (1 << city)) == 0) {
                int newAns = graph[pos][city] + tsp(graph, mask | (1 << city), city, dp, n);
                ans = Math.min(ans, newAns);
            }
        }

        return dp[mask][pos] = ans;
    }

    public static void main(String[] args) {
        // Example: 4 locations (A, B, C, D)
        int[][] graph = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };

        int n = graph.length;
        int[][] dp = new int[1 << n][n];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        int result = tsp(graph, 1, 0, dp, n); // Start from A (0)
        System.out.println("Minimum time to deliver pizzas to all locations: " + result);
    }
}
