package array;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 12/03/2019 Implement a MyCalendarThree class to store your
 * events. A new event can always be added.
 *
 * <p>Your class will have one method, book(int start, int end). Formally, this represents a booking
 * on the half open interval [start, end), the range of real numbers x such that start <= x < end.
 *
 * <p>A K-booking happens when K events have some non-empty intersection (ie., there is some time
 * that is common to all K events.)
 *
 * <p>For each call to the method MyCalendar.book, return an integer K representing the largest
 * integer such that there exists a K-booking in the calendar.
 *
 * <p>Your class will be called like this: MyCalendarThree cal = new MyCalendarThree();
 * MyCalendarThree.book(start, end) Example 1:
 *
 * <p>MyCalendarThree(); MyCalendarThree.book(10, 20); // returns 1 MyCalendarThree.book(50, 60); //
 * returns 1 MyCalendarThree.book(10, 40); // returns 2 MyCalendarThree.book(5, 15); // returns 3
 * MyCalendarThree.book(5, 10); // returns 3 MyCalendarThree.book(25, 55); // returns 3 Explanation:
 * The first two events can be booked and are disjoint, so the maximum K-booking is a 1-booking. The
 * third event [10, 40) intersects the first event, and the maximum K-booking is a 2-booking. The
 * remaining events cause the maximum K-booking to be only a 3-booking. Note that the last event
 * locally causes a 2-booking, but the answer is still 3 because eg. [10, 20), [10, 40), and [5, 15)
 * are still triple booked.
 */
public class MyCalendarThree {

  private List<Node> events;
  private int max;

  private class Node {
    int n, index;

    Node(int n, int index) {
      this.n = n;
      this.index = index;
    }

    public int getN() {
      return n;
    }

    public int getIndex() {
      return index;
    }
  }

  public MyCalendarThree() {
    events = new ArrayList<>();
    max = 0;
  }

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    MyCalendarThree calendar = new MyCalendarThree();
    System.out.println(calendar.book(10, 20));
    System.out.println(calendar.book(50, 60));
    System.out.println(calendar.book(10, 40));
    System.out.println(calendar.book(5, 15));
    System.out.println(calendar.book(5, 10));
    System.out.println(calendar.book(25, 55));
  }

  public int book(int start, int end) {
    events.add(new Node(start, 1));
    events.add(new Node(end, 0));
    events.sort((Comparator.comparing(Node::getN).thenComparing(Node::getIndex)));
    int count = 0;
    for (Node node : events) {
      if (node.index == 1 && node.n >= end) {
        break;
      }
      count += node.index == 1 ? 1 : -1;
      if (node.getN() >= start) {
        max = Math.max(max, count);
      }
    }
    return max;
  }
}
