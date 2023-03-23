package coding.test.netease;

import java.util.*;

public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long k = in.nextLong();//操作次数
        long x = in.nextLong();
        in.nextLine();
        long[] nums = new long[n];
        long max = 0;
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextLong();
            max = Math.max(max, nums[i]);
        }
        int len = nums.length - 1;

        while (k > 0) {
            long tmpMax = 0;
            for (int i = 0; i <= len; i++) {
                if (nums[i] >= max) {
                    nums[i] = nums[i] - x;
                    k--;
                }
                tmpMax = Math.max(tmpMax, nums[i]);
            }
            max = tmpMax;
        }
        System.out.println(max);
    }
}