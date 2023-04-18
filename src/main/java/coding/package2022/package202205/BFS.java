package coding.package2022.package202205;

import java.util.*;
import java.util.stream.Collectors;

class BfsSolution {
    //n 是节点数 
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        Map<Integer, List<Integer>> map = save(graph);
        return findbyBFS(start, target,map);
    }
    // Map<Integer,List<Integer>> map  存储邻接表 （有向图）
    public  Map<Integer,List<Integer>> save(int[][] graph){
        Map<Integer,List<Integer>> map = new HashMap<>();

        for(int i = 0; i<graph.length; i++){
            int key = graph[i][0];
            List<Integer> list = map.containsKey(key) ? map.get(key) : new ArrayList<>();
            list.add(graph[i][1]);
            map.put(key,list);
        }

        return map;
    }
    // bfs 寻找 路径
    public boolean findbyBFS(int start, int target, Map<Integer,List<Integer>> map){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while(!queue.isEmpty()){
            List list = map.get(start);
            list.stream().distinct().collect(Collectors.toList());
            if(list.contains(target)){
                return true;
            }else{
                queue.poll();
                queue.addAll(list);
            }
        }
        return false;
    }
}