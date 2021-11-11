package backtracking;

/**
 * Created by PRADHANG on 4/13/2017. Given a 2D board and a word, find if the word exists in the
 * grid.
 *
 * <p>The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells
 * are those horizontally or vertically neighboring. The same letter cell may not be used more than
 * once.
 *
 * <p>For example, Given board =
 *
 * <p>[ ['A','B','C','E'], ['S','F','C','S'], ['A','D','E','E'] ] word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true, word = "ABCB", -> returns false.
 */
public class WordSearch {
  private static final int[] R = {0, 0, 1, -1};
  private static final int[] C = {1, -1, 0, 0};
  private static boolean[][] visited;
  private static int length = 0, N, M;

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    char[][] board = {{'A'}};
    System.out.println(new WordSearch().exist(board, "A"));
  }

  public boolean exist(char[][] board, String word) {
    N = board.length;
    M = board[0].length;
    if (N * M < word.length()) return false;
    visited = new boolean[N][M];
    length = word.length();
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (board[i][j] == word.charAt(0)) {
          if (dfs(i, j, board, word, 1)) return true;
          visited[i][j] = false;
        }
      }
    }
    return false;
  }

  private boolean dfs(int r, int c, char[][] board, String word, int pos) {
    if (pos < length) {
      visited[r][c] = true;
      for (int i = 0; i < 4; i++) {
        int newR = r + R[i];
        int newC = c + C[i];
        if (newR >= 0 && newR < N && newC >= 0 && newC < M) {
          if (!visited[newR][newC]) {
            if (board[newR][newC] == word.charAt(pos)) {
              if (dfs(newR, newC, board, word, pos + 1)) return true;
              visited[newR][newC] = false;
            }
          }
        }
      }
    } else return true;
    return false;
  }
}
