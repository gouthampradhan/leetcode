package string;

/**
 * Created by gouthamvidyapradhan on 28/03/2019 We are given two strings, A and B.
 *
 * <p>A shift on A consists of taking string A and moving the leftmost character to the rightmost
 * position. For example, if A = 'abcde', then it will be 'bcdea' after one shift on A. Return True
 * if and only if A can become B after some number of shifts on A.
 *
 * <p>Example 1: Input: A = 'abcde', B = 'cdeab' Output: true
 *
 * <p>Example 2: Input: A = 'abcde', B = 'abced' Output: false Note:
 *
 * <p>A and B will have length at most 100.
 */
public class RotateString {

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    System.out.println(new RotateString().rotateString("abcde", "cdeab"));
  }

  public boolean rotateString(String A, String B) {
    if (A.length() == 1 || A.isEmpty() || B.length() == 1 || B.isEmpty()) {
      return A.equals(B);
    } else if (A.length() != B.length()) {
      return false;
    }
    for (int i = 0, l = A.length(); i < l; i++) {
      char s = A.charAt(0);
      A = A.substring(1) + s;
      if (A.equals(B)) return true;
    }
    return false;
  }
}
