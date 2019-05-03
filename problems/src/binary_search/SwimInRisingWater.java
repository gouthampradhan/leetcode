package binary_search;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by gouthamvidyapradhan on 07/04/2019 On an N x N grid, each square grid[i][j] represents
 * the elevation at that point (i,j).
 *
 * <p>Now rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from
 * a square to another 4-directionally adjacent square if and only if the elevation of both squares
 * individually are at most t. You can swim infinite distance in zero time. Of course, you must stay
 * within the boundaries of the grid during your swim.
 *
 * <p>You start at the top left square (0, 0). What is the least time until you can reach the bottom
 * right square (N-1, N-1)?
 *
 * <p>Example 1:
 *
 * <p>Input: [[0,2],[1,3]] Output: 3 Explanation: At time 0, you are in grid location (0, 0). You
 * cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t
 * = 0.
 *
 * <p>You cannot reach point (1, 1) until time 3. When the depth of water is 3, we can swim anywhere
 * inside the grid. Example 2:
 *
 * <p>Input: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]] Output: 16
 * Explanation: 0 1 2 3 4 24 23 22 21 5 12 13 14 15 16 11 17 18 19 20 10 9 8 7 6
 *
 * <p>The final route is marked in bold. We need to wait until time 16 so that (0, 0) and (4, 4) are
 * connected. Note:
 *
 * <p>2 <= N <= 50. grid[i][j] is a permutation of [0, ..., N*N - 1].
 *
 * <p>Solution: O(N ^ 2 x log N ^ 2) Binary search for the possible answers in the range [0 to N *
 * N-1] and dfs through the grid to check if the destination is reachable
 */
public class SwimInRisingWater {

  private final int[] R = {0, 0, 1, -1};
  private final int[] C = {1, -1, 0, 0};

  class Pair {
    int r, c;

    Pair(int r, int c) {
      this.r = r;
      this.c = c;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Pair)) return false;
      Pair pair = (Pair) o;
      return r == pair.r && c == pair.c;
    }

    @Override
    public int hashCode() {
      return Objects.hash(r, c);
    }
  }
  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    int[][] grid = {
      {0, 1, 2, 3, 4},
      {24, 23, 22, 21, 5},
      {12, 13, 14, 15, 16},
      {11, 17, 18, 19, 20},
      {10, 9, 8, 7, 6}
    };
    System.out.println(new SwimInRisingWater().swimInWater(grid));
  }

  public int swimInWater(int[][] grid) {
    int l = 0, h = (grid.length * grid.length);
    int ans = 0;
    while (l <= h) {
      int m = l + (h - l) / 2;
      Set<Pair> done = new HashSet<>();
      if (dfs(grid, 0, 0, done, m)) {
        ans = m;
        h = m - 1;
      } else {
        l = m + 1;
      }
    }
    return ans;
  }

  private boolean dfs(int[][] grid, int r, int c, Set<Pair> done, int V) {
    if (r == grid.length - 1 && c == grid[0].length - 1) return true;
    done.add(new Pair(r, c));
    for (int i = 0; i < 4; i++) {
      int newR = r + R[i];
      int newC = c + C[i];
      if (newR >= 0 && newR < grid.length && newC >= 0 && newC < grid[0].length) {
        int childH = Math.max(V, grid[newR][newC]);
        int curH = Math.max(V, grid[r][c]);
        if (curH == childH) {
          Pair child = new Pair(newR, newC);
          if (!done.contains(child)) {
            if (dfs(grid, newR, newC, done, V)) return true;
          }
        }
      }
    }
    return false;
  }
}
