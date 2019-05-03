package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 23/06/2017. You are given coins of different denominations and
 * a total amount of money amount. Write a function to compute the fewest number of coins that you
 * need to make up that amount. If that amount of money cannot be made up by any combination of the
 * coins, return -1.
 *
 * <p>Example 1: coins = [1, 2, 5], amount = 11 return 3 (11 = 5 + 5 + 1)
 *
 * <p>Example 2: coins = [2], amount = 3 return -1.
 *
 * <p>Note: You may assume that you have an infinite number of each kind of coin.
 *
 * <p>Solution: For example if you have N coins and amount equal to Q For every coin you have two
 * options i) If you chose to include this coin then, total amount reduces by the sum equivalent to
 * the value of this coin and you are left with N coins and Q = (Q - value of this coin) ii) If you
 * chose not to include this coin then, you are left with N - 1 coins (since you chose to not to
 * include this coin) and total amount is still equal to Q
 *
 * <p>Calculate recursively for each coin and possible amount Since there can be overlapping
 * sub-problems you can save the state in a 2D matrix - a typical DP approach.
 *
 * <p>For each state minimum is calculated using -> Min(1 + fn(i, amount - v[i]), fn(i + 1, amount))
 *
 * <p>Worst-case time complexity is O(N x Q) where N is the total number of coins and Q is the total
 * amount
 */
public class CoinChange {
  private int[][] DP;

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] coins = {1, 2, 5};
    System.out.println(new CoinChange().coinChange(coins, 11));
  }

  public int coinChange(int[] coins, int amount) {
    DP = new int[coins.length][amount + 1];
    int result = dp(amount, 0, coins);
    if (result == Integer.MAX_VALUE - 1) return -1;
    return result;
  }

  private int dp(int amount, int i, int[] coins) {
    if (amount == 0) return 0;
    else if (i >= coins.length || amount < 0) return Integer.MAX_VALUE - 1;
    if (DP[i][amount] != 0) return DP[i][amount];
    DP[i][amount] = Math.min(1 + dp(amount - coins[i], i, coins), dp(amount, i + 1, coins));
    return DP[i][amount];
  }
}
