package coding.package2022.package202211;

import coding.TreeNode;

import java.util.*;

/**
 * @author Annie Fang
 * @create 2022/11/19 12:53
 */
public class Solution1119 {
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        move(A.size(), A, C, B);
    }

    public void move(int size, List<Integer> A, List<Integer> B, List<Integer> C) {
        if (size == 1) {
            B.add(pop(A));
            return;
        }
        move(size - 1, A, B, C);
        B.add(pop(A));
        move(size - 1, C, B, A);
    }

    private Integer pop(List<Integer> a) {
        return a.remove(a.size() - 1);
    }


    public int unequalTriplets(int[] nums) {
        int count = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    if (nums[i] != nums[j] && nums[j] != nums[k] && nums[i] != nums[k]) {
                        System.out.println(nums[i] + " " + nums[j] + " " + nums[k]);
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> nums = new ArrayList<>();
        inorder(root, nums);
        int size = nums.size();
        for (Integer mid : queries) {
            List<Integer> tmp = new ArrayList<>();
            int index = binarySearch(nums, mid);
            if (index == size) {
                tmp.add(nums.get(size - 1));
                tmp.add(-1);
            } else if (nums.get(index).equals(mid)) {
                tmp.add(nums.get(index));
                tmp.add(nums.get(index));
            } else if (index == 0) {
                tmp.add(-1);
                tmp.add(nums.get(0));
            } else {
                tmp.add(nums.get(index - 1));
                tmp.add(nums.get(index));
            }
            ans.add(tmp);
        }
        return ans;
    }

    public int binarySearch(List<Integer> nums, int target) {
        int low = 0;
        int high = nums.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (target < nums.get(mid)) {
                high = mid - 1;
            } else if (target > nums.get(mid)) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return high + 1;
    }

    /**
     * 找到可以插入target的位置，（稳定）
     *
     * @param nums   有序数组
     * @param target 目标数字
     * @return 下标 0 ~ length 之间。
     */
    public int binarySearch(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (target < nums[mid]) {
                high = mid - 1;
            } else if (target > nums[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return high + 1;
    }

    public void inorder(TreeNode root, List<Integer> nums) {
        if (root == null) {
            return;
        }
        inorder(root.left, nums);
        nums.add(root.val);
        System.out.println(root.val);
        inorder(root.right, nums);
    }

    /**
     * TODO
     * @param roads
     * @param seats
     * @return
     */
    public long minimumFuelCost(int[][] roads, int seats) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] line : roads) {
            int keyA = line[0];
            int keyB = line[1];
            List<Integer> valA = map.getOrDefault(keyA, new ArrayList<>());
            List<Integer> valB = map.getOrDefault(keyB, new ArrayList<>());
            valA.add(keyB);
            valB.add(keyA);
            map.put(keyA, valA);
            map.put(keyB, valB);
        }
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        Map<Integer, Integer> height = new HashMap<>();
        height.put(0, 0);
        while (!queue.isEmpty()) {
            int key = queue.poll();
            int h = height.get(key);
            //高度
            List<Integer> list = map.get(key);
            for (Integer e : list) {
                if (height.containsKey(e)) {
                    continue;
                }
                height.put(e, h + 1);
                queue.add(e);
            }
        }
        return seats;
    }

    public static void main(String[] args) {
        Solution1119 so = new Solution1119();
        int[] nums = new int[]{6, 2, 13, 1, 4, 9, 15};
        Arrays.sort(nums);
        so.binarySearch(nums, 16);
    }
}
