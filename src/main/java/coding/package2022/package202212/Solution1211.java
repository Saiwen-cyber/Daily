package coding.package2022.package202212;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Annie Fang
 */
public class Solution1211 {
    public static void main(String[] args) {

        int[][] grid = new int[][]{{1, 2, 4}, {3, 3, 1}};
        Solution1211 so = new Solution1211();
        so.deleteGreatestValue(grid);
    }

    public int deleteGreatestValue(int[][] grid) {
        int n = grid[0].length - 1;
        int result = 0;
        Arrays.stream(grid).forEach(Arrays::sort);
        while (n >= 0) {
            int max = 0;
            for (int[] ints : grid) {
                max = Math.max(ints[n], max);
            }
            result += max;
            n--;
        }
        return result;
    }

    /**
     * @param nums
     * @return
     */
    public int longestSquareStreak(int[] nums) {
        //首次去重
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        Integer[] temp = set.toArray(new Integer[]{});

        Map<Integer, List<Integer>> map = new HashMap<>();
        int count = 0;
        int result = -1;
        while (count < temp.length) {
            for (int tmp : temp) {
                int sqrt = (int) Math.floor(Math.sqrt(tmp));
                sqrt = sqrt * sqrt != tmp ? tmp : sqrt;
                List<Integer> list = map.getOrDefault(tmp, new ArrayList<>());
                list.add(tmp);
                map.put(sqrt, list);
                count += sqrt == tmp ? 1 : 0;
                if (list.size() >= 2) {
                    result = Math.max(result, list.size());
                }
            }
        }
        return result;
    }

    class Allocator {
        Map<Integer, Integer> map;
        Map<Integer, List<Integer>> mIDMap;
        List<Integer> list;
        int remain;

        public Allocator(int n) {
            map = new HashMap<>(n);
            list = new ArrayList<>(n);
            mIDMap = new HashMap<>();
            remain = list.size();
        }

        public int allocate(int size, int mID) {
            if (remain < size) {
                return -1;
            }
            List<Integer> list = mIDMap.getOrDefault(mID,new ArrayList<>());
            return 0;
        }

        public int free(int mID) {
            if (!mIDMap.containsKey(mID)) {
                return -1;
            }
            List<Integer> list = mIDMap.get(mID);
            map.remove(mID);
            remain += list.size();
            return list.size();
        }
    }
}