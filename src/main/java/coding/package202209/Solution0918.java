package coding.package202209;

import coding.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ASUS
 */
public class Solution0918 {
    public int smallestEvenMultiple(int n) {
        return n * 2 / findMax(n, 2);
    }

    public int findMax(int n, int m) {
        for (int i = n; i > 0; i--) {
            if (n % i == 0 && m % i == 0) {
                return i;
            }
        }
        return 1;
    }


    public TreeNode reverseOddLevels(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        exec(list, 0);
        return root;
    }

    public void exec(List<TreeNode> nodeList, int height) {
        int len = nodeList.size() - 1;
        List<TreeNode> subTreeNode = new ArrayList<>();
        boolean isLeaf = nodeList.get(0).left == null;
        boolean isChange = height % 2 != 0;
        for (int i = 0; i < nodeList.size(); i++) {
            TreeNode par = nodeList.get(i);
            if (!isLeaf) {
                subTreeNode.add(par.left);
                subTreeNode.add(par.right);
            }
        }
        if (isChange) {
            for (int i = 0; i < nodeList.size() / 2; i++) {
                TreeNode node1 = nodeList.get(i);
                TreeNode node2 = nodeList.get(len - i);
                int tmp = node1.val;
                node1.val = node2.val;
                node2.val = tmp;
            }
        }

        if (!isLeaf) {
            exec(subTreeNode, height + 1);
        }
    }

    public int longestContinuousSubstring(String s) {
        int len = s.length();
        int re = 0;
        int tmp = 1;
        for (int i = 1; i < len; i++) {
            char pre = s.charAt(i - 1);
            char cur = s.charAt(i);
            if (cur - pre != 1) {
                re = Math.max(re, tmp);
                tmp = 0;
            }
            tmp++;
        }
        return Math.max(re, tmp);
    }


    public int[] sumPrefixScores(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        int len = words.length;
        int[] re = new int[len];
        for (int i = 0; i < len; i++) {
            int sum = 0;
            int tail = 0;
            StringBuilder sb = new StringBuilder();
            while (tail < words[i].length()) {
                char ch = words[i].charAt(tail);
                sb.append(ch);
                if (map.containsKey(sb.toString())) {
                    sum += map.get(sb.toString());
                } else {
                    int val = 0;
                    for (int j = i; j < len; j++) {
                        if (words[j].startsWith(sb.toString())) {
                            val++;
                        }
                    }
                    map.put(sb.toString(), val);
                    sum += val;
                }
                tail++;
            }
            re[i] = sum;
        }
        return re;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        TreeNode left = new TreeNode(13);
        TreeNode right = new TreeNode(11);
        root.left = left;
        root.right = right;
        Solution0918 so = new Solution0918();
        int re = so.longestContinuousSubstring("abcde");
        System.out.println(re);
        so.reverseOddLevels(root);
    }
}