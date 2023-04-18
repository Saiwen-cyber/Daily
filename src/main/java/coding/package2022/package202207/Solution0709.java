package coding.package2022.package202207;

import java.util.*;

/**
 * @author fang
 */
public class Solution0709 {

    private boolean isBadVersion(int mid) {
        return mid == 9;
    }

    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                if (isBadVersion(mid - 1)) {
                    right = mid - 1;
                } else {
                    return mid;
                }
            } else {
                if (isBadVersion(mid + 1)) {
                    return mid + 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }

    List<Integer> set;
    public void SmallestInfiniteSet() {
        set  = new ArrayList<>();
        for(int i = 1; i <= 1000; i++) {
            set.add(i);
        }
    }

    public int popSmallest() {
        return findMin();
    }

    public void addBack(int num) {
        if(set.contains(num)) {
            return;
        }
        set.add(num);
    }

    public int findMin(){
        Collections.sort(set);
        int val = set.get(0);
        set.remove(0);
        return val;
    }

    public int fillCups(int[] amount) {
        Arrays.sort(amount);
        boolean c = amount[2] > 0 && amount[1] > 0;
        if (c) {
            amount[2]--;
            amount[1]--;
            return 1 + fillCups(amount);
        }

        boolean b = amount[2] > 0 && amount[0] > 0;
        if (b) {
            amount[2]--;
            amount[0]--;
            return 1 + fillCups(amount);
        }
        boolean a = amount[1] > 0 && amount[0] > 0;
        if (a) {
            amount[1]--;
            amount[0]--;
            return 1 + fillCups(amount);
        }

        return amount[0] + amount[1] + amount[2];
    }

    public static void main(String[] args) {
        Solution0709 solution0709 = new Solution0709();
        solution0709.SmallestInfiniteSet();
        solution0709.findMin();
//        System.out.println(solution0709.fillCups(new int[]{5, 4, 4}));;
    }

}
