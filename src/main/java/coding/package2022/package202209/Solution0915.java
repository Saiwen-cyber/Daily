package coding.package2022.package202209;

import coding.TreeNode;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Solution0915 {
    public int bulbSwitch(int n) {
        int light = 0;
        int dark = n;
        boolean[] bulb = new boolean[n + 1];
        int re = 0;
        for (long i = 1; i <= n; i++) {
            long j = 0;
            while (j <= n) {
                if (j == 0) {
                    j = j + i;
                    continue;
                }
                if (bulb[(int) j]) {
                    light--;
                    dark++;
                } else {
                    light++;
                    dark--;
                }
                bulb[(int) j] = !bulb[(int) j];
                j = j + i;
            }
        }
        return light;
    }

    public int bulbSwitch2(int n) {
        return (int) Math.sqrt(n * 1.0);
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        return bfs(queue, root);
    }

    int bfs(Queue<TreeNode> queue, TreeNode root) {
        Map<TreeNode, Integer> map = new HashMap<>();
        queue.add(root);
        map.put(root, 1);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int height = map.get(node);
            if (node.left == null && node.right == null) {
                return height;
            }
            if (node.left != null) {
                queue.add(node.left);
                map.put(node.left, height + 1);
            }

            if (node.right != null) {
                queue.add(node.right);
                map.put(node.right, height + 1);
            }
        }
        return 1;
    }

    int bfsFindMax(Queue<TreeNode> queue, TreeNode root) {
        Map<TreeNode, Integer> map = new HashMap<>();
        queue.add(root);
        map.put(root, 1);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int height = map.get(node);
            if (node.left != null) {
                queue.add(node.left);
                map.put(node.left, height + 1);
            }

            if (node.right != null) {
                queue.add(node.right);
                map.put(node.right, height + 1);
            }
        }
        return 1;
    }
    public static void main(String[] args) {
        Queue<Integer> QU = new ArrayDeque<>();
//        System.out.println(Integer);
//        System.out.println(Math.pow(10,9) * 2> Integer.MAX_VALUE);
    }
}