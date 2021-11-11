package breadth_first_search;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 17/03/2019 On a 2x3 board, there are 5 tiles represented by the
 * integers 1 through 5, and an empty square represented by 0.
 *
 * <p>A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
 *
 * <p>The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
 *
 * <p>Given a puzzle board, return the least number of moves required so that the state of the board
 * is solved. If it is impossible for the state of the board to be solved, return -1.
 *
 * <p>Examples:
 *
 * <p>Input: board = [[1,2,3],[4,0,5]] Output: 1 Explanation: Swap the 0 and the 5 in one move.
 * Input: board = [[1,2,3],[5,4,0]] Output: -1 Explanation: No number of moves will make the board
 * solved. Input: board = [[4,1,2],[5,0,3]] Output: 5 Explanation: 5 is the smallest number of moves
 * that solves the board. An example path: After move 0: [[4,1,2],[5,0,3]] After move 1:
 * [[4,1,2],[0,5,3]] After move 2: [[0,1,2],[4,5,3]] After move 3: [[1,0,2],[4,5,3]] After move 4:
 * [[1,2,0],[4,5,3]] After move 5: [[1,2,3],[4,5,0]] Input: board = [[3,2,4],[1,5,0]] Output: 14
 * Note:
 *
 * <p>board will be a 2 x 3 array as described above. board[i][j] will be a permutation of [0, 1, 2,
 * 3, 4, 5].
 *
 * <p>Solution: Do a bfs of each state of the board to find the least possible moves.
 */
public class SlidingPuzzle {

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    int[][] board = {{1, 2, 3}, {4, 0, 5}};
    System.out.println(new SlidingPuzzle().slidingPuzzle(board));
  }

  class Node {
    Node(Board b) {
      this.b = b;
    }

    Board b;
    int dist;
  }

  private static final int[] R = {-1, 0, 0, 1};
  private static final int[] C = {0, 1, -1, 0};
  private static final String result = "123450";

  class Board {
    int[][] board;
    int r, c;
    final String state;

    Board(int[][] board) {
      this.board = board;
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < 2; i++) {
        for (int j = 0; j < 3; j++) {
          sb.append(board[i][j]);
        }
      }
      state = sb.toString();
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Board)) return false;
      Board board = (Board) o;
      return Objects.equals(state, board.state);
    }

    @Override
    public int hashCode() {
      return Objects.hash(state);
    }
  }

  private Set<Board> done;

  public int slidingPuzzle(int[][] board) {
    done = new HashSet<>();
    Board b = new Board(board);
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if (board[i][j] == 0) {
          b.r = i;
          b.c = j;
          break;
        }
      }
    }
    if (b.state.equals(result)) return 0;
    Queue<Node> queue = new ArrayDeque<>();
    queue.offer(new Node(b));
    while (!queue.isEmpty()) {
      Node child = queue.poll();
      Board br = child.b;
      done.add(br);
      for (int i = 0; i < 4; i++) {
        int newR = br.r + R[i];
        int newC = br.c + C[i];
        if (newR < 0 || newR >= 2 || newC < 0 || newC >= 3) continue;
        int num = br.board[newR][newC];
        int[][] tempB = clone(br.board);
        tempB[newR][newC] = 0;
        tempB[br.r][br.c] = num;
        Board tempBoard = new Board(tempB);
        tempBoard.r = newR;
        tempBoard.c = newC;
        if (!done.contains(tempBoard)) {
          if (tempBoard.state.equals(result)) {
            return child.dist + 1;
          }
          Node newChild = new Node(tempBoard);
          newChild.dist = child.dist + 1;
          queue.offer(newChild);
        }
      }
    }
    return -1;
  }

  private int[][] clone(int[][] original) {
    int[][] cloned = new int[2][3];
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 3; j++) {
        cloned[i][j] = original[i][j];
      }
    }
    return cloned;
  }
}
