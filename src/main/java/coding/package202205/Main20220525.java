package coding.package202205;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author fang
 */
public class Main20220525 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            if (stack.isEmpty()) {
                stack.add(nums2[i]);
                map.put(nums2[i], -1);
            } else {
                while (!stack.isEmpty() && stack.peek() < nums2[i]) {
                    stack.pop();
                }
                map.put(nums2[i], stack.isEmpty() ? -1 : stack.peek());
            }
        }
        int[] re = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            re[i] = map.get(nums1[i]);
        }

        return re;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{2, 4};
        int[] nums2 = new int[]{1, 2, 3, 4};

        Main20220525 main = new Main20220525();
        main.nextGreaterElement(nums1, nums2);

    }
}
