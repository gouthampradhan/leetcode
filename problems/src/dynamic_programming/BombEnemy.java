package dynamic_programming;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 25/01/2020 Given a 2D grid, each cell is either a wall 'W', an
 * enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
 * The bomb kills all the enemies in the same row and column from the planted point until it hits
 * the wall since the wall is too strong to be destroyed. Note: You can only put the bomb at an
 * empty cell.
 *
 * <p>Example:
 *
 * <p>Input: [["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]] Output: 3 Explanation: For the
 * given grid,
 *
 * <p>0 E 0 0 E 0 W E 0 E 0 0
 *
 * <p>Placing a bomb at (1,1) kills 3 enemies.
 */
public class BombEnemy {
  public static void main(String[] args) {
    char[][] grid = {{'0', 'E', '0', '0'}, {'E', '0', 'W', 'E'}, {'0', 'E', '0', '0'}};
    System.out.println(new BombEnemy().maxKilledEnemies(grid));
  }

  public int maxKilledEnemies(char[][] grid) {
    int[][] DP1 = new int[grid.length][grid[0].length];
    int[][] DP2 = new int[grid.length][grid[0].length];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 'E') {
          DP1[i][j] = 1;
        }
        if (grid[i][j] != 'W') {
          if (j - 1 >= 0) {
            DP1[i][j] += DP1[i][j - 1];
          }
          if (i - 1 >= 0) {
            DP1[i][j] += DP1[i - 1][j];
          }
        }
      }
    }

    for (int i = grid.length - 1; i >= 0; i--) {
      for (int j = grid[0].length - 1; j >= 0; j--) {
        if (grid[i][j] == 'E') {
          DP2[i][j] = 1;
        }
        if (grid[i][j] != 'W') {
          if (j + 1 < grid[0].length) {
            DP2[i][j] += DP2[i][j + 1];
          }
          if (i + 1 < grid.length) {
            DP2[i][j] += DP2[i + 1][j];
          }
        }
      }
    }

    int max = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == '0') {
          max = Math.max(max, DP1[i][j] + DP2[i][j]);
        }
      }
    }
    return max;
  }
}
