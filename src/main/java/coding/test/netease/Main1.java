package coding.test.netease;

import java.util.*;

public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        int[] weights = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            weights[i] = in.nextInt();
        }
        in.nextLine();
        for (int i = 0; i < n; i++) {
            int num = in.nextInt();
            System.out.println(num);
            weights[num] = 0;
            System.out.println(findMax(weights));
        }
    }

    private static int findMax(int[] weights) {
        int re = 0;
        int tmp = 0;
        for (int i = 1; i < weights.length; i++) {
            if (weights[i] != 0) {
                tmp = tmp + weights[i];
            } else {
                re = Math.max(re, tmp);
                tmp = 0;
            }
        }
        return Math.max(re,tmp);
    }
}