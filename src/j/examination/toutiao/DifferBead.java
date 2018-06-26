package j.examination.toutiao;

import java.util.Scanner;

/**
 * Created by j on 2017/9/10.
 */
public class DifferBead {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int c = in.nextInt();
        int[][] nums = new int[n][];
        for (int i = 0; i < n; i++) {
            nums[i] = new int[in.nextInt()];
            for (int j = 0; j < nums[i].length; j++) {
                nums[i][j] = in.nextInt();
            }
        }

        if (m > n) m = n;
        boolean[][] own = new boolean[c + 1][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                own[nums[i][j]][i] = true;
            }
        }

        int ans = 0;
        for (int color = 1; color <= c; color++) {
            int cal = 0;
            for (int i = 0; i < m; i++) {
                cal += (own[color][i] ? 1 : 0);
            }
            if (cal > 1) {
                ans++;
                continue;
            }

            for (int i = 1; i < n; i++) {
                cal -= (own[color][i - 1] ? 1 : 0);
                int tmp = (i + m - 1) % n;
                cal += (own[color][tmp] ? 1 : 0);
                if (cal > 1) {
                    ans++;
                    break;
                }
            }
        }

        System.out.println(ans);
    }
}
