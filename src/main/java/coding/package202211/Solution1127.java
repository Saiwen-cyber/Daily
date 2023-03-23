package coding.package202211;

public class Solution1127 {
    public static void main(String[] args) {
        Solution1127 so = new Solution1127();
        so.calPrefixSum(4);
    }

    private int[] calPrefixSum(int n) {
        int[] preSum = new int[n + 1];
        preSum[1] = 0;
        for (int i = 1; i <= n + 1; i++) {
            preSum[i + 1] = preSum[i] + i;
        }
        return preSum;
    }

    public int pivotInteger(int n) {
        int[] preSum = calPrefixSum(n);
        for (int i = 0; i < n + 1; i++) {
            if (preSum[i] + preSum[i + 1] == preSum[n + 1]) {
                return i;
            }
        }
        return -1;
    }

    public int appendCharacters(String s, String t) {
/*        "vrykt"
        "rkge"*/
        int i = 0;
        int j = 0;
        while (i < s.length() && j < t.length()) {
            char a = s.charAt(i);
            char b = t.charAt(j);
            if (a == b) {
                i++;
                j++;
            } else {
                i++;
            }
        }
        return t.length() - j;
    }

}