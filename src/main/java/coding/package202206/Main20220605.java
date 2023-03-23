package coding.package202206;

/**
 * @author fang
 */
public class Main20220605 {
    private static final String MIN = "MIN";
    private static final String MAX = "MAX";

    public int minMaxGame(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        int[] newNums = new int[len / 2];
        int count = 0;
        int left = 0;
        int right = 1;
        String flag = "MIN";
        while (count < newNums.length) {
            if (MIN.equals(flag)) {
                newNums[count] = Math.min(nums[left], nums[right]);
            } else if (MAX.equals(flag)) {
                newNums[count] = Math.max(nums[left], nums[right]);
            }
            count++;
            left = left + 2;
            right = right + 2;
            flag = flag.equals(MIN) ? MAX : MIN;
        }
        return minMaxGame(newNums);
    }



    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 4, 6};
        int[][] op = new int[][]{{1, 3}, {4, 7}, {6, 1}};
        int k = 2;
        Main20220605 main = new Main20220605();
//        main.minMaxGame(nums, op);
    }
}
