package Greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinCoinsCount {
    public static void main(String[] args) {
        List<Integer> result;
        int amount  = 134;
        List<Integer> coins = new ArrayList<>(){{
            add(1);
            add(5);
            add(10);
            add(25);
            add(100);
        }};
        result = solve(coins,amount);
        System.out.println("Result: " + result);
        System.out.println("Counts: " + result.size());
    }
    public static List<Integer> solve(List<Integer> coins, Integer amount) {
        Collections.reverse(coins);
        List<Integer> result = new ArrayList<>();
        for (Integer coin : coins) {
            while(coin <= amount) {
                result.add(coin);
                amount -= coin;
            }
        }
        return result;
    }
}