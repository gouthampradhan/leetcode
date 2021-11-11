package depth_first_search;

/**
 * Created by pradhang on 3/28/2017. You are given a 2D char matrix representing the game board. 'M'
 * represents an unrevealed mine, 'E' represents an unrevealed empty square, 'B' represents a
 * revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals)
 * mines, digit ('1' to '8') represents how many mines are adjacent to this revealed square, and
 * finally 'X' represents a revealed mine.
 *
 * <p>Now given the next click position (row and column indices) among all the unrevealed squares
 * ('M' or 'E'), return the board after revealing this position according to the following rules:
 *
 * <p>If a mine ('M') is revealed, then the game is over - change it to 'X'. If an empty square
 * ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its
 * adjacent unrevealed squares should be revealed recursively. If an empty square ('E') with at
 * least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the
 * number of adjacent mines. Return the board when no more squares will be revealed. Example 1:
 * Input:
 *
 * <p>[['E', 'E', 'E', 'E', 'E'], ['E', 'E', 'M', 'E', 'E'], ['E', 'E', 'E', 'E', 'E'], ['E', 'E',
 * 'E', 'E', 'E']]
 *
 * <p>Click : [3,0]
 *
 * <p>Output:
 *
 * <p>[['B', '1', 'E', '1', 'B'], ['B', '1', 'M', '1', 'B'], ['B', '1', '1', '1', 'B'], ['B', 'B',
 * 'B', 'B', 'B']]
 *
 * <p>Example 2: Input:
 *
 * <p>[['B', '1', 'E', '1', 'B'], ['B', '1', 'M', '1', 'B'], ['B', '1', '1', '1', 'B'], ['B', 'B',
 * 'B', 'B', 'B']]
 *
 * <p>Click : [1,2]
 *
 * <p>Output:
 *
 * <p>[['B', '1', 'E', '1', 'B'], ['B', '1', 'X', '1', 'B'], ['B', '1', '1', '1', 'B'], ['B', 'B',
 * 'B', 'B', 'B']]
 *
 * <p>Note: The range of the input matrix's height and width is [1,50]. The click position will only
 * be an unrevealed square ('M' or 'E'), which also means the input board contains at least one
 * clickable square. The input board won't be a stage when game is over (some mines have been
 * revealed). For simplicity, not mentioned rules should be ignored in this problem. For example,
 * you don't need to reveal all the unrevealed mines when the game is over, consider any cases that
 * you will win the game or flag any squares.
 */
public class Minesweeper {
  private static final int[] R = {1, 1, 1, 0, 0, -1, -1, -1};
  private static final int[] C = {-1, 0, 1, -1, 1, -1, 0, 1};

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    char[][] board = {
      {'E', 'E', 'E', 'E', 'E'},
      {'E', 'E', 'M', 'E', 'E'},
      {'E', 'E', 'E', 'E', 'E'},
      {'E', 'E', 'E', 'E', 'E'}
    };
    int[] click = {3, 0};
    new Minesweeper().updateBoard(board, click);
    for (int i = 0; i < board.length; i++) System.out.println(board[i]);
  }

  public char[][] updateBoard(char[][] board, int[] click) {
    int r = click[0];
    int c = click[1];
    dfs(board, r, c);
    return board;
  }

  private void dfs(char[][] board, int r, int c) {
    if (board[r][c] == 'M') {
      board[r][c] = 'X';
    } else {
      int mineCount = 0;
      for (int i = 0; i < 8; i++) {
        int newR = r + R[i];
        int newC = c + C[i];
        if (newR >= 0
            && newC >= 0
            && newR < board.length
            && newC < board[0].length
            && board[newR][newC] == 'M') // boundary check
        mineCount++;
      }
      if (mineCount > 0) board[r][c] = (char) (mineCount + '0');
      else {
        board[r][c] = 'B';
        for (int i = 0; i < 8; i++) {
          int newR = r + R[i];
          int newC = c + C[i];
          if (newR >= 0
              && newC >= 0
              && newR < board.length
              && newC < board[0].length
              && board[newR][newC] == 'E') // boundary check
          {
            dfs(board, newR, newC);
          }
        }
      }
    }
  }
}
