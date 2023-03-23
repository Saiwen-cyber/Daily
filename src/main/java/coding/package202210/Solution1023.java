package coding.package202210;

import java.util.*;

class Time {
    int hour;
    int min;

    public Time(int hour, int min) {
        this.hour = hour;
        this.min = min;
    }

    //this是否比time大
    public boolean isLarger(Time time) {
        if (this.hour < time.hour) {
            return false;
        }
        if (this.hour > time.hour) {
            return true;
        }
        return this.min > time.min;
    }
}

public class Solution1023 {

    public boolean haveConflict(String[] event1, String[] event2) {
        Time[] time1 = new Time[2];
        Time[] time2 = new Time[2];
        for (int i = 0; i < event1.length; i++) {
            String[] strs1 = event1[i].split(":");
            String[] strs2 = event2[i].split(":");


            time1[i] = new Time(Integer.parseInt(strs1[0]), Integer.parseInt(strs1[1]));
            time2[i] = new Time(Integer.parseInt(strs2[0]), Integer.parseInt(strs2[1]));
        }
        if (time2[0].isLarger(time1[1])) {
            return true;
        }
        if (time1[0].isLarger(time2[1])) {
            return true;
        }
        return false;
    }

    //
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public int subarrayGCD(int[] nums, int k) {
        int count = 0;
        int len = nums.length;
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            if (nums[i] % k == 0) {
                for (int j = i; j < len; j++) {
                    int max = gcd(nums[i], nums[j]);
                    if (max != k) {
                        break;
                    }
                    count++;
                }
            }
        }
        return count;
    }


    public int nextGreaterElement(int n) {
        String str = Integer.toString(n);
        int[] nums = converse(str);
        int m = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            m *= 10;
            int[] tmp = Arrays.copyOfRange(nums, i, nums.length);
            int[] next = findNext(tmp);
            if (next != tmp) {
                long left = m * converse(Arrays.copyOfRange(nums, 0, i));
                long right = converse(next);
                long res = left + right;
                return res > Integer.MAX_VALUE ? -1 : (int) res;
            }
        }
        return -1;
    }

    //tmp[1,len] 是递减的
    private int[] findNext(int[] tmp) {
        int index = -1;
        for (int i = tmp.length - 1; i >= 0; i--) {
            if (tmp[i] > tmp[0]) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return tmp;
        }
        int[] res = new int[tmp.length];
        res[0] = tmp[index];
        boolean flag = false;
        for (int i = 1, j = tmp.length - 1; i < res.length; ) {
            if (j == index) {
                j--;
                continue;
            }
            if (!flag && tmp[j] > tmp[0]) {
                res[i] = tmp[0];
                i++;
                flag = true;
                continue;

            }
            res[i] = tmp[j];
            j--;
            i++;
        }
        return res;
    }

    private int[] converse(String str) {
        int[] nums = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            nums[i] = Integer.parseInt(String.valueOf(str.charAt(i)));
        }
        return nums;
    }

    private long converse(int[] nums) {
        long res = 0;
        int m = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            res += m * nums[i];
            m *= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution1023 so = new Solution1023();
//        System.out.println(so.nextGreaterElement(1999999999));
            so.converse(new int[]{9,1,9,9,9,9,9,9,9,9});
        }
}
