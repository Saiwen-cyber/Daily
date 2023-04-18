package coding.package2022.package202210;

import java.util.*;

public class Solution1013 {
    public int maxChunksToSorted(int[] arr) {
        int index = 0;
        int[] nums = new int[arr.length];
        for (int num : arr) {
            nums[index] = num;
            index++;
        }
        Arrays.sort(arr);
        int count = 0;
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            int toFind = arr[i];
            boolean isFound = false;
            int tmp = j;
            while (j < nums.length) {
                if (nums[j] == toFind) {
                    isFound = true;
                    break;
                }
                j++;
            }
            if (isFound) {
                count++;
            } else {
                j = tmp;
            }
        }

        return count;
    }

    List<String> list = new ArrayList<>();

    public static void main(String[] args) {
        Solution1013 so = new Solution1013();
        so.maxChunksToSorted(new int[]{1, 0, 2, 3, 4});
    }
}