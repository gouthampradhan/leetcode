package breadth_first_search;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 09/02/2018. You have a lock in front of you with 4 circular
 * wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can
 * rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move
 * consists of turning one wheel one slot.
 *
 * <p>The lock initially starts at '0000', a string representing the state of the 4 wheels.
 *
 * <p>You are given a list of deadends dead ends, meaning if the lock displays any of these codes,
 * the wheels of the lock will stop turning and you will be unable to open it.
 *
 * <p>Given a target representing the value of the wheels that will unlock the lock, return the
 * minimum total number of turns required to open the lock, or -1 if it is impossible.
 *
 * <p>Example 1: Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202" Output: 6
 * Explanation: A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" ->
 * "1202" -> "0202". Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would
 * be invalid, because the wheels of the lock become stuck after the display becomes the dead end
 * "0102". Example 2: Input: deadends = ["8888"], target = "0009" Output: 1 Explanation: We can turn
 * the last wheel in reverse to move from "0000" -> "0009". Example 3: Input: deadends =
 * ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888" Output: -1
 * Explanation: We can't reach the target without getting stuck. Example 4: Input: deadends =
 * ["0000"], target = "8888" Output: -1 Note: The length of deadends will be in the range [1, 500].
 * target will not be in the list deadends. Every string in deadends and the string target will be a
 * string of4 digits from the 10,000 possibilities '0000' to '9999'.
 *
 * <p>Solution: Perform a bfs of each state starting from 0000 and return the minimum distance.
 */
public class OpenTheLock {

  class State {
    String state;
    int dist;

    State(String state, int dist) {
      this.state = state;
      this.dist = dist;
    }
  }

  private Set<String> done;
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    String[] A = {"0201", "0101", "0102", "1212", "2002"};
    System.out.println(new OpenTheLock().openLock(A, "0202"));
  }

  public int openLock(String[] deadends, String target) {
    done = new HashSet<>();
    Arrays.stream(deadends).forEach(e -> done.add(e));
    if (done.contains("0000")) return -1;
    if (target.equals("0000")) return 0;
    Queue<State> queue = new ArrayDeque<>();
    queue.offer(new State("0000", 0));
    done.add("0000");
    while (!queue.isEmpty()) {
      State state = queue.poll();
      if (state.state.equals(target)) return state.dist;
      String currState = state.state;
      for (int i = 0; i < 4; i++) {
        char c = currState.charAt(i);
        int cV = Integer.parseInt(String.valueOf(c));
        String prefix = currState.substring(0, i);
        String postFix = currState.substring(i + 1, 4);
        String newChild1 = prefix + (((cV + 1) > 9) ? 0 : (cV + 1)) + postFix;
        if (!done.contains(newChild1)) {
          queue.offer(new State(newChild1, state.dist + 1));
          done.add(newChild1);
        }
        String newChild2 = prefix + (((cV - 1) < 0) ? 9 : (cV - 1)) + postFix;
        if (!done.contains(newChild2)) {
          queue.offer(new State(newChild2, state.dist + 1));
          done.add(newChild2);
        }
      }
    }
    return -1;
  }
}
