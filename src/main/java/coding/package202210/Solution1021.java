package coding.package202210;

import coding.TreeNode;

import java.util.*;

class StockNode {
    int val;
    StockNode next;

    public StockNode(int val) {
        this.val = val;
    }
}

class StockSpanner {
    List<Integer> numList;
    Stack<Integer> s = new Stack<>();


    public StockSpanner() {
        numList = new ArrayList<>();
        numList.add(Integer.MAX_VALUE);
        s.push(0);
    }

    public int next(int price) {
        numList.add(price);
        int res = 0;
        while (numList.get(s.peek()) <= price) {
            s.pop();
        }
        res = numList.size() - 1 - s.peek();
        s.push(numList.size() - 1);
        return res;
    }
}

/**
 * @author ASUS
 */
public class Solution1021 {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        findPath(root, targetSum, new ArrayList<>(), ans);
        return ans;
    }

    public void findPath(TreeNode root, int targetSum, List<Integer> list,
                         List<List<Integer>> ans) {
        if (root == null) {
            if (targetSum == 0) {
                ans.add(list);
            }
            return;
        }
        list.add(root.val);
        findPath(root.left, targetSum - root.val, list, ans);
        findPath(root.right, targetSum - root.val, list, ans);
        list.remove(0);
    }

    public static void main(String[] args) {
        StockSpanner stockSpanner = new StockSpanner();
        System.out.println(stockSpanner.next(100));
        System.out.println(stockSpanner.next(80));
        System.out.println(stockSpanner.next(60));
        System.out.println(stockSpanner.next(70));
        System.out.println(stockSpanner.next(60));
        System.out.println(stockSpanner.next(75));
        System.out.println(stockSpanner.next(85));
    }
}