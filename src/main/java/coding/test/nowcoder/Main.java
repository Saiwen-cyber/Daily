package coding.test.nowcoder;

import java.util.*;

public class Main {
    static int count = 0;
    static Map<String, Boolean> map = new HashMap<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.nextLine();
        String b = in.nextLine();
        exec(a, b);
        System.out.println(count);
    }

    public static void exec(String a, String b) {
        if (a.length() > b.length()) {
            exec(b, a);
            return;
        }
        for (int i = 0; i < a.length(); i++) {
            for (int j = i + 1; j <= a.length(); j++) {
                String tmp = a.substring(i, j);
                if (map.containsKey(tmp)) {
                    continue;
                }
                if (b.contains(tmp)) {
                    map.put(tmp, true);
                    count++;
                }
            }
        }
    }
}