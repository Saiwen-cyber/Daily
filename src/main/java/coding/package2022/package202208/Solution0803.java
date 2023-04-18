package coding.package2022.package202208;

import java.util.*;

/**
 * @author fang
 */
public class Solution0803 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Solution {
        public int diameterOfBinaryTree(TreeNode root) {
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.add(root);
            int re = 0;
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }
                re = Math.max(re, findMaxD(node));
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            return re;
        }

        public int findMaxD(TreeNode root) {
            return depth(root);
        }

        public int depth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return depth(root.left) + depth(root.right) + 1;
        }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        int mid = nums.length / 2;
        TreeNode root = new TreeNode(nums[mid]);

        int[] arr1 = Arrays.copyOfRange(nums, 0, mid);
        int[] arr2 = Arrays.copyOfRange(nums, mid + 1, nums.length);
        TreeNode left = sortedArrayToBST(arr1);
        TreeNode right = sortedArrayToBST(arr2);

        root.left = left;
        root.right = right;
        return root;
    }

    static class MyQueue {
        Stack<Integer> stackA = new Stack<>();
        Stack<Integer> stackB = new Stack<>();

        public MyQueue() {
            stackA = new Stack<>();
            stackB = new Stack<>();
        }

        public void push(int x) {
            transfer(stackA, stackB, x);
        }

        private void transfer(Stack<Integer> data, Stack<Integer> empty, int x) {
            while (!data.isEmpty()) {
                empty.add(data.pop());
            }
            data.add(x);
            while (!empty.isEmpty()) {
                data.add(empty.pop());
            }
        }

        public int pop() {
            return stackA.pop();
        }

        public int peek() {
            return stackA.peek();
        }

        public boolean empty() {
            return stackA.isEmpty() && stackB.isEmpty();
        }
    }

    public int findCircleNum(int[][] isConnected) {
        int m = isConnected.length;
        int n = isConnected[0].length;
        int re = 0;
        boolean[] isLink = new boolean[m];

        for (int i = 0; i < m; i++) {
            if (isLink[i]) {
                continue;
            }
            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(i);
            while (!queue.isEmpty()) {
                Integer key = queue.poll();
                for (int j = 0; j < n; j++) {
                    if (isConnected[key][j] == 1 && j != key && !isLink[j]) {
                        isLink[j] = true;
                        queue.add(j);

                    }
                }
                if (isLink[key]) {
                    continue;
                }
                isLink[key] = true;
                re++;

            }
        }
        return re;
    }

    public int removeStones(int[][] stones) {
        int len = stones.length;
        int re = 0;
        boolean[][] isLink = new boolean[len][len];

        for (int i = 0; i < len; i++) {
            int x = stones[i][0];
            int y = stones[i][1];
            if (isLink[x][y]) {
                continue;
            }

            Queue<Integer[]> queue = new ArrayDeque<>();
            queue.add(new Integer[]{x, y});
            while (!queue.isEmpty()) {
                Integer[] nums = queue.poll();
                for (int[] stone : stones) {
                    if (isConnected(stone, nums) && !isLink[stone[0]][stone[1]]) {
                        isLink[stone[0]][stone[1]] = true;
                        queue.add(new Integer[]{stone[0], stone[1]});
                    }
                }
                if (isLink[nums[0]][nums[1]]) {
                    continue;
                }
                isLink[x][y] = true;
                re++;
            }
        }
        return re;
    }

    private boolean isConnected(int[] stone, Integer[] nums) {
        boolean isSame = stone[0] == nums[0] && stone[1] == nums[1];
        if (stone[0] == nums[0] || stone[1] == nums[1]) {
            return !isSame;
        }
        return false;
    }
    public static void main(String[] args) {

        Solution0803 solution0803 = new Solution0803();
        int[][] isConn = new int[][]{{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}};
        solution0803.removeStones(isConn);

        MyQueue obj = new MyQueue();
        MyQueue myQueue = new MyQueue();
        myQueue.push(1); // queue is: [1]
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        myQueue.peek(); // return 1
        myQueue.pop(); // return 1, queue is [2]
        myQueue.empty(); // return false
    }
}
