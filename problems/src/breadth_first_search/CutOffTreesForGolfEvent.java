package breadth_first_search;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 23/06/2018.
 *
 * <p>You are asked to cut off trees in a forest for a golf event. The forest is represented as a
 * non-negative 2D map, in this map:
 *
 * <p>0 represents the obstacle can't be reached. 1 represents the ground can be walked through. The
 * place with number bigger than 1 represents a tree can be walked through, and this positive number
 * represents the tree's height. You are asked to cut off all the trees in this forest in the order
 * of tree's height - always cut off the tree with lowest height first. And after cutting, the
 * original place has the tree will become a grass (value 1).
 *
 * <p>You will start from the point (0, 0) and you should output the minimum steps you need to walk
 * to cut off all the trees. If you can't cut off all the trees, output -1 in that situation.
 *
 * <p>You are guaranteed that no two trees have the same height and there is at least one tree needs
 * to be cut off.
 *
 * <p>Example 1: Input: [ [1,2,3], [0,0,4], [7,6,5] ] Output: 6 Example 2: Input: [ [1,2,3],
 * [0,0,0], [7,6,5] ] Output: -1 Example 3: Input: [ [2,3,4], [0,0,5], [8,7,6] ] Output: 6
 * Explanation: You started from the point (0,0) and you can cut off the tree in (0,0) directly
 * without walking. Hint: size of the given matrix will not exceed 50x50.
 *
 * <p>Solution: O(N x M) ^ 2: Bfs to each height starting from 1 and calculate the total sum of
 * distance.
 */
public class CutOffTreesForGolfEvent {

  public static void main(String[] args) throws Exception {}

  private static final int[] R = {0, 0, 1, -1};
  private static final int[] C = {1, -1, 0, 0};

  static class Cell implements Comparable<Cell> {
    int r, c;
    int distance;
    int height;

    Cell(int r, int c) {
      this.r = r;
      this.c = c;
    }

    @Override
    public int compareTo(Cell o) {
      return Integer.compare(this.height, o.height);
    }
  }

  public int cutOffTree(List<List<Integer>> forest) {
    int distance = 0;
    List<Cell> trees = new ArrayList<>();
    for (int i = 0; i < forest.size(); i++) {
      for (int j = 0; j < forest.get(0).size(); j++) {
        if (forest.get(i).get(j) > 1) {
          Cell cell = new Cell(i, j);
          cell.height = forest.get(i).get(j);
          trees.add(cell);
        }
      }
    }
    Collections.sort(trees);
    int sR = 0, sC = 0;
    for (Cell t : trees) {
      int dist = bfs(forest, t.height, sR, sC);
      if (dist == -1) return -1;
      else distance += dist;
      sR = t.r;
      sC = t.c;
    }
    return distance;
  }

  private int bfs(List<List<Integer>> forest, int target, int sR, int sC) {
    if (forest.get(sR).get(sC) == target) {
      forest.get(sR).set(sC, 1);
      return 0;
    }
    Cell start = new Cell(sR, sC);
    start.distance = 0;
    Queue<Cell> queue = new ArrayDeque<>();
    queue.add(start);
    boolean[][] done = new boolean[forest.size()][forest.get(0).size()];
    done[sR][sC] = true;
    while (!queue.isEmpty()) {
      Cell cell = queue.poll();
      for (int i = 0; i < 4; i++) {
        int newR = cell.r + R[i];
        int newC = cell.c + C[i];
        Cell newCell = new Cell(newR, newC);
        if (newR >= 0
            && newR < forest.size()
            && newC >= 0
            && newC < forest.get(0).size()
            && forest.get(newR).get(newC) != 0
            && !done[newCell.r][newCell.c]) {
          newCell.distance = cell.distance + 1;
          if (forest.get(newR).get(newC) == target) {
            forest.get(newR).set(newC, 1);
            return newCell.distance;
          }
          done[newCell.r][newCell.c] = true;
          queue.offer(newCell);
        }
      }
    }
    return -1;
  }
}
