package greedy;

/**
 * Created by gouthamvidyapradhan on 28/06/2017. There are N gas stations along a circular route,
 * where the amount of gas at station i is gas[i].
 *
 * <p>You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i
 * to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
 *
 * <p>Return the starting gas station's index if you can travel around the circuit once, otherwise
 * return -1.
 *
 * <p>Note: The solution is guaranteed to be unique.
 *
 * <p>Solution: O(N) If point B cant be reached from point A then all the intermediate points
 * between A and B cant be the starting point. Therefore reset the starting point to the new
 * starting point.
 */
public class GasStation {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] gas = {10, 20, 30, 10};
    int[] cost = {5, 30, 10, 10};
    System.out.println(new GasStation().canCompleteCircuit(gas, cost));
  }

  public int canCompleteCircuit(int[] gas, int[] cost) {
    int debt = 0, sum = 0, start = 0;
    for (int i = 0; i < gas.length; i++) {
      sum += gas[i] - cost[i];
      if (sum < 0) {
        debt += sum;
        sum = 0;
        start = i + 1;
      }
    }
    debt += sum;
    return debt >= 0 ? start : -1;
  }
}
