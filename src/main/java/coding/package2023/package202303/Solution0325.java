package coding.package2023.package202303;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

class Solution0325 {
    public static void main(String[] args) {
        Solution0325 so = new Solution0325();
        so.primeSubOperation(new int[]{998, 2});
        so.primeSubOperation(new int[]{2, 2});
        so.primeSubOperation(new int[]{5, 8, 3});
    }

    public boolean primeSubOperation(int[] nums) {
        int len = nums.length;
        int max = nums[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            int diff = nums[i] - nums[i + 1];
            if (diff < 0) {
                continue;
            }
            AtomicInteger tmp = new AtomicInteger();
            int finalI = i;
            IntStream.rangeClosed(2, nums[finalI])
                    .filter(this::isPrime)
                    .filter(prime -> prime > diff && prime < nums[finalI]).findFirst().ifPresent(tmp::set);
            int prime = tmp.get();
            if (prime == 0) {
                return false;
            }
            nums[i] -= prime;
        }
        return true;
    }

    public boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }

    public List<Long> minOperations(int[] nums, int[] queries) {
        Map<Integer, Long> queryMinOp = new HashMap<>();
        List<Long> ans = new ArrayList<>();
        for (int query : queries) {
            if (queryMinOp.containsKey(query)) {
                ans.add(queryMinOp.get(query));
                continue;
            }
            ans.add(minOperations(nums, query));
            queryMinOp.put(query, minOperations(nums, query));
        }
        return ans;
    }

    public Long minOperations(int[] nums, int target) {
        Map<Integer, Long> numMinOp = new HashMap<>();
        long res = 0L;
        for (int num : nums) {
            if (numMinOp.containsKey(num)) {
                res += numMinOp.get(num);
                continue;
            }
            if (num == target) {
                continue;
            }
            long tmp = (long) Math.abs(num - target);
            numMinOp.put(num, tmp);
            res += tmp;
        }
        return res;
    }
}