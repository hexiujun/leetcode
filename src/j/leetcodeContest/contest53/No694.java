package j.leetcodeContest.contest53;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by j on 2017/10/8.
 */
public class No694 {
    public int numDistinctIslands(int[][] grid) {
        int numOfDistict = 0;
        if (grid.length == 0 || grid[0].length == 0)
            return numOfDistict;

        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Map<Integer, List<Axis>> map = new HashMap<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0 || visited[i][j]) continue;
                int area = dfs(grid, i, j, visited);
                Axis root = new Axis(i, j, area);
                if (!map.containsKey(area))
                    map.put(area, new ArrayList<Axis>());
                addWithDistinct(root, map, grid);
            }
        }

        for (List<Axis> islands : map.values()) {
            numOfDistict += islands.size();
        }
        return numOfDistict;
    }

    private static void addWithDistinct(Axis root, Map<Integer, List<Axis>> map, int[][] grid) {
        List<Axis> islands = map.get(root.area);
        boolean tag = true;
        for (int i = 0; i < islands.size(); i++) {
            if (isEqual(root, islands.get(i), grid) && isEqual(islands.get(i), root, grid)) {
                tag = false;
                break;
            }
        }
        if (tag) islands.add(root);
    }

    private static boolean isEqual(Axis rootA, Axis rootB, int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        return isEqual0(rootA.x, rootA.y, rootB.x, rootB.y, grid, visited);
    }

    private static boolean isEqual0(int x, int y, int cx, int cy,int[][] grid, boolean[][] visited) {
        visited[x][y] = true;
        boolean tag = true;
        for (int k = 0; k < 4; k++) {
            int nexti = directX[k] + x;
            int nextj = directY[k] + y;
            if (valid(nexti, nextj, grid) && !visited[nexti][nextj]) {
                if (!valid(cx + directX[k], cy + directY[k], grid)) return false;
                tag = isEqual0(nexti, nextj, cx + directX[k], cy + directY[k], grid, visited);
                if (!tag) break;
            }
        }

        return tag;
    }

    private static boolean valid(int x, int y, int[][] grid) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1;
    }

    private static int dfs(int[][] grid, int i, int j, boolean[][] visited) {
        int area = 1;
        visited[i][j] = true;
        for (int k = 0; k < 4; k++) {
            int nexti = directX[k] + i;
            int nextj = directY[k] + j;
            if (valid(nexti, nextj, grid) && !visited[nexti][nextj])
                area += dfs(grid, nexti, nextj, visited);
        }
        return area;
    }

    static int[] directX = new int[]{-1, 1, 0, 0};
    static int[] directY = new int[]{0, 0, -1, 1};

    static class Axis {
        int area;
        int x;
        int y;

        public Axis(int x, int y, int area) {
            this.x = x;
            this.y = y;
            this.area = area;
        }
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1,1,0,1,1},{1,0,0,0,0},{0,0,0,0,1},{1,1,0,1,1}};
        //int[][] grid = new int[][]{{1, 1, 0, 0, 1}, {1, 0, 0, 1, 1}};
        System.out.println(new No694().numDistinctIslands(grid));
    }
}
