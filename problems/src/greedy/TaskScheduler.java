package greedy;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 26/11/2017.
 *
 * <p>Given a char array representing tasks CPU need to do. It contains capital letters A to Z where
 * different letters represent different tasks.Tasks could be done without original order. Each task
 * could be done in one interval. For each interval, CPU could finish one task or just be idle.
 *
 * <p>However, there is a non-negative cooling interval n that means between two same tasks, there
 * must be at least n intervals that CPU are doing different tasks or just be idle.
 *
 * <p>You need to return the least number of intervals the CPU will take to finish all the given
 * tasks.
 *
 * <p>Example 1: Input: tasks = ["A","A","A","B","B","B"], n = 2 Output: 8 Explanation: A -> B ->
 * idle -> A -> B -> idle -> A -> B. Note: The number of tasks is in the range [1, 10000]. The
 * integer n is in the range [0, 100].
 */
public class TaskScheduler {

  class Task {
    char t;
    int count;

    Task(char t, int count) {
      this.t = t;
      this.count = count;
    }

    public int getCount() {
      return count;
    }

    public void setCount(int count) {
      this.count = count;
    }
  }

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
    System.out.println(new TaskScheduler().leastInterval(tasks, 2));
  }

  /**
   * @param tasks
   * @param n
   * @return
   */
  public int leastInterval(char[] tasks, int n) {
    PriorityQueue<Task> queue =
        new PriorityQueue<>(Comparator.comparing(Task::getCount).reversed());
    List<Task> waiting = new ArrayList<>();
    Map<Character, Integer> map = new HashMap<>();
    for (char c : tasks) {
      if (map.get(c) == null) {
        map.put(c, 1);
      } else {
        int v = map.get(c) + 1;
        map.put(c, v);
      }
    }
    for (char c : map.keySet()) {
      Task task = new Task(c, map.get(c));
      queue.offer(task);
    }
    int count = 0;
    while (!queue.isEmpty()) {
      int i = 0;
      while (i <= n) {
        if (!queue.isEmpty()) {
          Task task = queue.poll();
          task.count--;
          if (task.count > 0) {
            waiting.add(task);
          }
        }
        count++;
        if (queue.isEmpty() && waiting.isEmpty()) break;
        i++;
      }
      queue.addAll(waiting);
      waiting.clear();
    }
    return count;
  }
}
