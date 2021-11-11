package breadth_first_search;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by gouthamvidyapradhan on 26/12/2017. You are given a m x n 2D grid initialized with
 * these three possible values.
 *
 * <p>-1 - A wall or an obstacle. 0 - A gate. INF - Infinity means an empty room. We use the value
 * 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than
 * 2147483647. Fill each empty room with the distance to its nearest gate. If it is impossible to
 * reach a gate, it should be filled with INF.
 *
 * <p>For example, given the 2D grid: INF -1 0 INF INF INF INF -1 INF -1 INF -1 0 -1 INF INF After
 * running your function, the 2D grid should be: 3 -1 0 1 2 2 1 -1 1 -1 2 -1 0 -1 3 4
 *
 * <p>Solution: O(n x m): Treat each coordinate of grid with 0 as a source and destination as the
 * coordinate of 2147483647 and perform a multi-sources BFS from each source.
 */
public class WallsAndGates {

  private static final int[] R = {0, 0, 1, -1};
  private static final int[] C = {1, -1, 0, 0};

  private class Cell {
    int r, c;

    Cell(int r, int c) {
      this.r = r;
      this.c = c;
    }
  }

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[][] A = {
      {Integer.MAX_VALUE, -1, 0, Integer.MAX_VALUE},
      {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, -1},
      {Integer.MAX_VALUE, -1, Integer.MAX_VALUE, -1},
      {0, -1, Integer.MAX_VALUE, Integer.MAX_VALUE}
    };
    new WallsAndGates().wallsAndGates(A);
  }

  public void wallsAndGates(int[][] rooms) {
    Queue<Cell> queue = new ArrayDeque<>();
    for (int i = 0; i < rooms.length; i++) {
      for (int j = 0; j < rooms[0].length; j++) {
        if (rooms[i][j] == 0) { // treat each co-ordinates of gate as a source
          Cell cell = new Cell(i, j);
          queue.offer(cell);
        }
      }
    }
    while (!queue.isEmpty()) {
      Cell top = queue.poll();
      for (int i = 0; i < 4; i++) {
        int newR = top.r + R[i];
        int newC = top.c + C[i];
        if (newR >= 0 && newC >= 0 && newR < rooms.length && newC < rooms[0].length) {
          if (rooms[newR][newC] == Integer.MAX_VALUE) {
            rooms[newR][newC] = rooms[top.r][top.c] + 1;
            queue.offer(new Cell(newR, newC));
          }
        }
      }
    }
  }
}
