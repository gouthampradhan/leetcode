package dynamic_programming;

import java.util.Arrays;

/** Created by gouthamvidyapradhan on 10/04/2021 */
public class CountDifferentPalindromicSubsequences {
  public static void main(String[] args) {
    System.out.println(
        new CountDifferentPalindromicSubsequences()
            .countPalindromicSubsequences(
                "abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba"));
  }

  private long[][][] DP;
  final char[] chars = {'a', 'b', 'c', 'd'};
  final int MOD = (int) 1e9 + 7;

  public int countPalindromicSubsequences(String S) {
    DP = new long[S.length()][S.length()][4];
    for (int i = 0; i < S.length(); i++) {
      for (int j = 0; j < S.length(); j++) {
        Arrays.fill(DP[i][j], -1);
      }
    }
    long result = 0L;
    for (char c : chars) {
      long r = dp(0, S.length() - 1, S, c);
      result = ((result + r) % MOD);
    }
    return (int) result;
  }

  private long dp(int i, int j, String s, char c) {
    if (i > j) return 0;
    else if (DP[i][j][c - 'a'] != -1) return DP[i][j][c - 'a'];
    else if (s.charAt(i) == s.charAt(j) && s.charAt(i) == c) {
      if (i == j) return 1;
      else {
        long sum = 0L;
        for (char aChar : chars) {
          long r = dp(i + 1, j - 1, s, aChar);
          if (aChar == c) {
            r = ((r + 2) % MOD);
          }
          sum = ((sum + r) % MOD);
        }
        DP[i][j][c - 'a'] = sum;
        return DP[i][j][c - 'a'];
      }
    } else if (s.charAt(i) == c) {
      DP[i][j][c - 'a'] = dp(i, j - 1, s, c);
      return DP[i][j][c - 'a'];
    } else if (s.charAt(j) == c) {
      DP[i][j][c - 'a'] = dp(i + 1, j, s, c);
      return DP[i][j][c - 'a'];
    } else {
      DP[i][j][c - 'a'] = dp(i + 1, j - 1, s, c);
      return DP[i][j][c - 'a'];
    }
  }
}
