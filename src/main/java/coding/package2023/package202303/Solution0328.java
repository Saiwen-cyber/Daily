package coding.package2023.package202303;

import java.util.Arrays;

public class Solution0328 {
    public static void main(String[] args) {
        Solution0328 so = new Solution0328();
        int[] days = new int[]{1, 4, 6, 7, 8, 20};
        int[] costs = new int[]{2, 7, 15};
        so.mincostTickets(days, costs);
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -2);
        return coinChangeExec(coins, amount, dp);
    }

    public int coinChangeExec(int[] coins, int amount, int[] dp) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        int res = -1;
        for (int coin : coins) {
            int coinNum;
            int newAmount = amount - coin;
            if (newAmount < 0) {
                continue;
            }
            if (dp[newAmount] != -2) {
                coinNum = dp[newAmount];
            } else {
                coinNum = coinChangeExec(coins, amount - coin, dp);
                dp[newAmount] = coinNum;
            }
            if (coinNum == -1) {
                continue;
            }
            res = res == -1 ? coinNum + 1 : Math.min(coinNum + 1, res);
        }
        return res;
    }

    public int mincostTickets(int[] days, int[] costs) {
        int[] pass = new int[]{1, 7, 30};
        int[] dp = new int[days.length + 1];
        Arrays.fill(dp, -1);
        minCostTicketsExec(days, pass, costs, dp, 0, 0);
        return dp[days.length];
    }

    //cur 当前花费
    //index [costs[0] ~ costs[index]);
    //计算从cost[index] ~ cost[len - 1];
    public int minCostTicketsExec(int[] days, int[] pass, int[] costs, int[] dp, int index, int curCost) {
        if (index == days.length) {
            return 0;
        }
        if (dp[index] != -1) {
            return dp[index];
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < pass.length; i++) {
            int nextIndex = findNextIndex(pass[i], days, index);
            int nextCost;
            nextCost = minCostTicketsExec(days, pass, costs, dp, nextIndex, curCost + costs[i]);
            ans = Math.min(ans, nextCost);
        }
        dp[index] = ans;
        return ans;
    }

    private int findNextIndex(int pass, int[] days, int index) {
        int val = days[index] + pass;
        int i;
        i = index;
        while (i < days.length && days[i] < val) {
            i++;
        }
        return i;
    }
}