package array;

/**
 * Created by gouthamvidyapradhan on 29/03/2019 A Tic-Tac-Toe board is given as a string array
 * board. Return True if and only if it is possible to reach this board position during the course
 * of a valid tic-tac-toe game.
 *
 * <p>The board is a 3 x 3 array, and consists of characters " ", "X", and "O". The " " character
 * represents an empty square.
 *
 * <p>Here are the rules of Tic-Tac-Toe:
 *
 * <p>Players take turns placing characters into empty squares (" "). The first player always places
 * "X" characters, while the second player always places "O" characters. "X" and "O" characters are
 * always placed into empty squares, never filled ones. The game ends when there are 3 of the same
 * (non-empty) character filling any row, column, or diagonal. The game also ends if all squares are
 * non-empty. No more moves can be played if the game is over. Example 1: Input: board = ["O ", " ",
 * " "] Output: false Explanation: The first player always plays "X".
 *
 * <p>Example 2: Input: board = ["XOX", " X ", " "] Output: false Explanation: Players take turns
 * making moves.
 *
 * <p>Example 3: Input: board = ["XXX", " ", "OOO"] Output: false
 *
 * <p>Example 4: Input: board = ["XOX", "O O", "XOX"] Output: true Note:
 *
 * <p>board is a length-3 array of strings, where each string board[i] has length 3. Each
 * board[i][j] is a character in the set {" ", "X", "O"}.
 *
 * <p>Solution: Do a brute-force check for each row, column and diagonals and keep track of count of
 * 'X' and 'O'
 */
public class ValidTicTacToeState {
  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    String[] board = {"XXX", "XOO", "OO "};
    System.out.println(new ValidTicTacToeState().validTicTacToe(board));
  }

  public boolean validTicTacToe(String[] board) {
    boolean xWon = hasWon(board, 'X');
    boolean oWon = hasWon(board, 'O');
    int xcount = 0, ocount = 0;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board[i].charAt(j) == 'X') {
          xcount++;
        } else if (board[i].charAt(j) == 'O') {
          ocount++;
        }
      }
    }
    if (xWon && oWon) return false;
    if (xWon) {
      return ((xcount - 1 == ocount));
    } else if (oWon) {
      return ((xcount == ocount));
    } else {
      return (xcount == ocount || xcount - 1 == ocount);
    }
  }

  private boolean hasWon(String[] board, char c) {
    boolean diagnol =
        ((board[0].charAt(0) == c && board[1].charAt(1) == c && board[2].charAt(2) == c)
            || (board[0].charAt(2) == c && board[1].charAt(1) == c && board[2].charAt(0) == c));
    if (diagnol) return true;
    for (int i = 0; i < 3; i++) {
      if (board[i].charAt(0) == c && board[i].charAt(1) == c && board[i].charAt(2) == c)
        return true;
    }
    for (int i = 0; i < 3; i++) {
      if (board[0].charAt(i) == c && board[1].charAt(i) == c && board[2].charAt(i) == c)
        return true;
    }
    return false;
  }
}
