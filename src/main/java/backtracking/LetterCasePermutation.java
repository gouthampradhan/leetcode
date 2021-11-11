package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gouthamvidyapradhan on 12/04/2018. Given a string S, we can transform every letter
 * individually to be lowercase or uppercase to create another string. Return a list of all possible
 * strings we could create.
 *
 * <p>Examples: Input: S = "a1b2" Output: ["a1b2", "a1B2", "A1b2", "A1B2"]
 *
 * <p>Input: S = "3z4" Output: ["3z4", "3Z4"]
 *
 * <p>Input: S = "12345" Output: ["12345"] Note:
 *
 * <p>S will be a string with length at most 12. S will consist only of letters or digits.
 *
 * <p>Solution: O(N x 2 ^ N) Backtrack and generate all possible combinations.
 */
public class LetterCasePermutation {

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    System.out.println(new LetterCasePermutation().letterCasePermutation("a1b2"));
  }

  public List<String> letterCasePermutation(String S) {
    List<String> result = new ArrayList<>();
    backtrack(S, result, 0, "");
    return result;
  }

  private void backtrack(String s, List<String> result, int i, String r) {
    if (i == s.length()) {
      result.add(r);
    } else {
      if (Character.isAlphabetic(s.charAt(i))) {
        backtrack(s, result, i + 1, r + s.charAt(i));
        if (Character.isLowerCase(s.charAt(i))) {
          backtrack(s, result, i + 1, r + Character.toUpperCase(s.charAt(i)));
        } else {
          backtrack(s, result, i + 1, r + Character.toLowerCase(s.charAt(i)));
        }
      } else {
        backtrack(s, result, i + 1, r + s.charAt(i));
      }
    }
  }
}
