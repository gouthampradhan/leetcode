package depth_first_search;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by gouthamvidyapradhan on 27/04/2018. Given a non-empty 2D array grid of 0's and 1's, an
 * island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.)
 * You may assume all four edges of the grid are surrounded by water.
 *
 * <p>Count the number of distinct islands. An island is considered to be the same as another if
 * they have the same shape, or have the same shape after rotation (90, 180, or 270 degrees only) or
 * reflection (left/right direction or up/down direction).
 *
 * <p>Example 1: 11000 10000 00001 00011 Given the above grid map, return 1.
 *
 * <p>Notice that: 11 1 and 1 11 are considered same island shapes. Because if we make a 180 degrees
 * clockwise rotation on the first island, then two islands will have the same shapes. Example 2:
 * 11100 10001 01001 01110 Given the above grid map, return 2.
 *
 * <p>Here are the two distinct islands: 111 1 and 1 1
 *
 * <p>Notice that: 111 1 and 1 111 are considered same island shapes. Because if we flip the first
 * array in the up/down direction, then they have the same shapes. Note: The length of each
 * dimension in the given grid does not exceed 50.
 *
 * <p>Solution: General idea is to get the co-ordinates of each shape using dfs and rotate/reflect
 * each point in a shape to transform each shape to a new possible shape (there are 8 possible
 * shapes after rotation and reflection). Sort the new coordinates of each transformed shape and
 * reduce each shape to a canonical key. Use a hash-set to count total number of such keys.
 *
 * <p>Some background on rotation and reflection: ------------------------------------------- Rotate
 * co-ordinates using formula [x′y′]=[[cosθ -sinθ], [sinθ cosθ]] [x y] where θ = {0, 90, 180, 270}
 * There are 4 possible rotation points and for each rotation point obtain the reflection on each x
 * and y axis. Rotation and reflection results in total of 8 points such as (x, y), (-x, y), (x,
 * -y), (-x, -y), (y, x), (-y, x), (y, -x) and (-y, -x).
 */
public class NumberOfDistinctIslandsII {
  private final int[] R = {0, 1, 0, -1};
  private final int[] C = {1, 0, -1, 0};
  private boolean[][] done;

  class Point implements Comparable<Point> {
    int x;
    int y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public int compareTo(Point o) {
      if (this.x == o.x) {
        return Integer.compare(this.y, o.y);
      }
      return Integer.compare(this.x, o.x);
    }
  }
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[][] grid = {{1, 1, 0, 0, 0}, {1, 0, 0, 0, 0}, {0, 0, 0, 0, 1}, {0, 0, 0, 1, 1}};
    System.out.println(new NumberOfDistinctIslandsII().numDistinctIslands2(grid));
  }

  public int numDistinctIslands2(int[][] grid) {
    List<List<Point>> shapes = new ArrayList<>();
    done = new boolean[grid.length][grid[0].length];
    Set<String> islands = new HashSet<>();
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (!done[i][j] && grid[i][j] == 1) {
          List<Point> points = new ArrayList<>();
          dfs(i, j, grid, points);
          shapes.add(points);
        }
      }
    }
    for (List<Point> shape : shapes) {
      List<List<Point>> eightShapes = rotateAndReflect(shape);
      islands.add(genKey(eightShapes));
    }
    return islands.size();
  }

  /**
   * Generate a canonical key
   *
   * @param eighShapes
   * @return
   */
  private String genKey(List<List<Point>> eighShapes) {
    List<String> keys = new ArrayList<>();
    for (List<Point> shape : eighShapes) {
      Collections.sort(shape);
      Point first = shape.get(0);
      keys.add(
          shape
              .stream()
              .map(s -> new Point(s.x - first.x, s.y - first.y))
              .map(p -> p.x + ":" + p.y)
              .collect(Collectors.joining(",")));
    }
    Collections.sort(keys);
    return keys.get(0);
  }

  /**
   * Rotate and reflect a given shape to 8 possible shapes
   *
   * @param shape
   * @return
   */
  private List<List<Point>> rotateAndReflect(List<Point> shape) {
    Map<Integer, List<Point>> map = new HashMap<>();
    for (int i = 0; i < 8; i++) {
      map.put(i, new ArrayList<>());
    }
    for (Point point : shape) {
      map.get(0).add(new Point(point.x, point.y));
      map.get(1).add(new Point(-point.x, point.y));
      map.get(2).add(new Point(point.x, -point.y));
      map.get(3).add(new Point(-point.x, -point.y));
      map.get(4).add(new Point(point.y, point.x));
      map.get(5).add(new Point(-point.y, point.x));
      map.get(6).add(new Point(point.y, -point.x));
      map.get(7).add(new Point(-point.y, -point.x));
    }
    return new ArrayList<>(map.values());
  }

  private void dfs(int r, int c, int[][] grid, List<Point> points) {
    done[r][c] = true;
    points.add(new Point(c, r));
    for (int i = 0; i < 4; i++) {
      int newR = r + R[i];
      int newC = c + C[i];
      if (newR >= 0
          && newC >= 0
          && newR < grid.length
          && newC < grid[0].length
          && grid[newR][newC] == 1
          && !done[newR][newC]) {
        dfs(newR, newC, grid, points);
      }
    }
  }
}
