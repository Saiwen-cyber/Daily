package coding.package202209;

import java.util.*;

/**
 * @author ASUS
 */
public class Solution0905 {
    public String minWindow(String s, String t) {

        Map<Character, Integer> tMap = new HashMap<>();
        Map<Character, Integer> sMap = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            char key = t.charAt(i);
            int val = tMap.getOrDefault(key, 0);
            tMap.put(key, val + 1);
        }
        int left = 0;
        int right = 0;
        Map<Integer, Integer> re = new HashMap<>();
        while (true) {
            boolean flag = false;
            while (!isCovered(sMap, tMap)) {
                if (right >= s.length()) {
                    return findMin(re, s);
                }
                char key = s.charAt(right);
                int val = sMap.getOrDefault(key, 0);
                if (tMap.containsKey(key)) {
                    sMap.put(key, val + 1);
                }
                right++;
            }
            char removeKey = s.charAt(left);
            while (!sMap.containsKey(removeKey)) {
                left++;
                removeKey = s.charAt(left);
            }
            re.put(right - 1, left);
            int val = sMap.get(removeKey);
            if (val <= 1) {
                sMap.remove(removeKey);
            } else {
                sMap.put(removeKey, val - 1);
            }
            left++;
        }
    }

    private String findMin(Map<Integer, Integer> re, String s) {
        if(re.isEmpty()) {
            return "";
        }
        int left = 0;
        int right = s.length() -  1;
        for (Map.Entry<Integer, Integer> entry : re.entrySet()) {
            if (entry.getKey() - entry.getValue() < right - left) {
                right = entry.getKey();
                left = entry.getValue();
            }
        }
        return s.substring(left, right + 1);
    }

    private boolean isCovered(Map<Character, Integer> sMap, Map<Character, Integer> tMap) {
        for (Map.Entry<Character, Integer> entry : tMap.entrySet()) {
            char key = entry.getKey();
            int val = entry.getValue();

            if (!sMap.containsKey(key) || sMap.get(key) < val) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String a1 = "ADOBECODEBANC";
        String b1 = "ABC";
        String a2 = "a";
        String b2 = "a";
        String a3 = "a";
        String b3 = "aa";
        String s = "bba";
        String t = "ba";
        Solution0905 solution = new Solution0905();
        System.out.println(solution.minWindow(a1, b1));
        System.out.println(solution.minWindow(a2, b2));
        System.out.println(solution.minWindow(a3, b3));
        System.out.println(solution.minWindow(s, t));
    }
}