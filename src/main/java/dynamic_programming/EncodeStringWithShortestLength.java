package dynamic_programming;
/**
 * Created by gouthamvidyapradhan on 15/09/2019 Given a non-empty string, encode the string such
 * that its encoded length is the shortest.
 *
 * <p>The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets
 * is being repeated exactly k times.
 *
 * <p>Note:
 *
 * <p>k will be a positive integer and encoded string will not be empty or have extra space. You may
 * assume that the input string contains only lowercase English letters. The string's length is at
 * most 160. If an encoding process does not make the string shorter, then do not encode it. If
 * there are several solutions, return any of them is fine.
 *
 * <p>Example 1:
 *
 * <p>Input: "aaa" Output: "aaa" Explanation: There is no way to encode it such that it is shorter
 * than the input string, so we do not encode it.
 *
 * <p>Example 2:
 *
 * <p>Input: "aaaaa" Output: "5[a]" Explanation: "5[a]" is shorter than "aaaaa" by 1 character.
 *
 * <p>Example 3:
 *
 * <p>Input: "aaaaaaaaaa" Output: "10[a]" Explanation: "a9[a]" or "9[a]a" are also valid solutions,
 * both of them have the same length = 5, which is the same as "10[a]".
 *
 * <p>Example 4:
 *
 * <p>Input: "aabcaabcd" Output: "2[aabc]d" Explanation: "aabc" occurs twice, so one answer can be
 * "2[aabc]d".
 *
 * <p>Example 5:
 *
 * <p>Input: "abbbabbbcabbbabbbc" Output: "2[2[abbb]c]" Explanation: "abbbabbbc" occurs twice, but
 * "abbbabbbc" can also be encoded to "2[abbb]c", so one answer can be "2[2[abbb]c]".
 *
 * <p>Solution: O(N ^ 4) Maintain a 2d String array of minimum substring and split each substring
 * and combine the minimum substrings So, the answer could be either DP[i][j] = min(DP[i][k] + DP[k
 * + 1][j]) or split (i, j) at every index k and check if a new minimum substring can be formed
 * which is lesser than the current minimum.
 */
public class EncodeStringWithShortestLength {

  private String[][] DP;

  public static void main(String[] args) {
    System.out.println(
        new EncodeStringWithShortestLength()
            .encode(
                "xabcabcabcxxabcabcabcxxabcabcabcxxabcdabcabcxxabcabcabcxxabcabcabcxxabcabcabcxxabcabcabcx"));
  }

  public String encode(String s) {
    DP = new String[s.length()][s.length()];
    return encodeStr(s, 0, s.length() - 1);
  }

  private String encodeStr(String s, int i, int j) {
    if (i == j) {
      DP[i][j] = String.valueOf(s.charAt(i));
      return DP[i][j];
    } else if (DP[i][j] != null) return DP[i][j];
    String currSubStr = s.substring(i, j + 1);
    DP[i][j] = currSubStr;
    for (int k = i + 1; k < j + 1; k++) {
      String left = encodeStr(s, i, k - 1);
      String right = encodeStr(s, k, j);
      if (left.length() + right.length() < DP[i][j].length()) {
        DP[i][j] = left + right;
      }
    }
    for (int k = i + 1; k < j + 1; k++) {
      if (currSubStr.length() % (k - i) == 0) {
        String subStr = s.substring(i, k);
        if (currSubStr.replaceAll(subStr, "").trim().isEmpty()) {
          String candidate =
              (currSubStr.length() / subStr.length()) + "[" + encodeStr(s, i, k - 1) + "]";
          if (candidate.length() < DP[i][j].length()) {
            DP[i][j] = candidate;
          }
        }
      }
    }
    return DP[i][j];
  }
}
