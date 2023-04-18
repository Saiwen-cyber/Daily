package coding.package2022.package202209;

import java.util.*;

public class Solution0925 {

    public int rotatedDigits(int n) {
        if (n == 1) {
            return 0;
        }
        return rotatedDigits(n - 1) + calGood(n);
    }

    public boolean isGood(char ch) {
        return ch == '2' || ch == '5' || ch == '6' || ch == '9';
    }

    public int calGood(int n) {
        int sum = 0;
        String num = Integer.toString(n);
        for (int i = 0; i < num.length(); i++) {
            char ch = num.charAt(i);
            if (isGood(ch)) {
                sum++;
            }
        }
        return sum;
    }

    public List<Integer> goodIndices(int[] nums, int k) {
        int len = nums.length;
        boolean[][] asc = iterate1(nums);
        boolean[][] desc = iterate2(nums);
        List<Integer> list = new ArrayList<>();
        for (int i = k; i < len - k; i++) {
            int low = i - k;
            int high = i + k;
            if (desc[low][i - 1] && asc[i + 1][high]) {
                list.add(i);
            }
        }

        return list;
    }

    public boolean[][] iterate1(int[] nums) {
        int len = nums.length;
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = dp[i][j - 1] && nums[j] >= nums[j - 1];
            }
        }
        return dp;
    }

    public boolean[][] iterate2(int[] nums) {
        int len = nums.length;
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = dp[i][j - 1] && nums[j] <= nums[j - 1];
            }
        }
        return dp;
    }

    private List<Integer> getDesc(int k, int[] nums, List<Integer> asc) {
        List<Integer> list = new ArrayList<>();
        for (Integer i : asc) {
            boolean flag = true;
            for (int j = i - k; j < i - 1; j++) {
                flag = flag && (nums[j + 1] <= nums[j]);
            }
            if (flag) {
                list.add(i);
                System.out.println(i);
            }
        }

        return list;
    }

    private List<Integer> getAsc(int k, int[] nums) {
        List<Integer> list = new ArrayList<>();
        int len = nums.length;
        for (int i = k; i < len - k; i++) {
            boolean flag = true;
            for (int j = i + 1; j < i + k; j++) {
                flag = flag && (nums[j + 1] >= nums[j]);
            }
            if (flag) {
                list.add(i);
                System.out.println(i);
            }
        }
        return list;
    }

    public int longestSubarray(int[] nums) {
        int max = nums[0];
        int len = 1;
        int re = 1;
        int i = 1;
        while (i < nums.length) {
            boolean isLarge = false;
            if (nums[i] < max) {
                re = 0;
                i++;
                continue;
            }
            if (nums[i] > max) {
                max = nums[i];
                i++;
                re = 1;
                isLarge = true;
            }
            while (i < nums.length && nums[i] == max) {
                re++;
                i++;
            }
            len = isLarge ? re : Math.max(len, re);
        }
        return len;
    }

    public String[] sortPeople(String[] names, int[] heights) {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < names.length; i++) {
            map.put(heights[i], names[i]);
        }
        String[] re = new String[map.size()];
        Arrays.sort(heights);
        int j = 0;
        for (int i = heights.length - 1; i >= 0; i--, j++) {
            re[j] = map.get(heights[i]);
        }
        return re;
    }

    public static void main(String[] args) {
        Solution0925 so = new Solution0925();
        int[] nums = new int[]{878724, 201541, 179099, 98437, 35765, 327555, 475851, 598885, 849470, 943442};
        int k = 4;
        nums = new int[]{253747, 459932, 263592, 354832, 60715, 408350, 959296};
        k = 2;

        nums = new int[]{2, 1, 1, 1, 3, 4, 1};
        k = 2;
        so.calGood(39);
        so.goodIndices(nums, k);
        so.goodIndices(new int[]{440043, 276285, 336957}, 1);
    }
}