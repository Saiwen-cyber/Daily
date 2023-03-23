package coding.package202210;

import coding.TreeNode;

import java.util.*;

/**
 * @author ASUS
 */
public class Solution1018 {

    public int atMostNGivenDigitSet(String[] digits, int n) {
        int sum = 0;
        if (n < 10) {
            for (String digit : digits) {
                if (Integer.parseInt(digit) <= n) {
                    sum++;
                }
            }
            return sum;
        }
        Integer[] nums = calculateDigit(n);
        int len = digits.length;
        int digitLen = nums.length;

        int[] tmp = new int[digitLen];
        for (int i = 1; i < digitLen; i++) {
            if (i == 1) {
                tmp[i] = 1;
                sum += tmp[i];
                continue;
            }
            tmp[i] = tmp[i - 1] * len;
            sum += tmp[i];
        }
        sum = sum * len;
        //当前一颗树的叶子长度
        int fullTree = tmp[digitLen - 1] * len;
        //n的第i位
        int i = 0;
        boolean hasEqual = false;
        while (i < nums.length && fullTree > 0) {
            boolean isLarger = true;
            for (int j = 0; j < len; j++) {
                int num = Integer.parseInt(digits[j]);
                //digits[i] 小于 n的当前数位
                if (num < nums[i]) {
                    continue;
                } else if (num > nums[i]) {
                    //还应该加上这一层
                    sum += j * fullTree;
                    return sum;
                } else {
                    sum += j * fullTree;
                    fullTree = fullTree / len;
                    i++;
                    if (i == nums.length) {
                        hasEqual = true;
                    }
                    isLarger = false;
                    break;
                }
            }
            if (isLarger) {
                return sum + fullTree * len;
            }
        }
        return hasEqual ? sum + 1 : sum;
    }

    public Integer[] calculateDigit(int n) {
        List<Integer> nums = new ArrayList<Integer>();
        while (n != 0) {
            int tmp = n % 10;
            n = n / 10;
            nums.add(tmp);
        }
        Collections.reverse(nums);
        Integer[] ans = new Integer[nums.size()];
        return nums.toArray(ans);
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        List<TreeNode> nodeList = new ArrayList<>();
        nodeList.add(root);
        while (!nodeList.isEmpty()) {
            // List<TreeNode> nodeList = new ArrayList<>();
            List<TreeNode> tmp = new ArrayList<>();
            List<Integer> list = new ArrayList<>();
            for (TreeNode node : nodeList) {
                list.add(node.val);
                if (node.left != null) {
                    tmp.add(node.left);
                }
                if (node.right != null) {
                    tmp.add(node.right);
                }
            }
            nodeList = tmp;
            ans.add(0,list);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] n = new int[][]{{1, 2}, {1, 3}, {2, 4}};
        Solution1018 so = new Solution1018();
//        int re = so.atMostNGivenDigitSet(new String[]{"1", "4", "9"}, 444);
//        int re = so.(new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4});
//        int re = so.atMostNGivenDigitSet(new String[]{"1", "3", "5","7"}, 100);
        String[] strs = new String[]{"1", "4", "5", "6", "7", "8"};

        int re = so.atMostNGivenDigitSet(strs, 52);
//        int re = so.atMostNGivenDigitSet(new String[]{"7"}, 8);


        System.out.println(re);
    }
}