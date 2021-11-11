package heap;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 26/07/2018. There are N children standing in a line. Each child
 * is assigned a rating value.
 *
 * <p>You are giving candies to these children subjected to the following requirements:
 *
 * <p>Each child must have at least one candy. Children with a higher rating get more candies than
 * their neighbors. What is the minimum candies you must give?
 *
 * <p>Example 1:
 *
 * <p>Input: [1,0,2] Output: 5 Explanation: You can allocate to the first, second and third child
 * with 2, 1, 2 candies respectively. Example 2:
 *
 * <p>Input: [1,2,2] Output: 4 Explanation: You can allocate to the first, second and third child
 * with 1, 2, 1 candies respectively. The third child gets 1 candy because it satisfies the above
 * two conditions.
 *
 * <p>Solution: O(N log N): Store the indexes in a heap, iterate through the heap one by one and
 * assign candies one greater than its neighbours. Take care of edge cases.
 */
public class Candy {

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] ratings = {29, 51, 87, 87, 72, 12};
    System.out.println(new Candy().candy(ratings));
  }

  public int candy(int[] ratings) {
    if (ratings.length == 1) return 1;
    PriorityQueue<Integer> pq =
        new PriorityQueue<>((o1, o2) -> Integer.compare(ratings[o1], ratings[o2]));
    for (int i = 0; i < ratings.length; i++) {
      pq.offer(i);
    }
    int[] count = new int[ratings.length];
    while (!pq.isEmpty()) {
      int index = pq.poll();
      if (index - 1 < 0) {
        if (ratings[index + 1] == ratings[index]) {
          count[index] = 1;
        } else {
          count[index] = count[index + 1] + 1;
        }
      } else if (index + 1 >= ratings.length) {
        if (ratings[index - 1] == ratings[index]) {
          count[index] = 1;
        } else {
          count[index] = count[index - 1] + 1;
        }
      } else {
        if ((ratings[index - 1] == ratings[index]) && (ratings[index + 1] == ratings[index])) {
          count[index] = 1;
        } else {
          if (((ratings[index - 1] == ratings[index]) && (ratings[index + 1] > ratings[index]))
              || ((ratings[index + 1] == ratings[index])
                  && (ratings[index - 1] > ratings[index]))) {
            count[index] = 1;
          } else if (((ratings[index - 1] == ratings[index])
              && (ratings[index + 1] < ratings[index]))) {
            count[index] = count[index + 1] + 1;
          } else if (((ratings[index + 1] == ratings[index])
              && (ratings[index - 1] < ratings[index]))) {
            count[index] = count[index - 1] + 1;
          } else {
            if (count[index - 1] > count[index + 1]) {
              count[index] = count[index - 1] + 1;
            } else {
              count[index] = count[index + 1] + 1;
            }
          }
        }
      }
    }
    int result = 0;
    for (int c : count) {
      result += c;
    }
    return result;
  }
}
