package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 01/08/2019 In the computer world, use restricted resource you
 * have to generate maximum benefit is what we always want to pursue.
 *
 * <p>For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there
 * is an array with strings consisting of only 0s and 1s.
 *
 * <p>Now your task is to find the maximum number of strings that you can form with given m 0s and n
 * 1s. Each 0 and 1 can be used at most once.
 *
 * <p>Note:
 *
 * <p>The given numbers of 0s and 1s will both not exceed 100 The size of given string array won't
 * exceed 600.
 *
 * <p>Example 1:
 *
 * <p>Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3 Output: 4
 *
 * <p>Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are
 * “10,”0001”,”1”,”0”
 *
 * <p>Example 2:
 *
 * <p>Input: Array = {"10", "0", "1"}, m = 1, n = 1 Output: 2
 *
 * <p>Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".
 *
 * <p>Solution: O(S x m x n) For every string array position we have two choices i. pick this value
 * or ii. not pick this value. Evaluate both these cases and cache the result in a dp array.
 */
public class OnesAndZeroes {
  public static void main(String[] args) {
    String[] str = {"10", "0", "1"};
    System.out.println(new OnesAndZeroes().findMaxForm(str, 1, 1));
  }

  public int findMaxForm(String[] strs, int m, int n) {
    int[][][] dp = new int[strs.length + 1][m + 1][n + 1];
    for (int i = strs.length - 1; i >= 0; i--) {
      String string = strs[i];
      int zero = 0;
      int one = 0;
      for (char c : string.toCharArray()) {
        if (c == '0') {
          zero++;
        } else {
          one++;
        }
      }
      for (int p = m; p >= 0; p--) {
        for (int q = n; q >= 0; q--) {
          dp[i][p][q] = dp[i + 1][p][q];
          if (p - zero >= 0 && q - one >= 0) {
            dp[i][p][q] = Math.max(dp[i][p][q], dp[i + 1][p - zero][q - one] + 1);
          }
        }
      }
    }
    return dp[0][m][n];
  }
}
