package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gouthamvidyapradhan on 15/12/2017. Given a set of non-overlapping intervals, insert a
 * new interval into the intervals (merge if necessary).
 *
 * <p>You may assume that the intervals were initially sorted according to their start times.
 *
 * <p>Example 1: Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 *
 * <p>Example 2: Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as
 * [1,2],[3,10],[12,16].
 *
 * <p>This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 *
 * <p>Solution: O(n): Iterate through each interval and check for each overlapping case
 */
public class InsertInterval {

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
    Interval i1 = new Interval(1, 2);
    Interval i2 = new Interval(3, 5);
    Interval i3 = new Interval(6, 7);
    Interval i4 = new Interval(8, 10);
    Interval i5 = new Interval(12, 16);
    List<Interval> list = Arrays.asList(i1, i2, i3, i4, i5);
    List<Interval> result = new InsertInterval().insert(list, new Interval(2, 5));
    result.stream().map(x -> x.start + " " + x.end).forEach(System.out::println);
  }

  public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    List<Interval> result = new ArrayList<>();
    for (int i = 0, l = intervals.size(); i < l; i++) {
      Interval curr = intervals.get(i);
      if (newInterval.start >= curr.start && newInterval.end <= curr.end) {
        insertRest(intervals, result, i);
        return result; // easy case
      } else if (newInterval.start < curr.start && newInterval.end > curr.end) {
        newInterval = new Interval(newInterval.start, newInterval.end);
        // merge and continue
      } else if (newInterval.start >= curr.start
          && newInterval.start <= curr.end
          && newInterval.end > curr.end) {
        newInterval = new Interval(curr.start, newInterval.end);
        // merge and continue
      } else if (newInterval.start < curr.start
          && newInterval.end >= curr.start
          && newInterval.end <= curr.end) {
        Interval newI = new Interval(newInterval.start, curr.end);
        result.add(newI);
        insertRest(intervals, result, i + 1);
        return result;
      } else if (newInterval.start > curr.end) {
        result.add(curr);
      } else {
        result.add(newInterval);
        insertRest(intervals, result, i);
        return result;
      }
    }
    result.add(newInterval);
    return result;
  }

  private void insertRest(List<Interval> intervals, List<Interval> result, int i) {
    int l = intervals.size();
    while (i < l) {
      result.add(intervals.get(i));
      i++;
    }
  }
}
