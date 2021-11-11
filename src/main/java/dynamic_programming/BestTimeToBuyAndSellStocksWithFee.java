package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 14/12/2017. Your are given an array of integers prices, for
 * which the i-th element is the price of a given stock on day i; and a non-negative integer fee
 * representing a transaction fee.
 *
 * <p>You may complete as many transactions as you like, but you need to pay the transaction fee for
 * each transaction. You may not buy more than 1 share of a stock at a time (ie. you must sell the
 * stock share before you buy again.)
 *
 * <p>Return the maximum profit you can make.
 *
 * <p>Example 1: Input: prices = [1, 3, 2, 8, 4, 9], fee = 2 Output: 8 Explanation: The maximum
 * profit can be achieved by: Buying at prices[0] = 1 Selling at prices[3] = 8 Buying at prices[4] =
 * 4 Selling at prices[5] = 9 The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8. Note:
 *
 * <p>0 < prices.length <= 50000. 0 < prices[i] < 50000. 0 <= fee < 50000.
 *
 * <p>Solution: O(n) for every step either you can buy stock or sell. Maintain two variables 'cash'
 * to save max value if you had sold the stock at current price and 'stock' to save max value if you
 * had purchased the stock at current price. Return max cash
 */
public class BestTimeToBuyAndSellStocksWithFee {

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] A = {1, 3, 2, 8, 4, 9};
    System.out.println(new BestTimeToBuyAndSellStocksWithFee().maxProfit(A, 2));
  }

  public int maxProfit(int[] prices, int fee) {
    int cash = 0, stock = -prices[0];
    for (int i = 1; i < prices.length; i++) {
      cash = Math.max(cash, prices[i] + stock - fee);
      stock = Math.max(stock, cash - prices[i]);
    }
    return cash;
  }
}
