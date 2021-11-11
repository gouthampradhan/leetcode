package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 01/04/2017. A message containing letters from A-Z is being
 * encoded to numbers using the following mapping:
 *
 * <p>'A' -> 1 'B' -> 2 ... 'Z' -> 26 Given an encoded message containing digits, determine the
 * total number of ways to decode it.
 *
 * <p>For example, Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 *
 * <p>The number of ways decoding "12" is 2.
 */
public class DecodeWays {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    System.out.println(new DecodeWays().numDecodings("3120"));
  }

  public int numDecodings(String s) {
    if (s == null || s.isEmpty()) return 0;
    int[] dp = new int[s.length() + 2];
    dp[s.length()] = 1;
    dp[s.length() + 1] = 1;
    for (int i = s.length() - 1; i >= 0; i--) {
      for (int j = i + 1; j < i + 3; j++) {
        if (j <= s.length()) {
          String subStr = s.substring(i, j);
          if (!subStr.startsWith("0")) {
            int intVal = Integer.parseInt(subStr);
            if (intVal <= 26) {
              dp[i] += dp[j];
            }
          }
        }
      }
    }
    return dp[0];
  }
}
