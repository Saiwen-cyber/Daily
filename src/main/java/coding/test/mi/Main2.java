package coding.test.mi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author fang
 */
public class Main2 {
    public static void main(String[] args) {
        System.out.println(pro());
    }

    public static int pro() {

        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String t = in.nextLine();

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            int max = list.isEmpty() ? -1 : list.get(list.size() - 1);
            if (s.indexOf(ch) > max) {
                list.add(i);
            }
        }
        int sub = list.isEmpty() ? 0 : list.get(0);
        for (int i = 1; i < list.size(); i++) {
            int a = list.get(i);
            int b = list.get(i - 1);
            sub += a - b;
        }
        sub += list.isEmpty() ? 0 : s.length() - 1 - list.get(list.size() - 1);
        int add = t.length() - list.size();
        return sub + add;
    }
}
