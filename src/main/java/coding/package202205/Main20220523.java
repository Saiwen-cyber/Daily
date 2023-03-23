package coding.package202205;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author fang
 */
public class Main20220523 {
    public void hanoi(List<Integer> A, List<Integer> B, List<Integer> C) {
        Stack<Integer> a = new Stack<>();
        a.addAll(A);
        exec(A.size(), a, new Stack<Integer>(), new Stack<Integer>());
    }

    public void exec(int size, Stack<Integer> a, Stack<Integer> b, Stack<Integer> c) {
        if (size < 1) {
            return;
        }
        //假设一共n个 先把上面n - 1 个移到中间栈
        exec(size - 1, a, c, b);
        //再把n 移到  目标栈
        c.add(a.pop());
        exec(size - 1, b, a, c);
    }

    public static void main(String[] args) {
        Main20220523 main = new Main20220523();
        List<Integer> A = new ArrayList<>();
        List<Integer> B = new ArrayList<>();
        List<Integer> C = new ArrayList<>();

        A.add(2);
        A.add(1);
        A.add(0);

        main.hanoi(A, B, C);

    }

}
