package breadth_first_search;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 14/03/2019 Given a matrix consists of 0 and 1, find the
 * distance of the nearest 0 for each cell.
 *
 * <p>The distance between two adjacent cells is 1. Example 1: Input:
 *
 * <p>0 0 0 0 1 0 0 0 0 Output: 0 0 0 0 1 0 0 0 0 Example 2: Input:
 *
 * <p>0 0 0 0 1 0 1 1 1 Output: 0 0 0 0 1 0 1 2 1 Note: The number of elements of the given matrix
 * will not exceed 10,000. There are at least one 0 in the given matrix. The cells are adjacent in
 * only four directions: up, down, left and right.
 *
 * <p>Solution: Add all the 0th cell to the queue and do a multi-source bfs to count the minimum
 * distance
 */
public class Matrix {
  private static class Node {
    int r, c;
    int d;

    Node(int r, int c) {
      this.r = r;
      this.c = c;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Node)) return false;
      Node node = (Node) o;
      return r == node.r && c == node.c;
    }

    @Override
    public int hashCode() {
      return Objects.hash(r, c);
    }
  }

  private final int[] R = {0, 0, 1, -1};
  private final int[] C = {1, -1, 0, 0};
  private Set<Node> done;
  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    int[][] temp = {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
    int[][] result = new Matrix().updateMatrix(temp);
    System.out.println();
  }

  public int[][] updateMatrix(int[][] matrix) {
    int[][] temp = new int[matrix.length][matrix[0].length];
    done = new HashSet<>();
    Queue<Node> queue = new ArrayDeque<>();
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        temp[i][j] = matrix[i][j];
        if (matrix[i][j] == 0) {
          Node node = new Node(i, j);
          queue.offer(node);
          done.add(node);
        }
      }
    }
    while (!queue.isEmpty()) {
      Node curr = queue.poll();
      for (int i = 0; i < 4; i++) {
        int newR = curr.r + R[i];
        int newC = curr.c + C[i];
        if (newR >= 0 && newR < matrix.length && newC >= 0 && newC < matrix[0].length) {
          Node child = new Node(newR, newC);
          if (!done.contains(child)) {
            done.add(child);
            child.d = curr.d + 1;
            temp[newR][newC] = child.d;
            queue.offer(child);
          }
        }
      }
    }
    return temp;
  }
}
