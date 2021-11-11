package array;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by gouthamvidyapradhan on 30/07/2019 Given a list of 24-hour clock time points in
 * "Hour:Minutes" format, find the minimum minutes difference between any two time points in the
 * list. Example 1: Input: ["23:59","00:00"] Output: 1 Note: The number of time points in the given
 * list is at least 2 and won't exceed 20000. The input time is legal and ranges from 00:00 to
 * 23:59.
 *
 * <p>Solution: O(N log N) convert each time value of the form hh:mm to minutes and sort the array.
 * For every pair (i, j) where j = i + 1 (also for the case where i = 0 and j = N - 1) check the
 * minute difference and return the minimum time difference as the answer.
 */
public class MinimumTimeDifference {
  public static void main(String[] args) {
    List<String> list = Arrays.asList("23:59", "00:00");
    System.out.println(new MinimumTimeDifference().findMinDifference(list));
  }

  public int findMinDifference(List<String> timePoints) {
    List<Integer> timeInMinutes =
        timePoints
            .stream()
            .map(
                t -> {
                  String[] strings = t.split(":");
                  return Integer.parseInt(strings[0]) * 60 + Integer.parseInt(strings[1]);
                })
            .sorted(Integer::compareTo)
            .collect(Collectors.toList());
    int min = Integer.MAX_VALUE;
    for (int i = 1, l = timeInMinutes.size(); i < l; i++) {
      int prev = timeInMinutes.get(i - 1);
      int curr = timeInMinutes.get(i);
      min = Math.min(min, curr - prev);
      min = Math.min(min, ((24 * 60) - curr) + prev);
    }
    int prev = timeInMinutes.get(0);
    int curr = timeInMinutes.get(timeInMinutes.size() - 1);
    min = Math.min(min, curr - prev);
    min = Math.min(min, ((24 * 60) - curr) + prev);
    return min;
  }
}
