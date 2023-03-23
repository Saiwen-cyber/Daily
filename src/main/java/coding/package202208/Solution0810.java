package coding.package202208;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fang
 */
public class Solution0810 {
    public int lengthOfLongestSubstring(String s) {
        StringBuilder sb = new StringBuilder("");
        int len = s.length();
        if(len == 0) {
            return 0;
        }
        int re = 0;
        int q = 0;
        while(q < len) {
            String ch = String.valueOf(s.charAt(q));
            if(sb.indexOf(ch) >= 0) {
                //添加长度
                re = Math.max(re,sb.length());
                sb.delete(0,sb.indexOf(ch) + 1);
            }
            sb.append(ch);
            q++;
        }

        return Math.max(re,sb.length());
    }

    public static void main(String[] args) {
        Solution0810 solution = new Solution0810();
        int re = solution.lengthOfLongestSubstring("abcabcbb");
        System.out.println(re);
    }
}