package coding.package202210;

import coding.TreeNode;

import java.util.*;

class Data {
    TreeNode node;
    int height;

    Data(int height, TreeNode node) {
        this.height = height;
        this.node = node;
    }
}

public class Solution1015 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        List<TreeNode> list = new ArrayList<>();
        List<Integer> val = new ArrayList<>();
        int curHeight = 0;

        list.add(root);
        val.add(root.val);


        while (true) {
            if (list.isEmpty()) {
                return ans;
            } else {
                if (curHeight % 2 != 0) {
                    Collections.reverse(val);
                }
                ans.add(val);
            }
            List<TreeNode> tmp = new ArrayList<>();
            List<Integer> tmpVal = new ArrayList<>();
            for (TreeNode node : list) {
                if (node.left != null) {
                    tmp.add(node.left);
                    tmpVal.add(node.left.val);
                }
                if (node.right != null) {
                    tmp.add(node.right);
                    tmpVal.add(node.right.val);
                }
            }
            curHeight++;
            list = tmp;
            val = tmpVal;
        }
    }

    public static void main(String[] args) {
        TreeNode vr = new TreeNode(20);
        vr.left = new TreeNode(15);
        vr.right = new TreeNode(7);
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = vr;

        Solution1015 so = new Solution1015();
        so.zigzagLevelOrder(root);
    }
}