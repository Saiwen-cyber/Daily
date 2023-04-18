package coding.package2022.package202210;

import java.util.*;

public class Solution1025 {
    int[] x = new int[]{1, -1, 0, 0};
    int[] y = new int[]{0, 0, 1, -1};
    int m, n;

    public int shortestBridge(int[][] grid) {
        int min = Integer.MAX_VALUE;
        m = grid.length;
        n = grid[0].length;

        Queue<int[]> queue = new ArrayDeque<>();
        boolean flag = false;
        for (int i = 0; i < m && !flag; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                dfs(grid, i, j, queue);
                flag = true;
                break;
            }
        }
        boolean[][] vis = new boolean[m][n];
        while (!queue.isEmpty()) {
            int[] nums = queue.poll();
            if (vis[nums[0]][nums[1]]) {
                continue;
            }
            vis[nums[0]][nums[1]] = true;

            for (int i = 0; i < 4; i++) {
                int newX = nums[0] + x[i];
                int newY = nums[1] + y[i];
                if (isLegal(newX, newY) && grid[newX][newY] != -1) {
                    if (grid[newX][newY] == 1) {
                        min = Math.min(nums[2], min);
                    }
                    int[] tmp = new int[]{newX, newY, nums[2] + 1};
                    queue.add(tmp);
                }
            }
        }
        return min;
    }

    //找到第一个岛 标记为-1;
    public void dfs(int[][] grid, int a, int b, Queue<int[]> queue) {
        if (grid[a][b] == -1) {
            return;
        }
        grid[a][b] = -1;
        queue.add(new int[]{a, b, 0});
        for (int i = 0; i < 4; i++) {
            int newX = a + x[i];
            int newY = b + y[i];
            if (isLegal(newX, newY) && grid[newX][newY] == 1) {
                dfs(grid, newX, newY, queue);
            }
        }
    }

    public boolean isLegal(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    public int search(int[] arr, int target) {
        int split = findP(arr);
        int[] left = Arrays.copyOfRange(arr, 0, split + 1);
        int[] right = Arrays.copyOfRange(arr, split + 1, arr.length);

        int index1 = left.length + leftBound(right, target);
        int index2 = leftBound(left, target);

        return index1 == -1 ? index2 : Math.min(index1, index2);
    }

    public int leftBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] == target) {
                right = mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            }
        }
        //没找到
        if (left == arr.length || arr[left] != target) {
            return -1;
        }
        return left;
    }

    public int findP(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        if (nums[left] < nums[right]) {
            return left;
        }

        while (left < nums.length - 1 && right > 0) {
            if (nums[left + 1] >= nums[left]) {
                left++;
            } else {
                return left;
            }
            if (nums[right - 1] <= nums[right]) {
                right--;
            } else {
                return right;
            }
        }
        return left;
    }

    public static void main(String[] args) {

        Solution1025 so = new Solution1025();
//        int i = so.shortestBridge(new int[][]{{0, 1}, {1, 0}});
//        System.out.println(i);
//        so.search(new int[]{15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14,}, 5);
        so.search(new int[]{5, 5, 5, 1, 3, 4, 5}, 5);

    }
}