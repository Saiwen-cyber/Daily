package coding.test.nowcoder;

import java.util.*;

public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Set<Integer> set = new HashSet<>();
        String[] var = in.nextLine().split(" ");
        String[] src = in.nextLine().split(" ");
        int[] nums = new int[src.length];
        int n = Integer.parseInt(var[0]);
        int k = Integer.parseInt(var[1]);
        int x = Integer.parseInt(var[2]);
/*        for (int i = 0; i < n; i++) {
            set.add(i);
            nums[i] = Integer.parseInt(src[i]);
        }
        System.out.println(exec(set, nums, k, x));*/
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(src[i]);
            if (num + k >= x) {
                sum += num;
            }
        }
        System.out.println(sum);
    }

    public static int exec(Set<Integer> set, int[] nums, int k, int x) {
        int sum = 0;
        int time = 0;
        while (!set.isEmpty() && time < k) {
            for (int i = 0; !set.isEmpty() && i < nums.length; i++) {
                //已经吃掉
                if (!set.contains(i)) {
                    continue;
                }
                //要被吃
                if (nums[i] >= x) {
                    sum += nums[i];
                    set.remove(i);
                } else {
                    nums[i] = nums[i] + 1;
                }
            }
            time++;
        }
        return sum;
    }

}