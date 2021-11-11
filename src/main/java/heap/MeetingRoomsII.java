package heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by gouthamvidyapradhan on 27/11/2017. Given an array of meeting time intervals consisting
 * of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference
 * rooms required.
 *
 * <p>For example, Given [[0, 30],[5, 10],[15, 20]], return 2.
 *
 * <p>Solution: Sort the array based on start-time of the interval. Then, use the min-heap based on
 * min end time. For every interval remove the top element of the priority queue if the end time of
 * the top <= start time of the new interval. Add the new interval to the queue. The max size of the
 * priority queue attained during this process will be the answer.
 */
public class MeetingRoomsII {

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
    Interval i1 = new Interval(0, 40);
    Interval i2 = new Interval(2, 10);
    Interval i3 = new Interval(10, 40);
    Interval i4 = new Interval(15, 20);
    Interval i5 = new Interval(20, 30);
    Interval i6 = new Interval(20, 40);
    Interval i7 = new Interval(1, 5);
    Interval[] intervals = {i1, i2, i3, i4, i5, i6, i7};
    System.out.println(minMeetingRooms(intervals));
  }

  public static int minMeetingRooms(Interval[] intervals) {
    Arrays.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));
    PriorityQueue<Interval> queue = new PriorityQueue<>((a, b) -> Integer.compare(a.end, b.end));
    int max = 0;
    for (Interval i : intervals) {
      while (!queue.isEmpty() && queue.peek().end <= i.start) {
        queue.poll();
      }
      queue.offer(i);
      max = Math.max(max, queue.size());
    }
    return max;
  }
}
