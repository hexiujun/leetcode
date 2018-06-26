package j.devideAndConquer;

import java.util.Scanner;

/**
 * Created by j on 2017/3/6.
 *
 * 327. Count of Range Sum
 *      ---(from leetcode :  https://leetcode.com/problems/count-of-range-sum/?tab=Description)
 * Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
 * Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.
 *
 * Note:
 * A naive algorithm of O(n2) is trivial. You MUST do better than that.
 *
 * Example:
 * Given nums = [-2, 5, -1], lower = -2, upper = 2,
 * Return 3.
 * The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.
 *
 * My solution :
 *  思路：分治的思想。在归并排序的过程中，同时计算满足条件的range sums的个数。
 *  Time Complexity: O(nlogn)；Space Complexity: O(n)
 */
public class CountOfRangeSum {
    public static void main(String[] args) {
        CountOfRangeSum s = new CountOfRangeSum();
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        while (T-- > 0) {
            int[] nums = new int[scanner.nextInt()];
            for (int i = 0; i < nums.length; i++)
                nums[i] = scanner.nextInt();
            int lower = scanner.nextInt();
            int upper = scanner.nextInt();
            System.out.println(s.countRangeSum(nums, lower, upper));
        }

    }

    public int countRangeSum(int[] nums, int lower, int upper) {
        if(nums.length == 0) return 0;

        int len = nums.length;
        long[] sum = new long[len];
        sum[0] = nums[0];
        for(int i = 1; i < len; i++)
            sum[i] = sum[i-1] + nums[i];

        int ans = 0;
        for (int i = 0; i < len; i++) {
            if (sum[i] >= lower && sum[i] <= upper)
                ans++;
        }
        ans += bisection(sum, 0, len - 1, lower, upper, nums);
        return ans;
    }

    public int bisection(long[] sum, int begin, int end, int lower, int upper, int[] nums) {
        if (begin == end) return 0;

        int ans = 0;
        int mid = (begin + end) >> 1;
        ans += bisection(sum, begin, mid, lower, upper, nums);
        ans += bisection(sum, mid + 1, end, lower, upper, nums);

        int lowerIndex = mid + 1, upperIndex;
        for (int i = begin; i <= mid; i++) {
            long low = lower + sum[i];
            long up = upper + sum[i];
            while (lowerIndex <= end && sum[lowerIndex] < low) lowerIndex++;
            if (lowerIndex == end + 1) break;
            upperIndex = lowerIndex;
            while (upperIndex <= end && sum[upperIndex] <= up) upperIndex++;
            upperIndex--;
            if (lowerIndex <= upperIndex)
                ans += upperIndex - lowerIndex + 1;
        }

        //merge
        long[] tmpArr = new long[end - begin + 1];
        int i = begin, j = mid + 1, tmpIndex = 0;
        while (i <= mid && j <= end) {
            if (sum[i] <= sum[j]) {
                tmpArr[tmpIndex++] = sum[i++];
            }
            else {
                tmpArr[tmpIndex++] = sum[j++];
            }
        }
        while (i <= mid)
            tmpArr[tmpIndex++] = sum[i++];
        while (j <= end)
            tmpArr[tmpIndex++] = sum[j++];
        for (tmpIndex = 0; tmpIndex < tmpArr.length; tmpIndex++)
            sum[tmpIndex + begin] = tmpArr[tmpIndex];

        return ans;
    }

}
