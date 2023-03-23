import java.awt.event.MouseAdapter;
import java.util.*;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int n = in.nextInt();
            in.nextLine();
            for (int i = 0; i < n; i++) {
                String str = in.nextLine();
                String[] strs = str.split(", ");
                for (int j = 0; j < strs.length; j++) {
                    if ("1".equals(strs[j]) && j != i) {
                        Set<Integer> set1 = map.getOrDefault(i, new HashSet<>());
                        Set<Integer> set2 = map.getOrDefault(j, new HashSet<>());
                        set1.add(j);
                        set2.add(i);
                        map.put(i, set1);
                        map.put(j, set2);
                    }

                }

            }
            int source = in.nextInt();
            exec(map, n, source);
        }
    }

    public static void exec(Map<Integer, Set<Integer>> map, int n, int source) {
        Queue<Integer> queue = new ArrayDeque<>();
        List<Integer> existed = new ArrayList<>();
        queue.add(source);
        while (!queue.isEmpty()) {
            int val = queue.poll();
            existed.add(val);
            Set<Integer> list = map.getOrDefault(val, new HashSet<>());
            for (int i = 0; i < list.size(); i++) {
                if (existed.isEmpty() || !existed.contains(val)) {
                    queue.add(val);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        Collections.sort(existed);
        for (int i = 0; i < existed.size(); i++) {
            sb.append(existed.get(i));
            if (i == existed.size() - 1) {
                System.out.println("[");
                System.out.println(sb.toString());
                System.out.println("]");
                return;
            }
            sb.append(", ");
        }
    }
}