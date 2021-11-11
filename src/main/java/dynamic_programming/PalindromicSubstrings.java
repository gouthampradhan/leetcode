package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 13/12/2017.
 *
 * <p>Given a string, your task is to count how many palindromic substrings in this string.
 *
 * <p>The substrings with different start indexes or end indexes are counted as different substrings
 * even they consist of same characters.
 *
 * <p>Example 1: Input: "abc" Output: 3 Explanation: Three palindromic strings: "a", "b", "c".
 * Example 2: Input: "aaa" Output: 6 Explanation: Six palindromic strings: "a", "a", "a", "aa",
 * "aa", "aaa". Note: The input string length won't exceed 1000.
 *
 * <p>Solution O(n ^ 2): Example abcba: Compare char at two indices each time for example if char at
 * index 0 and index 4 are equal and if substring 1 and 3 is a palindrome then, sub-string 0 and 4
 * is also a palindrome
 */
public class PalindromicSubstrings {

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    System.out.println(new PalindromicSubstrings().countSubstrings("aaa"));
  }

  public int countSubstrings(String s) {
    boolean[][] T = new boolean[s.length()][s.length()];
    int count = s.length();
    for (int i = 0, j = 0; i < T.length; i++, j++) {
      T[i][j] = true;
    }

    for (int k = 1, col = s.length(); k < col; k++) {
      for (int i = 0, j = k; i < col && j < col; i++, j++) {
        if (k == 1) {
          if (s.charAt(i) == s.charAt(j)) {
            T[i][j] = true;
            count++;
          }
        } else {
          if (s.charAt(i) == s.charAt(j) && T[i + 1][j - 1]) {
            T[i][j] = true;
            count++;
          }
        }
      }
    }
    return count;
  }
}
