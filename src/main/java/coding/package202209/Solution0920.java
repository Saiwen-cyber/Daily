package coding.package202209;

public class Solution0920 {
    boolean flag = false;
    int m = 0;
    int n = 0;

    public boolean exist(char[][] board, String word) {
        String a = "a";
        m = board.length;
        n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(i, j, new StringBuilder(), word, board, new boolean[m][n]);
            }
        }
        return flag;
    }

    int[][] dir = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    void dfs(int x, int y, StringBuilder pre, String word, char[][] board, boolean[][] vis) {
        if (pre.toString().equals(word)) {
            flag = true;
            return;
        }
        if (vis[x][y]) {
            return;
        }
        if (!word.startsWith(pre.toString())) {
            return;
        }
        for (int i = 0; i < 4; i++) {
            int newX = x + dir[i][0];
            int newY = y + dir[i][1];
            if (isLegal(newX, newY)) {
                pre.append(board[x][y]);
                vis[x][y] = true;
                dfs(newX, newY, pre, word, board, vis);
                pre.deleteCharAt(pre.toString().length() - 1);
                vis[x][y] = false;
            }
        }

    }

    boolean isLegal(int x, int y) {
        return x > -1 && x < m && y > -1 && y < n;
    }

    public static void main(String[] args) {

        Solution0920 so = new Solution0920();
        char[][] chars = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}};
        boolean re = so.exist(chars, "ABCC");
        System.out.println(re);
    }


}