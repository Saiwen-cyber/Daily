package coding.package2022.package202206;

import java.util.Stack;

/**
 * @author fang
 */
public class Main20220602 {
    public String removeDuplicateLetters(String s) {
        int len = s.length();
        Stack<Character> stack = new Stack<>();
        String right = s;
        for (int i = 0; i < len - 1; i++) {
            right = right.substring(1);
            char ch0 = s.charAt(i);
            char ch1 = s.charAt(i + 1);
            if (ch0 - ch1 < 0 && !stack.contains(ch0)) {
                stack.push(ch0);
            }
            if (ch0 - ch1 > 0 && right.indexOf(ch0) < 0) {
                stack.push(ch0);
            }
        }
        if (!stack.contains(s.charAt(len - 1))) {
            stack.push(s.charAt(len - 1));
        }
        return String.valueOf(stack);
    }

    public static void main(String[] args) {
        Main20220602 main = new Main20220602();
        main.removeDuplicateLetters("cbacdcbc");
    }

}
