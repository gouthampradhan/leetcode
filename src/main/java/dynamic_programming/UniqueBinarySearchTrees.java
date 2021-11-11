package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 31/03/2017. Given n, how many structurally unique BST's (binary
 * search trees) that store values 1...n?
 *
 * <p>For example, Given n = 3, there are a total of 5 unique BST's.
 *
 * <p>1 3 3 2 1 \ / / / \ \ 3 2 1 1 3 2 / / \ \ 2 1 2 3
 */
public class UniqueBinarySearchTrees {
  int[] dp;

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) throws Exception {
    System.out.println(new UniqueBinarySearchTrees().numTrees(5));
  }

  public int numTrees(int n) {
    dp = new int[n + 1];
    dp[0] = 1;
    return dp(n);
  }

  private int dp(int n) {
    if (dp[n] != 0) return dp[n];
    for (int i = 1; i <= n; i++) {
      dp[n] += dp(n - i) * dp(n - (n - i) - 1);
    }
    return dp[n];
  }
}
