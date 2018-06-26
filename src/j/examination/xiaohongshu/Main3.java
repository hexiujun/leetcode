package j.examination.xiaohongshu;

import java.util.Scanner;

/**
 * Created by j on 2017/10/13.
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        int k = in.nextInt();

        double average = 0.0;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        average = (double)sum / k;
        for (int i = k; i < n; i++) {
            sum -= nums[i - k];
            sum += nums[i];
            average = Math.max(average, (double)sum / k);
        }

        System.out.println(average);
    }
}
