package coding.package202210;

import java.util.*;

public class Solution1029 {
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfs(candidates, target, ans, list, 0);
        return ans;
    }

    private void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> list, int i) {
        if (target < 0 || i == candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }
        //二叉 选与不选
        //如果差值target 大于 candidate，那么有选的必要
        if (target - candidates[i] > 0) {
            list.add(candidates[i]);
            dfs(candidates, target - candidates[i], ans, list, i);
            list.remove(list.size() - 1);
        }
        //不选
        dfs(candidates, target, ans, list, i + 1);
    }

    //先用回溯买 特价，有合适的就可
    //再吧剩下的单买
    List<Integer> list = new ArrayList<>();

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int sum = 0;
        for (int i = 0; i < needs.size(); i++) {
            sum = sum + needs.get(i) * price.get(i);
        }
        backTrack(price, special, needs, 0, 0, sum);
        return 0;
    }

    public void backTrack(List<Integer> price, List<List<Integer>> special, List<Integer> needs,
                          int idx, int sumS, int sumN) {
        int n = needs.size();
        for (; idx < special.size(); idx++) {
            List<Integer> item = special.get(idx);
            if (isBelowZero(needs, item)) {
                list.add(sumN + sumS);
                continue;
            }
            for (int i = 0; i < needs.size(); i++) {
                needs.set(i, needs.get(i) - item.get(i));
                //sub the price that special item count
                sumN -= price.get(i) * item.get(i);
            }
            //bought idx-th special bag
            backTrack(price, special, needs, idx, sumS + item.get(n), sumN);
            //rollback
            for (int i = 0; i < needs.size(); i++) {
                needs.set(i, needs.get(i) + item.get(i));
                sumN += price.get(i) * item.get(i);
            }
        }
    }

    private boolean isBelowZero(List<Integer> needs, List<Integer> special) {
        for (int i = 0; i < needs.size(); i++) {
            if (needs.get(i) - special.get(i) < 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        price = [2,5], special = [[3,0,5],[1,2,10]], needs = [3,2]
        List<Integer> price = new ArrayList<>();
        price.add(2);
        price.add(5);
        List<Integer> needs = new ArrayList<>();
        needs.add(3);
        needs.add(2);
        List<List<Integer>> special = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        list1.add(3);
        list1.add(0);
        list1.add(5);

        list2.add(1);
        list2.add(2);
        list2.add(10);
        special.add(list1);
        special.add(list2);

        Solution1029 so = new Solution1029();
        so.shoppingOffers(price, special, needs);
    }

/*    private boolean isBeyond(List<Integer> needs, List<Integer> item) {
        for (int i = 0; i < needs.size(); i++) {
            if (item.get(i) > needs.get(i)) {
                return false;
            }
        }
        return true;
    }*/
}