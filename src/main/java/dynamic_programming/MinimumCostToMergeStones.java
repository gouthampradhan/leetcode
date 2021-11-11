package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 02/02/2020 There are N piles of stones arranged in a row. The
 * i-th pile has stones[i] stones.
 *
 * <p>A move consists of merging exactly K consecutive piles into one pile, and the cost of this
 * move is equal to the total number of stones in these K piles.
 *
 * <p>Find the minimum cost to merge all piles of stones into one pile. If it is impossible, return
 * -1.
 *
 * <p>Example 1:
 *
 * <p>Input: stones = [3,2,4,1], K = 2 Output: 20 Explanation: We start with [3, 2, 4, 1]. We merge
 * [3, 2] for a cost of 5, and we are left with [5, 4, 1]. We merge [4, 1] for a cost of 5, and we
 * are left with [5, 5]. We merge [5, 5] for a cost of 10, and we are left with [10]. The total cost
 * was 20, and this is the minimum possible. Example 2:
 *
 * <p>Input: stones = [3,2,4,1], K = 3 Output: -1 Explanation: After any merge operation, there are
 * 2 piles left, and we can't merge anymore. So the task is impossible. Example 3:
 *
 * <p>Input: stones = [3,5,1,2,6], K = 3 Output: 25 Explanation: We start with [3, 5, 1, 2, 6]. We
 * merge [5, 1, 2] for a cost of 8, and we are left with [3, 8, 6]. We merge [3, 8, 6] for a cost of
 * 17, and we are left with [17]. The total cost was 25, and this is the minimum possible.
 *
 * <p>Note:
 *
 * <p>1 <= stones.length <= 30 2 <= K <= 30 1 <= stones[i] <= 100
 */
public class MinimumCostToMergeStones {
  public static void main(String[] args) {
    int[] A = {3, 5, 1, 2, 6};
    System.out.println(new MinimumCostToMergeStones().mergeStones(A, 2));
  }

  private int[][][] DP;
  private int K;
  private int[] sum;

  public int mergeStones(int[] stones, int K) {
    if (((stones.length - 1) % (K - 1)) != 0) return -1;
    DP = new int[stones.length][stones.length][K + 1];
    this.K = K;
    sum = new int[stones.length];
    sum[0] = stones[0];
    for (int i = 1; i < stones.length; i++) {
      sum[i] = (sum[i - 1] + stones[i]);
    }
    for (int i = 0; i < stones.length; i++) {
      for (int j = 0; j < stones.length; j++) {
        for (int k = 1; k <= K; k++) {
          if (k == 1 && i == j) {
            DP[i][j][k] = 0;
          } else DP[i][j][k] = 999999;
        }
      }
    }
    for (int r = 2; r <= stones.length; r++) {
      for (int i = 0; i < stones.length; i++) {
        int j = i + r - 1;
        if (j < stones.length) {
          for (int k = 2; k <= K; k++) {
            int min = Integer.MAX_VALUE;
            for (int t = i; t < j; t++) {
              min = Math.min(min, DP[i][t][k - 1] + DP[t + 1][j][1]);
            }
            DP[i][j][k] = min;
          }
          DP[i][j][1] = DP[i][j][K] + (sum[j] - ((i - 1) >= 0 ? sum[i - 1] : 0));
        }
      }
    }
    return DP[0][stones.length - 1][1];
    // return dp(0, stones.length - 1, 1);
  }

  private int dp(int s, int e, int X) {
    if (s == e) {
      if (X == 1) return 0;
      else return 999999;
    }
    if (DP[s][e][X] != 0) return DP[s][e][X];
    if (X == 1) {
      DP[s][e][X] = dp(s, e, K) + sum[e] - ((s - 1) >= 0 ? sum[s - 1] : 0);
      return DP[s][e][X];
    } else {
      int min = Integer.MAX_VALUE;
      for (int t = s; t < e; t++) {
        min = Math.min(min, dp(s, t, X - 1) + dp(t + 1, e, 1));
      }
      DP[s][e][X] = min;
      return DP[s][e][X];
    }
  }
}
