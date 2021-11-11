package backtracking;

/**
 * Created by gouthamvidyapradhan on 05/12/2017.
 *
 * <p>Implement regular expression matching with support for '.' and '*'.
 *
 * <p>'.' Matches any single character. '*' Matches zero or more of the preceding element.
 *
 * <p>The matching should cover the entire input string (not partial).
 *
 * <p>The function prototype should be: bool isMatch(const char *s, const char *p)
 *
 * <p>Some examples: isMatch("aa","a") → false isMatch("aa","aa") → true isMatch("aaa","aa") → false
 * isMatch("aa", "a*") → true isMatch("aa", ".*") → true isMatch("ab", ".*") → true isMatch("aab",
 * "c*a*b") → true
 *
 * <p>Solution: When a wildcard is encountered try to match all the possible prefixes including
 * none, otherwise do a simple one to one match. If the end of string is reached simultaneously in
 * both string and pattern then return true
 */
public class RegularExpressionMatching {

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    System.out.println(new RegularExpressionMatching().isMatch("aa", "a*a*a"));
  }

  public boolean isMatch(String s, String p) {
    return backTrack(0, 0, s, p);
  }

  private boolean backTrack(int si, int pi, String s, String p) {
    if (si >= s.length() && pi >= p.length())
      return true; // end of the string has been reached hence return true
    else {
      if (pi >= p.length()) return false; // pattern has exhausted hence return false
      else if (si >= s.length()) {
        if (pi + 1 < p.length() && p.charAt(pi + 1) == '*') {
          return backTrack(si, pi + 2, s, p);
        } else
          return false; // string has exhausted and pattern does not contain wildcard hence return
                        // false
      } else if (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '.') {
        if (pi + 1 < p.length() && p.charAt(pi + 1) == '*') {
          // match 0 or more repeated preceding element
          if (backTrack(si, pi + 2, s, p)) return true;
          for (int i = si, l = s.length(); i < l; i++) {
            if (s.charAt(i) == p.charAt(pi) || p.charAt(pi) == '.') {
              if (backTrack(i + 1, pi + 2, s, p)) return true;
            } else {
              return false;
            }
          }
          return backTrack(s.length(), pi, s, p);
        } else {
          return backTrack(si + 1, pi + 1, s, p); // not wildcard match immediate chars
        }
      } else {
        if (pi + 1 < p.length() && p.charAt(pi + 1) == '*') {
          return backTrack(si, pi + 2, s, p);
        } else return false;
      }
    }
  }
}
