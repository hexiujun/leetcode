package j.test;

import java.util.Scanner;

public class Main1 {
    private static int[][] direct = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] container;
    static int maxArea = 0;
    static int count = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int m = in.nextInt();
        int n = in.nextInt();
        in.nextLine();
        container = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            String line = in.nextLine();
            for (int j = 0; j < n; j++) {
                container[i][j] = (line.charAt(j) == '*');
            }
        }

        solve();
        System.out.println(maxArea);

        in.close();
    }

    public static void solve() {
        // selected = new boolean[container.length][container[0].length];

        for (int i = 0; i < container.length; i++) {
            dfs(i, 0);
            dfs(i, container[0].length - 1);
        }
        for (int i = 0; i < container[0].length; i++) {
            dfs(0, i);
            dfs(container.length - 1, i);
        }

        for (int i = 1; i < container.length - 1; i++) {
            for (int j = 1; j < container[0].length - 1; j++) {
                count = 0;
                dfs(i, j);
                if (count > maxArea)
                    maxArea = count;
            }
        }
    }

    static void dfs(int i, int j) {
        if (i < 0 || i >= container.length || j < 0 || j >= container[0].length)
            return;
        if (!container[i][j]) {
            container[i][j] = true;
            count++;
            for (int k = 0; k < direct.length; k++) {
                int i0 = i + direct[k][0];
                int j0 = j + direct[k][1];
                dfs(i0, j0);
            }
        }
    }
}



