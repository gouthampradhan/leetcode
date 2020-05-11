package dynamic_programming;
/**
 * Created by gouthamvidyapradhan on 08/05/2020 Given a string S, count the number of distinct,
 * non-empty subsequences of S .
 *
 * <p>Since the result may be large, return the answer modulo 10^9 + 7.
 *
 * <p>Example 1:
 *
 * <p>Input: "abc" Output: 7 Explanation: The 7 distinct subsequences are "a", "b", "c", "ab", "ac",
 * "bc", and "abc". Example 2:
 *
 * <p>Input: "aba" Output: 6 Explanation: The 6 distinct subsequences are "a", "b", "ab", "ba", "aa"
 * and "aba". Example 3:
 *
 * <p>Input: "aaa" Output: 3 Explanation: The 3 distinct subsequences are "a", "aa" and "aaa".
 *
 * <p>Note:
 *
 * <p>S contains only lowercase letters. 1 <= S.length <= 2000
 */
public class DistinctSubsequencesII {
  public static void main(String[] args) {
    System.out.println(new DistinctSubsequencesII().distinctSubseqII("abac"));
  }

  final int MOD = (int) 1e9 + 7;

  public int distinctSubseqII(String S) {
    int[] DP = new int[S.length() + 1];
    DP[S.length()] = 1;
    for (int i = S.length() - 1; i >= 0; i--) {
      int sum = 0;
      for (int j = i + 1; j <= S.length(); j++) {
        sum = ((sum + DP[j]) % MOD);
        if (j < S.length() && S.charAt(j) == S.charAt(i)) {
          break;
        }
      }
      DP[i] = sum;
    }
    int ans = 0;
    for (int i : DP) {
      ans = ((ans + i) % MOD);
    }
    return ans - 1;
  }
}
