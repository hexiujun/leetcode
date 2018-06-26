package j.hihoCoder.offer30;

import java.util.Scanner;

/**
 * Created by j on 2017/10/8.
 */
public class B {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[] price = new int[n];
        for (int i = 0; i < n; i++) {
            price[i] = in.nextInt();
        }

        int[] max = new int[n];
        max[n - 1] = price[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            max[i] = Math.max(price[i], max[i + 1]);
        }

        int ans = 0;
        for (int i = 0; i < max.length; i++) {
            ans += max[i] - price[i];
        }
        System.out.println(ans);
    }
}
