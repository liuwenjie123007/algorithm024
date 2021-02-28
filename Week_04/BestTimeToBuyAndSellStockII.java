package Week_04;

/**
 * <h1>122. 买卖股票的最佳时机 II</h1>
 * <a>https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/description/</a>
 * <h3>解法：时间刺客大法，如果今天股票涨了，就回到明天买股票，今天卖出</h3>
 * <p>时间复杂度O(n), 空间复杂度O(1)</p>
 * @author WENJIE
 */
public class BestTimeToBuyAndSellStockII {
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) return 0;
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                profit += prices[i] - prices[i - 1];
        }
        return profit;
    }
}
