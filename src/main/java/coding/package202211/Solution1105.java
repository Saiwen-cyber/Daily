package coding.package202211;

import java.util.*;

public class Solution1105 {
    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int right = 0;
        Map<Character, Integer> map = new HashMap<>();
        int ans = 0;
        //[left, right)
        while (right < s.length()) {
            char key = s.charAt(right);
            if (map.containsKey(key)) {
                left = Math.max(map.get(key) + 1, left);
            }
            map.put(key, right);
            right++;
            ans = Math.max(ans, right - left);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] ans = new int[5];
        Solution1105 so = new Solution1105();
        so.lengthOfLongestSubstring("abba");
    }
}