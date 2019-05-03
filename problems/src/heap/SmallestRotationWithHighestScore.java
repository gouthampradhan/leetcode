package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by gouthamvidyapradhan on 06/04/2019 Given an array A, we may rotate it by a non-negative
 * integer K so that the array becomes A[K], A[K+1], A{K+2], ... A[A.length - 1], A[0], A[1], ...,
 * A[K-1]. Afterward, any entries that are less than or equal to their index are worth 1 point.
 *
 * <p>For example, if we have [2, 4, 1, 3, 0], and we rotate by K = 2, it becomes [1, 3, 0, 2, 4].
 * This is worth 3 points because 1 > 0 [no points], 3 > 1 [no points], 0 <= 2 [one point], 2 <= 3
 * [one point], 4 <= 4 [one point].
 *
 * <p>Over all possible rotations, return the rotation index K that corresponds to the highest score
 * we could receive. If there are multiple answers, return the smallest such index K.
 *
 * <p>Example 1: Input: [2, 3, 1, 4, 0] Output: 3 Explanation: Scores for each K are listed below: K
 * = 0, A = [2,3,1,4,0], score 2 K = 1, A = [3,1,4,0,2], score 3 K = 2, A = [1,4,0,2,3], score 3 K =
 * 3, A = [4,0,2,3,1], score 4 K = 4, A = [0,2,3,1,4], score 3 So we should choose K = 3, which has
 * the highest score.
 *
 * <p>Example 2: Input: [1, 3, 0, 2, 4] Output: 0 Explanation: A will always have 3 points no matter
 * how it shifts. So we will choose the smallest K, which is 0. Note:
 *
 * <p>A will have length at most 20000. A[i] will be in the range [0, A.length].
 *
 * <p>Solution O(NLogN). The key insight to this problem is to notice that the point of a number
 * changes to 1 from 0 if the position changes to A.length - 1 and similarly the point changes to 1
 * from 0 if the position changes to a index = NUM - 1. Maintain a priority queue with
 * rotation_count (the number of rotation required to change its points from either 0 to 1 or from 1
 * to 0), pop all the indices from priority queue which has rotation_count equal to current rotation
 * count and update the rotation_count to its next value. Maintain a max count and the rotation
 * index pair and return rotation index as the answer.
 */
public class SmallestRotationWithHighestScore {

  private class Node {
    int i, n, r, v;

    Node(int i, int n, int r, int v) {
      this.i = i;
      this.n = n;
      this.r = r;
      this.v = v;
    }
  }
  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    int[] A = {2, 3, 1, 4, 0};
    System.out.println(new SmallestRotationWithHighestScore().bestRotation(A));
  }

  public int bestRotation(int[] A) {
    PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.r));
    int curr = 0;
    for (int i = 0; i < A.length; i++) {
      int num = A[i];
      int v = 0, r = Integer.MAX_VALUE;
      if (num <= i) {
        v = 1;
        curr++;
      }
      if (num != 0) {
        r = v == 0 ? i + 1 : (i - num + 1);
      }
      pq.offer(new Node(i, num, r, v));
    }
    int R = 0, max = curr, ans = 0;
    while (R < A.length) {
      while (pq.peek().r - R == 0) {
        Node top = pq.poll();
        top.v = (top.v + 1) % 2;
        top.i = (top.i - R < 0) ? (A.length + (top.i - R)) : (top.i - R);
        top.r = top.v == 0 ? top.i + 1 : (top.i - top.n + 1);
        top.r += R;
        curr = (top.v == 0) ? curr - 1 : curr + 1;
        pq.offer(top);
      }
      if (curr > max) {
        ans = R;
        max = curr;
      }
      R++;
    }
    return ans;
  }
}
