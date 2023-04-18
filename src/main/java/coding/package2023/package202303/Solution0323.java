package coding.package2023.package202303;

import coding.UnionFind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution0323 {
    public static void main(String[] args) {
        int[] nums = new int[]{4, 6, 5, 9, 3, 7}, l = new int[]{0, 0, 2}, r = new int[]{2, 3, 5};
        Solution0323 so = new Solution0323();
        so.checkArithmeticSubarrays(nums, l, r);
    }

    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> res = new ArrayList<>();
        for (int i = 0; i < l.length; i++) {
            int[] subArray = Arrays.copyOfRange(nums, l[i], r[i] + 1);
            res.add(isArithmeticArray(subArray));
        }
        return res;
    }

    boolean isArithmeticArray(int[] array) {
        Arrays.sort(array);
        if (array.length <= 2) {
            return true;
        }
        int d = array[1] - array[0];
        for (int i = 2; i < array.length; i++) {
            if (array[i] - array[i - 1] != d) {
                return false;
            }
        }
        return true;
    }

    /**
     * 返回让整个网络connected的操作次数
     *
     * @param n           节点个数
     * @param connections 相连的边
     * @return the operation to make connected
     */
    public int makeConnected(int n, int[][] connections) {
        UnionFind uf = new UnionFind(n, connections);
        if (uf.count == 1) {
            //自身已经连通
            return 0;
        }
        if (connections.length < n - 1) {
            return -1;
        }
        return uf.count - 1;
    }

}