package coding.package202210;

import coding.TreeNode;

/**
 * @author ASUS
 */
public class Solution1019 {
    TreeNode leftNode;

    public TreeNode convertBiNode(TreeNode root) {
        inOrder(root);
        return leftNode;
    }

    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        root.right = leftNode;
        leftNode = root;
        inOrder(root.right);
        root.left = null;
    }

    public int countNodes(TreeNode root) {
        int leftH = -1;
        if (root == null) {
            return 0;
        }
        TreeNode node = root;
        while (node != null) {
            leftH++;
            node = node.left;
        }
        //高度为h的完全二叉树总节点个数 在 2^(h) ~ 2^(h+1)-1;
        int low = 1 << leftH;
        int high = (1 << (leftH + 1)) - 1;
        while (low < high) {
            int mid = low + ((high - low + 1) >> 1);
            //节点存在
            if (existed(root, mid, leftH)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private boolean existed(TreeNode root, int mid, int leftH) {
        TreeNode tmp = root;
        //10000...
        int comp = 1 << (leftH - 1);
        while (comp != 0 && tmp != null) {
            //当前bit为0
            if ((comp & mid) == 0) {
                tmp = tmp.left;
            } else {
                tmp = tmp.right;
            }
            //右移1位
            comp >>= 1;
        }
        return tmp != null;
    }

    public static void main(String[] args) {
        TreeNode node2 = new TreeNode(2);
        node2.left = new TreeNode(4);
        node2.right = new TreeNode(5);


        TreeNode node3 = new TreeNode(3);
        node3.left = new TreeNode(6);

        TreeNode root = new TreeNode(1);
        root.left = node2;
        root.right = node3;

        Solution1019 so = new Solution1019();
        so.countNodes(root);
    }


    private static TreeNode getTreeNodePre() {
        TreeNode node2 = new TreeNode(2);
        node2.left = new TreeNode(3);
        node2.right = new TreeNode(4);

        TreeNode node5 = new TreeNode(5);
        node5.right = new TreeNode(6);

        TreeNode root = new TreeNode(1);
        root.left = node2;
        root.right = node5;
        return root;
    }

    private static TreeNode getTreeNodeIn() {
        TreeNode node1 = new TreeNode(1);
        node1.left = new TreeNode(0);
        TreeNode node2 = new TreeNode(2);
        node2.left = node1;
        node2.right = new TreeNode(3);

        TreeNode node5 = new TreeNode(5);
        node5.right = new TreeNode(6);

        TreeNode root = new TreeNode(4);
        root.left = node2;
        root.right = node5;
        return root;
    }
}