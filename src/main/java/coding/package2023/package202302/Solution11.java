package coding.package2023.package202302;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Annie Fang
 * @create 2023/2/11 16:19
 */
public class Solution11 {
    /**
     * 一直取 top2，直到全为0
     *
     * @param amount
     * @return
     */
    public int fillCups(int[] amount) {
        int re = 0;
        Queue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int value : amount) {
            if (value > 0) {
                pq.add(value);
            }
        }

        while (pq.size() > 1) {
            int a = pq.remove();
            int b = pq.remove();
            a--;
            b--;
            re++;
            if (a > 0) {
                pq.add(a);
            }
            if (b > 0) {
                pq.add(b);
            }
        }
        return pq.isEmpty() ? re : re + pq.remove();
    }
}
