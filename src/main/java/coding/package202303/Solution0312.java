package coding.package202303;

import java.util.*;

/**
 * @author Annie Fang
 */
public class Solution0312 {
    public static void main(String[] args) {
        Solution0312 so = new Solution0312();
        int[] nums = new int[]{-687767, -860350, 950296, 52109, 510127, 545329, -291223, -966728, 852403, 828596, 456730, -483632, -529386, 356766, 147293, 572374, 243605, 931468, 641668, 494446};
        System.out.println(so.maxScore(nums));
        nums = new int[]{-32495, -144584, -270506, -394309, -298138, 922535};
        System.out.println(so.maxScore(nums));
        so.maxScore(nums);
    }

    public int maxScoreAc(int[] nums) {
        int zeroCount = 0;
        List<Integer> positive = new ArrayList<>();
        List<Integer> negative = new ArrayList<>();
        for (int num : nums) {
            if (num > 0) {
                positive.add(num);
            } else if (num < 0) {
                negative.add(num);
            } else {
                zeroCount++;
            }
        }
        int posCount = positive.size();
        positive.sort(Collections.reverseOrder());
        negative.sort(Collections.reverseOrder());
        Queue<Integer> positiveQueue = new ArrayDeque<>(positive);
        Queue<Integer> negativeQueue = new ArrayDeque<>(negative);
        int sum = 0;
        while (!negativeQueue.isEmpty()) {
            int negNum = negativeQueue.poll();
            sum += negNum;
            while (sum < 0) {
                if (positiveQueue.isEmpty()) {
                    break;
                }
                sum += positiveQueue.poll();
            }
            if (sum == 0) {
                zeroCount += positiveQueue.isEmpty() ? 0 : 1;
            } else if (sum > 0) {
                posCount++;
            }
        }
        if (posCount > 0) {
            return posCount + zeroCount;
        }
        return 0;
    }

    public int maxScore(int[] nums) {
        int zeroCount = 0;
        Queue<Integer> positive = new PriorityQueue<>((x, y) -> y - x);
        Queue<Integer> negative = new PriorityQueue<>((x, y) -> y - x);
        for (int num : nums) {
            if (num > 0) {
                positive.offer(num);
            } else if (num < 0) {
                negative.offer(num);
            } else {
                zeroCount++;
            }
        }
        int posCount = positive.size();
        int sum = 0;
        while (!negative.isEmpty()) {
            int negNum = negative.poll();
            sum += negNum;
            while (sum < 0) {
                if (positive.isEmpty()) {
                    break;
                }
                sum += positive.poll();
            }
            if (sum == 0) {
                zeroCount += positive.isEmpty() ? 0 : 1;
            } else if (sum > 0) {
                posCount++;
            }
        }
        if (posCount > 0) {
            return posCount + zeroCount;
        }
        return 0;
    }
}