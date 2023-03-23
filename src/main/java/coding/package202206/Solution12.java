package coding.package202206;

import java.util.*;

public class Solution12 {
    private Object DescComparator;

    public double calculateTax(int[][] brackets, int income) {
        int len = brackets.length;
        double re = 0;
        // int remain = income;
        int i = 0;
        int pre = 0;
        while (i < len) {
            int val = brackets[i][0];
            double percent = brackets[i][1] * 1.0 / 100;
            if (income - val >= 0) {
                re = re + (val - pre) * percent;
                pre = val;
                i++;
                continue;
            }
            re = re + (income - pre) * percent;
            i += 1;
            break;
        }
        return re;
    }

    public int distributeCookies(int[] cookies, int k) {
        int len = cookies.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum = sum + cookies[i];
        }
        return (int) Math.ceil(sum * 1.0 / k);
    }

    public int minimumaTimeRequired(int[] jobs, int k) {
        Arrays.sort(jobs);
        int len = jobs.length;
        int[] copy_jobs = new int[len];
        int j = 0;
        int sum = 0;
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

    public static void main(String[] args) {
        Solution12 solution = new Solution12();
        int[][] brackets = new int[][]{{3, 50}, {7, 10}, {12, 25}};
        int income = 10;
        double re = solution.calculateTax(brackets, income);
        System.out.println(re);
    }
}