package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 26/12/2017. Given a grid where each entry is only 0 or 1, find
 * the number of corner rectangles.
 *
 * <p>A corner rectangle is 4 distinct 1s on the grid that form an axis-aligned rectangle. Note that
 * only the corners need to have the value 1. Also, all four 1s used must be distinct.
 *
 * <p>Example 1: Input: grid = [[1, 0, 0, 1, 0], [0, 0, 1, 0, 1], [0, 0, 0, 1, 0], [1, 0, 1, 0, 1]]
 * Output: 1 Explanation: There is only one corner rectangle, with corners grid[1][2], grid[1][4],
 * grid[3][2], grid[3][4]. Example 2: Input: grid = [[1, 1, 1], [1, 1, 1], [1, 1, 1]] Output: 9
 * Explanation: There are four 2x2 rectangles, four 2x3 and 3x2 rectangles, and one 3x3 rectangle.
 * Example 3: Input: grid = [[1, 1, 1, 1]] Output: 0 Explanation: Rectangles must have four distinct
 * corners. Note: The number of rows and columns of grid will each be in the range [1, 200]. Each
 * grid[i][j] will be either 0 or 1. The number of 1s in the grid will be at most 6000.
 *
 * <p>Solution O(n + m ^ 2): For every row, consider each pair of 1s (every column pairs) and sum up
 * the previous occurrence of 1s for the same column.
 */
public class CornerRectangles {

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[][] A = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
    System.out.println(new CornerRectangles().countCornerRectangles(A));
  }

  public int countCornerRectangles(int[][] grid) {
    int[][] count = new int[grid[0].length][grid[0].length];
    int result = 0;
    for (int[] row : grid) {
      for (int i = 0; i < row.length; i++) {
        if (row[i] == 1) {
          for (int j = i + 1; j < row.length; j++) {
            if (row[j] == 1) {
              if (count[i][j] > 0) {
                result += count[i][j];
              }
              count[i][j]++;
            }
          }
        }
      }
    }
    return result;
  }
}
