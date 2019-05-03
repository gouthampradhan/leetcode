package two_pointers;

/**
 * Created by gouthamvidyapradhan on 04/04/2019 Given string S and a dictionary of words words, find
 * the number of words[i] that is a subsequence of S.
 *
 * <p>Example : Input: S = "abcde" words = ["a", "bb", "acd", "ace"] Output: 3 Explanation: There
 * are three words in words that are a subsequence of S: "a", "acd", "ace". Note:
 *
 * <p>All words in words and S will only consists of lowercase letters. The length of S will be in
 * the range of [1, 50000]. The length of words will be in the range of [1, 5000]. The length of
 * words[i] will be in the range of [1, 50].
 *
 * <p>Solution: O((w + S) x N (no of words)) Using two pointers technique check if each of the given
 * string is a sub-sequence of the main string.
 */
public class NumberOfMatchingSubsequences {

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    String[] A = {"a", "bb", "acd", "ace"};
    System.out.println(new NumberOfMatchingSubsequences().numMatchingSubseq("abcde", A));
  }

  public int numMatchingSubseq(String S, String[] words) {
    int count = 0;
    for (int i = 0; i < words.length; i++) {
      String w = words[i];
      if (isSubsequence(S, w)) {
        count++;
      }
    }
    return count;
  }

  private boolean isSubsequence(String S, String P) {
    int i = 0, j = 0;
    if (P.length() > S.length()) return false;
    for (; ; ) {
      if (j >= P.length()) return true;
      else if (i >= S.length()) return false;
      else {
        if (S.charAt(i) == P.charAt(j)) {
          i++;
          j++;
        } else {
          i++;
        }
      }
    }
  }
}
