package greedy;

import java.util.Arrays;

/**
 * Created by gouthamvidyapradhan on 28/06/2017. Given a collection of intervals, find the minimum
 * number of intervals you need to remove to make the rest of the intervals non-overlapping.
 *
 * <p>Note: You may assume the interval's end point is always bigger than its start point. Intervals
 * like [1,2] and [2,3] have borders "touching" but they don't overlap each other. Example 1: Input:
 * [ [1,2], [2,3], [3,4], [1,3] ]
 *
 * <p>Output: 1
 *
 * <p>Explanation: [1,3] can be removed and the rest of intervals are non-overlapping. Example 2:
 * Input: [ [1,2], [1,2], [1,2] ]
 *
 * <p>Output: 2
 *
 * <p>Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
 * Example 3: Input: [ [1,2], [2,3] ]
 *
 * <p>Output: 0
 *
 * <p>Explanation: You don't need to remove any of the intervals since they're already
 * non-overlapping.
 */
public class NonOverlappingIntervals {

  public static class Interval {
    int start;
    int end;

    Interval() {
      start = 0;
      end = 0;
    }

    Interval(int s, int e) {
      start = s;
      end = e;
    }
  }

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    Interval i1 = new Interval(1, 4);
    Interval i2 = new Interval(5, 9);
    Interval i3 = new Interval(3, 12);
    // Interval i4 = new Interval(1, 3);
    Interval[] intervals = {i1, i2, i3};
    System.out.println(new NonOverlappingIntervals().eraseOverlapIntervals(intervals));
  }

  public int eraseOverlapIntervals(Interval[] intervals) {
    if (intervals.length == 0) return 0;
    Arrays.sort(intervals, ((o1, o2) -> o1.end - o2.end));
    int count = 0;
    Interval prev = intervals[0];
    for (int i = 1; i < intervals.length; i++) {
      if (intervals[i].start < prev.end) {
        count++;
      } else {
        prev = intervals[i];
      }
    }
    return count;
  }
}
