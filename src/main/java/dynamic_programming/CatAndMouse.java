package dynamic_programming;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 03/03/2019
 *
 * <p>A game on an undirected graph is played by two players, Mouse and Cat, who alternate turns.
 *
 * <p>The graph is given as follows: graph[a] is a list of all nodes b such that ab is an edge of
 * the graph.
 *
 * <p>Mouse starts at node 1 and goes first, Cat starts at node 2 and goes second, and there is a
 * Hole at node 0.
 *
 * <p>During each player's turn, they must travel along one edge of the graph that meets where they
 * are. For example, if the Mouse is at node 1, it must travel to any node in graph[1].
 *
 * <p>Additionally, it is not allowed for the Cat to travel to the Hole (node 0.)
 *
 * <p>Then, the game can end in 3 ways:
 *
 * <p>If ever the Cat occupies the same node as the Mouse, the Cat wins. If ever the Mouse reaches
 * the Hole, the Mouse wins. If ever a position is repeated (ie. the players are in the same
 * position as a previous turn, and it is the same player's turn to move), the game is a draw. Given
 * a graph, and assuming both players play optimally, return 1 if the game is won by Mouse, 2 if the
 * game is won by Cat, and 0 if the game is a draw.
 *
 * <p>Example 1:
 *
 * <p>Input: [[2,5],[3],[0,4,5],[1,4,5],[2,3],[0,2,3]] Output: 0 Explanation: 4---3---1 | | 2---5 \
 * / 0
 *
 * <p>Note:
 *
 * <p>3 <= graph.length <= 50 It is guaranteed that graph[1] is non-empty. It is guaranteed that
 * graph[2] contains a non-zero element.
 *
 * <p>Solution: Each combination of mouse, cat and turn for a state (m, c, p). Transition between
 * different states form a state graph - start to color each state with either MOUSE, CAT or a DRAW
 * based on who wins this state. Perform a bottom up dp inorder to arrive at the answer.
 */
public class CatAndMouse {

  private final int CAT = 2, MOUSE = 1, DRAW = 0, PLAYER_CAT = 1, PLAYER_MOUSE = 0;
  private Queue<State> queue = new ArrayDeque<>();

  class State {
    int catPos, mPos, player, color;

    State(int mPos, int catPos, int player, int color) {
      this.catPos = catPos;
      this.mPos = mPos;
      this.player = player;
      this.color = color;
    }
  }

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    int[][] graph = {{2, 5}, {3}, {0, 4, 5}, {1, 4, 5}, {2, 3}, {0, 2, 3}};
    System.out.println(new CatAndMouse().catMouseGame(graph));
  }

  public int catMouseGame(int[][] graph) {
    int[][][] color = new int[graph.length][graph.length][2];
    int[][][] degree = new int[graph.length][graph.length][2];

    for (int i = 1; i < graph.length; i++) {
      for (int p = 0; p < 2; p++) {
        color[0][i][p] = MOUSE;
        queue.offer(new State(0, i, p, MOUSE));
        color[i][i][p] = CAT;
        queue.offer(new State(i, i, p, CAT));
      }
    }

    for (int m = 0; m < graph.length; m++) {
      for (int c = 1; c < graph.length; c++) {
        degree[m][c][0] = graph[m].length;
        degree[m][c][1] = graph[c].length;
        for (int v : graph[c]) {
          if (v == 0) {
            degree[m][c][1]--;
            break;
          }
        }
      }
    }

    while (!queue.isEmpty()) {
      State current = queue.poll();
      List<State> parents = getParents(graph, current, color);
      if (color[current.mPos][current.catPos][current.player] == CAT) {
        enqueue(queue, parents, PLAYER_CAT, CAT, color, degree);
      } else {
        enqueue(queue, parents, PLAYER_MOUSE, MOUSE, color, degree);
      }
    }
    return color[1][2][0];
  }

  private void enqueue(
      Queue<State> queue,
      List<State> parents,
      int player,
      int col,
      int[][][] color,
      int[][][] degree) {
    for (State parent : parents) {
      if (color[parent.mPos][parent.catPos][parent.player] == DRAW) {
        if (parent.player == player) {
          color[parent.mPos][parent.catPos][parent.player] = col;
          queue.offer(new State(parent.mPos, parent.catPos, parent.player, col));
        } else {
          int currDegree = --degree[parent.mPos][parent.catPos][parent.player];
          if (currDegree == 0) {
            color[parent.mPos][parent.catPos][parent.player] = col;
            queue.offer(new State(parent.mPos, parent.catPos, parent.player, col));
          }
        }
      }
    }
  }

  private List<State> getParents(int[][] graph, State current, int[][][] color) {
    int player = current.player;
    int[] positions;
    List<State> list = new ArrayList<>();
    if (player == PLAYER_MOUSE) {
      positions = graph[current.catPos];
      for (int pos : positions) {
        if (pos == 0) continue;
        if (color[current.mPos][pos][PLAYER_CAT] == DRAW) {
          list.add(new State(current.mPos, pos, PLAYER_CAT, DRAW));
        }
      }
    } else {
      positions = graph[current.mPos];
      for (int pos : positions) {
        if (color[pos][current.catPos][PLAYER_MOUSE] == DRAW) {
          list.add(new State(pos, current.catPos, PLAYER_MOUSE, DRAW));
        }
      }
    }
    return list;
  }
}
