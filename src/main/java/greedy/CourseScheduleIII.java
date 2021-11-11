package greedy;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by gouthamvidyapradhan on 27/06/2017.
 *
 * <p>There are n different online courses numbered from 1 to n. Each course has some
 * duration(course length) t and closed on dth day. A course should be taken continuously for t days
 * and must be finished before or on the dth day. You will start at the 1st day.
 *
 * <p>Given n online courses represented by pairs (t,d), your task is to find the maximal number of
 * courses that can be taken.
 *
 * <p>Example: Input: [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]] Output: 3 Explanation:
 * There're totally 4 courses, but you can take 3 courses at most: First, take the 1st course, it
 * costs 100 days so you will finish it on the 100th day, and ready to take the next course on the
 * 101st day. Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th
 * day, and ready to take the next course on the 1101st day. Third, take the 2nd course, it costs
 * 200 days so you will finish it on the 1300th day. The 4th course cannot be taken now, since you
 * will finish it on the 3300th day, which exceeds the closed date.
 *
 * <p>Note: The integer 1 <= d, t, n <= 10,000. You can't take two courses simultaneously.
 *
 * <p>Solution: O(N log N) 1. Sort the courses with earliest deadline time (Greedy sort) 2. Maintain
 * a max-heap of course duration. 3. Iterate through each course and increment the total time by
 * current course time and include this in the max-heap created in step 2. 4. If the total time
 * exceeds the current course deadline then, remove the course with max duration from max-heap
 * inorder to accommodate the new course.
 */
public class CourseScheduleIII {
  public static void main(String[] args) throws Exception {
    int[][] course = {{5, 5}, {2, 6}, {4, 6}};
    System.out.println(new CourseScheduleIII().scheduleCourse(course));
  }

  public int scheduleCourse(int[][] courses) {
    Arrays.sort(courses, (a, b) -> a[1] - b[1]);
    Queue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
    int time = 0;
    for (int[] course : courses) {
      time += course[0];
      pq.add(course[0]);
      if (time > course[1]) time -= pq.poll();
    }
    return pq.size();
  }
}
