package coding.package2023.package202303;

import java.util.*;
import java.util.stream.Collectors;

public class Solution0324 {

    public static void main(String[] args) throws Exception {
        Solution0324 solution = new Solution0324();
        int[][] connections = new int[][]{{0, 1}, {1, 3}, {2, 3}, {4, 0}, {4, 5}};
        connections = new int[][]{{1, 0}, {1, 2}, {2, 3}, {4, 2}};
        connections = new int[][]{{0, 1}, {2, 0}, {3, 2}};
        connections = new int[][]{{4, 3}, {2, 3}, {1, 2}, {1, 0}};
        connections = new int[][]{{1, 0}, {1, 2}, {3, 2}, {3, 4}};
        solution.minReorder(4, connections);
    }

    public int minReorder(int n, int[][] connections) {
        int modCount = 0;
        Map<Integer, List<Integer>> nextMap = new HashMap<>();
        Map<Integer, List<Integer>> preMap = new HashMap<>();
        Queue<Integer> cityQueue = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        for (int[] conn : connections) {
            List<Integer> next = nextMap.getOrDefault(conn[0], new ArrayList<>());
            List<Integer> pre = preMap.getOrDefault(conn[1], new ArrayList<>());
            next.add(conn[1]);
            nextMap.put(conn[0], next);
            pre.add(conn[0]);
            preMap.put(conn[1], pre);
        }
        cityQueue.add(0);
        while (!cityQueue.isEmpty()) {
            Integer city = cityQueue.poll();
            if (nextMap.containsKey(city)) {
                List<Integer> valid = nextMap.get(city).stream().filter(item -> !visited[item]).collect(Collectors.toList());
                cityQueue.addAll(valid);
                modCount += valid.size();
            }
            if (preMap.containsKey(city)) {
                cityQueue.addAll(preMap.get(city));
            }
            visited[city] = true;
        }
        return modCount;
    }
}