package DP;

public class Knapsack {
    public static void main(String[] args) {
        int capacity = 14;
        int number = 7;
        int[] weight = {3, 4, 2, 2, 7, 5, 6};
        int[] value = {2, 3, 3, 1, 6, 3, 5};
        int[][] dp = solve(capacity, number, weight, value);

        // 输出结果
        for (int i = 0; i < number + 1; i++) {
            for (int j = 0; j < capacity + 1; j++) {
                System.out.print(dp[i][j] + "\t");
                if (j == capacity) {
                    System.out.println();
                }
            }
        }
    }

    /**
     * @param c 背包最大容量
     * @param n 物品总数
     * @param w 物品重量数组
     * @param v 物品价值数组
     */
    public static int[][] solve(int c, int n, int[] w, int[] v) {

        int dp[][] = new int[n + 1][c + 1];

        // 选择0件物品时，无论背包容量多少，都是0
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = 0;
        }

        // 选择1件物品开始遍历
        for (int i = 1; i < n + 1; i++) {

            // 背包容量为1开始遍历
            for (int j = 1; j < c + 1; j++) {

                // 如果当前物品的重量大于背包容量，则不选择当前物品。注意w[i-1]是第i个物品的重量，因为数组下标是从0开始的。
                if (w[i-1] > j) {
                    dp[i][j] = dp[i - 1][j];
                    // 否则，比较：选择当前物品和不选择当前物品，取最大值。
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], (dp[i - 1][j - w[i-1]] + v[i-1]));
                }
            }
        }
        return dp;
    }

}

