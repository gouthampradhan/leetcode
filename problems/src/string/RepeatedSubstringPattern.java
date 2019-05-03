package string;

/**
 * Created by gouthamvidyapradhan on 25/03/2017. Given a non-empty string check if it can be
 * constructed by taking a substring of it and appending multiple copies of the substring together.
 * You may assume the given string consists of lowercase English letters only and its length will
 * not exceed 10000.
 *
 * <p>Example 1: Input: "abab"
 *
 * <p>Output: True
 *
 * <p>Explanation: It's the substring "ab" twice. Example 2: Input: "aba"
 *
 * <p>Output: False Example 3: Input: "abcabcabcabc"
 *
 * <p>Output: True
 *
 * <p>Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
 */
public class RepeatedSubstringPattern {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    System.out.println(new RepeatedSubstringPattern().repeatedSubstringPattern("a"));
  }

  public boolean repeatedSubstringPattern(String s) {
    boolean found;
    for (int i = 1, l = s.length(); i < l; i++) {
      found = true;
      String subI = s.substring(0, i);
      int j = i;
      if (j >= l) return false;
      while (j < l) {
        if ((j + i) >= l + 1) return false;
        String subJ = s.substring(j, j + i);
        if (!subI.equals(subJ)) {
          found = false;
          break;
        }
        j += i;
      }
      if (found) return true;
    }
    return false;
  }
}
