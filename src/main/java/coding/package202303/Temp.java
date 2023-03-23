package coding.package202303;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Annie Fang
 * @create 2023/3/18 16:59
 */
class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{1, 3, 5, 2, 1, 3, 1};
//        nums = new int[]{2, 1, 3, 4, 5, 2};
        nums = new int[]{2, 3, 5, 1, 3, 2};
        nums = new int[]{26, 18, 27, 13, 42, 14, 50, 34, 45, 22, 47, 50, 9, 43, 30, 9, 24, 6, 25, 43};
//        nums = new int[]{2, 5, 6, 6, 10};
        System.out.println(solution.findScore(nums));
        solution.maximizeGreatness(nums);
    }

    public int maximizeGreatness(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int ans = 0;
        Queue<Integer> larger = new ArrayDeque<>();
        larger.offer(nums[len - 1]);
        for (int i = len - 2; i >= 0; i--) {
            if (larger.isEmpty()) {
                larger.offer(nums[i]);
                continue;
            }
            if (larger.peek() > nums[i]) {
                ans++;
                larger.poll();
            }
            larger.offer(nums[i]);
        }
        return ans;
    }

    public long findScore(int[] nums) {
        long score = 0;
        PriorityQueue<Num> queue = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            Num num = new Num(nums[i], i);
            queue.offer(num);
        }
        while (!queue.isEmpty()) {
            Num num = queue.poll();
            int val = num.getValue();
            int index = num.getIndex();
            if (nums[index] == 0) {
                continue;
            }
//            System.out.println(val);
            score += val;
            nums[index] = 0;
            if (isValid(index + 1, nums.length)) {
                nums[index + 1] = 0;
            }
            if (isValid(index - 1, nums.length)) {
                nums[index - 1] = 0;
            }
        }
        return score;
    }

    private boolean isValid(int i, int length) {
        return i >= 0 && i < length;
    }

    static class Num implements Comparable<Num> {
        int value;
        int index;

        public Num(int value, int index) {
            this.value = value;
            this.index = index;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        @Override
        public int compareTo(Num anther) {
            return this.value == anther.value ?
                    this.index - anther.index : this.value - anther.value;
        }
    }

}