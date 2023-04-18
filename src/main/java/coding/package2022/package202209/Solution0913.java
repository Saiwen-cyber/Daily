package coding.package2022.package202209;

import java.util.*;

/**
 * @author ASUS
 */
public class Solution0913 {
    public int maximumSwap(int num) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((x, y) -> y - x);
        List<Integer> list = new ArrayList<>();
        storeReminder(num, queue, list);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int cur = list.get(0);
        int top = queue.poll();
        while (!queue.isEmpty() && i < list.size() && cur == top) {
            sb.append(cur);
            i++;
            cur = list.get(i);
            top = queue.poll();
        }
        sb.append(top);
        boolean isChanged = false;
        while (i < list.size()) {
            int val = list.get(i);
            if (!isChanged && val == top) {
                sb.append(cur);
                isChanged = true;
            }
            i++;
        }

        return Integer.parseInt(sb.toString());
    }

    public void storeReminder(int num, Queue<Integer> queue, List<Integer> list) {
        while (num != 0) {
            int val = num % 10;
            num = num / 10;
            queue.add(val);
            list.add(0, val);
        }
    }

    public int longestPalindrome(String[] words) {
        int max = 0;
        int re = 0;
        int len = words.length;
        Map<String, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        Set<String> spec = new HashSet<>();

        for (String word : words) {
            if (word.charAt(0) == word.charAt(1)) {
                spec.add(word);
                max++;
            }
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String src = entry.getKey();
            int val = entry.getValue();

            if (spec.contains(src)) {
                int cp = (val / 2);
                max -= cp * 2;
                re += cp;
                continue;
            }
            if (set.contains(src)) {
                continue;
            }
            String reverse = new StringBuffer(src).reverse().toString();
            int reverseVal = map.getOrDefault(reverse, 0);
            set.add(src);
            set.add(reverse);

            re += Math.min(val, reverseVal);
        }

        return re * 4 + max == 0 ? 0 : 2;
    }

    public static void main(String[] args) {
        Solution0913 so = new Solution0913();
        so.longestPalindrome(new String[]{"cc", "cc"});
        so.maximumSwap(2736);
        so.maximumSwap(9973);
    }
}