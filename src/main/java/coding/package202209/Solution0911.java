package coding.package202209;

import java.util.*;

public class Solution0911 {
    public int partitionString(String s) {
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);
            if (sb.indexOf(String.valueOf(key)) < 0) {
                sb.append(key);
            } else {
                list.add(sb.toString());
                sb.delete(0, sb.length() + 1);
                i--;
            }
        }
        return sb.length() != 0 ? list.size() + 1 : list.size();
    }

    public int minGroups(int[][] intervals) {

        Arrays.sort(intervals, (e1, e2) -> (e1[0] == e2[0] ? (e1[1] - e2[1]) : (e1[0] - e2[0])));
        PriorityQueue<Integer> queue = new PriorityQueue<>((x, y) -> x - y);
        for (int i = 0; i < intervals.length; i++) {

            int right = intervals[i][1];
            int val = queue.isEmpty() ? right : queue.peek();

            if (intervals[i][0] > val) {
                queue.poll();
            }
            queue.add(right);
        }

        return queue.size();
    }

    public static void main(String[] args) {
        Solution0911 so = new Solution0911();
        int[][] intervals = new int[][]{{5, 10}, {6, 8}, {1, 5}, {2, 3}, {1, 10}};

        System.out.println(so.minGroups(intervals));
    }

}