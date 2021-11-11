package greedy;
/**
 * Created by gouthamvidyapradhan on 09/04/2019 Suppose LeetCode will start its IPO soon. In order
 * to sell a good price of its shares to Venture Capital, LeetCode would like to work on some
 * projects to increase its capital before the IPO. Since it has limited resources, it can only
 * finish at most k distinct projects before the IPO. Help LeetCode design the best way to maximize
 * its total capital after finishing at most k distinct projects.
 *
 * <p>You are given several projects. For each project i, it has a pure profit Pi and a minimum
 * capital of Ci is needed to start the corresponding project. Initially, you have W capital. When
 * you finish a project, you will obtain its pure profit and the profit will be added to your total
 * capital.
 *
 * <p>To sum up, pick a list of at most k distinct projects from given projects to maximize your
 * final capital, and output your final maximized capital.
 *
 * <p>Example 1: Input: k=2, W=0, Profits=[1,2,3], Capital=[0,1,1].
 *
 * <p>Output: 4
 *
 * <p>Explanation: Since your initial capital is 0, you can only start the project indexed 0. After
 * finishing it you will obtain profit 1 and your capital becomes 1. With capital 1, you can either
 * start the project indexed 1 or the project indexed 2. Since you can choose at most 2 projects,
 * you need to finish the project indexed 2 to get the maximum capital. Therefore, output the final
 * maximized capital, which is 0 + 1 + 3 = 4. Note: You may assume all numbers in the input are
 * non-negative integers. The length of Profits array and Capital array will not exceed 50,000. The
 * answer is guaranteed to fit in a 32-bit signed integer.
 *
 * <p>Solution: O(N log N) where N is the size of Capital/Profit array. General intuition is to pick
 * a project which gives maximum profit for the available capital. Sum the profit with the original
 * capital and this becomes the new available capital now, again pick the project which gives
 * maximum profit. Continue this until K projects are picked. Maintain a priority queue to pick the
 * project which gives maximum profit.
 */
import java.util.*;

public class IPO {

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    int[] P = {1, 2, 3};
    int[] C = {1, 1, 2};
    System.out.println(new IPO().findMaximizedCapital(1, 0, P, C));
  }

  class Pair {
    int p, c;

    Pair(int p, int c) {
      this.p = p;
      this.c = c;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Pair)) return false;
      Pair pair = (Pair) o;
      return p == pair.p && c == pair.c;
    }

    public int getP() {
      return p;
    }

    public int getC() {
      return c;
    }

    @Override
    public int hashCode() {
      return Objects.hash(p, c);
    }
  }

  public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
    PriorityQueue<Pair> profits =
        new PriorityQueue<>(Comparator.comparing(Pair::getP).reversed().thenComparing(Pair::getC));
    PriorityQueue<Pair> capitals = new PriorityQueue<>(Comparator.comparing(Pair::getC));
    for (int i = 0; i < Profits.length; i++) {
      capitals.offer(new Pair(Profits[i], Capital[i]));
    }
    while (true) {
      while (!capitals.isEmpty() && capitals.peek().getC() <= W) {
        profits.offer(capitals.poll());
      }
      if (!profits.isEmpty() && profits.peek().getC() <= W && k > 0) {
        W += profits.poll().getP();
        k--;
      }
      if (capitals.isEmpty() || capitals.peek().getC() > W || k == 0) break;
    }
    while (k > 0 && !profits.isEmpty()) {
      W += profits.poll().getP();
      k--;
    }
    return W;
  }
}
