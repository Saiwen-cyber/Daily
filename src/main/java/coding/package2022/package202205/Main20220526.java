package coding.package2022.package202205;

import java.util.*;

/**
 * @author fang
 */
class Main20220526 {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> tmp = map.getOrDefault(nums[i], new ArrayList<>());
            tmp.add(i);
            map.put(nums[i], tmp);
        }
        List<List<Integer>> re = new ArrayList<>();
        for (int x = 0; x < nums.length; x++) {
            //前一个x不能与后一个x重复 相等
            if (x > 0 && nums[x] == nums[x - 1]) {
                continue;
            }
            for (int y = x + 1; y < nums.length; y++) {
                //前一个y不能与后一个y重复 相等
                if (y > x + 1 && nums[y] == nums[y - 1]) {
                    continue;
                }
                for (int z = y + 1; z < nums.length; y++) {
                    //前一个y不能与后一个y重复 相等
                    if (y > z + 1 && nums[z] == nums[z - 1]) {
                        continue;
                    }
                    if (x + y + z == 0) {
                        List<Integer> tmp = new ArrayList<>();
                        tmp.add(nums[x]);
                        tmp.add(nums[y]);
                        tmp.add(nums[z]);
                        re.add(tmp);
                    }
                }
            }
        }
        return re;
    }
}
