package dynamic_programming;

import java.util.Arrays;

/**
 * Created by gouthamvidyapradhan on 19/02/2020 You are given an even number of people num_people
 * that stand around a circle and each person shakes hands with someone else, so that there are
 * num_people / 2 handshakes total.
 *
 * <p>Return the number of ways these handshakes could occur such that none of the handshakes cross.
 *
 * <p>Since this number could be very big, return the answer mod 10^9 + 7
 *
 * <p>Example 1:
 *
 * <p>Input: num_people = 2 Output: 1 Example 2:
 *
 * <p>Input: num_people = 4 Output: 2 Explanation: There are two ways to do it, the first way is
 * [(1,2),(3,4)] and the second one is [(2,3),(4,1)]. Example 3:
 *
 * <p>Input: num_people = 6 Output: 5 Example 4:
 *
 * <p>Input: num_people = 8 Output: 14
 *
 * <p>Constraints:
 *
 * <p>2 <= num_people <= 1000 num_people % 2 == 0
 */
public class HandshakesThatDontCross {
  public static void main(String[] args) {
    System.out.println(new HandshakesThatDontCross().numberOfWays(20));
  }

  int[] DP;
  final int MOD = 1000000007;

  public int numberOfWays(int N) {
    // DP = new int[N + 1][N + 1];
    DP = new int[N + 1];
    Arrays.fill(DP, -1);
    //
    //      for(int i = 0; i <= N; i ++){
    //          Arrays.fill(DP[i], -1);
    //      }
    return dp(0, N - 1);
  }

  private int dp(int i, int j) {
    if (i > j) return 1;
    else if ((j - i + 1) % 2 != 0) return 0;
    else if (DP[j - i + 1] != -1) return DP[j - i + 1];
    else {
      int sum = 0;
      for (int k = i; k <= j; k++) {
        int left = (dp(i + 1, k - 1) % MOD);
        int right = (dp(k + 1, j) % MOD);
        sum = ((sum + ((left * right) % MOD)) % MOD);
      }
      DP[j - i + 1] = sum;
      return sum;
    }
  }
}
