package array;
/**
 * Created by gouthamvidyapradhan on 17/02/2018. * Given a 2D integer matrix M representing the gray
 * scale of an image, you need to design a smoother to make the gray scale of each cell becomes the
 * average gray scale (rounding down) of all the 8 surrounding cells and itself. If a cell has less
 * than 8 surrounding cells, then use as many as you can.
 *
 * <p>Example 1: Input: [[1,1,1], [1,0,1], [1,1,1]] Output: [[0, 0, 0], [0, 0, 0], [0, 0, 0]]
 * Explanation: For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0 For the point
 * (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0 For the point (1,1): floor(8/9) =
 * floor(0.88888889) = 0 Note: The value in the given matrix is in the range of [0, 255]. The length
 * and width of the given matrix are in the range of [1, 150].
 */
public class ImageSmoother {

  int[] R = {1, -1, 0, 0, 1, -1, 1, -1};
  int[] C = {0, 0, -1, 1, 1, 1, -1, -1};

  public static void main(String[] args) throws Exception {}

  public int[][] imageSmoother(int[][] M) {
    int[][] result = new int[M.length][M[0].length];
    for (int i = 0; i < M.length; i++) {
      for (int j = 0; j < M[0].length; j++) {
        int numCount = 0;
        int totalCount = 1;
        for (int k = 0; k < 8; k++) {
          int newR = i + R[k];
          int newC = j + C[k];
          if (newR >= 0 && newC >= 0 && newR < M.length && newC < M[0].length) {
            if (M[newR][newC] > 0) {
              numCount += M[newR][newC];
            }
            totalCount++;
          }
        }
        if (M[i][j] == 1) numCount++;
        result[i][j] = numCount / totalCount;
      }
    }
    return result;
  }
}
