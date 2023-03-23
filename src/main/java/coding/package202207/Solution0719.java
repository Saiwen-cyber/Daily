package coding.package202207;

import java.util.*;

public class Solution0719 {
    public String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);
        String re = strs[0];
        for (int i = 0; i < strs.length; i++) {
            re = isPrefix(re, strs[i]);
        }
        return re;
    }

    public String isPrefix(String prefix, String str) {
        while (true) {
            if (str.startsWith(prefix)) {
                return prefix;
            }
            prefix = prefix.substring(0, prefix.length() - 1);
        }
    }

    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1) {
            if (set.contains(n)) {
                return false;
            }
            set.add(n);
            n = calSum(n);
        }
        return true;
    }

    public int calSum(int n) {
        int re = 0;
        while (n != 0) {
            int val = n % 10;
            re = re + val * val;
            n = n / 10;
        }
        return re;
    }

    public static void main(String[] args) {
        Solution0719 solution0719 = new Solution0719();
        solution0719.isHappy(19);
        solution0719.longestCommonPrefix(new String[]{"flow", "flower", "flight"});
    }
}