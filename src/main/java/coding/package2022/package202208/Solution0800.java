package coding.package2022.package202208;

import java.util.*;

class Solution0800 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> combine = new ArrayList<Integer>();
        dfs(candidates, target, ans, combine, 0);
        return ans;
    }

    private void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int i) {
        if (i == candidates.length) {
            return;
        }
        if (target <= 0) {
//            刚开始我也懵了，但我想了想，如果加的是combine，而combine的地址一直是没有变化的
//        ，只是里面元素在变化，那么最后ans里面的所有combine，都会指向同一个地址，也就是回溯结束时的combine状态。
            ans.add(new ArrayList<Integer>(combine));
            return;
        }

        dfs(candidates, target, ans, combine, i + 1);
        //用自己
        if (target - candidates[i] >= 0) {
            combine.add(candidates[i]);
            dfs(candidates, target - candidates[i], ans, combine, i);
            //回溯操作
            combine.remove(combine.size() - 1);
        }

    }

    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> combine = new ArrayList<Integer>();
        boolean[] vis = new boolean[nums.length];
        dfs(nums, vis, ans, combine, 0);
        return ans;
    }

    private void dfs(int[] nums, boolean[] vis, List<List<Integer>> ans, List<Integer> combine, int i) {
        if (i == nums.length) {
            ans.add(new ArrayList<Integer>(combine));
            return;
        }
        for (int j = 0; j < nums.length; j++) {
            if (!vis[j]) {
                combine.add(nums[j]);
                vis[j] = true;
                dfs(nums, vis, ans, combine, i + 1);


                //回溯操作
                vis[j] = false;
                combine.remove(combine.size() - 1);
            }
        }

    }

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < asteroids.length; i++) {
            if (stack.isEmpty()) {
                stack.add(asteroids[i]);
                continue;
            }
            int top = stack.peek();
            int val = asteroids[i];

            if ((top > 0 && val > 0) || (top < 0 && val < 0)) {
                stack.add(val);
                continue;
            }
            int a = Math.abs(top);
            int b = Math.abs(val);
            if (a < b) {
                stack.pop();
                i--;
            }
        }
        int[] re = new int[stack.size()];
        int i = 0;
        for (Integer e : stack) {
            re[i] = e;
            i++;
        }
        return re;
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {

        int left = newInterval[0];
        int right = newInterval[1];

        if (intervals.length == 0) {
            return new int[][]{{left, right}};
        }

        int i = 0;
        int j = 0;
        int[][] re = new int[intervals.length + 1][2];
        boolean flag = false;
        for (j = 0; j < intervals.length; j++) {
            int[] array = intervals[j];
            //当前区间在 [l,r] 左边；
            if (array[1] < left) {
                re[i] = array;
                i++;
                //当前区间在 [l,r] 右边；
            } else if (array[0] > right) {
                re[i] = new int[]{left, right};
                i++;
                break;
                //当前区间在 [l,r] 中间；
            } else if (array[0] >= left && array[1] <= right) {
                // [l,r] 在 当前区间在 中间；
            } else if (array[0] < left && array[1] > right) {
                break;
                //有重合，更新l,r
            } else {
                left = Math.min(array[0], left);
                right = Math.max(array[1], right);
            }
        }
        if (j == intervals.length) {
            re[i] = new int[]{left, right};
            i++;
        }
        while (j < intervals.length) {
            re[i] = intervals[j];
            j++;
            i++;
        }
        // return re;
        return Arrays.copyOf(re, i);
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (e1,e2)->(e1[0]==e2[0]?(e1[1]-e2[1]):(e1[0]-e2[0])));
        int left = intervals[0][0];
        int right = intervals[0][1];

        int i = 0;
        int j;
        int[][] re = new int[intervals.length][2];
        for (j = 1; j < intervals.length; j++) {
            int[] array = intervals[j];
            //当前区间在 [l,r] 左边；
            if (array[1] < left) {
                re[i] = array;
                i++;
                //当前区间在 [l,r] 右边；
            } else if (array[0] > right) {
                re[i] = new int[]{left, right};
                i++;
                left = array[0];
                right = array[1];
                //当前区间在 [l,r] 中间；
            } else if (array[0] >= left && array[1] <= right) {
                // [l,r] 在 当前区间在 中间；
                //理论来说。不存在，
            } else if (array[0] < left && array[1] > right) {
                break;
                //有重合，更新l,r
            } else {
                left = Math.min(array[0], left);
                right = Math.max(array[1], right);
            }
            // System.out.println(left + " :" + right);
        }
        re[i] = new int[]{left, right};
        i++;
        // return re;
        return Arrays.copyOf(re, i);
    }

    public static void main(String[] args) {
        int[] a = new int[]{5, 10, -5};
        int[][] intervals = new int[][]{{1, 3}, {6, 9}};
        int[] newIntervals = new int[]{2, 5};

        Solution0800 solution = new Solution0800();
        solution.insert(intervals, newIntervals);
        solution.asteroidCollision(a);
    }
}