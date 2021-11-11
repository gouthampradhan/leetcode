package array;

/**
 * Created by gouthamvidyapradhan on 14/08/2019 Given a 01 matrix M, find the longest line of
 * consecutive one in the matrix. The line could be horizontal, vertical, diagonal or anti-diagonal.
 * Example: Input: [[0,1,1,0], [0,1,1,0], [0,0,0,1]] Output: 3 Hint: The number of elements in the
 * given matrix will not exceed 10,000.
 *
 * <p>Solution O(N x M) for each cell keep track of maximum value possible horizontally, vertically
 * and diagonally. Start iterating from left-right and top-bottom and repeat the same from
 * right-left and top to bottom to get max for anti-diagonal and return the max value.
 */
public class LongestLineofConsecutiveOneinMatrix {
  final int[] R = {0, 0, -1, 1};
  final int[] C = {1, -1, 0, 0};

  public static void main(String[] args) {
    int[][] M = {
      {1, 1, 0, 0, 1, 0, 0, 1, 1, 0},
      {1, 0, 0, 1, 0, 1, 1, 1, 1, 1},
      {1, 1, 1, 0, 0, 1, 1, 1, 1, 0},
      {0, 1, 1, 1, 0, 1, 1, 1, 1, 1},
      {0, 0, 1, 1, 1, 1, 1, 1, 1, 0},
      {1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
      {0, 1, 1, 1, 1, 1, 1, 0, 0, 1},
      {1, 1, 1, 1, 1, 0, 0, 1, 1, 1},
      {0, 1, 0, 1, 1, 0, 1, 1, 1, 1},
      {1, 1, 1, 0, 1, 0, 1, 1, 1, 1}
    };
    System.out.println(new LongestLineofConsecutiveOneinMatrix().longestLine(M));
  }

  class Cell {
    int h, v, d;

    Cell(int h, int v, int d) {
      this.h = h;
      this.v = v;
      this.d = d;
    }
  }

  public int longestLine(int[][] M) {
    if (M.length == 0) return 0;
    int max = 0;
    Cell[][] cells = new Cell[M.length][M[0].length];
    for (int i = 0; i < M.length; i++) {
      for (int j = 0; j < M[0].length; j++) {
        int h = 0, v = 0, d = 0;
        if (M[i][j] == 1) {
          h = 1;
          v = 1;
          d = 1;
          max = Math.max(max, 1);
          if (j - 1 >= 0) {
            Cell left = cells[i][j - 1];
            if (left.h > 0) {
              h += left.h;
              max = Math.max(max, h);
            }
          }
          if (i - 1 >= 0) {
            Cell top = cells[i - 1][j];
            if (top.v > 0) {
              v += top.v;
              max = Math.max(max, v);
            }
          }
          if (i - 1 >= 0 && j - 1 >= 0) {
            Cell diagonal = cells[i - 1][j - 1];
            if (diagonal.d > 0) {
              d += diagonal.d;
              max = Math.max(max, d);
            }
          }
        }
        cells[i][j] = new Cell(h, v, d);
      }
    }

    for (int i = 0; i < M.length; i++) {
      for (int j = M[0].length - 1; j >= 0; j--) {
        int h = 0, v = 0, d = 0;
        if (M[i][j] == 1) {
          h = 1;
          v = 1;
          d = 1;
          max = Math.max(max, 1);
          if (j + 1 < M[0].length) {
            Cell left = cells[i][j + 1];
            if (left.h > 0) {
              h += left.h;
              max = Math.max(max, h);
            }
          }
          if (i - 1 >= 0) {
            Cell top = cells[i - 1][j];
            if (top.v > 0) {
              v += top.v;
              max = Math.max(max, v);
            }
          }
          if (i - 1 >= 0 && j + 1 < M[0].length) {
            Cell diagonal = cells[i - 1][j + 1];
            if (diagonal.d > 0) {
              d += diagonal.d;
              max = Math.max(max, d);
            }
          }
        }
        cells[i][j] = new Cell(h, v, d);
      }
    }

    return max;
  }
}
