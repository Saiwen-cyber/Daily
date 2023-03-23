package coding.package202206;

import java.util.*;

/**
 * @author fang
 */
public class Solution {
    public int minimumaTimeRequired(int[] jobs, int k) {
        Arrays.sort(jobs);
        int len = jobs.length;
        int[] copy_jobs = new int[len];
        int j = 0, sum = 0;
        for (int i = len - 1; i >= 0; i--) {
            copy_jobs[j] = jobs[i];
            j++;
            sum = sum + jobs[i];
        }
        int left = sum / k;
        int right = sum;
        while (left < right) {
            int[] workerTime = new int[len];
            Arrays.fill(workerTime, 0);
            if (check(copy_jobs, workerTime, left, 0)) {
                return left;
            }
            left++;
        }
        return left;
    }

    //完成工作最短时间 的理想状态 是 均分
    //完成工作最短时间 的最坏状态 是 所有工作时间之和。
    //采取二分法 不断找到可行方案
    public boolean check(int[] jobs, int[] workerTime, int curLimit, int index) {
        if (index == jobs.length) {
            return true;
        }
        int curJob = jobs[index];
        for (int i = 0; i < jobs.length; i++) {
            if (workerTime[i] + curJob < curLimit) {
                workerTime[i] = workerTime[i] + curJob;
                //分配下一个工作
                if (check(jobs, workerTime, curLimit, index + 1)) {
                    //将当前这件工作分配给 当前这个工人是ok的
                    return true;
                }
                workerTime[i] = workerTime[i] - curJob;
            }
        }
        return false;
    }

    public int[] numberOfPairs(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int key = nums[i];
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        int cp = 0;
        int dog = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int val = entry.getValue();
            cp = cp + val / 2;
            dog = dog + val % 2;
        }
        return new int[]{cp, dog};
    }

    public int maximumSum(int[] nums) {
        int re = -1;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            int digitSum = findDigitSum(val);
            List<Integer> list = map.getOrDefault(digitSum, new ArrayList<>());
            list.add(val);
            map.put(digitSum, list);
        }

        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> list = entry.getValue();
            if (list.size() > 1) {
                list.sort((x, y) -> y - x);
                re = Math.max(re, list.size());
            }
        }
        return re;
    }

    public int findDigitSum(int num) {
        int re = 0;
        while (num != 0) {
            re = re + num % 10;
            num = num / 10;
        }
        return re;
    }

    public int minOperations(int[] nums, int[] numsDivide) {
        Map<Integer,Integer> map = new HashMap<>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length;i++) {
            int val = nums[i];
            if(isDiv(numsDivide,val)) {
                return i;
            }else{
                while(i + 1< nums.length && nums[i] == val && nums[i + 1] == val) {
                    i++;
                }
            }
        }
        return -1;
    }

    public boolean isDiv(int[] numsDivide,int num) {
        boolean re = true;
        for(int i = 0 ; i < numsDivide.length;i ++) {
            re = re && numsDivide[i] % num == 0;
        }
        return re;
    }

    public static void main(String[] args) {
        int []nums = new int[]{2,3,2,4,3}, numsDivide = new int[]{9,6,9,3,15};
        new Solution().minOperations(nums,numsDivide);
    }
}