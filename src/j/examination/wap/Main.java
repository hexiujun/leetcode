package j.examination.wap;

import java.util.Scanner;

/**
 * Created by j on 2017/9/24.
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt();
        String[] board = new String[n];
        for (int i = 0; i < n; i++) {
            board[i] = in.next();
        }

        int[][][][] dp = new int[n + 1][n + 1][m + 1][m + 1];

        int[][] dpCol = new int[n][m];
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                int coin = (board[i].charAt(j) == 'o') ? 1 : 0;
                if (i == 0)
                    dpCol[i][j] = coin;
                else
                    dpCol[i][j] = dpCol[i - 1][j] + coin;
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= n - i; j++) {
                for (int k = 1; k <= m; k++) {
                    for (int l = m - k; l >= 0; l--) {
                        // dp[j][n - i - j][l][m - l - k]
                        int delt = dpCol[j + i - 1][l];
                        if (j - 1 >= 0) delt -= dpCol[j - 1][l];
                        dp[j][n - i - j][l][m - l - k] = dp[j][n - i - j][l + 1][m - l - k] + delt;
                        if (dp[j][n - i - j][l][m - l - k] == k)
                            ans = Math.min(ans, n - i + m - k + Math.min(j, n - i - j) + Math.min(l, m - l - k));

                    }
                }
            }
        }

        if (ans != Integer.MAX_VALUE)
            System.out.println(ans);
        else
            System.out.println("-1");

        in.close();
    }
}
