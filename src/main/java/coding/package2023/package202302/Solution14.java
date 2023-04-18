package coding.package2023.package202302;

import coding.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author Annie Fang
 * @create 2023/2/14 21:27
 */
public class Solution14 {
    public static void main(String[] args) {
        Solution14 solution = new Solution14();
        String a = "1010";
        String b = "1011";
        String ans = "10101";

        String a1 = "11";
        String b1 = "1";
        String ans1 = "100";
        System.out.println(solution.addBinary(a, b).equals(ans));
        System.out.println(solution.addBinary(a1, b1).equals(ans1));
    }

    public String addBinary(String a, String b) {
        int carry = 0;
        int i, j;
        if (a.length() < b.length()) {
            return addBinary(b, a);
        }
        StringBuilder sb = new StringBuilder();
        for (i = a.length() - 1, j = b.length() - 1; i >= 0 && j >= 0; i--, j--) {
            int numA = a.charAt(i) - '0';
            int numB = b.charAt(j) - '0';
            int tmpSum = carry + numA + numB;
            carry = tmpSum / 2;
            sb.insert(0, tmpSum % 2);
        }

        while (i >= 0) {
            int numA = a.charAt(i) - '0';
            int tmpSum = carry + numA;
            carry = tmpSum / 2;
            sb.insert(0, tmpSum % 2);
            i--;
        }
        if (carry != 0) {
            sb.insert(0, carry);
        }
        return sb.toString();
    }


    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> leftQueue = new ArrayDeque<>();
        Queue<TreeNode> rightQueue = new ArrayDeque<>();

        leftQueue.add(root.left);
        rightQueue.add(root.right);
        while (!leftQueue.isEmpty() && !rightQueue.isEmpty()) {
            TreeNode nodeL = leftQueue.remove();
            TreeNode nodeR = rightQueue.remove();
            if (nodeL.val == -101 && nodeR.val == -101) {
                continue;
            }
            if (nodeL.val == -101 || nodeR.val == -101) {
                return false;
            }
            if (nodeL.val != nodeR.val) {
                return false;
            }

            leftQueue.add(nodeL.left == null ? new TreeNode(-101) : nodeL.left);
            leftQueue.add(nodeL.right == null ? new TreeNode(-101) : nodeL.right);

            rightQueue.add(nodeR.right == null ? new TreeNode(-101) : nodeR.right);
            rightQueue.add(nodeR.left == null ? new TreeNode(-101) : nodeR.left);
        }
        return true;
    }
}
