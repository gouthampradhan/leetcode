package array;

/**
 * Created by gouthamvidyapradhan on 03/12/2017.
 *
 * <p>Given two sparse matrices A and B, return the result of AB.
 *
 * <p>You may assume that A's column number is equal to B's row number.
 *
 * <p>Example:
 *
 * <p>A = [ [ 1, 0, 0], [-1, 0, 3] ]
 *
 * <p>B = [ [ 7, 0, 0 ], [ 0, 0, 0 ], [ 0, 0, 1 ] ]
 *
 * <p>| 1 0 0 | | 7 0 0 | | 7 0 0 | AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 | | 0 0 1 |
 *
 * <p>Solution: Skip the rows and columns which you already know would result in zero after
 * multiplication.
 */
public class SparseMatrixMultiplication {

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[][] A = {{1, 0, 0, 1}};
    int[][] B = {{7, 0, 0}, {0, 0, 0}, {0, 0, 1}, {0, 0, 1}};
    int[][] C = new SparseMatrixMultiplication().multiply(A, B);
  }

  public int[][] multiply(int[][] A, int[][] B) {
    boolean[] AH = new boolean[A.length]; // Metadata for matrix A
    boolean[] BH = new boolean[B[0].length]; // Metadata for matrix A
    for (int i = 0; i < A.length; i++) {
      for (int j = 0; j < A[0].length; j++) {
        if (A[i][j] != 0) {
          AH[i] = true;
          break;
        }
      }
    }

    for (int i = 0; i < B[0].length; i++) {
      for (int j = 0; j < B.length; j++) {
        if (B[j][i] != 0) {
          BH[i] = true;
          break;
        }
      }
    }

    int[][] C = new int[A.length][B[0].length];
    for (int i = 0; i < C.length; i++) {
      if (AH[i]) {
        for (int j = 0; j < C[0].length; j++) {
          if (BH[j]) {
            int sum = 0;
            for (int k = 0; k < A[0].length; k++) {
              sum += A[i][k] * B[k][j];
            }
            C[i][j] = sum;
          }
        }
      }
    }
    return C;
  }
}
