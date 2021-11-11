package divide_and_conquer;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 02/11/2019 Implement a MyCalendarTwo class to store your
 * events. A new event can be added if adding the event will not cause a triple booking.
 *
 * <p>Your class will have one method, book(int start, int end). Formally, this represents a booking
 * on the half open interval [start, end), the range of real numbers x such that start <= x < end.
 *
 * <p>A triple booking happens when three events have some non-empty intersection (ie., there is
 * some time that is common to all 3 events.)
 *
 * <p>For each call to the method MyCalendar.book, return true if the event can be added to the
 * calendar successfully without causing a triple booking. Otherwise, return false and do not add
 * the event to the calendar.
 *
 * <p>Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start,
 * end) Example 1:
 *
 * <p>MyCalendar(); MyCalendar.book(10, 20); // returns true MyCalendar.book(50, 60); // returns
 * true MyCalendar.book(10, 40); // returns true MyCalendar.book(5, 15); // returns false
 * MyCalendar.book(5, 10); // returns true MyCalendar.book(25, 55); // returns true Explanation: The
 * first two events can be booked. The third event can be double booked. The fourth event (5, 15)
 * can't be booked, because it would result in a triple booking. The fifth event (5, 10) can be
 * booked, as it does not use time 10 which is already double booked. The sixth event (25, 55) can
 * be booked, as the time in [25, 40) will be double booked with the third event; the time [40, 50)
 * will be single booked, and the time [50, 55) will be double booked with the second event.
 *
 * <p>Note:
 *
 * <p>The number of calls to MyCalendar.book per test case will be at most 1000. In calls to
 * MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].
 */
public class MyCalendarII {
  public static void main(String[] args) {
    MyCalendarII t = new MyCalendarII();
    System.out.println(t.book(20, 27));
    System.out.println(t.book(27, 36));
    System.out.println(t.book(27, 36));
    System.out.println(t.book(24, 33));
  }

  private class Pair {
    int a, b, index;

    Pair(int a, int b, int index) {
      this.a = a;
      this.b = b;
      this.index = index;
    }
  }

  TreeSet<Pair> treeSet;
  int count;

  public MyCalendarII() {
    count = 0;
    treeSet =
        new TreeSet<>(
            (o1, o2) -> {
              int r = Integer.compare(o1.a, o2.a);
              if (r == 0) {
                int r2 = Integer.compare(o1.b, o2.b);
                if (r2 == 0) {
                  return Integer.compare(o1.index, o2.index);
                } else return r2;
              }
              return r;
            });
  }

  public boolean book(int start, int end) {
    Pair range = new Pair(start, end, count++);
    Iterator<Pair> ascending = treeSet.iterator();
    Pair prev = null;
    while (ascending.hasNext()) {
      Pair cur = ascending.next();
      if (prev != null) {
        if ((range.a >= prev.a && range.a < prev.b) && (range.a >= cur.a && range.a < cur.b)) {
          return false;
        } else if ((prev.a >= range.a && prev.a < range.b)
            && (cur.a >= prev.a && cur.a < Math.min(prev.b, range.b))) {
          return false;
        } else if ((range.a >= prev.a && range.a < range.b)
            && (cur.a >= range.a && cur.a < Math.min(prev.b, range.b))) {
          return false;
        }
      }
      if ((range.a >= cur.a && range.a < cur.b) || (cur.a >= range.a && cur.a < range.b)) {
        prev = cur;
      }
    }
    treeSet.add(range);
    return true;
  }
}
