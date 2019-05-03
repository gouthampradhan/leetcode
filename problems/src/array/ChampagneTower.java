package array;

/**
 * Created by gouthamvidyapradhan on 28/03/2019 We stack glasses in a pyramid, where the first row
 * has 1 glass, the second row has 2 glasses, and so on until the 100th row. Each glass holds one
 * cup (250ml) of champagne.
 *
 * <p>Then, some champagne is poured in the first glass at the top. When the top most glass is full,
 * any excess liquid poured will fall equally to the glass immediately to the left and right of it.
 * When those glasses become full, any excess champagne will fall equally to the left and right of
 * those glasses, and so on. (A glass at the bottom row has it's excess champagne fall on the
 * floor.)
 *
 * <p>For example, after one cup of champagne is poured, the top most glass is full. After two cups
 * of champagne are poured, the two glasses on the second row are half full. After three cups of
 * champagne are poured, those two cups become full - there are 3 full glasses total now. After four
 * cups of champagne are poured, the third row has the middle glass half full, and the two outside
 * glasses are a quarter full, as pictured below.
 *
 * <p>Now after pouring some non-negative integer cups of champagne, return how full the j-th glass
 * in the i-th row is (both i and j are 0 indexed.)
 *
 * <p>Example 1: Input: poured = 1, query_glass = 1, query_row = 1 Output: 0.0 Explanation: We
 * poured 1 cup of champange to the top glass of the tower (which is indexed as (0, 0)). There will
 * be no excess liquid so all the glasses under the top glass will remain empty.
 *
 * <p>Example 2: Input: poured = 2, query_glass = 1, query_row = 1 Output: 0.5 Explanation: We
 * poured 2 cups of champange to the top glass of the tower (which is indexed as (0, 0)). There is
 * one cup of excess liquid. The glass indexed as (1, 0) and the glass indexed as (1, 1) will share
 * the excess liquid equally, and each will get half cup of champange.
 *
 * <p>Note:
 *
 * <p>poured will be in the range of [0, 10 ^ 9]. query_glass and query_row will be in the range of
 * [0, 99].
 *
 * <p>Solution: Calculate for every glass and for each row at a time. Use the value from the
 * previous row to calculate the current value.
 *
 * @see PascalsTriangle
 */
public class ChampagneTower {
  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    System.out.println(new ChampagneTower().champagneTower(4, 2, 1));
  }

  public double champagneTower(int poured, int query_row, int query_glass) {
    double[][] A = new double[query_row + 1][query_row + 1];
    A[0][0] = poured;
    for (int i = 1; i <= query_row; i++) {
      for (int j = 0; j <= query_row; j++) {
        if (A[i - 1][j] > 1.0) {
          A[i][j] += (A[i - 1][j] - 1.0) / 2;
        }
        if (j == 0) continue;
        if (A[i - 1][j - 1] > 1.0) {
          A[i][j] += (A[i - 1][j - 1] - 1.0) / 2;
        }
      }
    }
    if (A[query_row][query_glass] > 1.0) return 1;
    else return A[query_row][query_glass];
  }
}
