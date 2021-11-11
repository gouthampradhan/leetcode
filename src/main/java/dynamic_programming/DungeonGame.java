package dynamic_programming;

import java.util.Arrays;

/**
 * Created by gouthamvidyapradhan on 12/07/2017. The demons had captured the princess (P) and
 * imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid
 * out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must
 * fight his way through the dungeon to rescue the princess.
 *
 * <p>The knight has an initial health point represented by a positive integer. If at any point his
 * health point drops to 0 or below, he dies immediately.
 *
 * <p>Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon
 * entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the
 * knight's health (positive integers).
 *
 * <p>In order to reach the princess as quickly as possible, the knight decides to move only
 * rightward or downward in each step.
 *
 * <p>
 *
 * <p>Write a function to determine the knight's minimum initial health so that he is able to rescue
 * the princess.
 *
 * <p>For example, given the dungeon below, the initial health of the knight must be at least 7 if
 * he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
 *
 * <p>-2 (K) -3 3 -5 -10 1 10 30 -5 (P)
 *
 * <p>Notes:
 *
 * <p>The knight's health has no upper bound. Any room can contain threats or power-ups, even the
 * first room the knight enters and the bottom-right room where the princess is imprisoned.
 *
 * <p>Solution: O(MxN log Integer.MAX_VALUE) Binary search the suitable initial health value in the
 * range (1, Integer.MAX_VALUE) and do a top down DP to check the balance health when he reaches
 * princess.
 */
public class DungeonGame {
  private final int[] R = {0, -1};
  private final int[] C = {-1, 0};
  private int DP[][];

  public static void main(String[] args) throws Exception {
    int[][] dungeon = {{200}};
    System.out.println(new DungeonGame().calculateMinimumHP(dungeon));
  }

  public int calculateMinimumHP(int[][] dungeon) {
    DP = new int[dungeon.length][dungeon[0].length];
    int l = 0, h = Integer.MAX_VALUE, ans = 0;
    while (l <= h) {
      int m = l + (h - l) / 2;
      for (int i = 0; i < dungeon.length; i++) Arrays.fill(DP[i], Integer.MIN_VALUE);
      DP[0][0] = m + dungeon[0][0];
      if (dp(dungeon, dungeon.length - 1, dungeon[0].length - 1) > 0) {
        ans = m;
        h = m - 1;
      } else {
        l = m + 1;
      }
    }
    return ans == 0 ? 1 : ans;
  }

  private int dp(int[][] dungeon, int r, int c) {
    if (DP[r][c] != Integer.MIN_VALUE) return DP[r][c];
    for (int i = 0; i < 2; i++) {
      int newR = r + R[i];
      int newC = c + C[i];
      if (newR >= 0 && newR < dungeon.length && newC >= 0 && newC < dungeon[0].length) {
        int life = dp(dungeon, newR, newC);
        if (life <= 0) DP[r][c] = Math.max(DP[r][c], 0);
        else DP[r][c] = Math.max(DP[r][c], dungeon[r][c] + life);
      }
    }
    return DP[r][c];
  }
}
