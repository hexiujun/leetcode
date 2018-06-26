package j.dynamicProgramming;

import java.util.Arrays;

/**
 * Created by j on 2017/3/7.
 *
 *----------------------------------------------------------------------------
 494. Target Sum Add to List (from leetcode.com)

 Description

 You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -.
 For each integer, you should choose one from + and - as its new symbol.

 Find out how many ways to assign symbols to make sum of integers equal to target S.

 Example 1:
 Input: nums is [1, 1, 1, 1, 1], S is 3.
 Output: 5
 Explanation:

 -1+1+1+1+1 = 3
 +1-1+1+1+1 = 3
 +1+1-1+1+1 = 3
 +1+1+1-1+1 = 3
 +1+1+1+1-1 = 3

 There are 5 ways to assign symbols to make the sum of nums be target 3.
 Note:
 The length of the given array is positive and will not exceed 20.
 The sum of elements in the given array will not exceed 1000.
 Your output answer is guaranteed to be fitted in a 32-bit integer.

 *-----------------------------------------------------------------------------
 * 思路 ： 动态规划的思想。具体来说，记录一个状态表 dp(k, s) 表示数组nums中前k个元素分配"+/-"符号后的加和为s的分配方式的个数。
 *        考虑分配符号给第k个元素，则有2种情况：分配 "+" 或 "-"。则有如下递归表达式：
 *                      dp(k, s) = dp(k - 1, s + nums[k]) + dp(k - 1, s - nums[k]).
 *        递归出口：k == 0时，s = 0，则有dp(k, s) = 1; s != 0, dp(k, s) = 0.
 * 复杂度： 时间复杂度O(n * S)，空间复杂度O(n * S).
 */
public class TargetSum {
    public int findTargetSumWays(int[] nums, int S) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums)
            sum += num;
        if (S < -sum || S > sum) return 0;      // 最小可能值为nums中每个数全加“-”，故加和取值为[-sum, sum]。

        S += sum;                               // 将[-sum, sum] 映射到[0, 2 * sum]以对应非负下标存储状态。实际上，是先将所
                                                // 有num都初始化为-num，然后每次选择当前元素num[i]是否改为+，若是则S需要减去2 *num[i]。
        int[][] dp = new int[n + 1][S + 1];     // 建立状态表，
        solve(nums, n, S, dp);                  // 递归求解状态表

        return dp[n][S] - 1;
    }

    void solve(int[] nums, int n, int s, int[][] dp) {
        if (s < 0) return;
        if (n == 0) {                           // 递归出口
            if (s == 0)
                dp[n][s] = 2;                   // 为了区分dp[i][j]未被访问和已经被访问过但值为0，用了一个+1的技巧，2其实表示方式个数为1
            else
                dp[n][s] = 1;                   // 1 其实表示 0，即不存在加和为s
        } else {
            int way = 1;

            // 将nums[n]的符号设置为“+”，计算对应子问题的分配方式的个数
            int preS = s - (nums[n - 1] << 1);
            if (preS >= 0) {
                if (dp[n - 1][preS] == 0)      // 值为0表示还没计算过，需要递归
                    solve(nums, n - 1, preS, dp);
                way += dp[n - 1][preS] - 1;
            }

            // 将nums[n]的符号设置为“-”
            if (dp[n - 1][s] == 0)
                solve(nums, n - 1, s, dp);
            way += dp[n - 1][s] - 1;

            dp[n][s] = way;
        }
    }
}
