package j.test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by j on 2017/8/1.
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String s = in.next();
        char[] str = s.toCharArray();
        int n = str.length;
        int[][][] dp = new int[n][n][n + 1];
        dp[n - 1][n - 1][1] = 1;
        for (int i = 0; i < n; i++) {
            dp[i][n - 1][1] = 1;
            dp[n - 1][i][1] = 1;
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                for (int k = 1; k <= n; k++) {
                    dp[i][j][k] = dp[i + 1][j + 1][k - 1] + dp[i + 1][j][k] + dp[i][j + 1][k];
                }
            }
        }

        int[] res = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    res[i] += dp[j][k][i];
                }
            }
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, res[i]);
        }
        System.out.println(ans);
    }
}
