package coding.package2023.package202304;

import java.util.Stack;

public class Solution0418 {
    public static void main(String[] args) {
        Solution0418 solution = new Solution0418();
        solution.addMinimum("b");
    }

    public int[] rowAndMaximumOnes(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        boolean findSmall = false;
        int[] map = new int[m];
        int index = 0;
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    if (!findSmall) {
                        index = i;
                        findSmall = true;
                    }
                    map[i]++;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            if (map[i] > max) {
                max = map[i];
            }
        }
        return new int[]{index, max};
    }

    public int maxDivScore(int[] nums, int[] divisors) {
        int res = 0;
        int count = 0;
        for (int i = 0; i < divisors.length; i++) {
            int tmp = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] % divisors[i] == 0) {
                    tmp++;
                }
            }
            if (tmp > count) {
                res = i;
                count = tmp;
            } else if (tmp == count) {
                if (divisors[res] > divisors[tmp]) {
                    res = i;
                }
            }
        }
        return divisors[res];
    }

    public int addMinimum(String word) {
        Stack<Character> stack = new Stack<>();
        int res = 0;
        int i = 0;
        while (i < word.length() || !stack.isEmpty()) {
            char next;
            char top;

            if (!stack.isEmpty() && i >= word.length()) {
                top = stack.peek();
                next = next(top);
                if (next == 'a') {
                    return res;
                }
                stack.add(next);
                res++;
                continue;
            }

            char cur = word.charAt(i);
            if (stack.isEmpty()) {
                next = 'a';
            } else {
                top = stack.peek();
                next = next(top);
                if (next == 'a') {
                    stack.clear();
                }
            }
            stack.add(next);
            if (next != cur) {
                res++;
            } else {
                i++;
            }
        }
        return res;
    }

    private char next(char top) {
        if (top == 'a') {
            return 'b';
        }
        if (top == 'b') {
            return 'c';
        }
        if (top == 'c') {
            return 'a';
        }
        return 'a';
    }
}