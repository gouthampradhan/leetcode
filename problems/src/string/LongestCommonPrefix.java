package string;

/**
 * Created by gouthamvidyapradhan on 12/04/2018. Write a function to find the longest common prefix
 * string amongst an array of strings.
 *
 * <p>Solution: O(N x M) where N is the length of the given array and M is the max_length of a
 * string.
 */
public class LongestCommonPrefix {
  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) throws Exception {
    String[] A = {"abc", "a", "adkd"};
    System.out.println(new LongestCommonPrefix().longestCommonPrefix(A));
  }

  public String longestCommonPrefix(String[] strs) {
    if (strs.length == 0) return "";
    String result = strs[0];
    for (int i = 1; i < strs.length; i++) {
      String s = strs[i];
      for (int j = 0; j < result.length(); j++) {
        if (j >= s.length() || result.charAt(j) != s.charAt(j)) {
          result = result.substring(0, j);
          break;
        }
      }
    }
    return result;
  }
}
