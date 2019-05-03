package depth_first_search;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by gouthamvidyapradhan on 09/03/2019 There is a box protected by a password. The password
 * is n digits, where each letter can be one of the first k digits 0, 1, ..., k-1.
 *
 * <p>You can keep inputting the password, the password will automatically be matched against the
 * last n digits entered.
 *
 * <p>For example, assuming the password is "345", I can open it when I type "012345", but I enter a
 * total of 6 digits.
 *
 * <p>Please return any string of minimum length that is guaranteed to open the box after the entire
 * string is inputted.
 *
 * <p>Example 1: Input: n = 1, k = 2 Output: "01" Note: "10" will be accepted too. Example 2: Input:
 * n = 2, k = 2 Output: "00110" Note: "01100", "10011", "11001" will be accepted too. Note: n will
 * be in the range [1, 4]. k will be in the range [1, 10]. k^n will be at most 4096.
 *
 * <p>Solution O(n x k ^ n) Do a dfs and explore every possible states which form a n digit number
 * with-in the given range k. Maintain a 'result' string and keep appending the new digit in every
 * state, if the total number of states visited reaches k ^ n then, the result string will be the
 * answer.
 */
public class CrackingTheSafe {

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    System.out.println(new CrackingTheSafe().crackSafe(4, 5));
  }

  public String crackSafe(int n, int k) {
    int states = getStates(n, k);
    int[] N = new int[k];
    for (int i = 0; i < k; i++) {
      N[i] = i;
    }
    return generate(N, n, 0, 0, "", k, states);
  }

  private int getStates(int n, int k) {
    if (n == 0) return 1;
    if (n == 1) return k;
    int result = 1;
    for (int i = 0; i < n; i++) {
      result *= k;
    }
    return result;
  }

  private String generate(int[] N, int n, int i, int count, String num, int k, int states) {
    if (count == n) {
      return dfs(num, new StringBuilder(num), new HashSet<>(), k, states, 1);
    } else {
      for (int j = i; j < N.length; j++) {
        String result = generate(N, n, j, count + 1, num + String.valueOf(N[j]), k, states);
        if (!result.isEmpty()) {
          return result;
        }
      }
    }
    return "";
  }

  private String dfs(
      String num, StringBuilder result, Set<String> done, int k, int states, int count) {
    done.add(num);
    if (states == count) {
      return result.toString();
    } else {
      for (int i = 0; i < k; i++) {
        String newNum = (num + String.valueOf(i));
        String newState = newNum.substring(1);
        if (!done.contains(newState)) {
          String retValue =
              dfs(newState, result.append(String.valueOf(i)), done, k, states, count + 1);
          if (!retValue.isEmpty()) {
            return retValue;
          } else {
            result.deleteCharAt(result.length() - 1);
          }
        }
      }
    }
    done.remove(num);
    return "";
  }
}
