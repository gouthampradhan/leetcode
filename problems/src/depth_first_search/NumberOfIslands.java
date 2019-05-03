package depth_first_search;

/**
 * Created by gouthamvidyapradhan on 09/03/2017. Given a 2d grid map of '1's (land) and '0's
 * (water), count the number of islands. An island is surrounded by water and is formed by
 * connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid
 * are all surrounded by water.
 *
 * <p>Example 1:
 *
 * <p>11110 11010 11000 00000 Answer: 1
 *
 * <p>Example 2:
 *
 * <p>11000 11000 00100 00011 Answer: 3
 */
public class NumberOfIslands {
  int[] R = {0, 0, 1, -1};
  int[] C = {1, -1, 0, 0};
  private static int M, N;
  private static char temp[][];

  public int numIslands(char[][] grid) {
    M = grid.length;
    if (M == 0) return 0;
    N = grid[0].length;
    temp = new char[M][N];
    int count = 0;

    for (int i = 0; i < M; i++) {
      System.arraycopy(grid[i], 0, temp[i], 0, N);
    }

    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        if (temp[i][j] == '1') {
          ++count;
          dfs(i, j);
        }
      }
    }

    return count;
  }

  private void dfs(int r, int c) {
    temp[r][c] = '0';
    for (int i = 0; i < 4; i++) {
      int newR = r + R[i];
      int newC = c + C[i];
      if (newR >= 0 && newC >= 0 && newR < M && newC < N) {
        if (temp[newR][newC] != '0') // not visited
        {
          dfs(newR, newC);
        }
      }
    }
  }
}
