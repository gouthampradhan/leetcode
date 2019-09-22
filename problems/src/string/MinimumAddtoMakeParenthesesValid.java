package string;

/**
 * Created by gouthamvidyapradhan on 20/08/2019 Given a string S of '(' and ')' parentheses, we add
 * the minimum number of parentheses ( '(' or ')', and in any positions ) so that the resulting
 * parentheses string is valid.
 *
 * <p>Formally, a parentheses string is valid if and only if:
 *
 * <p>It is the empty string, or It can be written as AB (A concatenated with B), where A and B are
 * valid strings, or It can be written as (A), where A is a valid string. Given a parentheses
 * string, return the minimum number of parentheses we must add to make the resulting string valid.
 *
 * <p>Example 1:
 *
 * <p>Input: "())" Output: 1 Example 2:
 *
 * <p>Input: "(((" Output: 3 Example 3:
 *
 * <p>Input: "()" Output: 0 Example 4:
 *
 * <p>Input: "()))((" Output: 4
 *
 * <p>Note:
 *
 * <p>S.length <= 1000 S only consists of '(' and ')' characters.
 *
 * <p>Solution O(N) Keep track of count of open parentheses, when ever a closed parentheses appear
 * if the count of open parentheses is greater than 0 then decrement this value (identifying that
 * there is a matching parentheses already), if the count is 0 then there is a miss match with
 * parentheses and hence add one to the result. The final answer is the total of result + open
 * parentheses
 */
public class MinimumAddtoMakeParenthesesValid {
  public static void main(String[] args) {
    System.out.println(new MinimumAddtoMakeParenthesesValid().minAddToMakeValid("()))(("));
  }

  public int minAddToMakeValid(String S) {
    int result = 0;
    int open = 0;
    for (char c : S.toCharArray()) {
      if (c == '(') {
        open++;
      } else if (c == ')') {
        if (open > 0) {
          open--;
        } else result++;
      }
    }
    return result + open;
  }
}
