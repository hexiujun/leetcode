package j.hihoCoder.offer29;

import java.util.Scanner;

/**
 * Created by j on 2017/10/1.
 */
public class A {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while(t-- > 0) {
            int x = in.nextInt(), y = in.nextInt();
            int a = in.nextInt(), b = in.nextInt();

            boolean tag = false;
            while (a > x && b > y) {
                if (a == b)
                    a -= b;
                else if (a > b) {
                    a = update(a, b, x);
                } else {
                    b = update(b, a, y);
                }
            }
            if (a == x) {
                if (b > a)
                    b = update(b, a, y);
                tag = (b == y);
            } else if (b == y) {
                if (a > b)
                    a = update(a, b, x);
                tag = (a == x);
            }

            System.out.println(tag ? "YES" : "NO");
        }

        in.close();

    }

    private static int update(int a, int b, int x) {
        if (b > x)
            a %= b;
        else {
            if ((a - x) % b == 0)
                a = x;
            else
                a -= ((a - x) / b + 1) * b;
        }

        return a;
    }
}
