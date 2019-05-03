package two_pointers;

/**
 * Created by gouthamvidyapradhan on 03/12/2017.
 *
 * <p>Given a string S and a string T, find the minimum window in S which will contain all the
 * characters in T in complexity O(n).
 *
 * <p>For example, S = "ADOBECODEBANC" T = "ABC" Minimum window is "BANC".
 *
 * <p>Note: If there is no such window in S that covers all characters in T, return the empty string
 * "".
 *
 * <p>If there are multiple such windows, you are guaranteed that there will always be only one
 * unique minimum window in S.
 *
 * <p>Solution O(n). Sliding window sub-sting using two pointers.
 */
public class MinimumWindowSubstring {
  private int[] hash = new int[256];
  private int[] curr = new int[256];

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    System.out.println(new MinimumWindowSubstring().minWindow("ADOBECODEBANC", "ABC"));
  }

  public String minWindow(String s, String t) {
    if (s.isEmpty() && t.isEmpty()) return "";
    if (t.length() > s.length()) return "";
    int start = -1, end = -1, min = Integer.MAX_VALUE;
    for (int i = 0, l = t.length(); i < l; i++) {
      hash[t.charAt(i)]++;
    }

    for (int i = 0, l = t.length() - 1; i < l; i++) {
      curr[s.charAt(i)]++;
    }

    for (int i = 0, j = t.length() - 1, l = s.length(); j < l; ) {
      curr[s.charAt(j)]++;
      if (isMatch()) {
        if (j - i < min) {
          min = j - i;
          start = i;
          end = j;
        }
        while (j > i) {
          curr[s.charAt(i)]--;
          i++;
          if (isMatch()) {
            if (j - i < min) {
              min = j - i;
              start = i;
              end = j;
            }
          } else break;
        }
      }
      j++;
    }
    if (min == Integer.MAX_VALUE) {
      return "";
    }
    return s.substring(start, end + 1);
  }

  private boolean isMatch() {
    for (int i = 0; i < 256; i++) {
      if (curr[i] < hash[i]) {
        return false;
      }
    }
    return true;
  }
}
