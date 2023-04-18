package coding.package2023.package202304;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution0410 {
    public static void main(String[] args) {
        Solution0410 so = new Solution0410();
        so.isValid("()");
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        int[][] dp = new int[5][5];
        dp[0][1] = 1;
        res.add(list);
        for (int i = 1; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 1; j <= i + 1; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
                row.add(dp[i][j]);
            }
            res.add(row);
        }
        return res;
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (isClosedBrackets(s.charAt(i))) {
                if (stack.isEmpty()) {
                    return false;
                }
                if (stack.peek() == getOpenByClosed(s.charAt(i))) {
                    stack.pop();
                    continue;
                }
                return false;
            }
            stack.push(s.charAt(i));
        }
        return true;
    }

    private char getOpenByClosed(char ch) {
        if (ch == '}') {
            return '{';
        }
        if (ch == ']') {
            return '[';
        }
        if (ch == ')') {
            return '(';
        }
        return 'n';
    }

    public boolean isClosedBrackets(char ch) {
        return ch == '}' || ch == ']' || ch == ')';
    }
}