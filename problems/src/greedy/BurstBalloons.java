package greedy;

import java.util.Arrays;

/**
 * Created by gouthamvidyapradhan on 28/06/2017.
 *
 * <p>There are a number of spherical balloons spread in two-dimensional space. For each balloon,
 * provided input is the start and end coordinates of the horizontal diameter. Since it's
 * horizontal, y-coordinates don't matter and hence the x-coordinates of start and end of the
 * diameter suffice. Start is always smaller than end. There will be at most 104 balloons.
 *
 * <p>An arrow can be shot up exactly vertically from different points along the x-axis. A balloon
 * with xstart and xend bursts by an arrow shot at x if xstart ≤ x ≤ xend. There is no limit to the
 * number of arrows that can be shot. An arrow once shot keeps travelling up infinitely. The problem
 * is to find the minimum number of arrows that must be shot to burst all balloons.
 *
 * <p>Example:
 *
 * <p>Input: [[10,16], [2,8], [1,6], [7,12]]
 *
 * <p>Output: 2
 *
 * <p>Explanation: One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8]
 * and [1,6]) and another arrow at x
 */
public class BurstBalloons {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[][] baloons = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
    System.out.println(new BurstBalloons().findMinArrowShots(baloons));
  }

  public int findMinArrowShots(int[][] points) {
    if (points.length == 0) return 0;
    Arrays.sort(points, ((o1, o2) -> o1[1] - o2[1]));
    int count = 0;
    int leftMost = points[0][1];
    for (int i = 1; i < points.length; i++) {
      if (leftMost < points[i][0]) {
        count++;
        leftMost = points[i][1];
      }
    }
    return count + 1;
  }
}
