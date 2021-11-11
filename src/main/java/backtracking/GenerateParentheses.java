package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gouthamvidyapradhan on 24/06/2017. Given n pairs of parentheses, write a function to
 * generate all combinations of well-formed parentheses.
 *
 * <p>For example, given n = 3, a solution set is:
 *
 * <p>[ "((()))", "(()())", "(())()", "()(())", "()()()" ]
 */
public class GenerateParentheses {
  public static void main(String[] args) throws Exception {
    System.out.println(new GenerateParentheses().generateParenthesis(4));
  }

  public List<String> generateParenthesis(int n) {
    List<String> list = new ArrayList<>();
    backTrack(list, "", 0, 0, n);
    return list;
  }

  private void backTrack(List<String> list, String str, int open, int close, int n) {
    if (str.length() == n * 2) {
      list.add(str);
    } else {
      if (open < n) backTrack(list, str.concat("("), open + 1, close, n);
      if (close
          < open) // number of close should be less than open or else it can result in unbalanced
                  // parentheses
      backTrack(list, str.concat(")"), open, close + 1, n);
    }
  }
}
