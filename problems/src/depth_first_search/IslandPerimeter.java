package depth_first_search;

/**
 * Created by gouthamvidyapradhan on 16/02/2018. You are given a map in form of a two-dimensional
 * integer grid where 1 represents land and 0 represents water. Grid cells are connected
 * horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there
 * is exactly one island (i.e., one or more connected land cells). The island doesn't have "lakes"
 * (water inside that isn't connected to the water around the island). One cell is a square with
 * side length 1. The grid is rectangular, width and height don't exceed 100. Determine the
 * perimeter of the island.
 *
 * <p>Example:
 *
 * <p>[[0,1,0,0], [1,1,1,0], [0,1,0,0], [1,1,0,0]]
 *
 * <p>Answer: 16 Explanation: The perimeter is the 16 yellow stripes in the image below:
 *
 * <p>Solution: Perform a dfs and count + 1 if any adjacent cell is a 0 or border
 */
public class IslandPerimeter {

  int[] R = {1, -1, 0, 0};
  int[] C = {0, 0, 1, -1};
  boolean[][] done;
  int perimeter;
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[][] grid = {{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}};
    System.out.println(new IslandPerimeter().islandPerimeter(grid));
  }

  public int islandPerimeter(int[][] grid) {
    done = new boolean[grid.length][grid[0].length];
    perimeter = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 1 && !done[i][j]) {
          dfs(i, j, grid);
          break;
        }
      }
    }

    return perimeter;
  }

  private void dfs(int r, int c, int[][] grid) {
    done[r][c] = true;
    for (int i = 0; i < 4; i++) {
      int newR = r + R[i];
      int newC = c + C[i];
      if (newR < 0 || newC < 0 || newR >= grid.length || newC >= grid[0].length) {
        perimeter++;
      } else if (grid[newR][newC] == 0) {
        perimeter++;
      } else {
        if (!done[newR][newC]) {
          dfs(newR, newC, grid);
        }
      }
    }
  }
}
