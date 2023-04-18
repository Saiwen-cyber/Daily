package coding.package2022.package202212;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author Annie Fang
 * @create 2022/12/18 10:34
 */
public class Solution1218 {


    public static void main(String[] args) {
        Solution1218 so = new Solution1218();
        so.smallestValue(15);
        so.smallestValue(3);
    }

    public int[] cycleLengthQueries(int n, int[][] queries) {
        int[] res = new int[queries.length];
        int i = 0;
        for (int[] points : queries) {
            res[i++] = calHeight(points[0], points[1], n);
        }
        return res;
    }

    private int calHeight(int pointA, int pointB, int n) {
        if (pointA > pointB) {
            return calHeight(pointB, pointA, n);
        }
        if (isConn(pointA, pointB)) {
            return 1 + height(pointB, n) - height(pointA, n);
        } else {
            return 1 + height(pointB, n) + height(pointA, n);
        }
    }

    private boolean isConn(int pointA, int pointB) {
        //TODO 判断ab是不是有共同路径回到root
        return false;
    }

    public int height(int num, int n) {
        int i = 1;
        while (num >= Math.pow(2, i) && i < n) {
            i++;
        }
        return i - 1;
    }

    public int smallestValue(int n) {
        int res = 0;
        Set<Integer> set = new HashSet<>();
        int next = n;
        while (!set.contains(next)) {
            set.add(next);
            next = calNext(next, res);
        }
        return next;
    }

    public int calNext(int n, int res) {
        AtomicInteger tmp = new AtomicInteger();
        IntStream.rangeClosed(2, n)
                .filter(this::isPrime)
                .filter(prime -> n % prime == 0).findFirst().ifPresent(tmp::set);
        int prime = tmp.get();
        if (prime < 2) {
            return res;
        }
        res += tmp.get();
        return calNext(n / tmp.get(), res);
    }

    public boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }

    public int similarPairs(String[] words) {
        int res = 0;
        Map<HashSet<Character>, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            HashSet<Character> set = new HashSet<>();
            for (int j = 0; j < words[i].length(); j++) {
                char ch = words[i].charAt(j);
                set.add(ch);
            }
            List<Integer> list = map.getOrDefault(set, new ArrayList<>());
            res += list.size();
            list.add(i);
            map.put(set, list);
        }
        return res;
    }

}
