package j.examination.meitu;

import java.util.Scanner;

/**
 * Created by j on 2017/10/12.
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            String line = in.nextLine();
            String[] values = line.split(",");
            int[] prefer = new int[n];
            for (int i = 0; i < n; i++) {
                prefer[i] = Integer.parseInt(values[i]);
            }

            long sum = 0, max = 0;
            for (int i = 0; i < n; i++) {
                sum += prefer[i];
                if (sum < 0) {
                    sum = 0;
                }
                max = Math.max(sum, max);
            }

//            long max2 = 0, max3 = 0;
//            if (n == 1) max2 = prefer[0];
//            int p = 0, q = n - 1;
//            while (p < q) {
//                while (p < q)
//            }
//            if (last != -1) {
//
//            }
            long[] sumL = new long[n];
            long[] sumR = new long[n];
            sumL[0] = prefer[0];
            for (int i = 1; i < n; i++) {
                sumL[i] = sumL[i - 1] + prefer[i];
            }
            sumR[n - 1] = prefer[n - 1];
            for (int i = n - 2; i >= 0; i--) {
                sumR[i] = sumR[i + 1] + prefer[i];
            }
            long[] maxR = new long[n + 1];
            //maxR[n - 1] = sumR[n - 1];
            for (int i = n - 1; i >= 0; i--) {
                maxR[i] = Math.max(maxR[i + 1], sumR[i]);
            }
            long max2 = maxR[0];
            for (int i = 0; i < n - 1; i++) {
                max2 = Math.max(max2, sumL[i] + maxR[i + 1]);
            }

            max = Math.max(max, max2);
            System.out.println(max);
        }
    }
}
