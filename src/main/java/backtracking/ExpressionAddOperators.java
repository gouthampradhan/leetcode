package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gouthamvidyapradhan on 05/12/2017. Given a string that contains only digits 0-9 and a
 * target value, return all possibilities to add binary operators (not unary) +, -, or * between the
 * digits so they evaluate to the target value.
 *
 * <p>Examples: "123", 6 -> ["1+2+3", "1*2*3"] "232", 8 -> ["2*3+2", "2+3*2"] "105", 5 ->
 * ["1*0+5","10-5"] "00", 0 -> ["0+0", "0-0", "0*0"] "3456237490", 9191 -> []
 *
 * <p>Solution: Backtrack and keep track of the total and product value. In case of + or - add/sub
 * curr to total and curr becomes the new product In case of * take difference of total and prod and
 * add (product of curr value with previous product and make this a new product for the next
 * iteration)
 *
 * <p>Worst-case time complexity can be O(n * (2^n-1))
 */
public class ExpressionAddOperators {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    List<String> result = new ExpressionAddOperators().addOperators("202010201", 201);
    result.stream().forEach(System.out::println);
  }

  public List<String> addOperators(String num, int target) {
    List<String> result = new ArrayList<>();
    backTrack("", result, 0, num, target, 0L, 0L);
    return result;
  }

  private void backTrack(
      String exp, List<String> list, int curr, String num, int target, long total, long prod) {
    if (curr == num.length()) {
      if (total == target) {
        list.add(exp);
      }
    } else {
      for (int i = curr, l = num.length(); i < l; i++) {
        String newNum = num.substring(curr, i + 1);
        if (newNum.length() > 1 && newNum.startsWith("0")) {
          break;
        }
        long newNumL = Long.parseLong(newNum);
        if (curr == 0) {
          backTrack(newNum, list, i + 1, num, target, newNumL, newNumL);
        } else {
          backTrack(exp + "+" + newNum, list, i + 1, num, target, total + newNumL, newNumL);

          backTrack(exp + "-" + newNum, list, i + 1, num, target, total - newNumL, newNumL * -1L);

          backTrack(
              exp + "*" + newNum,
              list,
              i + 1,
              num,
              target,
              (total - prod + (prod * newNumL)),
              prod * newNumL);
        }
      }
    }
  }
}
