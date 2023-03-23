package coding.package202209;

import java.util.*;

/**
 * @author ASUS
 */
public class Solution0910 {

    int m, n;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> re = new ArrayList<>();
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] atl = new boolean[m][n];
        boolean[][] pac = new boolean[m][n];

        this.m = m;
        this.n = n;
        for (int i = 0; i < m; i++) {
            dfs(i, 0, heights, 0, pac);
        }
        for (int i = 0; i < n; i++) {
            dfs(0, i, heights, 0, pac);
        }
        for (int i = 0; i < m; i++) {
            dfs(i, n - 1, heights, 0, atl);
        }
        for (int i = 0; i < n; i++) {
            dfs(m - 1, i, heights, 0, atl);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (atl[i][j] && pac[i][j]) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    re.add(list);
                }
            }
        }
        return re;
    }

    private void dfs(int x, int y, int[][] heights, int pre, boolean[][] ocean) {
        if (!check(x, y)) {
            return;
        }
        if (heights[x][y] < pre) {
            return;
        }
        if (ocean[x][y]) {
            return;
        }
        ocean[x][y] = true;

        dfs(x - 1, y, heights, heights[x][y], ocean);
        dfs(x, y - 1, heights, heights[x][y], ocean);
        dfs(x + 1, y, heights, heights[x][y], ocean);
        dfs(x, y + 1, heights, heights[x][y], ocean);
    }

    private boolean check(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    //坏果子队列
    Queue<List<Integer>> list = new ArrayDeque<>();
    //标记每个果子变坏的时间
    Map<List<Integer>, Integer> badTimeMap = new HashMap<>();
    //标记数组
    boolean[][] vis;
    //是否在坏果子队列
    boolean[][] bad;
    int blank = 0;

    public int orangesRotting(int[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        vis = new boolean[m][n];
        bad = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    addToList(i, j, 0);
                }
                if (grid[i][j] == 0) {
                    blank++;
                }
            }
        }
        while (!list.isEmpty()) {
            List<Integer> tmp = list.poll();
            //为false ：虽然是坏果子，但是出队了。
            bad[tmp.get(0)][tmp.get(1)] = false;
            //从每个坏果子出发搜索
            dfs(tmp.get(0), tmp.get(1), grid, badTimeMap.get(tmp));
        }


        int badCount = m * n - blank;
        int i;
        int re = -1;
        //全是空白格
        if(blank == m*n) {
            return 0;
        }
        for (int e : badTimeMap.values()) {
            //最后的果子变坏时间
            re = Math.max(e, re);
        }
        //查看是不是每个果子都变坏了
        return badCount == badTimeMap.size() ? re : -1;
    }

    private void addToList(int i, int j, int time) {
        List<Integer> tmp = new ArrayList<>();
        tmp.add(i);
        tmp.add(j);
        list.add(tmp);
        //为true 表示是坏果子，还在队列中。
        bad[i][j] = true;
        badTimeMap.put(tmp, time);
    }

    public void dfs(int x, int y, int[][] grid, int min) {
        //x,y坐标不合法，直接退出
        if (!isLegal(x, y)) {
            return;
        }
        //如果访问过了，不再访问
        if (vis[x][y]) {
            return;
        }
        //如果是1，变为2，状态仍为没访问
        if (grid[x][y] == 1) {
            addToList(x, y, min);
            grid[x][y] = 2;
            return;
        }
        //如果是坏果子且还在坏果子队列，直接退出
        //因为只要在队列的一定会在出队之后，访问
        //现在不去访问，保证坏果子访问顺序。
        //状态仍为没访问
        if (bad[x][y]) {
            return;
        }
        vis[x][y] = true;
        if (grid[x][y] == 2) {
            dfs(x + 1, y, grid, min + 1);
            dfs(x - 1, y, grid, min + 1);
            dfs(x, y - 1, grid, min + 1);
            dfs(x, y + 1, grid, min + 1);
        }
    }
    //检查边界
    boolean isLegal(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }


    public static void main(String[] args) {
        Solution0910 so = new Solution0910();
        int[][] heights = new int[][]{{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}};
//        so.pacificAtlantic(heights);
        int[][] grid = new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
//        grid = new int[][]{{2, 1, 1}, {1, 1, 1}, {0, 1, 2}};

        so.orangesRotting(grid);
    }
}