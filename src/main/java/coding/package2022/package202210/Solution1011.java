package coding.package2022.package202210;

import java.util.*;

public class Solution1011 {
    public boolean areAlmostEqual(String s1, String s2) {
        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();
        int count = 0;
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 != len2) {
            return false;
        }
        for (int i = 0; i < len1; i++) {
            char ch1 = s1.charAt(i);
            char ch2 = s2.charAt(i);

            if (ch1 != ch2) {
                count++;
            }
            if (count > 2) {
                return false;
            }
        }
        for (char key : set1) {
            if (!set2.contains(key)) {
                return false;
            }
        }
        return (count == 0 || count == 2);

    }

    //由dfs 到  记忆化dfs 到动态规划转移方程
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i < len1 + 1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < len2 + 1; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i < len1 + 1; i++) {
            for (int j = 1; j < len2 + 1; j++) {
                char ch1 = word1.charAt(i);
                char ch2 = word2.charAt(j);
                //i 代表 s1的前i
                //j 代表 s2的前j
                //s2之后加一个字符
                int l1 = dp[i - 1][j] + 1;
                //在s1之后加一个字符
                int l2 = dp[i][j - 1] + 1;
                //替换i,j中字符其中一个
                int l3 = ch1 == ch2 ? dp[i - 1][j - 1] : dp[i - 1][j - 1] + 1;
                dp[i][j] = Math.min(l1, Math.min(l2, l3));
            }
        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        Solution1011 so = new Solution1011();
        so.areAlmostEqual("caa", "aaz");
    }
}