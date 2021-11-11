package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 23/12/2017. There are a row of n houses, each house can be
 * painted with one of the k colors. The cost of painting each house with a certain color is
 * different. You have to paint all the houses such that no two adjacent houses have the same color.
 *
 * <p>The cost of painting each house with a certain color is represented by a n x k cost matrix.
 * For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of
 * painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.
 *
 * <p>Note: All costs are positive integers.
 *
 * <p>Follow up: Could you solve it in O(nk) runtime?
 *
 * <p>Solution: Worst case run-time complexity of O(n x k) : Perform a prefix and postfix sum and
 * maintain a auxiliary array to keep track of prefix and post-fix sum. Perform a bottom-up dp to
 * calculate the final result. DP[i][j] = DP[i][j] + Min(LeftPrefixSum[i + 1][j], RightPrefixSum[i +
 * 1][j])
 */
public class PaintHouseII {

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[][] A = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};
    System.out.println(new PaintHouseII().minCostII(A));
  }

  public int minCostII(int[][] costs) {
    if (costs.length == 0) return 0;
    int[][] lMin = new int[costs.length][costs[0].length];
    int[][] rMin = new int[costs.length][costs[0].length];
    for (int i = costs.length - 2; i >= 0; i--) {
      int min = Integer.MAX_VALUE;
      for (int j = 0; j < costs[0].length; j++) {
        lMin[i + 1][j] = min;
        min = Math.min(min, costs[i + 1][j]);
      }
      min = Integer.MAX_VALUE;
      for (int j = costs[0].length - 1; j >= 0; j--) {
        rMin[i + 1][j] = min;
        min = Math.min(min, costs[i + 1][j]);
      }

      for (int j = 0; j < costs[0].length; j++) {
        if (j == 0) {
          costs[i][j] = costs[i][j] + rMin[i + 1][j];
        } else if (j == costs[0].length - 1) {
          costs[i][j] = costs[i][j] + lMin[i + 1][j];
        } else {
          costs[i][j] = costs[i][j] + Math.min(lMin[i + 1][j], rMin[i + 1][j]);
        }
      }
    }
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < costs[0].length; i++) {
      min = Math.min(min, costs[0][i]);
    }
    return min;
  }
}
