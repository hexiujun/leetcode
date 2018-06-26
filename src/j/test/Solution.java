package j.test;

class Solution {
    public int maxProfit(int[] prices, int fee) {
        return Math.max(recursive(prices, 1, true, prices[0], fee),
                recursive(prices, 1, false, 0, fee));
    }

    private int recursive(int[] prices, int currentDay, boolean ownStock, int p, int fee) {
        if (currentDay >= prices.length) return 0;

        if (ownStock)
            return Math.max(recursive(prices, currentDay + 1, false, 0, fee) + prices[currentDay] - p - fee,
                    recursive(prices, currentDay + 1, ownStock, p, fee));
        else
            return Math.max(recursive(prices, currentDay + 1, false, 0, fee),
                    recursive(prices, currentDay + 1, true, prices[currentDay], fee));
    }
}