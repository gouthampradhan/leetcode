package heap;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 30/11/2019 We have a list of points on the plane. Find the K
 * closest points to the origin (0, 0).
 *
 * <p>(Here, the distance between two points on a plane is the Euclidean distance.)
 *
 * <p>You may return the answer in any order. The answer is guaranteed to be unique (except for the
 * order that it is in.)
 *
 * <p>Example 1:
 *
 * <p>Input: points = [[1,3],[-2,2]], K = 1 Output: [[-2,2]] Explanation: The distance between (1,
 * 3) and the origin is sqrt(10). The distance between (-2, 2) and the origin is sqrt(8). Since
 * sqrt(8) < sqrt(10), (-2, 2) is closer to the origin. We only want the closest K = 1 points from
 * the origin, so the answer is just [[-2,2]]. Example 2:
 *
 * <p>Input: points = [[3,3],[5,-1],[-2,4]], K = 2 Output: [[3,3],[-2,4]] (The answer [[-2,4],[3,3]]
 * would also be accepted.)
 *
 * <p>Note:
 *
 * <p>1 <= K <= points.length <= 10000 -10000 < points[i][0] < 10000 -10000 < points[i][1] < 10000
 */
public class KClosestPointsToOrigin {
  public static void main(String[] args) {
    int[][] A = {{3, 3}, {5, -1}, {-2, 4}};
    int[][] ans = new KClosestPointsToOrigin().kClosest(A, 2);
    System.out.println();
  }

  class Point {
    int a, b;

    Point(int a, int b) {
      this.a = a;
      this.b = b;
    }

    public long distance() {
      return (long) (a * a) + (long) (b * b);
    }
  }

  public int[][] kClosest(int[][] points, int K) {
    PriorityQueue<Point> pq =
        new PriorityQueue<>((o1, o2) -> Long.compare(o2.distance(), o1.distance()));
    for (int[] p : points) {
      pq.offer(new Point(p[0], p[1]));
      if (pq.size() > K) {
        pq.poll();
      }
    }
    int[][] ans = new int[K][2];
    int i = 0;
    while (!pq.isEmpty()) {
      Point point = pq.poll();
      ans[i][0] = point.a;
      ans[i++][1] = point.b;
    }
    return ans;
  }
}
