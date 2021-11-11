package backtracking;

/**
 * Created by gouthamvidyapradhan on 21/01/2018. Implement wildcard pattern matching with support
 * for '?' and '*'.
 *
 * <p>'?' Matches any single character. '*' Matches any sequence of characters (including the empty
 * sequence).
 *
 * <p>The matching should cover the entire input string (not partial).
 *
 * <p>The function prototype should be: bool isMatch(const char *s, const char *p)
 *
 * <p>Some examples: isMatch("aa","a") → false isMatch("aa","aa") → true isMatch("aaa","aa") → false
 * isMatch("aa", "*") → true isMatch("aa", "a*") → true isMatch("ab", "?*") → true isMatch("aab",
 * "c*a*b") → false
 *
 * <p>Solution: Maintain two indexes one for string and other one for the pattern. 1. If the
 * characters match in both the indexes or if the char at pattern is '?' then increment both the
 * indexes. 2. If a star(*) is encountered save the position of star in the given string as
 * 'startPosAtStr' and position of star in the pattern as 'starIdx' and this time increment only
 * index for pattern. 3. If the characters do not match and if the start is not encountered
 * previously return false else increment 'startPosAtStr' and assign this as the new index for
 * string and start the new pattern index from starIdx + 1
 */
public class WildcardMatching {

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    System.out.println(new WildcardMatching().isMatch("abebd", "?be******e"));
  }

  public boolean isMatch(String s, String p) {
    int starIdx = -1;
    int starPosAtStr = -1;
    int j = 0;
    for (int i = 0, l = s.length(); i < l; ) {
      if (j < p.length()) {
        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
          i++;
          j++;
        } else if (p.charAt(j) == '*') {
          starIdx = j;
          starPosAtStr = i;
          j++; // increment only pattern index. This is because '*' can match also empty string.
        } else if (starIdx != -1) {
          i = ++starPosAtStr;
          j = starIdx + 1;
        } else return false;
      } else if (starIdx != -1) {
        i = ++starPosAtStr;
        j = starIdx + 1;
      } else return false;
    }
    // check if the remaining characters in pattern contains only '*'
    while (j < p.length()) {
      if (p.charAt(j) == '*') {
        j++;
      } else break;
    }
    return j == p.length();
  }
}
