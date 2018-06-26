package j.hihoCoder.offer30;

import java.util.Scanner;

/**
 * Created by j on 2017/10/8.
 * 题库编号：1605
 * 标签：递推公式，快速幂
 */
public class C {
    private static final int Mod = 1000000007;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            int n = in.nextInt();
            if (n == 1)
                System.out.println(1);
            else {
                long[][] a = new long[][]{{3, -1}, {1, 0}};
                int m = n - 1;
                long[][] aM = fastPow(a, m);
                System.out.println(aM[0][0]);
            }
        }

        in.close();
    }

    private static long[][] fastPow(final long[][] a, int n) {
        if (n == 1) return a;

        long[][] a1, a2;
        if (n % 2 != 0) {
            a1 = a;
            a2 = fastPow(a, n - 1);
        } else {
            a1 = fastPow(a, n / 2);
            a2 = a1;
        }

        return multiplyMatrixWithMod(a1, a2);
    }

    private static long[][] multiplyMatrixWithMod(final long[][] a1, final long[][] a2) {
        int len = a1.length;
        long[][] result = new long[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                result[i][j] = 0;
                for (int k = 0; k < len; k++) {
                    result[i][j] = (result[i][j] + a1[i][k] * a2[k][j]) % Mod;
                    result[i][j] = (Mod + result[i][j]) % Mod;
                }
            }
        }
        return result;
    }
}
