/* (C) 2024 YourCompanyName */
package dynamic_programming;

import java.util.Arrays;

/**
 * Created by gouthamvidyapradhan on 10/04/2021 Given a string S, find the number of different
 * non-empty palindromic subsequences in S, and return that number modulo 10^9 + 7.
 *
 * <p>A subsequence of a string S is obtained by deleting 0 or more characters from S.
 *
 * <p>A sequence is palindromic if it is equal to the sequence reversed.
 *
 * <p>Two sequences A_1, A_2, ... and B_1, B_2, ... are different if there is some i for which A_i
 * != B_i.
 *
 * <p>Example 1: Input: S = 'bccb' Output: 6 Explanation: The 6 different non-empty palindromic
 * subsequences are 'b', 'c', 'bb', 'cc', 'bcb', 'bccb'. Note that 'bcb' is counted only once, even
 * though it occurs twice. Example 2: Input: S =
 * 'abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba' Output: 104860361 Explanation:
 * There are 3104860382 different non-empty palindromic subsequences, which is 104860361 modulo 10^9
 * + 7. Note:
 *
 * <p>The length of S will be in the range [1, 1000]. Each character S[i] will be in the set {'a',
 * 'b', 'c', 'd'}.
 *
 * <p>Solution: O(N ^ 2) x 4
 */
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
