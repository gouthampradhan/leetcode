package string;

/**
 * Created by gouthamvidyapradhan on 21/07/2018.
 *
 * <p>Given a string s, you are allowed to convert it to a palindrome by adding characters in front
 * of it. Find and return the shortest palindrome you can find by performing this transformation.
 *
 * <p>Example 1:
 *
 * <p>Input: "aacecaaa" Output: "aaacecaaa" Example 2:
 *
 * <p>Input: "abcd" Output: "dcbabcd"
 *
 * <p>Solution: O(N ^ 2): for i : (s.length() - 1 -> 0) check if (0, i) is a paliandrome, if not
 * append char at i to result string else return string (result + (0, i))
 */
public class ShortestPalindrome {

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    System.out.println(new ShortestPalindrome().shortestPalindrome("aaaaaaaaaa"));
  }

  public String shortestPalindrome(String s) {
    if (s.length() == 0 || s.length() == 1) {
      return s;
    } else if (s.length() == 2) {
      if (s.charAt(0) == s.charAt(1)) {
        return s;
      } else {
        return (s.charAt(1) + s);
      }
    }
    if (isPaliandrome(s, 0, s.length() - 1)) return s;
    StringBuilder sb = new StringBuilder("");
    for (int i = 0, j = s.length() - 1; j >= i; j--) {
      if (!isPaliandrome(s, i, j)) {
        sb.append(s.charAt(j));
      } else {
        sb.append(s.substring(0, s.length()));
        break;
      }
    }
    return sb.toString();
  }

  boolean isPaliandrome(String s, int x, int y) {
    for (int i = x, j = y; i < j; i++, j--) {
      if (s.charAt(i) != s.charAt(j)) return false;
    }
    return true;
  }
}
