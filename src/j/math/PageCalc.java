package j.math;

import java.util.*;

public class PageCalc {
    static char[] num;
    static long[] B;
    static long[] B0;
    static int[] pow10;
    static int page;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        page = in.nextInt();

        num = String.valueOf(page).toCharArray();
        int wei = num.length;
        long[] res = new long[10];
        if (wei < 2) {
            for (int i = 1; i < 10; i++) {
                if (i <= page) res[i] = 1;
            }
            System.out.print("" + res[0]);
            for (int i = 1; i < 10; i++) {
                System.out.print(" " + res[i]);
            }
            System.out.println();
            return;
        }

        pow10 = new int[wei];
        pow10[0] = 1;
        for (int i = 1; i < wei; i++)
            pow10[i] = 10 * pow10[i - 1];

        B = new long[wei];
        B[1] = 1;
        for (int i = 2; i < wei; i++)
            B[i] = 10 * B[i - 1] + pow10[i - 1];

        B0 = new long[wei];
        B0[1] = 0;
        for (int i = 2; i < wei; i++) {
            long incre = 0;
            for (int k = 1; k < i; k++)
                incre += (pow10[k] - pow10[k - 1]) * (i - 1 - k);
            incre += i - 1;
            B0[i] = 10 * B0[i - 1] + 9 * incre;
        }


        res[0] = calc0(wei) + B0[wei - 1];
        for (int digit = 1; digit < 10; digit++) res[digit] = calc(digit, wei);

        System.out.print("" + res[0]);
        for (int i = 1; i < 10; i++) {
            System.out.print(" " + res[i]);
        }
        System.out.println();
    }

    public static long calc0(int n) {
        if (n == 1) return 0;
        int an = num[num.length - n] - '0';
        long ans = 0;
        ans += (an - 1) * B0[n - 1];
        if (an > 1) {
            long incre = 0;
            for (int k = 1; k < n; k++)
                incre += (pow10[k] - pow10[k - 1] ) * (n - 1 - k);
            incre += n - 1;
            ans += (an - 1) * incre;
        }
        int pre = n - 1;
        while (pre >= 1 && num[num.length - pre] == '0') pre--;
        if (pre < 1)
            ans += n - 1;
        else {
            long incre = 0;
            for (int k = 1; k < pre; k++)
                incre += (pow10[k] - pow10[k - 1]) * (n - 1 - k);
            incre += n - 1;
            ans += calc0(pre) + incre + B0[pre - 1] + (page % pow10[pre] + 1 - pow10[pre - 1]) * (n - 1 - pre);
        }

        return ans;
    }

    public static long calc(int digit, int n) {
        int an = num[num.length - n] - '0';
        if (n == 1)
            return digit <= an ? 1 : 0;
        if (an == 0)
            return calc(digit, n - 1);
        long ans = 0;
        ans += an * B[n - 1];
        if (digit < an) {
            ans += pow10[n - 1];
        } else if (digit == an) {
            ans += 1 + page % pow10[n - 1];
        }
        ans += calc(digit, n - 1);

        return ans;
    }
}

