package coding.package202209;

import java.util.*;

/**
 * @author fang
 */
public class Solution0906 {
    public int uniqueLetterString(String s) {
        int len = s.length();
        int sum = 0;
        //字符s[i]有贡献(在字符串中是唯一字符)的子串 起止点。
        int[] l = new int[len];
        int[] r = new int[len];

        Arrays.fill(l, -1);
        Arrays.fill(r, len);

        for (int i = 0; i < len; i++) {
            char key = s.charAt(i);
            if (i != 0) {
                int index = s.lastIndexOf(key, i - 1);
                l[i] = index < 0 ? -1 : index;
            }
            if (i != len - 1) {
                int index = s.indexOf(key, i + 1);
                r[i] = index < 0 ? len : index;
            }
            sum += (i - l[i]) * (r[i] - i);
        }
        return sum;
    }

    public int maxProduct(int[] nums) {
        int len = nums.length;
        int[] dpMin = new int[len];
        int[] dpMax = new int[len];

        int re = 0;
        dpMin[0] = nums[0];
        dpMax[0] = nums[0];
        re = nums[0];
        for (int i = 1; i < len; i++) {
            if (nums[i] > 0) {
                dpMin[i] = Math.min(dpMin[i - 1] * nums[i], nums[i]);
                dpMax[i] = Math.max(dpMax[i - 1] * nums[i], nums[i]);
            } else {
                dpMin[i] = Math.min(dpMax[i - 1] * nums[i], nums[i]);
                dpMax[i] = Math.max(dpMin[i - 1] * nums[i], nums[i]);
            }
            int max = Math.max(dpMin[i - 1] * nums[i], dpMax[i - 1] * nums[i]);
            max = Math.max(max, nums[i]);
            re = Math.max(re, max);
            System.out.println(dpMax[i]);
            System.out.println(dpMin[i]);

            System.out.println(re);

        }
        return re;
    }

    /*
        //有向图的拓扑排序
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            //入度
            int[] indegree = new int[numCourses];
            //拓扑顺序
            int[] topo = new int[numCourses];
            //存放入度为0的顶点
            Stack<Integer> stack = new Stack<>();
            int count = 0;
            //求入度
            for (int[] cp : prerequisites) {
                indegree[cp[0]]++;
            }
            //入度为0的进栈
            for (int i = 0; i < numCourses; i++) {
                if (indegree[i] == 0) {
                    stack.push(i);
                }
            }
            while (!stack.isEmpty()) {
                int val = stack.pop();
                topo[count] = val;
                for (int[] cp : prerequisites) {
                    if (cp[1] == val) {
                        indegree[cp[0]]--;
                        if (indegree[cp[0]] == 0) {
                            stack.push(cp[0]);
                        }
                    }
                }
                count++;
            }
            if (count != numCourses) {
                return new int[]{};
            } else {
                return topo;
            }
        }
    */
//有向图的拓扑排序
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //入度
        int[] indegree = new int[numCourses];
        //存储入度为i的顶点，不用遍历查找
        Map<Integer, List<Integer>> map = new HashMap<>();
        //拓扑顺序
        int[] topo = new int[numCourses];
        //存放入度为0的顶点
        Stack<Integer> stack = new Stack<>();
        //输出顶点数；
        int count = 0;
        //求入度
        for (int[] cp : prerequisites) {
            indegree[cp[0]]++;
            int key = cp[1];
            List<Integer> val = map.getOrDefault(key, new ArrayList<>());

            val.add(cp[0]);
            map.put(key, val);
        }
        //入度为0的进栈
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                stack.push(i);
            }
        }
        while (!stack.isEmpty()) {
            int key = stack.pop();
            topo[count] = key;
            List<Integer> list = map.getOrDefault(key, new ArrayList<>());
            while (!list.isEmpty()) {
                int next = list.get(0);
                indegree[next]--;
                if (indegree[next] == 0) {
                    stack.push(next);
                }
                list.remove(0);
            }
            count++;
        }
        //如果输出顶点数 < 图中顶点数，则存在环；
        if (count != numCourses) {
            return new int[]{};
        } else {
            return topo;
        }
    }

    public static void main(String[] args) {
        Solution0906 so = new Solution0906();

        int[] nums = new int[]{-1, -2, -9, -6};
        int[][] prerequisites = new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}};

        so.findOrder(4, prerequisites);
    }


}
