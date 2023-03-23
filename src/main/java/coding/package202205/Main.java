package coding.package202205;

import java.util.Scanner;

public class Main {
    public static int maxCommon(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }
        //两个整数的最大公约数等于其中较小的那个数和两数相除余数的最大公约数。
        int tmp = m % n;
        while (tmp != 0) {
            m = n;
            n = tmp;
            // 当余数为0时，n则为两正整数最大公约数
            tmp = m % n;
        }
        return n;
    }

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int n, m;
        while (cin.hasNextInt()) {
            n = cin.nextInt();
            m = cin.nextInt();
            int re = maxCommon(m, n);
            if (re == 0) {
                System.out.println("输入有误，请重新输入两个正整数");
            } else {
                System.out.println("最大公约数:" + re + "  最小公倍数:" + (m * n) / re);
            }
        }
    }
}
