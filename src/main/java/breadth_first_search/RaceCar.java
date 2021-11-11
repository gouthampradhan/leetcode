package breadth_first_search;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 19/08/2018 Your car starts at position 0 and speed +1 on an
 * infinite number line. (Your car can go into negative positions.)
 *
 * <p>Your car drives automatically according to a sequence of instructions A (accelerate) and R
 * (reverse).
 *
 * <p>When you get an instruction "A", your car does the following: position += speed, speed *= 2.
 *
 * <p>When you get an instruction "R", your car does the following: if your speed is positive then
 * speed = -1 , otherwise speed = 1. (Your position stays the same.)
 *
 * <p>For example, after commands "AAR", your car goes to positions 0->1->3->3, and your speed goes
 * to 1->2->4->-1.
 *
 * <p>Now for some target position, say the length of the shortest sequence of instructions to get
 * there.
 *
 * <p>Example 1: Input: target = 3 Output: 2 Explanation: The shortest instruction sequence is "AA".
 * Your position goes from 0->1->3. Example 2: Input: target = 6 Output: 5 Explanation: The shortest
 * instruction sequence is "AAARA". Your position goes from 0->1->3->7->7->6.
 *
 * <p>Note:
 *
 * <p>1 <= target <= 10000.
 *
 * <p>Solution: O(n log n) Do a BFS and visit every possible state. Prune the search space by
 * avoiding negative vertices and keep a boundary target of approximately (target * 2) - beyond this
 * boundary target the race car should not progress in the forward direction.
 */
public class RaceCar {

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    System.out.println(new RaceCar().racecar(1000));
  }

  private class Node {
    int v, s, d;

    Node(int v, int s, int d) {
      this.v = v;
      this.s = s;
      this.d = d;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Node)) return false;
      Node node = (Node) o;
      return v == node.v && s == node.s;
    }

    @Override
    public int hashCode() {
      return Objects.hash(v, s);
    }
  }

  public int racecar(int target) {
    if (target == 0) return 0;
    Queue<Node> queue = new ArrayDeque<>();
    Set<Node> done = new HashSet<>();
    Node start = new Node(0, 1, 0);
    done.add(start);
    queue.offer(start);
    while (!queue.isEmpty()) {
      Node curr = queue.poll();
      if (curr.v < (target * 2)) {
        Node c1 = new Node(curr.v + curr.s, curr.s * 2, curr.d + 1);
        if (c1.v >= 0) {
          if (!done.contains(c1)) {
            queue.offer(c1);
            done.add(c1);
            if (target == c1.v) {
              return c1.d;
            }
          }
        }
      }
      Node c2 = new Node(curr.v, curr.s < 0 ? 1 : -1, curr.d + 1);
      if (!done.contains(c2)) {
        done.add(c2);
        queue.offer(c2);
      }
    }
    return -1;
  }
}
