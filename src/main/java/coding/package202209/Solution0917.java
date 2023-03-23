package coding.package202209;

import java.util.*;

/**
 * @author ASUS
 */
public class Solution0917 {
    public int maxLengthBetweenEqualCharacters(String s) {
        int len = s.length();
        int[][] mes = new int[26][len];
        int[] p = new int[26];
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            int index = ch - 'a';
            mes[index][p[index]] = i + 1;
            p[index] += 1;
        }
        int re = -1;
        for (int i = 0; i < 26; i++) {
            int j = 0;
            boolean flag = false;
            while (j < len) {
                if (mes[i][j] == 0) {
                    break;
                }
                j++;
                flag = true;
            }
            if (!flag) {
                continue;
            }
            int tmp = mes[i][j - 1] - mes[i][0] - 1;
            re = Math.max(re, tmp);
        }
        return re;
    }

    public static void main(String[] args) {
        Solution0917 so = new Solution0917();
        int re = so.maxLengthBetweenEqualCharacters("mgntdygtxrvxjnwksqhxuxtrv");
        System.out.println(re);
    }
}