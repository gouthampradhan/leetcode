package dynamic_programming;

import java.util.Arrays;

/**
 * Created by gouthamvidyapradhan on 22/04/2020 You are given a string s containing lowercase
 * letters and an integer k. You need to :
 *
 * <p>First, change some characters of s to other lowercase English letters. Then divide s into k
 * non-empty disjoint substrings such that each substring is palindrome. Return the minimal number
 * of characters that you need to change to divide the string.
 *
 * <p>Example 1:
 *
 * <p>Input: s = "abc", k = 2 Output: 1 Explanation: You can split the string into "ab" and "c", and
 * change 1 character in "ab" to make it palindrome. Example 2:
 *
 * <p>Input: s = "aabbc", k = 3 Output: 0 Explanation: You can split the string into "aa", "bb" and
 * "c", all of them are palindrome. Example 3:
 *
 * <p>Input: s = "leetcode", k = 8 Output: 0
 *
 * <p>Constraints:
 *
 * <p>1 <= k <= s.length <= 100. s only contains lowercase English letters.
 */
public class PalindromePartitioningIII {

  public static void main(String[] args) {
    System.out.println(new PalindromePartitioningIII().palindromePartition("leetcode", 8));
  }

  int[][][] DP;

  public int palindromePartition(String s, int k) {
    DP = new int[s.length()][s.length()][k + 1];
    for (int i = 0; i < s.length(); i++) {
      for (int j = 0; j < s.length(); j++) {
        Arrays.fill(DP[i][j], -1);
      }
    }
    return dp(0, s.length() - 1, k, s);
  }

  private int dp(int i, int j, int n, String s) {
    if (i == j && n == 1) return 0;
    else if ((j - i + 1 < n) || (n <= 0)) return -1;
    else if (DP[i][j][n] != -1) return DP[i][j][n];
    else if (n == 1) {
      int result = count(s.substring(i, j + 1));
      DP[i][j][n] = result;
      return result;
    } else {
      int min = Integer.MAX_VALUE;
      for (int k = i; k < j; k++) {
        int left = dp(i, k, 1, s);
        int right = dp(k + 1, j, n - 1, s);
        if (right != -1) {
          min = Math.min(min, left + right);
        }
      }
      if (min != Integer.MAX_VALUE) {
        DP[i][j][n] = min;
        return min;
      }
      return -1;
    }
  }

  private int count(String s) {
    int cnt = 0;
    for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
      if (s.charAt(i) != s.charAt(j)) {
        cnt++;
      }
    }
    return cnt;
  }
}
