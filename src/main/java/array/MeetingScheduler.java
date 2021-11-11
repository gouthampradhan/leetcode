package array;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 19/11/2019 Given the availability time slots arrays slots1 and
 * slots2 of two people and a meeting duration duration, return the earliest time slot that works
 * for both of them and is of duration duration.
 *
 * <p>If there is no common time slot that satisfies the requirements, return an empty array.
 *
 * <p>The format of a time slot is an array of two elements [start, end] representing an inclusive
 * time range from start to end.
 *
 * <p>It is guaranteed that no two availability slots of the same person intersect with each other.
 * That is, for any two time slots [start1, end1] and [start2, end2] of the same person, either
 * start1 > end2 or start2 > end1.
 *
 * <p>Example 1:
 *
 * <p>Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8 Output:
 * [60,68] Example 2:
 *
 * <p>Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12 Output:
 * []
 *
 * <p>Constraints:
 *
 * <p>1 <= slots1.length, slots2.length <= 10^4 slots1[i].length, slots2[i].length == 2 slots1[i][0]
 * < slots1[i][1] slots2[i][0] < slots2[i][1] 0 <= slots1[i][j], slots2[i][j] <= 10^9 1 <= duration
 * <= 10^6
 */
public class MeetingScheduler {
  public static void main(String[] args) {
    int[][] slots1 = {{10, 50}, {60, 120}, {140, 210}};
    int[][] slots2 = {{0, 15}, {60, 70}};
    List<Integer> result = new MeetingScheduler().minAvailableDuration(slots1, slots2, 12);
    System.out.println();
  }

  private class Node {
    int s, e, type;

    Node(int s, int e, int type) {
      this.s = s;
      this.e = e;
      this.type = type;
    }
  }

  public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
    PriorityQueue<Node> pq =
        new PriorityQueue<>(
            (o1, o2) -> {
              int r = Integer.compare(o1.s, o2.s);
              if (r == 0) {
                return Integer.compare(o1.e, o2.e);
              } else return r;
            });
    for (int[] s : slots1) {
      pq.offer(new Node(s[0], s[1], 1));
    }
    for (int[] s : slots2) {
      pq.offer(new Node(s[0], s[1], 2));
    }
    Node prev = null;
    while (!pq.isEmpty()) {
      Node node = pq.poll();
      if (prev == null) {
        prev = node;
      } else {
        if (prev.type != node.type) {
          int s = Math.max(prev.s, node.s);
          int e = Math.min(prev.e, node.e);
          if ((e - s) >= duration) {
            return Arrays.asList(s, s + duration);
          }
        }
        if (node.e > prev.e) {
          prev = node;
        }
      }
    }
    return new ArrayList<>();
  }
}
