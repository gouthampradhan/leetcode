package dynamic_programming;

import java.util.Arrays;

/**
 * Created by gouthamvidyapradhan on 22/03/2017. You are given coins of different denominations and
 * a total amount of money. Write a function to compute the number of combinations that make up that
 * amount. You may assume that you have infinite number of each kind of coin.
 *
 * <p>Note: You can assume that
 *
 * <p>0 <= amount <= 5000 1 <= coin <= 5000 the number of coins is less than 500 the answer is
 * guaranteed to fit into signed 32-bit integer Example 1:
 *
 * <p>Input: amount = 5, coins = [1, 2, 5] Output: 4 Explanation: there are four ways to make up the
 * amount: 5=5 5=2+2+1 5=2+1+1+1 5=1+1+1+1+1 Example 2:
 *
 * <p>Input: amount = 3, coins = [2] Output: 0 Explanation: the amount of 3 cannot be made up just
 * with coins of 2. Example 3:
 *
 * <p>Input: amount = 10, coins = [10] Output: 1
 */
public class CoinChange2 {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] coins = {1, 2, 5};
    System.out.println(new CoinChange2().change(5, coins));
  }

  public int change(int amount, int[] coins) {
    int[][] dp = new int[coins.length][amount + 1];
    for (int i = 0, l = coins.length; i < l; i++) Arrays.fill(dp[i], -1);
    return dp(dp, 0, coins, coins.length, amount);
  }

  private int dp(int[][] dp, int i, int[] coins, int l, int n) {
    if (n == 0) return 1;
    else if (i >= l) return 0;
    if (n < 0) return 0;
    if (dp[i][n] != -1) return dp[i][n];
    dp[i][n] = dp(dp, i + 1, coins, l, n) + dp(dp, i, coins, l, n - coins[i]);
    return dp[i][n];
  }
}
