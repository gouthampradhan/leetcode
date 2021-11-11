package math;

/**
 * Created by gouthamvidyapradhan on 23/06/2018. N couples sit in 2N seats arranged in a row and
 * want to hold hands. We want to know the minimum number of swaps so that every couple is sitting
 * side by side. A swap consists of choosing any two people, then they stand up and switch seats.
 *
 * <p>The people and seats are represented by an integer from 0 to 2N-1, the couples are numbered in
 * order, the first couple being (0, 1), the second couple being (2, 3), and so on with the last
 * couple being (2N-2, 2N-1).
 *
 * <p>The couples' initial seating is given by row[i] being the value of the person who is initially
 * sitting in the i-th seat.
 *
 * <p>Example 1:
 *
 * <p>Input: row = [0, 2, 1, 3] Output: 1 Explanation: We only need to swap the second (row[1]) and
 * third (row[2]) person. Example 2:
 *
 * <p>Input: row = [3, 2, 0, 1] Output: 0 Explanation: All couples are already seated side by side.
 * Note:
 *
 * <p>len(row) is even and in the range of [4, 60]. row is guaranteed to be a permutation of
 * 0...len(row)-1.
 *
 * <p>Solution: O(N ^ 2). Find the index i of every even-number n and (n + 1)th number. If the index
 * i of number n is even then swap the number (n + 1) with index i + 1, else swap the number (n + 1)
 * with index i - 1. Count the total swaps and return the answer.
 */
public class CouplesHoldingHands {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] A = {1, 3, 4, 0, 2, 5};
    System.out.println(new CouplesHoldingHands().minSwapsCouples(A));
  }

  public int minSwapsCouples(int[] row) {
    int N = row.length;
    int count = 0;
    for (int i = 0; i < N; i += 2) {
      int pos = find(row, i);
      if ((pos % 2) == 0) {
        if (row[pos + 1] != i + 1) {
          int nexNumPos = find(row, i + 1);
          swap(row, pos + 1, nexNumPos);
          count++;
        }
      } else {
        if (row[pos - 1] != i + 1) {
          int nexNumPos = find(row, i + 1);
          swap(row, pos - 1, nexNumPos);
          count++;
        }
      }
    }
    return count;
  }

  private int find(int[] A, int n) {
    for (int i = 0; i < A.length; i++) {
      if (A[i] == n) {
        return i;
      }
    }
    return -1;
  }

  private void swap(int[] A, int i, int j) {
    int temp = A[i];
    A[i] = A[j];
    A[j] = temp;
  }
}
