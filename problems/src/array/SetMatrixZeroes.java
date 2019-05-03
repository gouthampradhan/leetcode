package array;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by pradhang on 3/28/2017. Given a m x n matrix, if an element is 0, set its entire row
 * and column to 0. Do it in place.
 *
 * <p>click to show follow up.
 *
 * <p>Follow up: Did you use extra space? A straight forward solution using O(mn) space is probably
 * a bad idea. A simple improvement uses O(m + n) space, but still not the best solution. Could you
 * devise a constant space solution?
 */
public class SetMatrixZeroes {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[][] matrix = {{0, 8, 7}, {9, 0, 8}, {9, 9, 0}};

    new SetMatrixZeroes().setZeroes(matrix);
  }

  public void setZeroes(int[][] matrix) {
    Set<Integer> row = new HashSet<>();
    Set<Integer> col = new HashSet<>();
    int m = matrix.length;
    int n = matrix[0].length;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (matrix[i][j] == 0) {
          row.add(i);
          col.add(j);
        }
      }
    }

    for (int r : row) {
      for (int j = 0; j < n; j++) {
        matrix[r][j] = 0;
      }
    }

    for (int c : col) {
      for (int i = 0; i < m; i++) {
        matrix[i][c] = 0;
      }
    }
  }
}
