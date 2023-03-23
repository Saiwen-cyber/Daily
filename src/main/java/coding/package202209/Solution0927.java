package coding.package202209;

import java.util.*;

public class Solution0927 {
    public int[] missingTwo(int[] nums) {
        int[] re = new int[2];
        long n = nums.length + 2;

        //等比数列前n项和
        long sumAdd = n * (1 + n) / 2;
        //前n项平方和公式
        long sumSqr = n * (n + 1) * (2 * n + 1) / 6;
        for (int num : nums) {
            sumAdd -= num;
            sumSqr -= num * num;
        }
        //假设a + b = x，a^2 + b^2 = y
        //所以a^2 + b^2 + 2ab = x^2;
        //所以ab = (x^2 - y) / 2;
        //将b作为未知数 ，代入 a = x - b
        //整理后可得方程 b^2 - xb + (x^2 - y) / 2= 0;
        //二次方程根的判定
        //https://baike.baidu.com/item/%E4%BA%8C%E6%AC%A1%E6%96%B9%E7%A8%8B/419128
        //判断可得,此方程必有两个不相等的实数根
        // “delta” = 2y - x^2 >0
        //b1 = (根号“delta” + 2 x ) / 2;
        //b2 = ( - 根号“delta” + 2 x ) / 2;
        //计算得b = b1、b2中大于0那个
        //则 a = x - b1;
        //x = sumAdd, y = sumSqr
        double tmp1 = 2 * sumSqr - sumAdd * sumAdd;
        int tmp2 = (int) Math.sqrt(tmp1);
        re[0] = (int) ((tmp2 + sumAdd) / 2);
        re[1] = (int) (sumAdd - re[0]);
        return re;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3};
        Solution0927 so = new Solution0927();
        so.missingTwo(nums);
    }
}