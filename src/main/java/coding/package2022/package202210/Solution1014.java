package coding.package2022.package202210;


import coding.TreeNode;

import java.util.*;

public class Solution1014 {
    Queue a = new ArrayDeque();
    private static final String PUSH = "PUSH";
    private static final String POP = "POP";
    public List<TreeNode> generateTrees(int n) {
        return generate(1, n);
    }

    //返回从 i 到 j 生成的子树
    public List<TreeNode> generate(int i, int j) {
        List<TreeNode> list = new ArrayList<>();
        if (i > j) {
            list.add(null);
            return list;
        }

        for (int k = i; k <= j; k++) {
            //起点不能是1，粗心，起点是i
            List<TreeNode> subLeft = generate(i, k - 1);
            List<TreeNode> subRight = generate(k + 1, j);
            for (TreeNode left : subLeft) {
                for (TreeNode right : subRight) {
                    TreeNode root = new TreeNode(k);
                    root.left = left;
                    root.right = right;
                    list.add(root);
                }
            }
        }
        return list;
    }

    List<Integer> nums = new ArrayList<>();
    Map<Integer, TreeNode> map = new HashMap<>();

    public void recoverTree(TreeNode root) {
        inOrder(root);
        //需要交换的下标
        List<Integer> swap = new ArrayList<>();
        for (int i = 0; i < nums.size() - 1; i++) {
            if (nums.get(i) > nums.get(i + 1)) {
                if (swap.isEmpty()) {
                    swap.add(i);
                } else {
                    swap.add(i + 1);
                }
            }
        }
        swap(swap);
    }

    private void swap(List<Integer> swap) {
        int a = nums.get(swap.get(0));
        int b = swap.size() == 1 ? nums.get(swap.get(0) + 1) : nums.get(swap.get(1));
        TreeNode nodeA = map.get(a);
        TreeNode nodeB = map.get(b);

        nodeA.val = b;
        nodeB.val = a;

        map.put(a, nodeA);
        map.put(b, nodeB);
    }

    public void inOrder(TreeNode tree) {
        if (tree == null) {
            return;
        }
        inOrder(tree.left);
        nums.add(tree.val);
        map.put(tree.val, tree);
        inOrder(tree.right);
    }

    public static void main(String[] args) {
        Solution1014 so = new Solution1014();
        so.generateTrees(3);
    }
}