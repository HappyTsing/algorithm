package Greedy;

/**
 * 给定 n 个物品和一个容量为 C 的背包，物品 i 的重量是 Wi,其价值为 Vi
 * 背包问题是如何选择入背包的物品，使得装入背包的物品的总价值最大
 * 注意和0/1背包的区别，在当前背包问题中可以将物品的一部分装入背包，但不能重复装入。
 */
public class Knapsack {
    public static void main(String[] args) {
        int capacity = 20;
        int number = 7;
        // 此处 weight 和 value 数组已经按照单位价值降序排列，若给定的是乱序，则需要首先排序数组。
        int[] weight = { 3, 4, 5, 6, 7, 8, 9 };
        int[] value = { 24, 28, 30, 30, 28, 24, 18 };
        solve(capacity, number, weight, value);
    }

    /**
     * @param c 背包最大容量
     * @param n 物品总数
     * @param w 物品重量数组
     * @param v 物品价值数组
     */
    public static void solve(int c, int n, int[] w, int[] v) {
        int c_left = c;
        int all_value = 0;
        for (int i = 0; i < n; i++) {
            if (c_left >= w[i]) {
                all_value += v[i];
                c_left -= w[i];
            } else {
                all_value += c_left * v[i] / w[i];
                break;
            }
        }
        System.out.println("最多容纳价值: " + all_value);
    }
}
