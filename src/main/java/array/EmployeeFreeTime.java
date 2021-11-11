package array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gouthamvidyapradhan on 08/03/2019 We are given a list schedule of employees, which
 * represents the working time for each employee.
 *
 * <p>Each employee has a list of non-overlapping Intervals, and these intervals are in sorted
 * order.
 *
 * <p>Return the list of finite intervals representing common, positive-length free time for all
 * employees, also in sorted order.
 *
 * <p>Example 1: Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]] Output: [[3,4]] Explanation:
 * There are a total of three employees, and all common free time intervals would be [-inf, 1], [3,
 * 4], [10, inf]. We discard any intervals that contain inf as they aren't finite. Example 2: Input:
 * schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]] Output: [[5,6],[7,9]] (Even though we are
 * representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays.
 * For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not
 * defined.)
 *
 * <p>Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.
 *
 * <p>Note:
 *
 * <p>schedule and schedule[i] are lists with lengths in range [1, 50]. 0 <= schedule[i].start <
 * schedule[i].end <= 10^8.
 *
 * <p>Solution: O(L X N x N) Where L is the number of schedules, N is the length of schedules for
 * each employee. Merge all the intervals to form a single merged array, now by using this array
 * form a result array with the intervals which form the free time.
 */
public class EmployeeFreeTime {

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
   */
  public static void main(String[] args) {
    List<List<Interval>> schedule = new ArrayList<>();
    List<Interval> ints1 = new ArrayList<>();
    ints1.add(new Interval(45, 56));
    ints1.add(new Interval(89, 96));

    List<Interval> ints3 = new ArrayList<>();
    ints3.add(new Interval(5, 21));
    ints3.add(new Interval(57, 74));

    schedule.add(ints1);
    schedule.add(ints3);

    List<Interval> result = new EmployeeFreeTime().employeeFreeTime(schedule);
    for (Interval i : result) {
      System.out.println(i.start + " " + i.end);
    }
  }

  public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
    if (schedule.isEmpty()) return new ArrayList<>();
    List<Interval> merged = schedule.get(0);
    for (int i = 1, l = schedule.size(); i < l; i++) {
      List<Interval> employeeInt = schedule.get(i);
      for (Interval in : employeeInt) {
        merged = merge(merged, in);
      }
    }
    List<Interval> result = new ArrayList<>();
    for (int i = 0, l = merged.size(); i + 1 < l; i++) {
      if (merged.get(i).end != merged.get(i + 1).start) {
        result.add(new Interval(merged.get(i).end, merged.get(i + 1).start));
      }
    }
    return result;
  }

  private List<Interval> merge(List<Interval> list, Interval interval) {
    List<Interval> result = new ArrayList<>();
    for (int i = 0, l = list.size(); i < l; i++) {
      Interval curr = list.get(i);
      if (interval.start >= curr.start && interval.end <= curr.end) {
        return list;
      } else if (interval.start >= curr.start && interval.start <= curr.end) {
        interval = new Interval(curr.start, interval.end);
      } else if (interval.end >= curr.start && interval.end <= curr.end) {
        interval = new Interval(interval.start, curr.end);
      } else if (interval.end < curr.start) {
        result.add(interval);
        for (int j = i; j < l; j++) {
          result.add(list.get(j));
        }
        return result;
      } else if (interval.start > curr.end) {
        result.add(curr);
      }
    }
    result.add(interval);
    return result;
  }
}
