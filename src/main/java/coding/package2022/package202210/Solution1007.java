package coding.package2022.package202210;

import java.util.*;

public class Solution1007 {
    public int maxAscendingSum(int[] nums) {
        Queue<Integer> sum = new PriorityQueue<>((x, y) -> y - x);
        int pre = nums[0];
        int tmpSum = pre;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > pre) {
                tmpSum += nums[i];
            } else {
                sum.add(tmpSum);
                tmpSum = nums[i];
            }
            pre = nums[i];
        }
        sum.add(tmpSum);
        return sum.isEmpty() ? nums[0] : sum.poll();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{10, 20, 30, 5, 10, 50};
        Solution1007 so = new Solution1007();
        so.maxAscendingSum(nums);
    }
}