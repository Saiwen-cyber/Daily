package coding.package2022.package202210;

import java.util.*;

public class Solution1016 {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        if (dislikes.length == 0) {
            return true;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        Map<Integer, Boolean> tag = new HashMap<>();
        //维护数据源
        for (int i = 0; i < dislikes.length; i++) {
            int a = dislikes[i][0];
            int b = dislikes[i][1];
            List<Integer> listA;
            List<Integer> listB;
            listA = map.getOrDefault(a, new ArrayList<>());
            listB = map.getOrDefault(b, new ArrayList<>());
            listA.add(b);
            listB.add(a);
            map.put(a, listA);
            map.put(b, listB);
        }
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            int key = entry.getKey();
            if (tag.containsKey(key)) {
                continue;
            }
            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(key);
            tag.put(key, false);
            while (!queue.isEmpty()) {
                int num = queue.poll();
                boolean color = tag.get(num);

                for (int node : map.get(num)) {
                    if (tag.containsKey(node)) {
                        if (tag.get(node) == color) {
                            return false;
                        }
                        continue;
                    }
                    tag.put(node, !color);
                    queue.add(node);
                }
            }

        }
        return true;
    }

    /**
     * 并查集实现
     * 二分图（）
     * 思路：某人的dislike 列表为一个集合。
     * 如果在某个集合中，发现有互不喜欢的一对，return false;
     *
     * @param n        总数 1... n
     * @param dislikes 互不喜欢的数组
     **/
    public boolean possibleBipartition2(int n, int[][] dislikes) {
        int[] parent = new int[n + 1];
        List<Integer>[] lists = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            lists[i] = new ArrayList<Integer>();
        }
        //维护数据源
        for (int i = 0; i < dislikes.length; i++) {
            lists[dislikes[i][1]].add(dislikes[i][0]);
            lists[dislikes[i][0]].add(dislikes[i][1]);
            parent[i] = i;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < lists[i].size(); j++) {
                int tmp = lists[i].get(j);
                if (find(i, parent) == find(tmp, parent)) {
                    return false;
                }
                //做连接
                union(lists[i].get(0), tmp, parent);
            }
        }
        return true;
    }

    private void union(int i, int j, int[] parent) {
        int tmp = parent[j];
        for (int k = 0; k < parent.length; k++) {
            if (parent[k] == tmp) {
                parent[k] = parent[i];
            }
        }
    }

    private int find(int i, int[] parent) {
        if (parent[i] == i) {
            return parent[i];
        }
        //将同属一族的都更新到一起
        parent[i] = find(parent[i], parent);
        return parent[i];
    }

    public int totalFruit(int[] fruits) {
        int ans = 0;
        int count = 1;

        int pre = -1;
        int cur = 0;
        int a = 0;
        int b = -1;
        for (int i = 1; i < fruits.length; i++) {
            if (fruits[i] != fruits[cur]) {
                pre = cur;
                cur = i;
            }
            if (fruits[i] == fruits[a] || (b != -1 && fruits[i] == fruits[b])) {
                count++;
            } else if (b == -1) {
                b = i;
                count++;
            } else {
                ans = Math.max(ans, count);
                a = pre;
                b = i;
                count = b - a + 1;
            }
        }
        return Math.max(ans, count);
    }


    public static void main(String[] args) {
        int[][] n = new int[][]{{1, 2}, {1, 3}, {2, 4}};
        Solution1016 so = new Solution1016();
//        boolean re = so.possibleBipartition2(4, n);
        int re = so.totalFruit(new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4});
        System.out.println(re);
    }
}