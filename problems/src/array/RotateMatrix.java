package array;

/**
 * Created by gouthamvidyapradhan on 21/03/2017. You are given an n x n 2D matrix representing an
 * image.
 *
 * <p>Rotate the image by 90 degrees (clockwise).
 *
 * <p>Follow up: Could you do this in-place?
 */
public class RotateMatrix {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[][] A = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    new RotateMatrix().rotate(A);
    for (int i = 0; i < A.length; i++) {
      for (int j = 0; j < A[0].length; j++) {
        System.out.println(A[i][j]);
      }
    }
  }

  public void rotate(int[][] matrix) {
    int lc = 0, tr = 0, rc = matrix[0].length - 1, br = matrix.length - 1;
    while (tr < br) {
      for (int i = lc, j = tr, k = rc, l = br;
          i < rc && j < br && k > lc && l > tr;
          i++, j++, k--, l--) {
        int temp1 = matrix[j][rc];
        matrix[j][rc] = matrix[tr][i];
        int temp2 = matrix[br][k];
        matrix[br][k] = temp1;
        temp1 = matrix[l][lc];
        matrix[l][lc] = temp2;
        matrix[tr][i] = temp1;
      }
      lc++;
      tr++;
      rc--;
      br--;
    }
  }
}
