package coding.package202302;

import coding.TreeNode;


import java.util.*;
import java.util.stream.Collectors;

class Solution15 {
    public static void main(String[] args) {
        Solution15 solution = new Solution15();
        int[] pro = new int[]{4, 7, 8, 15, 3, 5};
        pro = new int[]{557663, 280817, 472963, 156253, 273349, 884803, 756869, 763183, 557663, 964357, 821411, 887849, 891133, 453379, 464279, 574373, 852749, 15031, 156253, 360169, 526159, 410203, 6101, 954851, 860599, 802573, 971693, 279173, 134243, 187367, 896953, 665011, 277747, 439441, 225683, 555143, 496303, 290317, 652033, 713311, 230107, 770047, 308323, 319607, 772907, 627217, 119311, 922463, 119311, 641131, 922463, 404773, 728417, 948281, 612373, 857707, 990589, 12739, 9127, 857963, 53113, 956003, 363397, 768613, 47981, 466027, 981569, 41597, 87149, 55021, 600883, 111953, 119083, 471871, 125641, 922463, 562577, 269069, 806999, 981073, 857707, 831587, 149351, 996461, 432457, 10903, 921091, 119083, 72671, 843289, 567323, 317003, 802129, 612373};
        solution.findValidSplit(pro);
        int[] num = new int[]{1, 2, 0, 0};
        int k = 34;
        solution.addToArrayForm(num, k);
    }

    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> ans = new ArrayList<>();
        int len = num.length;
        int carry = 0;
        int i, j;
        for (i = len - 1; i >= 0 && k != 0; i--) {
            int digit = k % 10;
            int sum = carry + digit + num[i];
            carry = sum / 10;
            ans.add(0, sum % 10);

            k = k / 10;
        }

        while (i >= 0) {
            int sum = carry + num[i];
            carry = sum / 10;
            ans.add(0, sum % 10);
            i--;
        }

        if (carry != 0) {
            ans.add(0, carry);
        }
        Collections.reverse(ans);
        return ans;
    }

    public long kthLargestLevelSum(TreeNode root, int k) {
        Map<Integer, Long> levelSumMap = new HashMap<>();
        dfs(root, 1, levelSumMap);
        List<Long> sumValueList = levelSumMap.values()
                .stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        return k - 1 >= sumValueList.size() ? -1 : sumValueList.get(k - 1);
    }

    public void dfs(TreeNode root, int high, Map<Integer, Long> map) {
        if (Objects.isNull(root)) {
            return;
        }
        long levelSum = map.getOrDefault(high, 0L);
        levelSum += root.val;
        map.put(high, levelSum);
        dfs(root.left, high + 1, map);
        dfs(root.right, high + 1, map);
    }

    /**
     * 找到每个数的质因子，
     * primeFactorIndex
     * @param nums
     * @return
     */
    public int findValidSplit(int[] nums) {
        int len = nums.length;
        long[] prePro = new long[len];
        long[] sufPro = new long[len];
        prePro[0] = nums[0];
        for (int i = 1; i < len; i++) {
            prePro[i] = nums[i] * prePro[i - 1];
        }
        long allPro = prePro[len - 1];
        sufPro[0] = allPro / nums[0];
        if (gcd(sufPro[0], prePro[0]) == 1) {
            return 0;
        }
        for (int i = 1; i < len; i++) {
            sufPro[i] = sufPro[i - 1] / nums[i];
            if (i > len - 2) {
                continue;
            }
            if (gcd(prePro[i], sufPro[i]) == 1) {
                return i;
            }
        }
        return -1;
    }

    public long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

}