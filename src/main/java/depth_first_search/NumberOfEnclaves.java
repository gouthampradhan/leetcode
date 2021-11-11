package depth_first_search;

/**
 * Created by gouthamvidyapradhan on 31/07/2019 Given a 2D array A, each cell is 0 (representing
 * sea) or 1 (representing land)
 *
 * <p>A move consists of walking from one land square 4-directionally to another land square, or off
 * the boundary of the grid.
 *
 * <p>Return the number of land squares in the grid for which we cannot walk off the boundary of the
 * grid in any number of moves.
 *
 * <p>Example 1:
 *
 * <p>Input: [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]] Output: 3 Explanation: There are three 1s
 * that are enclosed by 0s, and one 1 that isn't enclosed because its on the boundary. Example 2:
 *
 * <p>Input: [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]] Output: 0 Explanation: All 1s are either on
 * the boundary or can reach the boundary.
 *
 * <p>Note:
 *
 * <p>1 <= A.length <= 500 1 <= A[i].length <= 500 0 <= A[i][j] <= 1 All rows have the same size.
 * Solution O(N x M) Do a dfs to count number of enclaves - in each dfs check if it violates the
 * condition to be considered a enclave.
 */
public class NumberOfEnclaves {

  final int[] R = {0, 0, -1, 1};
  final int[] C = {1, -1, 0, 0};

  boolean[][] done;
  int count = 0;
  int answer = 0;
  boolean possible = true;

  public static void main(String[] args) {
    int[][] A = {{0, 1, 1, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 0}};
    System.out.println(new NumberOfEnclaves().numEnclaves(A));
  }

  public int numEnclaves(int[][] A) {
    done = new boolean[A.length][A[0].length];
    for (int i = 0; i < A.length; i++) {
      for (int j = 0; j < A[0].length; j++) {
        if (!done[i][j] && A[i][j] == 1) {
          count = 0;
          possible = true;
          dfs(A, i, j);
          if (possible) {
            answer += count;
          }
        }
      }
    }
    return answer;
  }

  private void dfs(int[][] A, int r, int c) {
    done[r][c] = true;
    if (r == 0 || c == 0 || r == A.length - 1 || c == A[0].length - 1) {
      possible = false;
    }
    count++;
    for (int i = 0; i < 4; i++) {
      int newR = r + R[i];
      int newC = c + C[i];
      if (newR < A.length && newC < A[0].length && newR >= 0 && newC >= 0 && !done[newR][newC]) {
        if (A[newR][newC] == 1) {
          dfs(A, newR, newC);
        }
      }
    }
  }
}
