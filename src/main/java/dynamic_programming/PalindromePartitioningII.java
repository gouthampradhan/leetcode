package dynamic_programming;

import java.util.Arrays;

/**
 * Created by pradhang on 4/3/2017. Given a string s, partition s such that every substring of the
 * partition is a palindrome.
 *
 * <p>Return the minimum cuts needed for a palindrome partitioning of s.
 *
 * <p>For example, given s = "aab", Return 1 since the palindrome partitioning ["aa","b"] could be
 * produced using 1 cut.
 */
public class PalindromePartitioningII {
  private int A[];
  private boolean[][] paliandrome;

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    System.out.println(
        new PalindromePartitioningII()
            .minCut(
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
  }

  public int minCut(String s) {
    if (s == null || s.isEmpty()) return 0;
    A = new int[s.length()];
    Arrays.fill(A, Integer.MAX_VALUE);
    char[] charArr = s.toCharArray();
    paliandrome = new boolean[charArr.length][charArr.length];
    return doNext(charArr, 0) - 1;
  }

  private int doNext(char[] s, int p) {
    if (p >= s.length) return 0;
    if (A[p] < Integer.MAX_VALUE) return A[p];
    for (int i = p, l = s.length; i < l; i++) {
      if (p + 1 <= i - 1) {
        paliandrome[p][i] = (paliandrome[p + 1][i - 1] && (s[p] == s[i]));
      } else {
        paliandrome[p][i] = (i == p) || (s[i] == s[p]);
      }
      if (paliandrome[p][i]) {
        A[p] = Math.min(doNext(s, i + 1) + 1, A[p]);
      }
    }
    return A[p];
  }
}
