package coding.package2022.package202208;

import java.util.Arrays;

public class Solution08x {
    public int threeSumClosest(int[] nums, int target) {
        int re = 0;
        int len = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            int newTarget = target - nums[i];
            int left = i + 1;
            int right = i + 2;
            int curSum = nums[left] + nums[right];
            while (left >= i + 1 && right < len && left < right) {
                curSum = nums[left] + nums[right];
                if (curSum == newTarget) {
                    return target;
                } else if (curSum > newTarget) {
                    left--;
                } else if (curSum < newTarget) {
                    right++;
                }
            }
            int a = Math.abs(curSum + newTarget - target);
            int b = Math.abs(re - target);
            re = a < b ? curSum + newTarget : re;

        }
        return re;
    }

    private int sum(int[] nums, int start, int right) {
        int re = 0;
        while (start <= right) {
            re = re + nums[start];
            start++;
        }
        return re;
    }
}
