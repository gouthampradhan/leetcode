package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 17/04/2020 Return the largest possible k such that there exists
 * a_1, a_2, ..., a_k such that:
 *
 * <p>Each a_i is a non-empty string; Their concatenation a_1 + a_2 + ... + a_k is equal to text;
 * For all 1 <= i <= k, a_i = a_{k+1 - i}.
 *
 * <p>Example 1:
 *
 * <p>Input: text = "ghiabcdefhelloadamhelloabcdefghi" Output: 7 Explanation: We can split the
 * string on "(ghi)(abcdef)(hello)(adam)(hello)(abcdef)(ghi)". Example 2:
 *
 * <p>Input: text = "merchant" Output: 1 Explanation: We can split the string on "(merchant)".
 * Example 3:
 *
 * <p>Input: text = "antaprezatepzapreanta" Output: 11 Explanation: We can split the string on
 * "(a)(nt)(a)(pre)(za)(tpe)(za)(pre)(a)(nt)(a)". Example 4:
 *
 * <p>Input: text = "aaa" Output: 3 Explanation: We can split the string on "(a)(a)(a)".
 *
 * <p>Constraints:
 *
 * <p>text consists only of lowercase English characters. 1 <= text.length <= 1000
 */
public class LongestChunkedPalindromeDecomposition {
  public static void main(String[] args) {
    System.out.println(
        new LongestChunkedPalindromeDecomposition().longestDecomposition("merchant"));
  }

  private int[] DP;

  public int longestDecomposition(String text) {
    DP = new int[text.length()];
    return dp(0, text.length() - 1, text);
  }

  private int dp(int i, int e, String text) {
    if (i > e) return 0;
    else if (i == e) return 1;
    else if (DP[i] > 0) return DP[i];
    else {
      for (int j = e; j > i; j--) {
        if (text.charAt(j) == text.charAt(i)) {
          if (text.substring(j, e + 1).equals(text.substring(i, i + (e - j + 1)))) {
            DP[i] = Math.max(DP[i], dp(i + (e - j + 1), j - 1, text) + 2);
          }
        }
      }
      DP[i] = DP[i] == 0 ? 1 : DP[i];
      return DP[i];
    }
  }
}
