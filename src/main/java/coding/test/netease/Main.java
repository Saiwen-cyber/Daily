package coding.test.netease;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long k = in.nextLong();
        in.nextLine();
        long[] nums = new long[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextLong();
        }
//        Arrays.sort(nums);
        int ans = 0;
        Map<Long, List<Integer>> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        list.add((n - 1));
        map.put(nums[n - 1], list);
        for (int i = n - 2; i >= 0; i--) {
            long key = nums[i];
            List<Integer> tmp = new ArrayList<>();
            for (Map.Entry<Long, List<Integer>> entry : map.entrySet()) {
                long e = entry.getKey();
                if (Math.abs(e - nums[i]) % k == 0) {
                    tmp = entry.getValue();
                    key = e;
                    break;
                }
            }
            tmp.add(i);
            map.put(key, tmp);
            ans = Math.max(ans, tmp.size());
        }
        System.out.println(ans);
    }


}