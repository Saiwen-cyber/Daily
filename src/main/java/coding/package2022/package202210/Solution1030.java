package coding.package2022.package202210;

import java.util.*;

public class Solution1030 {
    public List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        backTrack(res, s, 0, sb);
        return res;
    }

    public void backTrack(List<String> res, String s, int idx, StringBuilder sb) {
        if (idx == s.length()) {
            res.add(sb.toString());
            return;
        }
        char[] chars = new char[2];
        chars[0] = s.charAt(idx);
        chars[1] = converse(chars[0]);
        for (int i = 0; i < chars.length; i++) {
            if (i == 1 && chars[1] == chars[0]) {
                continue;
            }
            sb.append(chars[i]);
            backTrack(res, s, idx + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public char converse(char ch) {
        if (ch >= 'a' && ch <= 'z') {
            return (char) (ch - ('a' - 'A'));
        }
        if (ch >= 'A' && ch <= 'Z') {
            return (char) (ch + ('a' - 'A'));
        }
        return ch;
    }


    public double[] dicesProbability(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        backtrack(map, n, 0, 0, 1);
        int len = map.size();
        int f = factorial(n);
        double[] res = new double[len];
        int i = 0;
        Set<Integer> set = new HashSet<>();
        for (int j = 1; j <= 6; j++) {
            int sum = n * j;
            set.add(sum);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int count = entry.getKey() * f + (set.contains(entry.getKey()) ? 1 : 0);
            res[i++] = count * 1.0 / Math.pow(6, n);
        }
        return res;
    }

    //即将要抛第idx个骰子
    //i 骰子[1,2,3,4,5,6]
    public void backtrack(Map<Integer, Integer> map, int n, int idx, int sum, int i) {
        if (idx == n) {
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            return;
        }
        for (; i <= 6; i++) {
            backtrack(map, n, idx + 1, sum + i, i + 1);
        }
    }

    public int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        Solution1030 so = new Solution1030();
        so.dicesProbability(1);
    }
}