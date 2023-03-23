package coding.package202208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Solution {
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

public class MainClass {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);
            line = in.readLine();
            int target = Integer.parseInt(line);

            int ret = new Solution().threeSumClosest(nums, target);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}