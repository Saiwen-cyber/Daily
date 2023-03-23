package coding.package202209;

import java.util.*;

/**
 * @author ASUS
 */
public class Solution0914 {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<Integer> queue = new ArrayDeque<>();
        int len = routes.length;
        Map<Integer, Integer> vis = new HashMap<>();

        for (int i = 0; i < len; i++) {
            //车站 -> 所在的道路
            for (int point : routes[i]) {
                List<Integer> list = map.getOrDefault(point, new ArrayList<>());
                list.add(i);
                map.put(point, list);
                if (point == source) {
                    queue.add(i);
                    vis.put(i, 1);
                }
            }
        }
        while (!queue.isEmpty()) {
            int i = queue.poll();
            int curTime = vis.get(i);

            for (int point : routes[i]) {
                if (point == target) {
                    return curTime;
                }
                List<Integer> list = map.get(point);
                for (Integer e : list) {
                    if (!vis.containsKey(e)) {
                        queue.add(e);
                        vis.put(e, curTime + 1);
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] nums = new int[][]{{1, 2, 7}, {3, 6, 7}};
        nums = new int[][]{{1, 9, 12, 20, 23, 24, 35, 38}, {10, 21, 24, 31, 32, 34, 37, 38, 43}, {10, 19, 28, 37}, {8}, {19}, {11, 17, 23, 31, 41, 43, 44},
                {21, 26, 29, 33}, {5, 11, 33, 41}, {4, 5, 8, 9, 24, 44}};
        Solution0914 so = new Solution0914();
        so.numBusesToDestination(nums, 37, 28);
    }
}