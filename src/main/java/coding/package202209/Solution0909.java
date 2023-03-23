package coding.package202209;

import java.util.*;

/**
 * @author ASUS
 */
public class Solution0909 {
    class Folder {
        List<Folder> subF = new ArrayList<>();
        Folder parent;
        int depth;
        String name;

        Folder(int depth, String name, Folder parent) {
            this.depth = depth;
            this.name = name;
            this.parent = parent;
        }

        void SetSubF(List<Folder> subF) {
            this.subF = subF;
        }

    }

    public int minOperations(String[] logs) {
        Folder root = new Folder(0, "root", null);
        Folder cur = root;
        List<Folder> subF = new ArrayList<>();

        for (int i = 0; i < logs.length; i++) {
            if ("../".equals(logs[i])) {
                cur = cur.parent == null ? root : cur.parent;
            } else if (!logs[i].startsWith(".")) {
                String name = logs[i].substring(0, logs[i].length() - 1);
                int depth = cur.depth + 1;
                Folder tmp = new Folder(depth, name, cur);
                List<Folder> list = cur.subF;
                list.add(tmp);
                cur.SetSubF(list);
                cur = tmp;
            }
        }
        return cur.depth;
    }

    public int leastInterval(char[] tasks, int n) {
        int len = tasks.length;
        if (n == 0) {
            return len;
        }
        int maxSum = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (char key : tasks) {
            int val = map.getOrDefault(key, 0) + 1;
            map.put(key, val);
            maxSum = Math.max(val, maxSum);
        }
        List<Character> keyList = new ArrayList<>(map.keySet());
        int x = 0;
        for (int e : map.values()) {
            if (e >= maxSum) {
                x++;
            }
        }
        int[][] buckets = new int[maxSum][n + 1];
        keyList.sort((a, b) -> (map.get(b) - map.get(a)));

        int sum = (maxSum - 1) * (n + 1) + x;
        int re = sum;
        for (char key : keyList) {
            sum -= map.get(key);
            if (sum <= 0) {
                return tasks.length;
            }
        }
        return re;
    }

    public static void main(String[] args) {
        Solution0909 solution = new Solution0909();
//        char[] tasks = new char[]{'A', 'A', 'A', 'B', 'B', 'B', 'C', 'C', 'C', 'C', 'D', 'D', 'D'};
        char[] tasks = new char[]{'A', 'A', 'A', 'B', 'B', 'B', 'C', 'C', 'C', 'D', 'D', 'E'};
        System.out.println(solution.leastInterval(tasks, 2));
    }
}