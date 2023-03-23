package coding.package202210;

import java.util.*;

public class Solution1022 {
    public double myPowPos(double x, int n) {
        if (n == 0) {
            return x;
        }
        return x * myPowPos(x, n - 1);
    }

    public double myPowNeg(double x, int n) {
        return 1 / myPowPos(x, n);
    }

    public boolean verifyPostorder(int[] postorder) {
        if (postorder.length <= 1) {
            return true;
        }
        int len = postorder.length;
        int root = postorder[len - 1];
        int[] left = {};
        int[] right = {};
        for (int i = 0; i < len - 1; i++) {
            if (postorder[i] > root) {
                left = Arrays.copyOfRange(postorder, 0, i);
                right = Arrays.copyOfRange(postorder, 0, len - 1);
            }
        }
        return verifyPostorder(left) && verifyPostorder(right);
    }
}