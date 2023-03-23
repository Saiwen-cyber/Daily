package coding.test.mi;

import java.util.*;

/**
 * @author fang
 */
public class Main3 {
    public static void main(String[] args) {
        int[][] intervals = getInput();
        System.out.println(getOutput(intervals));
    }

    public static int getOutput(int[][] intervals) {
        if (intervals.length < 1) {
            return 0;
        }
        Arrays.sort(intervals, (x, y) -> x[0] == y[0] ? x[1] - y[1] : x[0] - y[0]);
        int left = intervals[0][0];
        int right = intervals[0][1];
        int sum = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > right) {
                sum += right - left;
                left = intervals[i][0];
                right = intervals[i][1];
            } else {
                right = intervals[i][1];
            }
        }
        return sum + (right - left);
    }

    public static int[][] getInput() {
        Scanner in = new Scanner(System.in);
        List<String> input = new ArrayList<>();

        while (in.hasNextLine()) {
            String s = in.nextLine();
            if (s.equals("")) {
                break;
            }
            input.add(s);
        }
        int[][] re = new int[input.size()][2];
        int i = 0;
        for (String line : input) {
            String[] str = line.split(" ");
            re[i][0] = Integer.parseInt(str[0]);
            re[i][1] = Integer.parseInt(str[1]);
            i++;
        }
        return re;
    }
}
