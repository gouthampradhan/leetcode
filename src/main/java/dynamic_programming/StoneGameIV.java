/* (C) 2024 YourCompanyName */
package dynamic_programming;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 03/12/2020 Alice and Bob take turns playing a game, with Alice
 * starting first.
 *
 * <p>Initially, there are n stones in a pile. On each player's turn, that player makes a move
 * consisting of removing any non-zero square number of stones in the pile.
 *
 * <p>Also, if a player cannot make a move, he/she loses the game.
 *
 * <p>Given a positive integer n. Return True if and only if Alice wins the game otherwise return
 * False, assuming both players play optimally.
 *
 * <p>Example 1:
 *
 * <p>Input: n = 1 Output: true Explanation: Alice can remove 1 stone winning the game because Bob
 * doesn't have any moves. Example 2:
 *
 * <p>Input: n = 2 Output: false Explanation: Alice can only remove 1 stone, after that Bob removes
 * the last one winning the game (2 -> 1 -> 0). Example 3:
 *
 * <p>Input: n = 4 Output: true Explanation: n is already a perfect square, Alice can win with one
 * move, removing 4 stones (4 -> 0). Example 4:
 *
 * <p>Input: n = 7 Output: false Explanation: Alice can't win the game if Bob plays optimally. If
 * Alice starts removing 4 stones, Bob will remove 1 stone then Alice should remove only 1 stone and
 * finally Bob removes the last one (7 -> 3 -> 2 -> 1 -> 0). If Alice starts removing 1 stone, Bob
 * will remove 4 stones then Alice only can remove 1 stone and finally Bob removes the last one (7
 * -> 6 -> 2 -> 1 -> 0). Example 5:
 *
 * <p>Input: n = 17 Output: false Explanation: Alice can't win the game if Bob plays optimally.
 *
 * <p>Constraints:
 *
 * <p>1 <= n <= 10^5
 */
public class StoneGameIV {
  public static void main(String[] args) {
    System.out.println(new StoneGameIV().winnerSquareGame(1000));
  }

  public boolean winnerSquareGame(int n) {
    Set<Integer> perfectSquare = new HashSet<>();
    perfectSquare.add(1);
    for (int i = 2; (long) (i * i) <= n; i++) {
      genSquare(i * i, n, perfectSquare);
    }
    int[] pq = new int[perfectSquare.size()];
    int i = 0;
    for (int s : perfectSquare) {
      pq[i++] = s;
    }
    Arrays.sort(pq);
    int[] DP = new int[n + 1];
    int status = dp(n, 0, pq, DP);
    return status != 1;
  }

  private int dp(int n, int p, int[] perfectSquares, int[] DP) {
    if (n == 0) return 1;
    else if (DP[n] != 0) return DP[n];
    else {
      int result = 1;
      for (int sq : perfectSquares) {
        if (n < sq) break;
        int r = dp(n - sq, ((p + 1) % 2), perfectSquares, DP);
        if (r == 1) {
          result = 2;
          break;
        }
      }
      DP[n] = result;
      return result;
    }
  }

  private void genSquare(int sq, int limit, Set<Integer> perfectSquare) {
    if (!perfectSquare.contains(sq)) {
      perfectSquare.add(sq);
      if (((long) sq * sq) <= limit) {
        genSquare(sq * sq, limit, perfectSquare);
      }
    }
  }
}
