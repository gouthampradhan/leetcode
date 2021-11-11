package math;

import java.util.Arrays;

/**
 * Created by gouthamvidyapradhan on 30/01/2020 You have n super washing machines on a line.
 * Initially, each washing machine has some dresses or is empty.
 *
 * <p>For each move, you could choose any m (1 ≤ m ≤ n) washing machines, and pass one dress of each
 * washing machine to one of its adjacent washing machines at the same time .
 *
 * <p>Given an integer array representing the number of dresses in each washing machine from left to
 * right on the line, you should find the minimum number of moves to make all the washing machines
 * have the same number of dresses. If it is not possible to do it, return -1.
 *
 * <p>Example1
 *
 * <p>Input: [1,0,5]
 *
 * <p>Output: 3
 *
 * <p>Explanation: 1st move: 1 0 <-- 5 => 1 1 4 2nd move: 1 <-- 1 <-- 4 => 2 1 3 3rd move: 2 1 <-- 3
 * => 2 2 2 Example2
 *
 * <p>Input: [0,3,0]
 *
 * <p>Output: 2
 *
 * <p>Explanation: 1st move: 0 <-- 3 0 => 1 2 0 2nd move: 1 2 --> 0 => 1 1 1 Example3
 *
 * <p>Input: [0,2,0]
 *
 * <p>Output: -1
 *
 * <p>Explanation: It's impossible to make all the three washing machines have the same number of
 * dresses. Note: The range of n is [1, 10000]. The range of dresses number in a super washing
 * machine is [0, 1e5].
 */
public class SuperWashingMachines {
  public static void main(String[] args) {
    //
  }

  public int findMinMoves(int[] machines) {
    long sum = Arrays.stream(machines).asLongStream().sum();
    if (((sum / machines.length) < 0) || ((sum % machines.length) != 0)) return -1;
    int n = (int) (sum / machines.length);
    int count = 0, moves = Integer.MIN_VALUE;
    for (int i = 0; i < machines.length; i++) {
      count += (machines[i] - n);
      moves = Math.max(moves, Math.max(Math.abs(count), (machines[i] - n)));
    }
    return moves;
  }
}
