package dynamic_programming;
/**
 * Created by gouthamvidyapradhan on 26/03/2019 There are G people in a gang, and a list of various
 * crimes they could commit.
 *
 * <p>The i-th crime generates a profit[i] and requires group[i] gang members to participate.
 *
 * <p>If a gang member participates in one crime, that member can't participate in another crime.
 *
 * <p>Let's call a profitable scheme any subset of these crimes that generates at least P profit,
 * and the total number of gang members participating in that subset of crimes is at most G.
 *
 * <p>How many schemes can be chosen? Since the answer may be very large, return it modulo 10^9 + 7.
 *
 * <p>Example 1:
 *
 * <p>Input: G = 5, P = 3, group = [2,2], profit = [2,3] Output: 2 Explanation: To make a profit of
 * at least 3, the gang could either commit crimes 0 and 1, or just crime 1. In total, there are 2
 * schemes. Example 2:
 *
 * <p>Input: G = 10, P = 5, group = [2,3,5], profit = [6,7,8] Output: 7 Explanation: To make a
 * profit of at least 5, the gang could commit any crimes, as long as they commit one. There are 7
 * possible schemes: (0), (1), (2), (0,1), (0,2), (1,2), and (0,1,2).
 *
 * <p>Note:
 *
 * <p>1 <= G <= 100 0 <= P <= 100 1 <= group[i] <= 100 0 <= profit[i] <= 100 1 <= group.length =
 * profit.length <= 100
 *
 * <p>Solution: O(G x P) Time and Space complexity. The problem is similar to the standard Knapsack
 * DP problem. For every group value (ranging from 0 - 100) if a minimum of profit can be achieved
 * then add this to the total count. Sum up the count (profitable schemes) for every group value
 * ranging from 0 - G and return this as your answer.
 */
public class ProfitableSchemes {

  private final int MOD = 1000000007;
  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    int[] group = {2, 3};
    int[] profit = {2, 5};
    System.out.println(new ProfitableSchemes().profitableSchemes(5, 2, group, profit));
  }

  public int profitableSchemes(int G, int P, int[] group, int[] profit) {
    int[][] DP = new int[G + 1][P + 1];
    int ans = 0;
    DP[0][0] = 1;
    for (int k = group.length - 1; k >= 0; k--) {
      int g = group[k];
      int p = profit[k];
      for (int i = DP.length - 1; i >= 0; i--) {
        for (int j = DP[0].length - 1; j >= 0; j--) {
          int r1 = (i - g < 0) ? 0 : DP[i - g][Math.max(0, j - p)];
          int r2 = DP[i][j];
          DP[i][j] = ((r1 % MOD) + (r2 % MOD)) % MOD;
        }
      }
    }
    for (int i = 0; i < DP.length; i++) {
      ans = (ans + DP[i][P]) % MOD;
    }
    return ans;
  }
}
