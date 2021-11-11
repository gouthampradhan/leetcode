package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 15/02/2018. Say you have an array for which the ith element is
 * the price of a given stock on day i.
 *
 * <p>Design an algorithm to find the maximum profit. You may complete at most two transactions.
 *
 * <p>Note: You may not engage in multiple transactions at the same time (ie, you must sell the
 * stock before you buy again).
 *
 * <p>Solution: O(n): In the first iteration calculate the max profit that can be made by one buy
 * and sell by iterating from right to left and saving this in a dp array and maintaining a max
 * value. In the second iteration starting from left to right maintain a min value and calculate the
 * max profit that can be made by one buy and sell by taking the difference of current with min and
 * calculate the total profit of two transactions by summing up the current profit made with the
 * profit in dp array.
 */
public class BestTimeToBuyAndSellStockIII {

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) throws Exception {
    int[] A = {10, 9, 8, 7};
    System.out.println(new BestTimeToBuyAndSellStockIII().maxProfit(A));
  }

  public int maxProfit(int[] prices) {
    if (prices.length == 0 || prices.length == 1) return 0;
    int[] dp = new int[prices.length];
    int min = prices[0];
    int max = prices[prices.length - 1];
    for (int i = prices.length - 2; i >= 0; i--) {
      dp[i] = Math.max(max - prices[i], dp[i + 1]);
      max = Math.max(max, prices[i]);
    }
    max = Integer.MIN_VALUE;
    for (int i = 0; i < prices.length; i++) {
      max = Math.max(max, prices[i] - min + dp[i]);
      min = Math.min(min, prices[i]);
    }
    return max;
  }
}
