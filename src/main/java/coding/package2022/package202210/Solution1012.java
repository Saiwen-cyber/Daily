package coding.package2022.package202210;

import coding.ListNode;

import java.util.*;

public class Solution1012 {
    public String longestPalindrome(String s) {
        String ans = "";
        for (int i = 0; i < s.length(); i++) {
            String tmp = findLongestByOne(s, i);
            String tmp2 = findLongestByTwo(s, i, i + 1);

            tmp = tmp.length() > tmp2.length() ? tmp : tmp2;
            ans = tmp.length() > ans.length() ? tmp : ans;
        }
        return ans;
    }

    public String findLongestByOne(String s, int mid) {
        int left = mid - 1;
        int right = mid + 1;
        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(mid));
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) == s.charAt(right)) {
                sb.insert(0, s.charAt(left)).append(s.charAt(right));
                left--;
                right++;
            } else {
                break;
            }
        }
        return sb.toString();
    }

    public String findLongestByTwo(String s, int left, int right) {
        StringBuilder sb = new StringBuilder();
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            sb.insert(0, s.charAt(left)).append(s.charAt(right));
            left--;
            right++;
        }
        return sb.toString();
    }

    public int numComponents(ListNode head, int[] nums) {
        int res = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        while (head != null) {
            boolean flag = false;
            while (!set.isEmpty() && set.contains(head.val)) {
                flag = true;
                set.remove(head.val);
                head = head.next;
            }
            if (flag) {
                res++;
            } else {
                head = head.next;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution1012 so = new Solution1012();
        String ans = so.longestPalindrome("cbbd");
        System.out.println(ans);
    }
}