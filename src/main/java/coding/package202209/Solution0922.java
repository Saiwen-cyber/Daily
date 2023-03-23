package coding.package202209;

import java.util.*;

public class Solution0922 {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        Map<Integer, Integer> mapX = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            mapX.put(arr[i], i);
        }
        for (int[] piece : pieces) {
            if (!mapX.containsKey(piece[0])) {
                return false;
            }
            int pre = mapX.get(piece[0]);
            for (int j = 1; j < piece.length; j++) {
                int cur = mapX.get(piece[j]);
                if (cur != pre + 1) {
                    return false;
                }
                pre = cur;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{91, 4, 64, 78};
        int[][] pieces = new int[][]{{78}, {4, 64}, {91}};
        Solution0922 so = new Solution0922();
        boolean re = so.canFormArray(arr, pieces);
        System.out.println(re);
    }
}