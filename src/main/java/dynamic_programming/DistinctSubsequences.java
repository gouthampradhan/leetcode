package dynamic_programming;

import java.util.Arrays;

/**
 * Created by gouthamvidyapradhan on 08/05/2020 Given a string S and a string T, count the number of
 * distinct subsequences of S which equals T.
 *
 * <p>A subsequence of a string is a new string which is formed from the original string by deleting
 * some (can be none) of the characters without disturbing the relative positions of the remaining
 * characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 *
 * <p>It's guaranteed the answer fits on a 32-bit signed integer.
 *
 * <p>Example 1:
 *
 * <p>Input: S = "rabbbit", T = "rabbit" Output: 3 Explanation: As shown below, there are 3 ways you
 * can generate "rabbit" from S. (The caret symbol ^ means the chosen letters)
 *
 * <p>rabbbit ^^^^ ^^ rabbbit ^^ ^^^^ rabbbit ^^^ ^^^ Example 2:
 *
 * <p>Input: S = "babgbag", T = "bag" Output: 5 Explanation: As shown below, there are 5 ways you
 * can generate "bag" from S. (The caret symbol ^ means the chosen letters)
 *
 * <p>babgbag ^^ ^ babgbag ^^ ^ babgbag ^ ^^ babgbag ^ ^^ babgbag ^^^
 */
public class DistinctSubsequences {
  int[][] DP;

  public static void main(String[] args) {
    System.out.println(new DistinctSubsequences().numDistinct("babgbag", "bag"));
  }

  public int numDistinct(String s, String t) {
    DP = new int[s.length()][t.length()];
    for (int i = 0; i < s.length(); i++) {
      Arrays.fill(DP[i], -1);
    }
    return dp(0, 0, s, t);
  }

  private int dp(int i, int j, String s, String t) {
    if (j >= t.length()) return 1;
    else if (i >= s.length()) return 0;
    else if (DP[i][j] != -1) return DP[i][j];
    else {
      if (s.charAt(i) != t.charAt(j)) {
        DP[i][j] = dp(i + 1, j, s, t);
      } else {
        DP[i][j] = dp(i + 1, j + 1, s, t) + dp(i + 1, j, s, t);
      }
      return DP[i][j];
    }
  }
}
