/* (C) 2024 YourCompanyName */
package dynamic_programming;

import java.util.*;

/** Created by gouthamvidyapradhan on 14/05/2020 */
public class ConstrainedSubsequenceSum {

  public static void main(String[] args) {
    int[] A = {10, -2, -10, -5, 20};
    System.out.println(new ConstrainedSubsequenceSum().constrainedSubsetSum(A, 2));
  }

  class Node {
    int v, i;

    public int getV() {
      return v;
    }

    public int getI() {
      return i;
    }

    Node(int v, int i) {
      this.v = v;
      this.i = i;
    }
  }

  public int constrainedSubsetSum(int[] nums, int k) {
    Queue<Node> pQ =
        new PriorityQueue<>(Comparator.comparing(Node::getV).thenComparing(Node::getI).reversed());
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < nums.length; i++) {
      int value = nums[i];
      while (!pQ.isEmpty() && (i - pQ.peek().i > k)) {
        pQ.poll();
      }
      if (pQ.isEmpty()) {
        pQ.offer(new Node(value, i));
      } else {
        if (pQ.peek().v + value > value) {
          pQ.offer(new Node(pQ.peek().v + value, i));
        } else {
          pQ.offer(new Node(value, i));
        }
      }
      max = Math.max(max, pQ.peek().v);
    }
    return max;
  }
}
