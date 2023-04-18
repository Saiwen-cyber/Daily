package coding.package2022.package202205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Solution2 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        //辅助栈；
        Stack<Integer> tmp = new Stack<>();
        int j = 0;
        for (int i = 0; i < pushed.length; i++) {
            int e = pushed[i];
            tmp.push(e);
            while (!tmp.isEmpty() && tmp.peek() == popped[j]) {
                tmp.pop();
                j++;
            }
        }
        return tmp.isEmpty();
    }
}

public class MainClass {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public int findNthDigit(int n) {
        Map<Integer, Long> map = new HashMap<>();
        map.put(1, 9L);
        if (n < 10) {
            return n;
        } else {
            n = n - 10;
        }
        int e = 1;
        for (int i = 2; i < 10; i++) {
            long val = i * 10 * 9 * e;
            e = e * 10;
            map.put(i, val);
            if (n < val) {
                return find(i, n, map);
            } else {
                n -= val;
            }
        }
        return -1;
    }

    public int find(int digitNum, int n, Map<Integer, Long> map) {
        long pre = map.get(digitNum - 1);
        long a = n / digitNum;
        long b = n % digitNum;
        long start = (int) Math.pow(10, digitNum - 1) + (a - 1);
        int index = 0;
        if (b == 0) {
            index = digitNum;
        } else {
            start = start + 1;
            index = (int) b - 1;
        }
        System.out.print(start);
        String re = String.valueOf(start);
        return re.charAt(index) - '0';
    }

    public int findNthDigit2(int n) {
        int digit = 1;
        long start = 1;
        long count = 9;
        while (n > count) { // 1.
            n -= count;
            digit += 1;
            start *= 10;
            count = digit * start * 9;
        }
        long num = start + (n - 1) / digit; // 2.
        return Long.toString(num).charAt((n - 1) % digit) - '0'; // 3.
    }

    public int percentageLetter(String s, char letter) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        int val = map.get(letter);
        return 0;
    }

    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        Map<Integer, Integer> map = new HashMap<>();
        int len = capacity.length;
        int zero = 0;
        for (int i = 0; i < len; i++) {
            if (capacity[i] - rocks[i] == 0) {
                zero++;
            } else {
                queue.add(capacity[i] - rocks[i]);
            }
        }
        int re = 0;
        int val;
        while (additionalRocks > 0 && !queue.isEmpty()) {
            val = queue.poll();
            additionalRocks = additionalRocks - val;
            if (additionalRocks >= 0) {
                re++;
            }
        }
        return zero + re;
    }

    private static final int num = 1000000007;

//    public int totalStrength(int[] strength) {
//        int len = strength.length;
//
//        //以i结尾的所有子串的力量和
//        long[] solve = new long[len];
//        //当前从 min下标到i的最小值
//        List<Integer> minList = new ArrayList<>();
//        //从下标到i 的数之和
//        List<Long> sumList = new ArrayList<>();
//        long sum = strength[0];
//        int min = strength[0];
//        solve[0] = sum * min;
//        minList.add(min);
//        sumList.add(sum);
//        //solve[i] 与solve[i-1] 的关系
//        // 推导
//        long re = 0;
//        int minSum = min;
//        for (int i = 1; i < len; i++) {
//            for (int i = 0; i < minList.size(); i--) {
//                int tmpMin = minList.get(i);
//                if (strength[i] < tm)
//            }
//            solve[i] = solve[i - 1] + strength[i] * minSum;
//            minSum = minSum + strength[i];
//            re = re + solve[i] % num;
//        }
//        return Math.toIntExact(re);
//    }

    public long oneSum(int[] strs) {
        int min = strs[0] % num;
        long sum = min % num;
        long re = (min * min) % num;
        for (int i = 1; i < strs.length; i++) {
            min = (Math.min(min, strs[i])) % num;
            sum = (sum + strs[i]) % num;
            re = (min * sum) % num;
        }
        return re;
    }

    public static void main(String[] args) throws IOException {
        MainClass main = new MainClass();
        int[] strength = new int[]{100000000, 300000000, 100000000, 200000000};
//        main.totalStrength(strength);
        int[] capacity = new int[]{2, 3, 4, 5};
        int[] rocks = new int[]{1, 2, 4, 4};
        int additionalRocks = 2;
        main.maximumBags(capacity, rocks, 2);
        main.percentageLetter("footer", 'o');
        System.out.println(main.findNthDigit(10000));
        if (1 == 1) {
            return;
        }
        List<Integer> list = new ArrayList();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.remove((Integer) 2);
        list.remove((Integer) 4);

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] pushed = stringToIntegerArray(line);
            line = in.readLine();
            int[] popped = stringToIntegerArray(line);

            boolean ret = new Solution2().validateStackSequences(pushed, popped);

            String out = booleanToString(ret);

            System.out.print(out);
        }

        char[] chars = "cdmcjdn".toCharArray();
        StringBuffer sb = new StringBuffer();
        sb.deleteCharAt(sb.length() - 1);
        Set<String> set = new HashSet<>();
        String[] strings = (String[]) set.toArray();

    }


}