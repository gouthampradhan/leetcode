package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by gouthamvidyapradhan on 13/06/2017. Given a collection of intervals, merge all
 * overlapping intervals.
 *
 * <p>For example, Given [1,3],[2,6],[8,10],[15,18], return [1,6],[8,10],[15,18].
 *
 * <p>Solution: O(N log N) where N is the number of intervals 1. Sort the intervals based on start
 * index 2. Mark the first interval as the current interval 3. For every ith interval starting 1 ->
 * N, if the ith interval overlaps with the current interval then create a new current interval.
 * Else, add the current interval to result set and begin a new current interval.
 */
public class MergeIntervals {
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

  public static void main(String[] args) throws Exception {
    Interval i1 = new Interval(1, 2);
    Interval i2 = new Interval(3, 4);
    Interval i3 = new Interval(5, 6);
    Interval i4 = new Interval(1, 10);
    List<Interval> result = new MergeIntervals().merge(Arrays.asList(i1, i2, i3, i4));
    result.forEach((I) -> System.out.println(I.start + " " + I.end));
  }

  public List<Interval> merge(List<Interval> intervals) {
    if (intervals.isEmpty()) return new ArrayList<>();
    Collections.sort(intervals, (o1, o2) -> Integer.compare(o1.start, o2.start));
    List<Interval> result = new ArrayList<>();
    Interval curr = intervals.get(0);
    for (int i = 1, l = intervals.size(); i < l; i++) {
      Interval I = intervals.get(i);
      if (I.start >= curr.start
          && I.start <= curr.end) { // check if the new interval overlaps with the current
        curr.end = curr.end > I.end ? curr.end : I.end;
      } else {
        result.add(curr);
        curr = I;
      }
    }
    result.add(curr);
    return result;
  }
}
