package backtracking;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 14/05/2019 Think about Zuma Game. You have a row of balls on
 * the table, colored red(R), yellow(Y), blue(B), green(G), and white(W). You also have several
 * balls in your hand.
 *
 * <p>Each time, you may choose a ball in your hand, and insert it into the row (including the
 * leftmost place and rightmost place). Then, if there is a group of 3 or more balls in the same
 * color touching, remove these balls. Keep doing this until no more balls can be removed.
 *
 * <p>Find the minimal balls you have to insert to remove all the balls on the table. If you cannot
 * remove all the balls, output -1.
 *
 * <p>Examples:
 *
 * <p>Input: "WRRBBW", "RB" Output: -1 Explanation: WRRBBW -> WRR[R]BBW -> WBBW -> WBB[B]W -> WW
 *
 * <p>Input: "WWRRBBWW", "WRBRW" Output: 2 Explanation: WWRRBBWW -> WWRR[R]BBWW -> WWBBWW ->
 * WWBB[B]WW -> WWWW -> empty
 *
 * <p>Input:"G", "GGGGG" Output: 2 Explanation: G -> G[G] -> GG[G] -> empty
 *
 * <p>Input: "RBYYBBRRB", "YRBGB" Output: 3 Explanation: RBYYBBRRB -> RBYY[Y]BBRRB -> RBBBRRB ->
 * RRRB -> B -> B[B] -> BB[B] -> empty
 *
 * <p>Note: You may assume that the initial row of balls on the table won’t have any 3 or more
 * consecutive balls with the same color. The number of balls on the table won't exceed 20, and the
 * string represents these balls is called "board" in the input. The number of balls in your hand
 * won't exceed 5, and the string represents these balls is called "hand" in the input. Both input
 * strings will be non-empty and only contain characters 'R','Y','B','G','W'.
 *
 * <p>Solution: Maintain a count of each colored balls. Reduce the string to a new string by
 * removing the contiguous same coloured balls which exceeds the count of 3 starting from index i =
 * 0. Each new string formed (by adding a new ball to a position i) makes a new state. Backtrack and
 * traverse the search space and keep track of count of balls used. Maintain a minimum count
 * variable and return that as the answer.
 */
public class ZumaGame {
  public static void main(String[] args) {
    System.out.println(new ZumaGame().findMinStep("BBWWRRYYRRWWBB", "Y"));
  }

  Map<Character, Integer> map;
  int min = Integer.MAX_VALUE;

  public int findMinStep(String board, String hand) {
    map = new HashMap<>();
    for (char c : hand.toCharArray()) {
      map.putIfAbsent(c, 0);
      map.put(c, map.get(c) + 1);
    }
    backtrack(board, 0);
    return min == Integer.MAX_VALUE ? -1 : min;
  }

  private void backtrack(String board, int total) {
    if (board.isEmpty()) {
      min = Math.min(min, total);
    } else {
      int i = 0, j = 0;
      for (int l = board.length(); i < l; ) {
        if (j < l && board.charAt(j) == board.charAt(i)) {
          j++;
        } else {
          if (j - i > 2) {
            backtrack(board.substring(0, i) + ((j < l) ? board.substring(j) : ""), total);
          } else {
            int a = j - i;
            char c = board.charAt(i);
            if (map.containsKey(c)) {
              int count = map.get(c);
              if (count >= (3 - a)) {
                if ((count - (3 - a)) == 0) {
                  map.remove(c);
                } else {
                  map.put(c, count - (3 - a));
                }
                backtrack(
                    board.substring(0, i) + ((j < l) ? board.substring(j) : ""), total + (3 - a));
                map.put(c, count);
              }
            }
          }
          i = j;
          j++;
        }
      }
    }
  }
}
