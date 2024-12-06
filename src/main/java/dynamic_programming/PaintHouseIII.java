/* (C) 2024 YourCompanyName */
package dynamic_programming;

import java.util.Arrays;

/**
 * Created by gouthamvidyapradhan on 22/10/2020 There is a row of m houses in a small city, each
 * house must be painted with one of the n colors (labeled from 1 to n), some houses that has been
 * painted last summer should not be painted again.
 *
 * <p>A neighborhood is a maximal group of continuous houses that are painted with the same color.
 * (For example: houses = [1,2,2,3,3,2,1,1] contains 5 neighborhoods [{1}, {2,2}, {3,3}, {2},
 * {1,1}]).
 *
 * <p>Given an array houses, an m * n matrix cost and an integer target where:
 *
 * <p>houses[i]: is the color of the house i, 0 if the house is not painted yet. cost[i][j]: is the
 * cost of paint the house i with the color j+1. Return the minimum cost of painting all the
 * remaining houses in such a way that there are exactly target neighborhoods, if not possible
 * return -1.
 *
 * <p>Example 1:
 *
 * <p>Input: houses = [0,0,0,0,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target
 * = 3 Output: 9 Explanation: Paint houses of this way [1,2,2,1,1] This array contains target = 3
 * neighborhoods, [{1}, {2,2}, {1,1}]. Cost of paint all houses (1 + 1 + 1 + 1 + 5) = 9. Example 2:
 *
 * <p>Input: houses = [0,2,1,2,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target
 * = 3 Output: 11 Explanation: Some houses are already painted, Paint the houses of this way
 * [2,2,1,2,2] This array contains target = 3 neighborhoods, [{2,2}, {1}, {2,2}]. Cost of paint the
 * first and last house (10 + 1) = 11. Example 3:
 *
 * <p>Input: houses = [0,0,0,0,0], cost = [[1,10],[10,1],[1,10],[10,1],[1,10]], m = 5, n = 2, target
 * = 5 Output: 5 Example 4:
 *
 * <p>Input: houses = [3,1,2,3], cost = [[1,1,1],[1,1,1],[1,1,1],[1,1,1]], m = 4, n = 3, target = 3
 * Output: -1 Explanation: Houses are already painted with a total of 4 neighborhoods
 * [{3},{1},{2},{3}] different of target = 3.
 *
 * <p>Constraints:
 *
 * <p>m == houses.length == cost.length n == cost[i].length 1 <= m <= 100 1 <= n <= 20 1 <= target
 * <= m 0 <= houses[i] <= n 1 <= cost[i][j] <= 10^4
 */
public class PaintHouseIII {
  public static void main(String[] args) {
    //        int[] h = {0,0};
    //        int[][] cost = {{1,2}, {1,2}};
    int[] h = {3, 1, 2, 3};
    int[][] cost = {{1, 10}, {10, 1}, {1, 10}, {10, 1}, {1, 10}};
    int m = 5;
    int n = 2;
    int target = 5;
    System.out.println(new PaintHouseIII().minCost(h, cost, m, n, target));
  }

  int[][][] DP;

  public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
    DP = new int[houses.length][target + 1][n + 1];
    for (int i = 0; i < houses.length; i++) {
      for (int j = 0; j < target + 1; j++) {
        Arrays.fill(DP[i][j], -2);
      }
    }
    int result = dp(0, 0, target, cost, houses);
    return result;
  }

  private int dp(int i, int c, int t, int[][] cost, int[] houses) {
    if (t == 0 && i == houses.length) return 0;
    else if (t == -1 || i == houses.length) return -1;
    else if (DP[i][t][c] != -2) return DP[i][t][c];
    else {
      int min = Integer.MAX_VALUE;
      if (houses[i] != 0) {
        int result;
        if (houses[i] == c) {
          result = dp(i + 1, c, t, cost, houses);
        } else {
          result = dp(i + 1, houses[i], t - 1, cost, houses);
        }
        if (result != -1) {
          if (c != 0) {
            min = Math.min(min, result);
          } else min = result;
        }
      } else {
        for (int co = 1; co < cost[0].length + 1; co++) {
          int result;
          if (co != c) {
            result = dp(i + 1, co, t - 1, cost, houses);
          } else {
            result = dp(i + 1, co, t, cost, houses);
          }
          if (result != -1) {
            min = Math.min(min, cost[i][co - 1] + result);
          }
        }
      }
      DP[i][t][c] = (min == Integer.MAX_VALUE ? -1 : min);
      return DP[i][t][c];
    }
  }
}
