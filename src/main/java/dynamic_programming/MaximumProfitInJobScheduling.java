/* (C) 2024 YourCompanyName */
package dynamic_programming;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 18/06/2020 We have n jobs, where every job is scheduled to be
 * done from startTime[i] to endTime[i], obtaining a profit of profit[i].
 *
 * <p>You're given the startTime, endTime and profit arrays, return the maximum profit you can take
 * such that there are no two jobs in the subset with overlapping time range.
 *
 * <p>If you choose a job that ends at time X you will be able to start another job that starts at
 * time X.
 *
 * <p>Example 1:
 *
 * <p>Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70] Output: 120
 * Explanation: The subset chosen is the first and fourth job. Time range [1-3]+[3-6] , we get
 * profit of 120 = 50 + 70. Example 2:
 *
 * <p>Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60] Output: 150
 * Explanation: The subset chosen is the first, fourth and fifth job. Profit obtained 150 = 20 + 70
 * + 60. Example 3:
 *
 * <p>Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4] Output: 6
 *
 * <p>Constraints:
 *
 * <p>1 <= startTime.length == endTime.length == profit.length <= 5 * 104 1 <= startTime[i] <
 * endTime[i] <= 109 1 <= profit[i] <= 104
 */
public class MaximumProfitInJobScheduling {
  private class Pair {
    int a, b;

    Pair(int a, int b) {
      this.a = a;
      this.b = b;
    }
  }

  public static void main(String[] args) {
    int[] st = {4, 2, 4, 8, 2};
    int[] et = {5, 5, 5, 10, 8};
    int[] p = {1, 2, 8, 10, 4};
    System.out.println(new MaximumProfitInJobScheduling().jobScheduling(st, et, p));
  }

  Map<Integer, Integer> DP;
  TreeMap<Integer, List<Pair>> graph;

  public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
    DP = new HashMap<>();
    graph = new TreeMap<>();
    int start = 0;
    for (int i = 0; i < startTime.length; i++) {
      List<Pair> children = graph.getOrDefault(startTime[i], new ArrayList<>());
      children.add(new Pair(endTime[i], profit[i]));
      graph.putIfAbsent(startTime[i], children);
      start = Math.min(start, startTime[i]);
    }
    return dp(start);
  }

  private int dp(int i) {
    Integer current = graph.ceilingKey(i);
    if (current == null) return 0;
    else if (DP.containsKey(current)) return DP.get(current);
    else {
      List<Pair> children = graph.get(current);
      int profit = 0;
      for (Pair c : children) {
        profit = Math.max(profit, dp(c.a) + c.b);
      }
      profit = Math.max(profit, dp(current + 1));
      DP.put(current, profit);
      return profit;
    }
  }
}
