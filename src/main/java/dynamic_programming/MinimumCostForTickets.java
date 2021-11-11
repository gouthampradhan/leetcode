package dynamic_programming;

import java.util.Arrays;

/**
 * Created by gouthamvidyapradhan on 05/05/2019 In a country popular for train travel, you have
 * planned some train travelling one year in advance. The days of the year that you will travel is
 * given as an array days. Each day is an integer from 1 to 365.
 *
 * <p>Train tickets are sold in 3 different ways:
 *
 * <p>a 1-day pass is sold for costs[0] dollars; a 7-day pass is sold for costs[1] dollars; a 30-day
 * pass is sold for costs[2] dollars. The passes allow that many days of consecutive travel. For
 * example, if we get a 7-day pass on day 2, then we can travel for 7 days: day 2, 3, 4, 5, 6, 7,
 * and 8.
 *
 * <p>Return the minimum number of dollars you need to travel every day in the given list of days.
 *
 * <p>Example 1:
 *
 * <p>Input: days = [1,4,6,7,8,20], costs = [2,7,15] Output: 11 Explanation: For example, here is
 * one way to buy passes that lets you travel your travel plan: On day 1, you bought a 1-day pass
 * for costs[0] = $2, which covered day 1. On day 3, you bought a 7-day pass for costs[1] = $7,
 * which covered days 3, 4, ..., 9. On day 20, you bought a 1-day pass for costs[0] = $2, which
 * covered day 20. In total you spent $11 and covered all the days of your travel. Example 2:
 *
 * <p>Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15] Output: 17 Explanation: For
 * example, here is one way to buy passes that lets you travel your travel plan: On day 1, you
 * bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30. On day 31, you bought a
 * 1-day pass for costs[0] = $2 which covered day 31. In total you spent $17 and covered all the
 * days of your travel.
 *
 * <p>Note:
 *
 * <p>1 <= days.length <= 365 1 <= days[i] <= 365 days is in strictly increasing order. costs.length
 * == 3 1 <= costs[i] <= 1000
 *
 * <p>Solution: O(N ^ 2 x 3)
 */
public class MinimumCostForTickets {

  public static void main(String[] args) {
    int[] days = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31};
    int[] costs = {2, 7, 15};
    System.out.println(new MinimumCostForTickets().mincostTickets(days, costs));
  }
  /**
   * Main method
   *
   * @param days
   * @param costs
   * @return
   */
  public int mincostTickets(int[] days, int[] costs) {
    int[] min = new int[days.length];
    Arrays.fill(min, Integer.MAX_VALUE);
    for (int i = days.length - 1; i >= 0; i--) {
      for (int j = 0; j < costs.length; j++) {
        if (j == 0) {
          min[i] = Math.min(min[i], costs[j] + ((i + 1 >= min.length) ? 0 : min[i + 1]));
        } else if (j == 1) {
          int c = 0;
          for (int k = i + 1; k < days.length; k++) {
            if (days[k] >= (days[i] + 7)) {
              c = min[k];
              break;
            }
          }
          min[i] = Math.min(min[i], costs[j] + c);
        } else {
          int c = 0;
          for (int k = i + 1; k < days.length; k++) {
            if (days[k] >= (days[i] + 30)) {
              c = min[k];
              break;
            }
          }
          min[i] = Math.min(min[i], costs[j] + c);
        }
      }
    }
    return min[0];
  }
}
