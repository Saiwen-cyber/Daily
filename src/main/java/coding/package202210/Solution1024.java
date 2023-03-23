package coding.package202210;

import java.util.Arrays;

/**
 * @author ASUS
 */
public class Solution1024 {
    public int partitionDisjoint(int[] nums) {
        int len = nums.length;
        //0 ~ i 的最大值
        int[] max = new int[len];
        //i ~ len 的最小值
        int[] min = new int[len];
        int ma = Integer.MIN_VALUE;
        int mi = Integer.MAX_VALUE;
        for (int i = 0, j = len - 1; i < len && j >= 0; i++, j--) {
            max[i] = Math.max(ma, nums[i]);
            min[j] = Math.min(mi, nums[j]);

            ma = max[i];
            mi = min[j];
        }

        for (int i = 0; i < len - 1; i++) {
            if (max[i] <= min[i + 1]) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution1024 so = new Solution1024();
        int[] nums = new int[]{5, 0, 3, 8, 6};
        so.partitionDisjoint(nums);
    }
}