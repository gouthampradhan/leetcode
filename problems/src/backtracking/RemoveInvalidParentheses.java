package backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by gouthamvidyapradhan on 17/10/2017. Remove the minimum number of invalid parentheses in
 * order to make the input string valid. Return all possible results.
 *
 * <p>Note: The input string may contain letters other than the parentheses ( and ).
 *
 * <p>Examples: "()())()" -> ["()()()", "(())()"] "(a)())()" -> ["(a)()()", "(a())()"] ")(" -> [""]
 *
 * <p>Solution: O(N x 2 ^ N) backtrack and generate all combination of unique parentheses. Keep
 * track of a counter which keeps track of validity of parentheses. Prune the search space by
 * checking for validity of parenthesis on the fly by checking if the counter goes below 0 in which
 * case a valid combination is impossible and also keep track of selected count and total count of
 * characters in each state to check if the difference between them is above the min threshold.
 */
public class RemoveInvalidParentheses {

  private Set<String> done;
  private int maxLen = Integer.MIN_VALUE;
  private int minDiff = Integer.MAX_VALUE;
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    List<String> result = new RemoveInvalidParentheses().removeInvalidParentheses("())())");
    result.forEach(System.out::println);
  }

  public List<String> removeInvalidParentheses(String s) {
    done = new HashSet<>();
    List<String> result = new ArrayList<>();
    backTrack(s, 0, 0, result, "", 0, 0);
    return result;
  }

  private void backTrack(
      String s, int i, int count, List<String> result, String state, int selected, int total) {
    if (i >= s.length()) {
      if (count == 0) {
        if (selected >= maxLen) {
          result.add(state);
          maxLen = selected;
          minDiff = total - selected;
        }
      }
    } else {
      done.add(state);
      char c = s.charAt(i);
      if (c == '(') {
        if (!done.contains(state + "(")) {
          backTrack(s, i + 1, count + 1, result, state + "(", selected + 1, total + 1);
        }
        if ((total - selected + 1) <= minDiff) {
          backTrack(s, i + 1, count, result, state, selected, total + 1);
        }
      } else if (c == ')') {
        if (count - 1 < 0) {
          if ((total - selected + 1) <= minDiff) {
            backTrack(s, i + 1, count, result, state, selected, total + 1);
          }
        } else {
          if (!done.contains(state + ")")) {
            backTrack(s, i + 1, count - 1, result, state + ")", selected + 1, total + 1);
          }
          if ((total - selected + 1) <= minDiff) {
            backTrack(s, i + 1, count, result, state, selected, total + 1);
          }
        }
      } else {
        backTrack(s, i + 1, count, result, state + c, selected + 1, total + 1);
      }
    }
  }
}
