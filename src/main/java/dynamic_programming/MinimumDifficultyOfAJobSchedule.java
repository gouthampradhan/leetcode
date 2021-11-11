package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 19/02/2020 You want to schedule a list of jobs in d days. Jobs
 * are dependent (i.e To work on the i-th job, you have to finish all the jobs j where 0 <= j < i).
 *
 * <p>You have to finish at least one task every day. The difficulty of a job schedule is the sum of
 * difficulties of each day of the d days. The difficulty of a day is the maximum difficulty of a
 * job done in that day.
 *
 * <p>Given an array of integers jobDifficulty and an integer d. The difficulty of the i-th job is
 * jobDifficulty[i].
 *
 * <p>Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs
 * return -1.
 *
 * <p>Example 1:
 *
 * <p>Input: jobDifficulty = [6,5,4,3,2,1], d = 2 Output: 7 Explanation: First day you can finish
 * the first 5 jobs, total difficulty = 6. Second day you can finish the last job, total difficulty
 * = 1. The difficulty of the schedule = 6 + 1 = 7 Example 2:
 *
 * <p>Input: jobDifficulty = [9,9,9], d = 4 Output: -1 Explanation: If you finish a job per day you
 * will still have a free day. you cannot find a schedule for the given jobs. Example 3:
 *
 * <p>Input: jobDifficulty = [1,1,1], d = 3 Output: 3 Explanation: The schedule is one job per day.
 * total difficulty will be 3. Example 4:
 *
 * <p>Input: jobDifficulty = [7,1,7,1,7,1], d = 3 Output: 15 Example 5:
 *
 * <p>Input: jobDifficulty = [11,111,22,222,33,333,44,444], d = 6 Output: 843
 *
 * <p>Constraints:
 *
 * <p>1 <= jobDifficulty.length <= 300 0 <= jobDifficulty[i] <= 1000 1 <= d <= 10
 */
public class MinimumDifficultyOfAJobSchedule {
  public static void main(String[] args) {
    int[] A = {11, 111, 22, 222, 33, 333, 44, 444};
    System.out.println(new MinimumDifficultyOfAJobSchedule().minDifficulty(A, 6));
  }

  int[][] DP;

  public int minDifficulty(int[] jobDifficulty, int d) {
    DP = new int[jobDifficulty.length][d + 1];
    int result = dp(0, d, jobDifficulty);
    if (result == 50000) return -1;
    else return result;
  }

  private int dp(int i, int d, int[] J) {
    if (i >= J.length && d == 0) return 0;
    else if (J.length - i < d || d <= 0) return 50000;
    else if (DP[i][d] != 0) return DP[i][d];
    int max = J[i];
    int min = Integer.MAX_VALUE;
    for (int k = i; k <= J.length - 1; k++) {
      max = Math.max(max, J[k]);
      min = Math.min(min, max + dp(k + 1, d - 1, J));
    }
    DP[i][d] = min;
    return min;
  }
}
