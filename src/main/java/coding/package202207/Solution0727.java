package coding.package202207;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fang
 */
public class Solution0727 {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int [][]direction = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        List<Integer> list = new ArrayList<>(m*n);
        int dir = 0;
        int row = 0;
        int column = 0;
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;
        list.add(matrix[0][0]);
        while(list.size() < m*n) {
            int newRow = row + direction[dir][0];
            int newColumn = column + direction[dir][1];
            if(isLegal(newRow,newColumn,m,n) && !visited[newRow][newColumn]) {
                row = newRow;
                column = newColumn;
                visited[row][column] = true;
                list.add(matrix[row][column]);
            }else {
                dir++;
                dir = dir % 4;
            }
        }
        return list;
    }

    public boolean isLegal(int row, int column, int m, int n) {
        if (row < 0 || column < 0 || row >= m || column >= n) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        Solution0727 solution = new Solution0727();
        solution.spiralOrder(matrix);
    }
}
