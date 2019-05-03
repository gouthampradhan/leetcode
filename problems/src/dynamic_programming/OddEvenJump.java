package dynamic_programming;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 17/03/2019 You are given an integer array A. From some starting
 * index, you can make a series of jumps. The (1st, 3rd, 5th, ...) jumps in the series are called
 * odd numbered jumps, and the (2nd, 4th, 6th, ...) jumps in the series are called even numbered
 * jumps.
 *
 * <p>You may from index i jump forward to index j (with i < j) in the following way:
 *
 * <p>During odd numbered jumps (ie. jumps 1, 3, 5, ...), you jump to the index j such that A[i] <=
 * A[j] and A[j] is the smallest possible value. If there are multiple such indexes j, you can only
 * jump to the smallest such index j. During even numbered jumps (ie. jumps 2, 4, 6, ...), you jump
 * to the index j such that A[i] >= A[j] and A[j] is the largest possible value. If there are
 * multiple such indexes j, you can only jump to the smallest such index j. (It may be the case that
 * for some index i, there are no legal jumps.) A starting index is good if, starting from that
 * index, you can reach the end of the array (index A.length - 1) by jumping some number of times
 * (possibly 0 or more than once.)
 *
 * <p>Return the number of good starting indexes.
 *
 * <p>Example 1:
 *
 * <p>Input: [10,13,12,14,15] Output: 2 Explanation: From starting index i = 0, we can jump to i = 2
 * (since A[2] is the smallest among A[1], A[2], A[3], A[4] that is greater or equal to A[0]), then
 * we can't jump any more. From starting index i = 1 and i = 2, we can jump to i = 3, then we can't
 * jump any more. From starting index i = 3, we can jump to i = 4, so we've reached the end. From
 * starting index i = 4, we've reached the end already. In total, there are 2 different starting
 * indexes (i = 3, i = 4) where we can reach the end with some number of jumps. Example 2:
 *
 * <p>Input: [2,3,1,1,4] Output: 3 Explanation: From starting index i = 0, we make jumps to i = 1, i
 * = 2, i = 3:
 *
 * <p>During our 1st jump (odd numbered), we first jump to i = 1 because A[1] is the smallest value
 * in (A[1], A[2], A[3], A[4]) that is greater than or equal to A[0].
 *
 * <p>During our 2nd jump (even numbered), we jump from i = 1 to i = 2 because A[2] is the largest
 * value in (A[2], A[3], A[4]) that is less than or equal to A[1]. A[3] is also the largest value,
 * but 2 is a smaller index, so we can only jump to i = 2 and not i = 3.
 *
 * <p>During our 3rd jump (odd numbered), we jump from i = 2 to i = 3 because A[3] is the smallest
 * value in (A[3], A[4]) that is greater than or equal to A[2].
 *
 * <p>We can't jump from i = 3 to i = 4, so the starting index i = 0 is not good.
 *
 * <p>In a similar manner, we can deduce that: From starting index i = 1, we jump to i = 4, so we
 * reach the end. From starting index i = 2, we jump to i = 3, and then we can't jump anymore. From
 * starting index i = 3, we jump to i = 4, so we reach the end. From starting index i = 4, we are
 * already at the end. In total, there are 3 different starting indexes (i = 1, i = 3, i = 4) where
 * we can reach the end with some number of jumps. Example 3:
 *
 * <p>Input: [5,1,3,4,2] Output: 3 Explanation: We can reach the end from starting indexes 1, 2, and
 * 4.
 *
 * <p>Note:
 *
 * <p>1 <= A.length <= 20000 0 <= A[i] < 100000
 *
 * <p>Solution: O(N log N) For each array index and for each odd/even turn pre-calculate the next
 * jump index - this can be achieved in O(n log n) by using a balanced tree. Check for each array
 * index if we can reach end of the array by using the pre-calculated values for next jump - cache
 * the values to avoid recalculating. Sum up total number of such start indices and that will be the
 * answer.
 */
public class OddEvenJump {

  private class Node {
    int num, pos;

    Node(int num, int pos) {
      this.num = num;
      this.pos = pos;
    }
  }

  TreeSet<Node> treeSet;
  int[][] next;
  int[][] possible;
  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    int[] A = {10, 13, 12, 14, 15};
    System.out.println(new OddEvenJump().oddEvenJumps(A));
  }

  public int oddEvenJumps(int[] A) {
    treeSet = new TreeSet<>(Comparator.comparingInt(o -> o.num));
    next = new int[2][A.length];
    possible = new int[2][A.length];
    Arrays.fill(next[0], -1);
    Arrays.fill(next[1], -1);
    Arrays.fill(possible[0], -1);
    Arrays.fill(possible[1], -1);
    for (int i = A.length - 1; i >= 0; i--) {
      int num = A[i];
      // odd case
      Node curr = new Node(num, i);
      Node ceil = treeSet.ceiling(new Node(num, i));
      if (ceil != null) {
        next[0][i] = ceil.pos;
      }
      // even case
      Node floor = treeSet.floor(new Node(num, i));
      if (floor != null) {
        next[1][i] = floor.pos;
      }
      treeSet.remove(curr);
      treeSet.add(curr);
    }
    int count = 0;
    for (int i = 0; i < A.length; i++) {
      count += dp(A, i, next, possible, 0) == 1 ? 1 : 0;
    }
    return count;
  }

  private int dp(int[] A, int i, int[][] next, int[][] possible, int oddOrEven) {
    if (i == A.length - 1) return 1;
    else if (possible[oddOrEven][i] == 1 || possible[oddOrEven][i] == 0) {
      return possible[oddOrEven][i];
    } else {
      int nextPos = oddOrEven == 0 ? next[0][i] : next[1][i];
      if (nextPos == -1) {
        possible[oddOrEven][i] = 0;
        return possible[oddOrEven][i];
      } else {
        possible[oddOrEven][i] = dp(A, nextPos, next, possible, ((oddOrEven + 1) % 2));
        return possible[oddOrEven][i];
      }
    }
  }
}
