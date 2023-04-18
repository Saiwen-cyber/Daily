package coding.package2022.package202209;

import java.util.*;

/**
 * @author fang
 */
public class Solution0907 {
    private static final char BLANK_SPACE = ' ';

    public String reorderSpaces(String text) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        List<String> list = new ArrayList<>();
        int i = 0;
        while (i < text.length()) {
            if (text.charAt(i) == BLANK_SPACE) {
                count++;
                i++;
                continue;
            }
            while (i < text.length() && text.charAt(i) != BLANK_SPACE) {
                sb.append(text.charAt(i));
                i++;
            }
            list.add(sb.toString());
            sb.delete(0, sb.length() + 1);
        }

        int avg = count / (list.size() - 1);
        for (int j = 0; j < list.size() && count >= 0; j++) {
            sb.append(list.get(j));
            avg = Math.min(count, avg);
//            sb.append(String.valueOf(BLANK_SPACE).repeat(avg));
            count -= avg;
        }
//        sb.append(String.valueOf(BLANK_SPACE).repeat(count));
        return sb.toString();
    }

/*    private static int[] dp;

    public int coinChange(int[] coins, int amount) {
        dp = new int[amount + 1];
        Arrays.fill(dp, -2);
        return coinChangeExec(coins, amount);
    }

    //dp[amount] 表示

    public int coinChangeExec(int[] coins, int amount) {
        int re = Integer.MAX_VALUE;
        //base case
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        if (dp[amount] != -2) {
            return dp[amount];
        }
        for (int coin : coins) {
            int subResult = coinChangeExec(coins, amount - coin);
            if (subResult != -1) {
                re = Math.min(re, 1 + subResult);
            }
        }
        re = (re == Integer.MAX_VALUE) ? -1 : re;
        dp[amount] = re;
        return re;
    }*/

    private boolean[][] dp;
    private int n;
    private int[] nums;

    //    dp[i][j] 表示从数组的 [0,i][0,i] 下标范围内选取若干个正整数（可以是 00 个），
//    是否存在一种选取方案使得被选取的正整数的和等于 jj。初始时，\textit{dp}dp 中的全部元素都是 \text{false}false。
    public boolean canPartition(int[] nums) {
        int len = nums.length;
        if (nums.length == 1) {
            return false;
        }
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        init(len, target, nums);
        return dp(target);
    }

    private void init(int len, int target, int[] nums) {
        dp = new boolean[len][target + 1];
        n = len;
        this.nums = nums;
    }

    private boolean dp(int target) {
        //target is zero
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= target; j++) {
                if (j >= nums[i]) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n - 1][target];
    }

    public static void main(String[] args) {
        Solution0907 so = new Solution0907();
        int[] coins = new int[]{1, 2, 5};
        int amount = 11;

        int[] array = new int[]{1, 5, 11, 5};
        so.canPartition(array);
//        so.coinChange(coins, amount);
        String text = "a b   c d";
        so.reorderSpaces(text);
    }
}