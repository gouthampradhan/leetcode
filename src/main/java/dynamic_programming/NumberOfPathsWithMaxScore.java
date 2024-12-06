/* (C) 2024 YourCompanyName */
package dynamic_programming;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by gouthamvidyapradhan on 13/04/2021 You are given a square board of characters. You can
 * move on the board starting at the bottom right square marked with the character 'S'.
 *
 * <p>You need to reach the top left square marked with the character 'E'. The rest of the squares
 * are labeled either with a numeric character 1, 2, ..., 9 or with an obstacle 'X'. In one move you
 * can go up, left or up-left (diagonally) only if there is no obstacle there.
 *
 * <p>Return a list of two integers: the first integer is the maximum sum of numeric characters you
 * can collect, and the second is the number of such paths that you can take to get that maximum
 * sum, taken modulo 10^9 + 7.
 *
 * <p>In case there is no path, return [0, 0].
 *
 * <p>Example 1:
 *
 * <p>Input: board = ["E23","2X2","12S"] Output: [7,1] Example 2:
 *
 * <p>Input: board = ["E12","1X1","21S"] Output: [4,2] Example 3:
 *
 * <p>Input: board = ["E11","XXX","11S"] Output: [0,0]
 *
 * <p>Constraints:
 *
 * <p>2 <= board.length == board[i].length <= 100 Solution: O(N x N) where N is the length of board.
 */
public class NumberOfPathsWithMaxScore {
  public static void main(String[] args) {
    String[] board = {"E11", "XXX", "11S"};
    List<String> input = Arrays.stream(board).collect(Collectors.toList());
    int[] r = new NumberOfPathsWithMaxScore().pathsWithMaxScore(input);
    System.out.println(r[0] + " " + r[1]);
  }

  long[][] M, N;
  final int[] R = {0, 1, 1};
  final int[] C = {1, 1, 0};
  int MOD = (int) 1e9 + 7;

  public int[] pathsWithMaxScore(List<String> board) {
    M = new long[board.size()][board.get(0).length()];
    N = new long[board.size()][board.get(0).length()];
    N[board.size() - 1][board.get(0).length() - 1] = 1;
    for (int i = board.size() - 1; i >= 0; i--) {
      for (int j = board.get(i).length() - 1; j >= 0; j--) {
        char curr = board.get(i).charAt(j);
        if (curr != 'X') {
          int currInt = 0;
          if (curr != 'S' && curr != 'E') {
            currInt = Integer.parseInt(String.valueOf(curr));
          }
          long currMax = -1;
          for (int k = 0; k < 3; k++) {
            int newR = i + R[k];
            int newC = j + C[k];
            if (newR < board.size()
                && newC < board.get(0).length()
                && board.get(newR).charAt(newC) != 'X'
                && N[newR][newC] != 0) {
              M[i][j] = Math.max(M[i][j], ((currInt + M[newR][newC]) % MOD));
              long newMax = ((currInt + M[newR][newC]) % MOD);
              if (newMax > currMax) {
                currMax = newMax;
                N[i][j] = N[newR][newC];
              } else if (newMax == currMax) {
                N[i][j] = ((N[newR][newC] + N[i][j]) % MOD);
              }
            }
          }
        }
      }
    }
    int[] res = new int[2];
    res[0] = (int) M[0][0];
    res[1] = (int) N[0][0];
    return res;
  }
}
