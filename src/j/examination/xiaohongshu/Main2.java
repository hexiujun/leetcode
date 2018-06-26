package j.examination.xiaohongshu;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by j on 2017/10/13.
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }

        int[] zeroMoreThan = new int[n + 1];
        int[] oneMoreThan = new int[n + 1];
        Arrays.fill(zeroMoreThan, -1);
        Arrays.fill(oneMoreThan, -1);
        int max = 0, zero = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0)  zero++;
            int dummy = zero - (i + 1 - zero);
            if (dummy == 0) {

            } else if (dummy < 0) {

            } else {

            }
        }

        System.out.println(max);
    }
}
