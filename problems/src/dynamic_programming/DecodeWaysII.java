package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 11/12/2017. A message containing letters from A-Z is being
 * encoded to numbers using the following mapping way:
 *
 * <p>'A' -> 1 'B' -> 2 ... 'Z' -> 26 Beyond that, now the encoded string can also contain the
 * character '*', which can be treated as one of the numbers from 1 to 9.
 *
 * <p>Given the encoded message containing digits and the character '*', return the total number of
 * ways to decode it.
 *
 * <p>Also, since the answer may be very large, you should return the output mod 109 + 7.
 *
 * <p>Example 1: Input: "*" Output: 9 Explanation: The encoded message can be decoded to the string:
 * "A", "B", "C", "D", "E", "F", "G", "H", "I". Example 2: Input: "1*" Output: 9 + 9 = 18 Note: The
 * length of the input string will fit in range [1, 105]. The input string will only contain the
 * character '*' and digits '0' - '9'.
 *
 * <p>Solution: O(n) consider each digit and a pair of digits and perform a cartesian product to
 * calculate the total number of ways. A pair of digits are to be considered only if their combined
 * value does not exceed 26. Corner cases with combination of * and 0s can be tricky
 */
public class DecodeWaysII {

  private final int CONST = 1000000007;
  private int[] dp;
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    System.out.println(new DecodeWaysII().numDecodings("10"));
  }

  public int numDecodings(String s) {
    dp = new int[s.length() + 1];
    if (s.charAt(s.length() - 1) == '*') {
      dp[s.length() - 1] = 9;
    } else if (s.charAt(s.length() - 1) == '0') {
      dp[s.length() - 1] = 0;
    } else dp[s.length() - 1] = 1;
    dp[s.length()] = 1;

    for (int i = s.length() - 2; i >= 0; i--) {
      char curr = s.charAt(i);
      char next = s.charAt(i + 1);
      switch (curr) {
        case '0':
          dp[i] = 0;
          break;
          // number begins with a '*'
        case '*':
          dp[i] = (int) ((9 * (long) dp[i + 1]) % CONST);
          switch (next) {
              // The next char is a '*'
            case '*':
              dp[i] =
                  (int)
                      ((dp[i] + ((15 * (long) dp[i + 2]) % CONST))
                          % CONST); // multiplication can be
              // very large hence type casting to long is necessary
              break;

            case '0':
              dp[i] = (int) ((dp[i] + ((2 * (long) dp[i + 2]) % CONST)) % CONST);
              break;

            default:
              if ((next - '0') > 6) {
                dp[i] = ((dp[i] + (dp[i + 2])) % CONST);
              } else {
                dp[i] = (int) ((dp[i] + ((2 * (long) dp[i + 2]) % CONST)) % CONST);
              }
              break;
          }
          break;

        default:
          dp[i] = dp[i + 1];
          switch (next) {
            case '*':
              if ((curr - '0') == 1) {
                dp[i] = (int) ((dp[i] + ((9 * (long) dp[i + 2]) % CONST)) % CONST);
              } else if ((curr - '0') == 2) {
                dp[i] = (int) ((dp[i] + ((6 * (long) dp[i + 2]) % CONST)) % CONST);
              }
              break;

            default:
              if ((curr - '0') == 1) {
                dp[i] = ((dp[i] + dp[i + 2]) % CONST);
              } else if ((curr - '0') == 2 && (next - '0' <= 6)) {
                dp[i] = ((dp[i] + dp[i + 2]) % CONST);
              }
              break;
          }
      }
    }
    return dp[0];
  }
}
