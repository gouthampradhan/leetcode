package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 12/05/2020 Given an integer array arr, in one move you can
 * select a palindromic subarray arr[i], arr[i+1], ..., arr[j] where i <= j, and remove that
 * subarray from the given array. Note that after removing a subarray, the elements on the left and
 * on the right of that subarray move to fill the gap left by the removal.
 *
 * <p>Return the minimum number of moves needed to remove all numbers from the array.
 *
 * <p>Example 1:
 *
 * <p>Input: arr = [1,2] Output: 2 Example 2:
 *
 * <p>Input: arr = [1,3,4,1,5] Output: 3 Explanation: Remove [4] then remove [1,3,1] then remove
 * [5].
 *
 * <p>Constraints:
 *
 * <p>1 <= arr.length <= 100 1 <= arr[i] <= 20
 */
public class PalindromeRemoval {
  public static void main(String[] args) {
    int[] A = {1, 3, 1, 2, 4, 2};
    System.out.println(new PalindromeRemoval().minimumMoves(A));
  }

  int[][] DP;

  public int minimumMoves(int[] arr) {
    DP = new int[arr.length][arr.length];
    return dp(0, arr.length - 1, arr);
  }

  private int dp(int i, int j, int[] arr) {
    if (i > j) return 1;
    else if (DP[i][j] != 0) return DP[i][j];
    else {
      int min = Integer.MAX_VALUE;
      for (int t = j; t >= i; t--) {
        if (arr[i] == arr[t]) {
          min = Math.min(min, dp(i + 1, t - 1, arr) + ((t + 1 > j) ? 0 : dp(t + 1, j, arr)));
        }
      }
      DP[i][j] = min;
      return min;
    }
  }
}
