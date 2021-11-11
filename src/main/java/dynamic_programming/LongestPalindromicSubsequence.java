package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 27/03/2017. Given an unsorted array of integers, find the
 * length of longest increasing subsequence.
 *
 * <p>For example, Given [10, 9, 2, 5, 3, 7, 101, 18], The longest increasing subsequence is [2, 3,
 * 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is
 * only necessary for you to return the length.
 *
 * <p>Your algorithm should run in O(n2) complexity.
 *
 * <p>Follow up: Could you improve it to O(n log n) time complexity?
 */
public class LongestPalindromicSubsequence {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    System.out.println(new LongestPalindromicSubsequence().longestPalindromeSubseq("bbbab"));
  }

  public int longestPalindromeSubseq(String s) {
    int[][] dp = new int[s.length() + 1][s.length() + 1];
    String sI = new StringBuilder(s).reverse().toString();
    for (int i = 1, l = s.length(); i <= l; i++)
      for (int j = 1; j <= l; j++) {
        dp[i][j] =
            (s.charAt(i - 1) == sI.charAt(j - 1))
                ? dp[i - 1][j - 1] + 1
                : Math.max(dp[i - 1][j], dp[i][j - 1]);
      }

    return dp[s.length()][s.length()];
  }
}
