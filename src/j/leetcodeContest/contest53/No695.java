package j.leetcodeContest.contest53;

/**
 * Created by j on 2017/10/8.
 */
public class No695 {
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        if (grid.length == 0 || grid[0].length == 0)
            return maxArea;

        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0 || visited[i][j]) continue;
                maxArea = Math.max(maxArea, dfs(grid, i, j, visited));
            }
        }

        return maxArea;
    }

    private static int dfs(int[][] grid, int i, int j, boolean[][] visited) {
        int area = 1;
        visited[i][j] = true;
        for (int k = 0; k < 4; k++) {
            int nexti = directX[k] + i;
            int nextj = directY[k] + j;
            if (nexti >= 0 && nexti < grid.length && nextj >= 0 && nextj < grid[0].length
                    && grid[nexti][nextj] != 0 && !visited[nexti][nextj])
                area += dfs(grid, nexti, nextj, visited);
        }
        return area;
    }

    static int[] directX = new int[]{-1, 1, 0, 0};
    static int[] directY = new int[]{0, 0, -1, 1};
}
