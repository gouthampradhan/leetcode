package two_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by gouthamvidyapradhan on 23/01/2018. You have k lists of sorted integers in ascending
 * order. Find the smallest range that includes at least one number from each of the k lists.
 *
 * <p>We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.
 *
 * <p>Example 1: Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]] Output: [20,24] Explanation:
 * List 1: [4, 10, 15, 24,26], 24 is in range [20,24]. List 2: [0, 9, 12, 20], 20 is in range
 * [20,24]. List 3: [5, 18, 22, 30], 22 is in range [20,24]. Note: The given list may contain
 * duplicates, so ascending order means >= here. 1 <= k <= 3500 -105 <= value of elements <= 105.
 * For Java users, please note that the input type has been changed to List<List<Integer>>. And
 * after you reset the code template, you'll see this point.
 *
 * <p>Solution O(n log m) where m is the total number of lists and n in the total elements in all
 * the list combined.
 */
public class SmallestRange {

  class MinIndex {
    int i, j;

    MinIndex(int i, int j) {
      this.i = i;
      this.j = j;
    }
  }

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    List<List<Integer>> list = new ArrayList<>();
    List<Integer> row1 = Arrays.asList(4, 10, 15, 24, 26);
    List<Integer> row2 = Arrays.asList(0, 9, 12, 20);
    List<Integer> row3 = Arrays.asList(5, 18, 22, 30);
    list.add(row1);
    list.add(row2);
    list.add(row3);
    int[] R = new SmallestRange().smallestRange(list);
    System.out.println(R[0] + " " + R[1]);
  }

  public int[] smallestRange(List<List<Integer>> nums) {
    PriorityQueue<MinIndex> pq =
        new PriorityQueue<>(
            (o1, o2) -> Integer.compare(nums.get(o1.i).get(o1.j), nums.get(o2.i).get(o2.j)));
    int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    for (int i = 0, l = nums.size(); i < l; i++) {
      min = Math.min(min, nums.get(i).get(0));
      max = Math.max(max, nums.get(i).get(0));
      pq.offer(new MinIndex(i, 0));
    }
    if (min == max) return new int[] {min, max};
    int ansMin = min, ansMax = max;
    while (true) {
      MinIndex minIndex = pq.poll();
      if (minIndex.j + 1 >= nums.get(minIndex.i).size()) {
        return new int[] {ansMin, ansMax};
      }
      int next = nums.get(minIndex.i).get(minIndex.j + 1);
      max = Math.max(max, next); // update max if any
      pq.offer(new MinIndex(minIndex.i, minIndex.j + 1));
      min = nums.get(pq.peek().i).get(pq.peek().j); // new minimum
      if ((max - min) < (ansMax - ansMin)) {
        ansMax = max;
        ansMin = min;
      }
    }
  }
}
