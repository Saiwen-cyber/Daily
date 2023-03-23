package coding.package202210;

class Solution1113 {
    public double[] convertTemperature(double celsius) {
        double[] ans = new double[2];
        ans[0] = celsius + 273.15;
        ans[1] = celsius * 1.80 + 32.00;
        return ans;
    }

    public int subarrayLCM(int[] nums, int k) {
        int len = nums.length;
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (k % nums[i] != 0) {
                continue;
            }
            int min = nums[i];
            for (int j = i; j < len; j++) {
                min = lcm(min, nums[j]);
                if (min == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    public int maxPalindromes(String s, int k) {
        int ans = 0;
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
            if (k == 1) {
                ans++;
            }
        }
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                char a = s.charAt(i);
                char b = s.charAt(j);
                if (a != b) {
                    dp[i][j] = false;
                } else {
                    if (j - i + 1 < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && j - i + 1 >= k) {
                    ans++;
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        Solution1113 so = new Solution1113();
//        int[] nums = new int[]{3, 12, 9, 6};
//        so.subarrayLCM(nums, 3);
        so.maxPalindromes("fttfjofpnpfydwdwdnns", 2);

    }

}