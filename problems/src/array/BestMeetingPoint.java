package array;

/**
 * Created by gouthamvidyapradhan on 10/03/2019 A group of two or more people wants to meet and
 * minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks
 * the home of someone in the group. The distance is calculated using Manhattan Distance, where
 * distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 *
 * <p>Example:
 *
 * <p>Input:
 *
 * <p>1 - 0 - 0 - 0 - 1 | | | | | 0 - 0 - 0 - 0 - 0 | | | | | 0 - 0 - 1 - 0 - 0
 *
 * <p>Output: 6
 *
 * <p>Explanation: Given three people living at (0,0), (0,4), and (2,2): The point (0,2) is an ideal
 * meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.
 *
 * <p>Solution: O(N ^ 2 + M ^ 2) + O(N x M): Calculate the total number of persons in each row and
 * each column and then take a minimum of cartesian product of each row and each column.
 */
public class BestMeetingPoint {

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    int[][] grid = {{1, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}};
    System.out.println(new BestMeetingPoint().minTotalDistance(grid));
  }

  public int minTotalDistance(int[][] grid) {
    int[] countR = new int[grid.length];
    int[] countC = new int[grid[0].length];

    int[] distR = new int[grid.length];
    int[] distC = new int[grid[0].length];

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 1) {
          countR[i]++;
          countC[j]++;
        }
      }
    }

    for (int i = 0; i < distR.length; i++) {
      for (int j = 0; j < distR.length; j++) {
        if (countR[j] != 0) {
          distR[i] += Math.abs(j - i) * countR[j];
        }
      }
    }

    for (int i = 0; i < distC.length; i++) {
      for (int j = 0; j < distC.length; j++) {
        if (countC[j] != 0) {
          distC[i] += Math.abs(j - i) * countC[j];
        }
      }
    }

    int min = Integer.MAX_VALUE;
    for (int i = 0; i < distR.length; i++) {
      for (int j = 0; j < distC.length; j++) {
        min = Math.min(min, distR[i] + distC[j]);
      }
    }

    return min;
  }
}
