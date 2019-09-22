package array;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 15/08/2019 We are given a matrix with R rows and C columns has
 * cells with integer coordinates (r, c), where 0 <= r < R and 0 <= c < C.
 *
 * <p>Additionally, we are given a cell in that matrix with coordinates (r0, c0).
 *
 * <p>Return the coordinates of all cells in the matrix, sorted by their distance from (r0, c0) from
 * smallest distance to largest distance. Here, the distance between two cells (r1, c1) and (r2, c2)
 * is the Manhattan distance, |r1 - r2| + |c1 - c2|. (You may return the answer in any order that
 * satisfies this condition.)
 *
 * <p>Example 1:
 *
 * <p>Input: R = 1, C = 2, r0 = 0, c0 = 0 Output: [[0,0],[0,1]] Explanation: The distances from (r0,
 * c0) to other cells are: [0,1] Example 2:
 *
 * <p>Input: R = 2, C = 2, r0 = 0, c0 = 1 Output: [[0,1],[0,0],[1,1],[1,0]] Explanation: The
 * distances from (r0, c0) to other cells are: [0,1,1,2] The answer [[0,1],[1,1],[0,0],[1,0]] would
 * also be accepted as correct. Example 3:
 *
 * <p>Input: R = 2, C = 3, r0 = 1, c0 = 2 Output: [[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]] Explanation:
 * The distances from (r0, c0) to other cells are: [0,1,1,2,2,3] There are other answers that would
 * also be accepted as correct, such as [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]].
 *
 * <p>Note:
 *
 * <p>1 <= R <= 100 1 <= C <= 100 0 <= r0 < R 0 <= c0 < C
 *
 * <p>Solution: O (log (R x C)) Straight forward solution, find the Manhattan distance from the
 * given cell to all the cells in the grid and sort by min distance and return their coordinates.
 */
public class MatrixCellsinDistanceOrder {
  public static void main(String[] args) {
    //
  }

  class Cell {
    int max, i, j;

    Cell(int max, int i, int j) {
      this.max = max;
      this.i = i;
      this.j = j;
    }
  }

  public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
    List<Cell> list = new ArrayList<>();
    for (int i = 0; i < R; i++) {
      for (int j = 0; j < C; j++) {
        int sum = Math.abs(r0 - i) + Math.abs(c0 - j);
        list.add(new Cell(sum, i, j));
      }
    }
    list.sort(Comparator.comparingInt(o -> o.max));
    int[][] A = new int[list.size()][2];
    for (int i = 0; i < A.length; i++) {
      A[i][0] = list.get(i).i;
      A[i][1] = list.get(i).j;
    }
    return A;
  }
}
