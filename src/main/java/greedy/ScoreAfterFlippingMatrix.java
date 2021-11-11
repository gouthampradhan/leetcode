package greedy;

/**
 * Created by gouthamvidyapradhan on 26/04/2019 We have a two dimensional matrix A where each value
 * is 0 or 1.
 *
 * <p>A move consists of choosing any row or column, and toggling each value in that row or column:
 * changing all 0s to 1s, and all 1s to 0s.
 *
 * <p>After making any number of moves, every row of this matrix is interpreted as a binary number,
 * and the score of the matrix is the sum of these numbers.
 *
 * <p>Return the highest possible score.
 *
 * <p>Example 1:
 *
 * <p>Input: [[0,0,1,1],[1,0,1,0],[1,1,0,0]] Output: 39 Explanation: Toggled to
 * [[1,1,1,1],[1,0,0,1],[1,1,1,1]]. 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
 *
 * <p>Note:
 *
 * <p>1 <= A.length <= 20 1 <= A[0].length <= 20 A[i][j] is 0 or 1.
 *
 * <p>Solution: O(N x N) Select each row and greedily flip the row if it maximizes the value. Select
 * each column and flip the column if the count of 1's in the column is smaller than count of 0's
 * Sum up the final value from each column return the answer.
 */
public class ScoreAfterFlippingMatrix {

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    int[][] A = {{1, 0}, {1, 0}, {1, 0}, {1, 1}};
    System.out.println(new ScoreAfterFlippingMatrix().matrixScore(A));
  }

  public int matrixScore(int[][] A) {
    for (int[] a : A) {
      int temp1 = makeNum(a);
      flip(a);
      int temp2 = makeNum(a);
      if (temp1 > temp2) {
        // revert
        flip(a);
      }
    }
    for (int i = 0; i < A[0].length; i++) {
      int count = 0;
      for (int j = 0; j < A.length; j++) {
        if (A[j][i] == 1) {
          count++;
        }
      }
      if (count < (A.length - count)) {
        for (int j = 0; j < A.length; j++) {
          if (A[j][i] == 0) {
            A[j][i] = 1;
          } else {
            A[j][i] = 0;
          }
        }
      }
    }
    int sum = 0;
    for (int[] a : A) {
      sum += makeNum(a);
    }
    return sum;
  }

  private int makeNum(int[] a) {
    int n = 0;
    for (int i = 0; i < a.length; i++) {
      if (a[i] == 1) {
        n |= (1 << (a.length - i - 1));
      }
    }
    return n;
  }

  private void flip(int[] A) {
    for (int i = 0; i < A.length; i++) {
      if (A[i] == 1) {
        A[i] = 0;
      } else {
        A[i] = 1;
      }
    }
  }
}
