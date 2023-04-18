package coding.package2022.package202212;

import java.util.*;

public class Solution1204 {
    public static void main(String[] args) {

        int[][] roads = new int[][]{{1, 2, 9}, {2, 3, 6}, {2, 4, 5}, {1, 4, 7}};
//        roads = new int[][]{{18, 20, 9207}, {14, 12, 1024}, {11, 9, 3056}, {8, 19, 416}, {3, 18, 5898}, {17, 3, 6779},
//                {13, 15, 3539}, {15, 11, 1451}, {19, 2, 3805}, {9, 8, 2238}, {1, 16, 618}, {16, 14, 55},
//                {17, 7, 6903}, {12, 13, 1559}, {2, 17, 3693}};

        Solution1204 so = new Solution1204();
        so.minScore(4, roads);
    }

    public boolean isCircularSentence(String sentence) {
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            int len = words[i].length();
            int next = (i + 1) % words.length;
            if (words[i].charAt(len - 1) != words[next].charAt(0)) {
                return false;
            }
        }
        return true;
    }

    public long dividePlayers(int[] skill) {
        long result = 0;
        Map<Long, Long> map = new HashMap<>();
        long sum = 0;
        int len = skill.length;
        for (long value : skill) {
            sum += value;
            long val = map.getOrDefault(value, 0L);
            map.put(value, val + 1);
        }
        long unit = sum / (len / 2);
        if (sum % (len / 2) != 0) {
            return -1;
        }
        Set<Long> passSet = new HashSet<>();
        for (Map.Entry<Long, Long> entry : map.entrySet()) {
            long x = entry.getKey();
            long y = unit - x;
            if (passSet.contains(x)) {
                continue;
            }
            if (x == y) {
                if (entry.getValue() % 2 != 0) {
                    return -1;
                }
                result += (entry.getValue() / 2) * (x * x);
                passSet.add(x);
                continue;
            }

            if (map.containsKey(y) && entry.getValue().equals(map.get(y))) {
                passSet.add(x);
                passSet.add(y);
                result += (x * y) * entry.getValue();
            } else {
                return -1;
            }
        }
        return result;
    }


    public int minScore(int n, int[][] roads) {
        int res = Integer.MAX_VALUE;
        int[] minVal = new int[n + 1];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n + 1; i++) {
            minVal[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < roads.length; i++) {
            int p1 = roads[i][0];
            int p2 = roads[i][1];
            //与p1相连的邻接点
            List<Integer> l1 = map.getOrDefault(p1, new ArrayList<>());
            //与p1相连的邻接点
            List<Integer> l2 = map.getOrDefault(p2, new ArrayList<>());
            l1.add(p2);
            l2.add(p1);

            map.put(p1, l1);
            map.put(p2, l2);

            minVal[p1] = Math.min(minVal[p1], roads[i][2]);
            minVal[p2] = Math.min(minVal[p2], roads[i][2]);
        }
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        boolean[] vis = new boolean[n + 1];
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            vis[node] = true;
            res = Math.min(res, minVal[node]);
            List<Integer> list = map.get(node);
            for (Integer next : list) {
                if (vis[next]) {
                    continue;
                }
                queue.add(next);
            }
        }
        return res;
    }
}