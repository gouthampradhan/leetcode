package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 17/03/2017. Say you have an array for which the ith element is
 * the price of a given stock on day i.
 *
 * <p>If you were only permitted to complete at most one transaction (ie, buy one and sell one share
 * of the stock), design an algorithm to find the maximum profit.
 *
 * <p>Example 1: Input: [7, 1, 5, 3, 6, 4] Output: 5
 *
 * <p>max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
 * Example 2: Input: [7, 6, 4, 3, 1] Output: 0
 *
 * <p>In this case, no transaction is done, i.e. max profit = 0.
 */
public class BestTimeToBuyAndSellStocks {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] prices = {1, 1, 1, 1, 1};
    System.out.println(new BestTimeToBuyAndSellStocks().maxProfit(prices));
  }

  public int maxProfit(int[] prices) {
    if (prices.length == 0) return 0;
    int[] max = new int[prices.length];
    max[prices.length - 1] = prices[prices.length - 1];
    for (int i = prices.length - 2; i >= 0; i--) {
      max[i] = Math.max(prices[i], max[i + 1]);
    }
    int result = Integer.MIN_VALUE;
    for (int i = 0, l = max.length; i < l; i++) {
      result = Math.max(result, max[i] - prices[i]);
    }
    return result;
  }
}
