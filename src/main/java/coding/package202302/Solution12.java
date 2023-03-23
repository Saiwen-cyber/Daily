package coding.package202302;

import java.util.*;

/**
 * @author Annie Fang
 * @create 2023/2/12 23:11
 */
public class Solution12 {
    public static void main(String[] args) {
        int[][] roads = new int[][]{{0,1},{0,2},{0,3}};
        Solution12 solution = new Solution12();
        solution.minimumFuelCost(roads,5);
    }
    int fuel = 0;
    /**以captical为root,根据road相连接形成一颗tree,递归计算到达每一个city时，消耗的步骤size，结果为 size之和 */
    public long minimumFuelCost(int[][] roads, int seats) {
        //邻接表
        Map<Integer,List<Integer>> map = new HashMap<>();
        for(int[] road : roads) {
            List<Integer> roadA = map.getOrDefault(road[0],new ArrayList<>());
            List<Integer> roadB = map.getOrDefault(road[1],new ArrayList<>());

            roadA.add(road[1]);
            roadB.add(road[0]);

            map.put(road[0],roadA);
            map.put(road[1],roadB);
        }
        return dfs(map,seats,-1,0);
    }

    public long dfs(Map<Integer,List<Integer>> map,int seats,int parent,int node) {
        int rep = 1;
        List<Integer> childList = map.getOrDefault(node,new ArrayList<>());
        for(int child : childList) {
            if(child == parent) {
                continue;
            }
            rep += dfs(map,seats,node,child);
        }
        // rep = 1 seats = 5 举例计算
        //fuel the minimum fuel to the root，when node = root ，no need.
        fuel += Math.ceil((double) rep / seats);
        return fuel;
    }
}
