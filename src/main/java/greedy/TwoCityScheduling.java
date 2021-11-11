package greedy;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 15/08/2019 There are 2N people a company is planning to
 * interview. The cost of flying the i-th person to city A is costs[i][0], and the cost of flying
 * the i-th person to city B is costs[i][1].
 *
 * <p>Return the minimum cost to fly every person to a city such that exactly N people arrive in
 * each city.
 *
 * <p>Example 1:
 *
 * <p>Input: [[10,20],[30,200],[400,50],[30,20]] Output: 110 Explanation: The first person goes to
 * city A for a cost of 10. The second person goes to city A for a cost of 30. The third person goes
 * to city B for a cost of 50. The fourth person goes to city B for a cost of 20.
 *
 * <p>The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each
 * city.
 *
 * <p>Note:
 *
 * <p>1 <= costs.length <= 100 It is guaranteed that costs.length is even. 1 <= costs[i][0],
 * costs[i][1] <= 1000
 *
 * <p>Solution: O(N log N) The general idea is to first allocate all the candidates to city A and
 * sum up the cost and mark this as MIN. Now, make pairs with (costA - CostB, i) and sort this list
 * of pairs in descending order (this is a greedy way of getting to the minimum possible value) -
 * take the first half of this sorted list and sum of their values and reduce this value from MIN to
 * get the answer.
 */
public class TwoCityScheduling {
  public static void main(String[] args) {
    int[][] A = {{10, 20}, {30, 200}, {400, 50}, {30, 20}};
    System.out.println(new TwoCityScheduling().twoCitySchedCost(A));
  }

  class Pair {
    int max, i;

    Pair(int max, int i) {
      this.max = max;
      this.i = i;
    }
  }

  public int twoCitySchedCost(int[][] costs) {
    int min = 0;

    for (int i = 0; i < costs.length; i++) {
      min += costs[i][0];
    }

    List<Pair> list = new ArrayList<>();
    for (int i = 0; i < costs.length; i++) {
      list.add(new Pair(costs[i][0] - costs[i][1], i));
    }
    list.sort((o1, o2) -> Integer.compare(o2.max, o1.max));

    for (int i = 0, N = (list.size() / 2); i < N; i++) {
      min -= list.get(i).max;
    }
    return min;
  }
}
