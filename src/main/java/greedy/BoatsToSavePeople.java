package greedy;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 09/05/2019 The i-th person has weight people[i], and each boat
 * can carry a maximum weight of limit.
 *
 * <p>Each boat carries at most 2 people at the same time, provided the sum of the weight of those
 * people is at most limit.
 *
 * <p>Return the minimum number of boats to carry every given person. (It is guaranteed each person
 * can be carried by a boat.)
 *
 * <p>Example 1:
 *
 * <p>Input: people = [1,2], limit = 3 Output: 1 Explanation: 1 boat (1, 2) Example 2:
 *
 * <p>Input: people = [3,2,2,1], limit = 3 Output: 3 Explanation: 3 boats (1, 2), (2) and (3)
 * Example 3:
 *
 * <p>Input: people = [3,5,3,4], limit = 5 Output: 4 Explanation: 4 boats (3), (3), (4), (5) Note:
 *
 * <p>1 <= people.length <= 50000 1 <= people[i] <= limit <= 30000
 *
 * <p>Solution O N log N Simple strategy is to greedy try to put in maximum possible people in a
 * boat and increment the boat counter. Use TreeMap and sorting to achieve this easily
 */
public class BoatsToSavePeople {
  public static void main(String[] args) {
    int[] A = {3, 5, 3, 4};
    System.out.println(new BoatsToSavePeople().numRescueBoats(A, 8));
  }

  public int numRescueBoats(int[] people, int limit) {
    TreeMap<Integer, Integer> treeMap = new TreeMap<>();
    int boats = 0;
    for (int p : people) {
      treeMap.putIfAbsent(p, 0);
      treeMap.put(p, treeMap.get(p) + 1);
    }
    Arrays.sort(people);
    for (int p : people) {
      if (treeMap.containsKey(p)) {
        int count = treeMap.remove(p);
        --count;
        if (count != 0) {
          treeMap.put(p, count);
        }
        int balance = limit - p;
        Map.Entry<Integer, Integer> floor = treeMap.floorEntry(balance);
        if (floor != null) {
          int c = floor.getValue();
          --c;
          treeMap.remove(floor.getKey());
          if (c != 0) {
            treeMap.put(floor.getKey(), c);
          }
        }
        boats++;
      }
    }
    return boats;
  }
}
