package j.dynamicProgramming;

import java.util.Scanner;

/**
 * Created by j on 2017/10/14.
 */
public class AlternatingSum {
    private static final int MOD = 1000000000 + 7;
    private static long l;
    private static long r;
    private static int k;
    private static int[] value;
    private static int sum;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        l = in.nextLong();
        r = in.nextLong();
        k = in.nextInt();
        solve();

        System.out.println(sum);
        in.close();
    }

    private static void solve() {
        boolean[][] dp = new boolean[18][201];
        for (int i = 0; i < 10; i++) {
            dp[0][i + 100] = true;
        }
        for (int t = 1; t < dp.length; t++) {
            for (int i = 0; i < dp[0].length; i++) {
                for (int j = 0; j < 10; j++) {
                    int v = (t % 2 == 0) ? j : -j;
                    if (i - v >= 0 && i - v < dp[0].length && dp[t - 1][i - v]) {
                        dp[t][i] = true;
                        break;
                    }
                }
            }
        }

        sum = 0;
        if (k == 1 && r == 1000000000000000000l) {
            sum += 1;
            r--;
        }

        value = new int[dp.length];
        int len1 = String.valueOf(l).length(), len2 = String.valueOf(r).length();
        for (int i = len1; i <= len2; i++) {
            if (dp[i - 1][k + 100])
                dfs(i - 1, k + 100, dp, i);
        }
    }

    private static void dfs(int loc, int k, boolean[][] dp, int len) {
        if (loc == 0) {
            for (int i = 0; i < 10; i++) {
                if (k == i + 100) {
                    value[loc] = i;
//                    for (int j = 0; j < value.length; j++) {
//                        System.out.print(value[j]);
//                    }
//                    System.out.println();
                    sum = (sum + sumTo(len)) % MOD;
                    break;
                }
            }
            return;
        }

        for (int i = 0; i < 10; i++) {
            int v = (loc % 2 == 0) ? i : -i;
            if (k - v >= 0 && k - v < dp[0].length && dp[loc - 1][k - v]) {
                value[loc] = i;
                dfs(loc - 1, k - v, dp, len);
            }
        }
    }

    private static int sumTo(int len) {
        if (value[0] == 0) return 0;

        long locSum = 0;
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < len; i++) {
            buf.append(value[i]);
        }
        //System.out.println(buf.toString());
        locSum = Long.parseLong(buf.toString());
        if (locSum >= l && locSum <= r)
            return (int)(locSum % MOD);

        return 0;
    }
}
