package greedy;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by gouthamvidyapradhan on 29/06/2017. Suppose you have a random list of people standing
 * in a queue. Each person is described by a pair of integers (h, k), where h is the height of the
 * person and k is the number of people in front of this person who have a height greater than or
 * equal to h. Write an algorithm to reconstruct the queue.
 *
 * <p>Note: The number of people is less than 1,100.
 *
 * <p>Example
 *
 * <p>Input: [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *
 * <p>Output: [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */
public class QueueReconstructionByHeight {
  public static void main(String[] args) throws Exception {
    int[][] A = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
    int[][] r = new QueueReconstructionByHeight().reconstructQueue(A);
    for (int[] i : r) {
      System.out.println(i[0] + " " + i[1]);
    }
  }

  public int[][] reconstructQueue(int[][] people) {
    Arrays.sort(people, ((o1, o2) -> (o2[0] - o1[0] == 0) ? o1[1] - o2[1] : o2[0] - o1[0]));
    LinkedList<int[]> list = new LinkedList<>();
    for (int[] p : people) list.add(p[1], p);
    int[][] result = new int[people.length][2];
    for (int i = 0, l = list.size(); i < l; i++) result[i] = list.get(i);
    return result;
  }
}
