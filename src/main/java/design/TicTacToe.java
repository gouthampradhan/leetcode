package design;

/**
 * Created by gouthamvidyapradhan on 13/05/2017.
 *
 * <p>Design a Tic-tac-toe game that is played between two players on a n x n grid.
 *
 * <p>You may assume the following rules:
 *
 * <p>A move is guaranteed to be valid and is placed on an empty block. Once a winning condition is
 * reached, no more moves is allowed. A player who succeeds in placing n of their marks in a
 * horizontal, vertical, or diagonal row wins the game. Example: Given n = 3, assume that player 1
 * is "X" and player 2 is "O" in the board.
 *
 * <p>TicTacToe toe = new TicTacToe(3);
 *
 * <p>toe.move(0, 0, 1); -> Returns 0 (no one wins) |X| | | | | | | // Player 1 makes a move at (0,
 * 0). | | | |
 *
 * <p>toe.move(0, 2, 2); -> Returns 0 (no one wins) |X| |O| | | | | // Player 2 makes a move at (0,
 * 2). | | | |
 *
 * <p>toe.move(2, 2, 1); -> Returns 0 (no one wins) |X| |O| | | | | // Player 1 makes a move at (2,
 * 2). | | |X|
 *
 * <p>toe.move(1, 1, 2); -> Returns 0 (no one wins) |X| |O| | |O| | // Player 2 makes a move at (1,
 * 1). | | |X|
 *
 * <p>toe.move(2, 0, 1); -> Returns 0 (no one wins) |X| |O| | |O| | // Player 1 makes a move at (2,
 * 0). |X| |X|
 *
 * <p>toe.move(1, 0, 2); -> Returns 0 (no one wins) |X| |O| |O|O| | // Player 2 makes a move at (1,
 * 0). |X| |X|
 *
 * <p>toe.move(2, 1, 1); -> Returns 1 (player 1 wins) |X| |O| |O|O| | // Player 1 makes a move at
 * (2, 1). |X|X|X|
 *
 * <p>Follow up: Could you do better than O(n2) per move() operation?
 *
 * <p>Solution: The below solution works in O(1) time complexity. 1. For each player move, keep
 * track of count of selection for each row and each column. 2. To keep track of counts in each
 * diagonals we need to first know if the move is made on either one of the diagonals. The move is
 * made in either of the diagonals if and only if (row = col) AND/OR (col + row = N - 1) 3. As soon
 * as the count in any of column, row or diagonal reach N then return the current player as the
 * winner, else return 0.
 */
public class TicTacToe {
  /** Cell class to keep track of first player and second player row/column count */
  private class Cell {
    int player1 = 0, player2 = 0;
  }

  Cell[] rows, columns; // Array of row and column cells
  private int N, fPD1 = 0, fPD2 = 0, sPD1 = 0, sPD2 = 0; // fPD1 -> first_player_diagonal1

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    TicTacToe toe = new TicTacToe(3);
    System.out.println(toe.move(0, 0, 1));
    System.out.println(toe.move(0, 2, 2));
    System.out.println(toe.move(1, 0, 1));
    System.out.println(toe.move(1, 1, 2));
    System.out.println(toe.move(2, 0, 1));
  }

  /** Initialize your data structure here. */
  public TicTacToe(int n) {
    N = n;
    rows = new Cell[N];
    columns = new Cell[N];
  }

  /**
   * Move and check who wins.
   *
   * @param row row
   * @param col col
   * @param player player
   * @return integer indicating the winner
   */
  public int move(int row, int col, int player) {
    switch (player) {
      case 1:
        increment(rows, row, 1);
        increment(columns, col, 1);
        if ((col + row) == (N - 1)) fPD2++;
        if (row == col) fPD1++;
        if (rows[row].player1 == N || columns[col].player1 == N || fPD1 == N || fPD2 == N) return 1;
        break;

      case 2:
        increment(rows, row, 2);
        increment(columns, col, 2);
        if ((col + row) == (N - 1)) sPD2++;
        if (row == col) sPD1++;
        if (rows[row].player2 == N || columns[col].player2 == N || sPD1 == N || sPD2 == N) return 2;
        break;
    }
    return 0;
  }

  /**
   * Increment row / col count
   *
   * @param cells array of cells
   * @param key row / col key
   * @param player Player object
   */
  private void increment(Cell[] cells, int key, int player) {
    Cell p = cells[key];
    if (p == null) {
      p = new Cell();
      cells[key] = p;
    }
    if (player == 1) p.player1++;
    else p.player2++;
  }
}
