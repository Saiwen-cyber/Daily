package coding.util;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] strs = line.split(" ");
            int sum = 0;
            int count = Integer.parseInt(strs[0]);
            if (count == 0) {
                break;
            }
            while (count > 0) {
                sum += Integer.parseInt(strs[count - 1]);
                count--;
            }
            System.out.println(sum);
        }
    }
}