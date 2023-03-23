package coding.package202206;
import java.util.Stack;

/**
 * @author ASUS
 */
public class Solution08 {
    public String removeDuplicateLetters(String s) {
        int len = s.length();
        Stack<Character> stack = new Stack<>();
        String right = s;
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < len - 1; i++) {
            right = right.substring(1);
            char ch0 = s.charAt(i);
            char ch1 = s.charAt(i+1);
            if(ch0 - ch1 > 0 && right.indexOf(ch0) >= 0) {
                continue;
            }
            char peak = stack.isEmpty() ? ch0 :stack.peek();
            while(ch0 - peak < 0 && right.indexOf(peak) >= 0) {
                stack.pop();
                sb.delete(sb.length() - 1, sb.length() - 1);
                if(stack.isEmpty()){
                    break;
                }
                peak = stack.peek();
            }
            stack.push(ch0);
            sb.append(ch0);
        }
        if (!stack.contains(s.charAt(len - 1))) {
            stack.push(s.charAt(len - 1));
            sb.append(s.charAt(len - 1));
        }
        return sb.toString();
    }

    //栈维护去除关键字符之后的字符串
    //对于当前s[i] ,如果栈中已有则不加入
    //对于比较时，从栈中删除元素，如果后面没有，则不删除
    public String removeDuplicateLetters(String s, Integer index) {
        int len = s.length();
        String right = s;
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < len - 1; i++) {
            right = right.substring(1);
            char ch0 = s.charAt(i);
            if(sb.indexOf(String.valueOf(ch0)) >= 0) {
                continue;
            }
            char peak = sb.length() == 0 ? ch0 : sb.charAt(sb.length() - 1);
            while(ch0 - peak < 0 && right.indexOf(peak) >= 0) {
                sb.delete(sb.length() - 1, sb.length());
                if(sb.length() == 0){
                    break;
                }
                peak = sb.charAt(sb.length() - 1);
            }
            sb.append(ch0);
        }
        if (sb.indexOf(String.valueOf(s.charAt(len - 1))) < 0) {
            sb.append(s.charAt(len - 1));
        }
        return sb.toString();
    }
    public String removeKdigits(String num, int k) {
        int k_copy = k;
        if(num.length() == k) {
            return "0";
        }
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < num.length(); i++) {
            char ch = num.charAt(i);
            while(sb.length() != 0 && k > 0 && sb.charAt(sb.length() - 1) - ch > 0) {
                sb.delete(sb.length() - 1, sb.length());
                k--;
            }
            sb.append(ch);
        }

        boolean first = true;
        StringBuffer re = new StringBuffer();
        for(int i = 0; i < num.length() - k_copy; i++) {
            if(first && sb.charAt(i) == '0') {
                continue;
            }
            re.append(sb.charAt(i));
            first = false;
        }
        return re.length() == 0 ? "0" : re.toString();
    }

    public static void main(String[] args) {
        String num = "1432219";
        int k = 3;
        Solution08 solution08 = new Solution08();
        String re = solution08.removeKdigits(num, k);
        System.out.println(re);
    }
}