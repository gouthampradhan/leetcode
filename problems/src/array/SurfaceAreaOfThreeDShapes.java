package array;

/**
 * Created by gouthamvidyapradhan on 30/04/2019 On a N * N grid, we place some 1 * 1 * 1 cubes.
 *
 * <p>Each value v = grid[i][j] represents a tower of v cubes placed on top of grid cell (i, j).
 *
 * <p>Return the total surface area of the resulting shapes.
 *
 * <p>Example 1:
 *
 * <p>Input: [[2]] Output: 10 Example 2:
 *
 * <p>Input: [[1,2],[3,4]] Output: 34 Example 3:
 *
 * <p>Input: [[1,0],[0,2]] Output: 16 Example 4:
 *
 * <p>Input: [[1,1,1],[1,0,1],[1,1,1]] Output: 32 Example 5:
 *
 * <p>Input: [[2,2,2],[2,1,2],[2,2,2]] Output: 46
 *
 * <p>Note:
 *
 * <p>1 <= N <= 50 0 <= grid[i][j] <= 50
 *
 * <p>Solution: O(N x M) For each cell, check each adjacent cell and sum the value of (current cell
 * - adjacent cell) if the current cell value is greater than adjacent cell. For every cell which
 * has value grater then 0, the top surface area is by default 1 therefore add one to the sum of
 * each cell.
 */
public class SurfaceAreaOfThreeDShapes {

  private final int[] R = {0, 0, -1, 1};
  private final int[] C = {1, -1, 0, 0};
  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    int[][] A = {{2}};
    System.out.println(new SurfaceAreaOfThreeDShapes().surfaceArea(A));
  }

  public int surfaceArea(int[][] grid) {
    int sum = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        int cell = grid[i][j];
        for (int k = 0; k < 4; k++) {
          int newR = i + R[k];
          int newC = j + C[k];
          if (newR >= 0 && newC >= 0 && newR < grid.length && newC < grid[0].length) {
            int adjacent = grid[newR][newC];
            if (cell > adjacent) {
              sum += (cell - adjacent);
            }
          } else if (newR < 0 || newR >= grid.length || newC < 0 || newC >= grid[0].length) {
            sum += cell;
          }
        }
        if (cell > 0) {
          sum += 2;
        }
      }
    }
    return sum;
  }
}
