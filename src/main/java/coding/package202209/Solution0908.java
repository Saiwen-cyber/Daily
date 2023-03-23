package coding.package202209;

import coding.TreeNode;

import java.util.Queue;
import java.util.Stack;

/**
 * @author fang
 */
public class Solution0908 {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        return findTargetOnlyRoot(root, targetSum) + pathSum(root.left, targetSum) + pathSum(root.right, targetSum);
    }

    public int findTargetOnlyRoot(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        int count = root.val == targetSum ? 1 : 0;
        return count + findTargetOnlyRoot(root.left, targetSum - root.val) + findTargetOnlyRoot(root.right, targetSum - root.val);
    }

    public int[] constructArray(int n, int k) {
        int start = 1;
        int end = n;
        int[] re = new int[n];
        int index = 0;
        for (int i = 1; i <= n - k; i++) {
            re[index++] = i;
        }
        for (int i = n - k, j = n; i <= j; i++, j--) {
            re[index++] = i;
            if (i != j) {
                re[index++] = j;
            }
        }
        return re;
    }
}
