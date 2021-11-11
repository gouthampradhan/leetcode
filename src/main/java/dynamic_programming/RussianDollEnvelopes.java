package dynamic_programming;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 05/05/2019 You have a number of envelopes with widths and
 * heights given as a pair of integers (w, h). One envelope can fit into another if and only if both
 * the width and height of one envelope is greater than the width and height of the other envelope.
 *
 * <p>What is the maximum number of envelopes can you Russian doll? (put one inside other)
 *
 * <p>Note: Rotation is not allowed.
 *
 * <p>Example:
 *
 * <p>Input: [[5,4],[6,4],[6,7],[2,3]] Output: 3 Explanation: The maximum number of envelopes you
 * can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 *
 * <p>Solution: O(N ^ 2) Sort the envelopes based on increasing order of area and for each envelope
 * iterate through all the possible envelopes which are smaller than that the current envelope and
 * check the maximum possible envelopes which an be russian dolled.
 */
public class RussianDollEnvelopes {

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    int[][] A = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
    System.out.println(new RussianDollEnvelopes().maxEnvelopes(A));
  }

  class Envelope {
    int l, b;

    Envelope(int l, int b) {
      this.l = l;
      this.b = b;
    }
  }
  /**
   * @param envelopes
   * @return
   */
  public int maxEnvelopes(int[][] envelopes) {
    if (envelopes.length == 0) return 0;
    List<Envelope> list = new ArrayList<>();
    for (int[] row : envelopes) {
      list.add(new Envelope(row[0], row[1]));
    }
    list.sort(((o1, o2) -> Integer.compare(o2.l * o2.b, o1.l * o1.b)));
    int[] DP = new int[envelopes.length];
    Arrays.fill(DP, 1);
    for (int i = list.size() - 1; i >= 0; i--) {
      Envelope env = list.get(i);
      for (int j = i + 1, l = list.size(); j < l; j++) {
        Envelope childEnv = list.get(j);
        if (env.l > childEnv.l && env.b > childEnv.b) {
          DP[i] = Math.max(DP[i], DP[j] + 1);
        }
      }
    }
    int ans = 1;
    for (int i : DP) {
      ans = Math.max(ans, i);
    }
    return ans;
  }
}
