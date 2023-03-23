package coding.package202210;

public class Solution1026 {
/*    public int shortestSubarray(int[] nums, int k) {
        int len = nums.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int target = k;
            int count = 0;
            for (int j = i; j < nums.length && target > 0; j++) {
                count++;
                target -= nums[j];
            }
            min = Math.min(count, min);
        }
        return min;
    }*/

    public int shortestSubarray(int[] nums, int k) {
        int len = nums.length;
        int sum = 0;
        int left = 0, right = 0;
        int min = Integer.MAX_VALUE;

        for (; right < len; right++) {
//            while (nums[left] < 0 && left < right) {
//                sum -= nums[left];
//                left++;
//            }
            if (sum + nums[right] < k) {
                sum += nums[right];
                if (sum <= 0) {
                    //从下一个下标重新开始
                    left = right + 1;
                    sum = 0;
                }
                continue;
            }
            int val = nums[right];
            int index = right - 1;
            boolean flag = false;
            //从index向左查找，找到恰好 sum[index,...,right] >= k,的下标 i；
            //更新left 为新的下标i
            while (val < k && index >= left) {
                val += nums[index];
                index--;
                flag = true;
            }

            //如果进了循环
            //当循环跳出时，val >= k,更新sum, left
            if (flag) {
                sum = val;
                left = index + 1;
            }
            //没进循环的原因
            //情况1、nums[right] 本身就大于等于 k，需要更新sum，left
            //情况2、right - 1 < left 并且 left <= right ,因此此时情况left = right，什么也无需做

            if (nums[right] >= k) {
                left = right;
                sum = nums[right];
            }
            // 此时区间[left, right]为被选中的区间
            min = Math.min(min, right - left + 1);
        }
        if (min == Integer.MAX_VALUE) {
            return -1;
        }
        return min;
    }

    public static void main(String[] args) {
        Solution1026 so = new Solution1026();
        int[] nums;
        int res;
        nums = new int[]{-36, 10, -28, -42, 17, 83, 87, 79, 51, -26, 33, 53, 63, 61, 76, 34, 57, 68, 1, -30};
        res = so.shortestSubarray(nums, 484);
        System.out.println(res);

        nums = new int[]{58, -27, -11, 63, 90, 83, 61, -44, -39, 30};
        res = so.shortestSubarray(nums, 61);
        System.out.println(res);

        nums = new int[]{-28, 81, -20, 28, -29};
        res = so.shortestSubarray(nums, 89);
        System.out.println(res);

        nums = new int[]{48, 99, 37, 4, -31};
        res = so.shortestSubarray(nums, 140);
        System.out.println(res);

        nums = new int[]{84, -37, 32, 40, 95};
        res = so.shortestSubarray(nums, 167);
        System.out.println(res);

        nums = new int[]{2, -1, 2};
        res = so.shortestSubarray(nums, 3);
        System.out.println(res);

        nums = new int[]{17, 85, 93, -45, -21};
        res = so.shortestSubarray(nums, 150);
        System.out.println(res);


    }
}