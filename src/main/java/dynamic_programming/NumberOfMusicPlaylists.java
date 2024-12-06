/* (C) 2024 YourCompanyName */
package dynamic_programming;

import java.util.Arrays;

/** Created by gouthamvidyapradhan on 13/06/2020 */
public class NumberOfMusicPlaylists {
  public static void main(String[] args) {
    //
  }

  int[][] DP;
  final int MOD = (int) 1e9 + 7;

  public int numMusicPlaylists(int N, int L, int K) {
    DP = new int[L + 1][N + 1];
    for (int i = 0; i <= L; i++) {
      Arrays.fill(DP[i], -1);
    }
    DP[0][0] = 1;
    return (int) dp(L, N, K, N);
  }

  private long dp(int i, int j, int K, int N) {
    if (i < j) return 0;
    else if (i < 0 || j < 0) return 0;
    else if (DP[i][j] != -1) return DP[i][j];
    else {
      long sum = 0L;
      sum += ((dp(i - 1, j - 1, K, N) * (N - (j - 1))) % MOD);
      sum += (dp(i - 1, j, K, N) * (Math.max(j - K, 0)) % MOD);
      DP[i][j] = (int) (sum % MOD);
      return DP[i][j];
    }
  }
}
