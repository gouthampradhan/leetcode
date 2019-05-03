package dynamic_programming;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 22/03/2019 Alex and Lee play a game with piles of stones. There
 * are an even number of piles arranged in a row, and each pile has a positive integer number of
 * stones piles[i].
 *
 * <p>The objective of the game is to end with the most stones. The total number of stones is odd,
 * so there are no ties.
 *
 * <p>Alex and Lee take turns, with Alex starting first. Each turn, a player takes the entire pile
 * of stones from either the beginning or the end of the row. This continues until there are no more
 * piles left, at which point the person with the most stones wins.
 *
 * <p>Assuming Alex and Lee play optimally, return True if and only if Alex wins the game.
 *
 * <p>Example 1:
 *
 * <p>Input: [5,3,4,5] Output: true Explanation: Alex starts first, and can only take the first 5 or
 * the last 5. Say he takes the first 5, so that the row becomes [3, 4, 5]. If Lee takes 3, then the
 * board is [4, 5], and Alex takes 5 to win with 10 points. If Lee takes the last 5, then the board
 * is [3, 4], and Alex takes 4 to win with 9 points. This demonstrated that taking the first 5 was a
 * winning move for Alex, so we return true.
 *
 * <p>Note:
 *
 * <p>2 <= piles.length <= 500 piles.length is even. 1 <= piles[i] <= 500 sum(piles) is odd.
 *
 * <p>Solution: O(N ^ 2) Each state can be considered as State = (total stones left, player's turn).
 * Do a dfs on each state and memoize the result in order not to recalculate. When all the stones
 * are exhausted - Alex wins if the total collected stones by her is greater than total collected by
 * Lee
 */
public class StoneGame {

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    int[] A = {5, 3, 4, 5};
    System.out.println(new StoneGame().stoneGame(A));
  }

  public boolean stoneGame(int[] piles) {
    int sum = 0;
    for (int i = 0; i < piles.length; i++) {
      sum += piles[i];
    }
    int[][] A = new int[2][sum + 1];
    Arrays.fill(A[0], -1);
    Arrays.fill(A[1], -1);
    int result = dp(A, piles, 0, piles.length - 1, sum, 0, 0, 0);
    return result == 1;
  }

  private int dp(int[][] A, int[] piles, int i, int j, int sum, int p, int sumA, int sumB) {
    if (A[p][sum] != -1) return A[p][sum];
    else {
      if (p == 0) {
        if (i <= j) {
          int result = dp(A, piles, i + 1, j, sum - piles[i], (p + 1) % 2, sumA + piles[i], sumB);
          if (result == 0) {
            A[p][sum] = 1;
            return 1;
          } else {
            result = dp(A, piles, i, j - 1, sum - piles[j], (p + 1) % 2, sumA + piles[j], sumB);
            A[p][sum] = result;
            return result;
          }
        } else {
          if (sumA > sumB) return 1;
          else return 0;
        }
      } else {
        if (i <= j) {
          int result = dp(A, piles, i + 1, j, sum - piles[i], (p + 1) % 2, sumA, sumB + piles[i]);
          if (result == 0) {
            A[p][sum] = 1;
            return 1;
          } else {
            result = dp(A, piles, i, j - 1, sum - piles[j], (p + 1) % 2, sumA, sumB + piles[j]);
            A[p][sum] = result;
            return result;
          }
        } else {
          if (sumB > sumA) return 1;
          else return 0;
        }
      }
    }
  }
}
