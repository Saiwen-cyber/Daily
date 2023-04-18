package coding.package2022.package202210;

import coding.TreeNode;

import java.util.*;

public class Solution1028 {
    public int shortestSubarray(int[] nums, int k) {
        int len = nums.length;
        long[] preSum = new long[len + 1];
        preSum[0] = 0;
        //前缀和
        for (int i = 1; i <= nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        //存储i以前的下标
        //preSum形成 单调递增队列，且添加进去i为队列的 preSum值 最大
        //双端队列
        Deque<Integer> queue = new ArrayDeque<>();
        int res = Integer.MAX_VALUE;
        //preSum[right] - preSum(left)
        //[left, right);
        for (int i = 0; i <= nums.length; i++) {

            //队列前端 为 preSum最小值，
            //此时preSum[i] - preSum[j] 最大
            while (!queue.isEmpty() && preSum[i] - preSum[queue.peekFirst()] >= k) {
                res = Math.min(res, i - queue.pollFirst());
            }
            //poll比preSum[i]大的
            //因为 假设，x > i > j,preSum[i] <= preSum[j] ,且有preSum[x] - preSum[j] >= k,
            //那么一定有preSum[x] - preSum[i] >= k
            //那么preSum[j] 没用了
            while (!queue.isEmpty() && preSum[i] <= preSum[queue.peekLast()]) {
                queue.pollLast();
            }
            queue.offerLast(i);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public int sumSubarrayMins(int[] arr) {
        int len = arr.length;
        int sum = 0;
        int[] left = new int[len];
        int[] right = new int[len];
        // sum = (tmp + sum) % 1000000007;

        Stack<Integer> stack = new Stack<>();
        for (int j = 0; j < len; j++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[j]) {
                stack.pop();
            }
            left[j] = stack.isEmpty() ? j : stack.peek();
            System.out.print(left[j] + " ");
            stack.add(j);
        }
        System.out.println();
        stack = new Stack<>();
        for (int j = len - 1; j >= 0; j--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[j]) {
                stack.pop();
            }
            right[j] = stack.isEmpty() ? j : stack.peek();
            System.out.print(right[j] + " ");
            stack.add(j);
        }
        // System.out.println();

        return sum;
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        System.out.println(depth(root.left));
        System.out.println(depth(root.right));

        return Math.abs(depth(root.left) - depth(root.right)) <= 1;
    }

    public int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int h1 = depth(node.left);
        int h2 = depth(node.right);
        return Math.max(h1, h2) + 1;
    }

    public static void main(String[] args) {
        Solution1028 so = new Solution1028();
        int[] nums;
        int res;

        nums = new int[]{-28, 81, -20, 28, -29};
        res = so.shortestSubarray(nums, 89);
        System.out.println(res);
    }
}