package coding.package2022.package202211;

import java.util.*;

public class Solution1106 {
    public long maximumSubarraySum(int[] nums, int k) {
        long res = 0L;
        int len = nums.length;
        int left = 0;
        int right = 0;
        long[] prefixSum = calPrefixSum(nums);
        Map<Integer, Integer> map = new HashMap<>();
        //[left, right]
        while (right < len) {
            if (map.containsKey(nums[right])) {
                left = Math.max(left, map.get(nums[right] + 1));
            }
            if (right - left + 1 >= k) {
                left = right - k + 1;
                //refresh [left, right] 子数组之和
                res = Math.max(res, prefixSum[right + 1] - prefixSum[left]);
            }
            map.put(nums[right], right);
            right++;

        }
        return res;
    }

    private long[] calPrefixSum(int[] nums) {
        long[] preSum = new long[nums.length + 1];
        preSum[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        return preSum;
    }

    public long totalCost(int[] costs, int k, int candidates) {
        int len = costs.length;
        int right = 0;
        int left = len - 1;

        int round = 0;
        long sum = 0;
        PriorityQueue<Integer> pre = new PriorityQueue<>();
        PriorityQueue<Integer> suf = new PriorityQueue<>();
        for (int i = 0; i < candidates; i++) {
            pre.add(costs[right++]);
        }
        for (int i = 0; i < candidates && left >= right; i++) {
            suf.add(costs[left--]);
        }
        /*[0,right] [left,len] */
        while (round < k && left >= right && !pre.isEmpty() && !suf.isEmpty()) {
            int a = pre.peek();
            int b = suf.peek();
            if (a <= b) {
                sum += pre.remove();
                pre.add(costs[right++]);
            } else {
                sum += suf.remove();
                suf.add(costs[left--]);
            }
            round++;
        }

        while (round < k) {
            int a = pre.isEmpty() ? Integer.MAX_VALUE : pre.peek();
            int b = suf.isEmpty() ? Integer.MAX_VALUE : suf.peek();
            if (a <= b) {
                sum += pre.isEmpty() ? 0 : pre.poll();
            } else {
                sum += suf.poll();
            }
            round++;
        }
        return sum;
    }

    public static void main(String[] args) {
//        int[] nums = {1, 5, 4, 2, 9, 9, 9};
//        int k = 3;
        Solution1106 so = new Solution1106();

        int[] costs = {17, 12, 10, 2, 7, 2, 11, 20, 8};
        int k = 3;
        int candidates = 4;
//        long res = so.totalCost(costs, k, candidates);
//        System.out.println(res);
        costs = new int[]{18, 64, 12, 21, 21, 78, 36, 58, 88, 58,
                99, 26, 92, 91, 53, 10, 24, 25, 20, 92,
                73, 63, 51, 65, 87, 6, 17, 32, 14, 42,
                46, 65, 43, 9, 75};
        k = 13;
        candidates = 23;

        costs = new int[]{1, 2, 4, 1};
        k = 3;
        candidates = 3;

        costs = new int[]{980, 719, 216, 384, 883, 283, 880, 230, 882, 142, 806, 218, 604, 299, 285, 949, 237, 319, 680,
                278, 143, 697, 406, 464, 962, 415, 325, 99, 636, 577, 701, 205, 38, 154, 130, 232, 897, 188, 831, 467, 278,
                492, 159, 368, 998, 572, 870, 794, 949, 939, 904, 15, 505, 996, 767, 562, 414, 65, 473, 841, 400, 18, 260,
                410, 615, 902, 266, 732, 543, 746, 686, 804, 714, 318, 756, 803, 826, 843, 297, 369, 950, 495,
                379, 2, 919, 283, 397, 463, 271, 132, 40, 452, 366, 269, 276, 283, 563, 281, 697, 878};
        k = 59;
        candidates = 15;
        long ans = so.totalCost(costs, k, candidates);
        System.out.println(ans);
//        int[] nums = {1, 2, 2};
//        so.maximumSubarraySum(nums, 2);
    }
}