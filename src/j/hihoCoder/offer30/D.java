package j.hihoCoder.offer30;

import java.util.Scanner;

/**
 * Created by j on 2017/10/8.
 */
public class D {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int t = in.nextInt();
        while (in.hasNext() && t-- > 0) {
            int n = in.nextInt();
            int a = 2, b = 3;
            boolean tag = false;
            for (a = 2; a < n - 1; a++) {
                for (b = a + 1; b < n; b++) {
                    if (((long)a * b) % n == 1) {
                        tag = true;break;
                    }
                }
                if (tag) break;
            }

            if (tag) {
                System.out.println(1 + " " + a + " " + b);
            } else System.out.println("-1");
        }

        in.close();
    }
}
