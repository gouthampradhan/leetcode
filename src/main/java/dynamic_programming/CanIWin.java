package dynamic_programming;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gouthamvidyapradhan on 04/07/2017. In the "100 game," two players take turns adding,
 * to a running total, any integer from 1..10. The player who first causes the running total to
 * reach or exceed 100 wins.
 *
 * <p>What if we change the game so that players cannot re-use integers?
 *
 * <p>For example, two players might take turns drawing from a common pool of numbers of 1..15
 * without replacement until they reach a total >= 100.
 *
 * <p>Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first
 * player to move can force a win, assuming both players play optimally.
 *
 * <p>You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal
 * will not be larger than 300.
 *
 * <p>Example
 *
 * <p>Input: maxChoosableInteger = 10 desiredTotal = 11
 *
 * <p>Output: false
 *
 * <p>Explanation: No matter which integer the first player choose, the first player will lose. The
 * first player can choose an integer from 1 up to 10. If the first player choose 1, the second
 * player can only choose integers from 2 up to 10. The second player will win by choosing 10 and
 * get a total = 11, which is >= desiredTotal. Same with other integers chosen by the first player,
 * the second player will always win.
 */
public class CanIWin {

  private Map<Boolean, Map<Integer, Boolean>> DP;

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    System.out.println(new CanIWin().canIWin(5, 15));
  }

  public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
    int sum = 0;
    for (int i = 1; i <= maxChoosableInteger; i++) sum += i;
    if (desiredTotal == 0) return true;
    else if (desiredTotal > sum)
      return false; // if the desiredTotal exceeds the max possible sum return false;
    DP = new HashMap<>();
    DP.put(true, new HashMap<>());
    DP.put(false, new HashMap<>());
    return dp(0, maxChoosableInteger, desiredTotal, true, 0);
  }

  private boolean dp(int state, int M, int D, boolean P, int sum) {
    if (sum >= D) return false;
    Map<Integer, Boolean> map = DP.get(P);
    if (map.containsKey(state)) return map.get(state);
    else {
      map.put(state, false);
      for (int i = 0; i < M; i++) {
        if ((state & (1 << i)) == 0) {
          if (!dp(state | (1 << i), M, D, !P, sum + i + 1)) {
            map.put(state, true);
            break;
          }
        }
      }
    }
    return map.get(state);
  }
}
