package depth_first_search;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gouthamvidyapradhan on 01/07/2018. We have a grid of 1s and 0s; the 1s in a cell
 * represent bricks. A brick will not drop if and only if it is directly connected to the top of the
 * grid, or at least one of its (4-way) adjacent bricks will not drop.
 *
 * <p>We will do some erasures sequentially. Each time we want to do the erasure at the location (i,
 * j), the brick (if it exists) on that location will disappear, and then some other bricks may drop
 * because of that erasure.
 *
 * <p>Return an array representing the number of bricks that will drop after each erasure in
 * sequence.
 *
 * <p>Example 1: Input: grid = [[1,0,0,0],[1,1,1,0]] hits = [[1,0]] Output: [2] Explanation: If we
 * erase the brick at (1, 0), the brick at (1, 1) and (1, 2) will drop. So we should return 2.
 * Example 2: Input: grid = [[1,0,0,0],[1,1,0,0]] hits = [[1,1],[1,0]] Output: [0,0] Explanation:
 * When we erase the brick at (1, 0), the brick at (1, 1) has already disappeared due to the last
 * move. So each erasure will cause no bricks dropping. Note that the erased brick (1, 0) will not
 * be counted as a dropped brick.
 *
 * <p>Note:
 *
 * <p>The number of rows and columns in the grid will be in the range [1, 200]. The number of
 * erasures will not exceed the area of the grid. It is guaranteed that each erasure will be
 * different from any other erasure, and located inside the grid. An erasure may refer to a location
 * with no brick - if it does, no bricks drop.
 *
 * <p>Solution: O(R x C): Erase all the bricks in the grid and do a union of all the bricks using a
 * union-find disjoint set. (A modified union-find disjoint set is necessary to keep track of size
 * of the connected component and to check if its connected to roof or not) Once you have the
 * different connected components of the grid, solve the problem in the reverse order by iterating
 * the hits in the reverse order. First set 1 in the grid for each hits and count the connected
 * bricks in all four directions which are not linked to roof of the grid.
 */
public class BricksFallingWhenHit {

  private static final int[] R = {0, 0, 1, -1};
  private static final int[] C = {1, -1, 0, 0};

  /** @author gouthamvidyapradhan Class to represent UnionFind Disjoint Set */
  private static class UnionFind {
    private int[] p;
    private int[] rank;
    private boolean[] roof;
    private int[] size;

    UnionFind(int s) {
      this.p = new int[s];
      this.rank = new int[s];
      this.size = new int[s];
      this.roof = new boolean[s];
      init();
    }
    /** Initialize with its same index as its parent */
    private void init() {
      for (int i = 0; i < p.length; i++) {
        p[i] = i;
        size[i] = 1;
      }
    }
    /**
     * Find the representative vertex
     *
     * @param i
     * @return
     */
    private int findSet(int i) {
      if (p[i] != i) {
        p[i] = findSet(p[i]);
      }
      return p[i];
    }

    /**
     * Set as roof
     *
     * @param i
     */
    public void setAsRoof(int i) {
      roof[i] = true;
    }
    /**
     * Perform union of two vertex
     *
     * @param i
     * @param j
     * @return true if union is performed successfully, false otherwise
     */
    public boolean union(int i, int j) {
      int x = findSet(i);
      int y = findSet(j);
      if (x != y) {
        if (rank[x] > rank[y]) {
          p[y] = p[x];
          roof[x] = (roof[x] || roof[y]);
          size[x] = size[x] + size[y];
        } else {
          p[x] = p[y];
          roof[y] = (roof[x] || roof[y]);
          size[y] = size[x] + size[y];
          if (rank[x] == rank[y]) {
            rank[y]++; // increment the rank
          }
        }
        return true;
      }
      return false;
    }

    /**
     * is attached to roof
     *
     * @param i
     * @return
     */
    public boolean isRoof(int i) {
      return roof[findSet(i)];
    }

    /**
     * is attached to roof
     *
     * @param i
     * @return
     */
    public int size(int i) {
      return size[findSet(i)];
    }
  }

  /**
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[][] grid = {{1, 1, 1, 1, 1}, {0, 0, 1, 0, 1}, {1, 0, 1, 0, 1}, {1, 1, 1, 0, 1}};
    int[][] hits = {{1, 2}, {2, 2}, {2, 4}, {0, 4}, {0, 0}};
    int[] r = new BricksFallingWhenHit().hitBricks(grid, hits);
    for (int i = 0; i < r.length; i++) {
      System.out.print(r[i] + " ");
    }
  }

  public int[] hitBricks(int[][] grid, int[][] hits) {
    int nR = grid.length;
    int nC = grid[0].length;
    UnionFind unionFind = new UnionFind((nR * nC) + 1);
    for (int i = 0; i < nC; i++) {
      if (grid[0][i] == 1) {
        unionFind.setAsRoof(i + 1);
      }
    }
    for (int k = 0; k < hits.length; k++) {
      int[] h = hits[k];
      if (grid[h[0]][h[1]] == 0) {
        h[0] = -1;
        h[1] = -1;
      } else {
        grid[h[0]][h[1]] = 0;
      }
    }
    boolean[][] done = new boolean[grid.length][grid[0].length];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 1 && !done[i][j]) {
          dfs(i, j, grid, done, unionFind);
        }
      }
    }
    int[] result = new int[hits.length];
    for (int i = hits.length - 1; i >= 0; i--) {
      int[] h = hits[i];
      int r = h[0];
      int c = h[1];
      if (r == -1) continue;
      grid[r][c] = 1;
      int cell = (r * nC) + c + 1;
      int sum = 0;
      List<Integer> notLinkedToRoof = new ArrayList<>();
      List<Integer> linkedToRoof = new ArrayList<>();
      for (int k = 0; k < 4; k++) {
        int newR = r + R[k];
        int newC = c + C[k];
        if (newR >= 0 && newR < nR && newC >= 0 && newC < nC && grid[newR][newC] == 1) {
          int newCell = (newR * nC) + newC + 1;
          if (unionFind.isRoof(newCell)) {
            linkedToRoof.add(newCell);
          } else {
            notLinkedToRoof.add(newCell);
          }
        }
      }
      for (int nr : notLinkedToRoof) {
        unionFind.union(cell, nr);
      }
      if (!linkedToRoof.isEmpty() || unionFind.isRoof(cell)) {
        sum += (unionFind.size(cell) - 1);
      }
      for (int rr : linkedToRoof) {
        unionFind.union(cell, rr);
      }
      result[i] = sum;
    }
    return result;
  }

  private void dfs(int r, int c, int[][] grid, boolean[][] done, UnionFind unionFind) {
    done[r][c] = true;
    int cell = (r * grid[0].length) + c + 1;
    for (int i = 0; i < 4; i++) {
      int newR = r + R[i];
      int newC = c + C[i];
      if (newR >= 0 && newR < grid.length && newC >= 0 && newC < grid[0].length) {
        if (grid[newR][newC] == 1 && !done[newR][newC]) {
          int newCell = (newR * grid[0].length) + newC + 1;
          unionFind.union(cell, newCell);
          dfs(newR, newC, grid, done, unionFind);
        }
      }
    }
  }
}
