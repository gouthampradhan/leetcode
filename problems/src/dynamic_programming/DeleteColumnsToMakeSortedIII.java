package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 21/02/2020 We are given an array A of N lowercase letter
 * strings, all of the same length.
 *
 * <p>Now, we may choose any set of deletion indices, and for each string, we delete all the
 * characters in those indices.
 *
 * <p>For example, if we have an array A = ["babca","bbazb"] and deletion indices {0, 1, 4}, then
 * the final array after deletions is ["bc","az"].
 *
 * <p>Suppose we chose a set of deletion indices D such that after deletions, the final array has
 * every element (row) in lexicographic order.
 *
 * <p>For clarity, A[0] is in lexicographic order (ie. A[0][0] <= A[0][1] <= ... <= A[0][A[0].length
 * - 1]), A[1] is in lexicographic order (ie. A[1][0] <= A[1][1] <= ... <= A[1][A[1].length - 1]),
 * and so on.
 *
 * <p>Return the minimum possible value of D.length.
 *
 * <p>Example 1:
 *
 * <p>Input: ["babca","bbazb"] Output: 3 Explanation: After deleting columns 0, 1, and 4, the final
 * array is A = ["bc", "az"]. Both these rows are individually in lexicographic order (ie. A[0][0]
 * <= A[0][1] and A[1][0] <= A[1][1]). Note that A[0] > A[1] - the array A isn't necessarily in
 * lexicographic order. Example 2:
 *
 * <p>Input: ["edcba"] Output: 4 Explanation: If we delete less than 4 columns, the only row won't
 * be lexicographically sorted. Example 3:
 *
 * <p>Input: ["ghi","def","abc"] Output: 0 Explanation: All rows are already lexicographically
 * sorted.
 *
 * <p>Note:
 *
 * <p>1 <= A.length <= 100 1 <= A[i].length <= 100
 */
public class DeleteColumnsToMakeSortedIII {
  public static void main(String[] args) {
    String[] A = {"ghi", "def", "abc"};
    System.out.println(new DeleteColumnsToMakeSortedIII().minDeletionSize(A));
  }

  int[] DP;

  public int minDeletionSize(String[] A) {
    DP = new int[A[0].length()];
    int max = 0;
    for (int i = 0; i < A[0].length(); i++) {
      max = Math.max(max, dp(A, i));
    }
    return A[0].length() - max;
  }

  private int dp(String[] A, int i) {
    if (i >= A[0].length()) return 0;
    else if (DP[i] != 0) return DP[i];
    DP[i] = 1;
    for (int j = i + 1; j < A[0].length(); j++) {
      boolean possible = true;
      for (String str : A) {
        if (str.charAt(j) < str.charAt(i)) {
          possible = false;
          break;
        }
      }
      if (possible) {
        DP[i] = Math.max(DP[i], dp(A, j) + 1);
      }
    }
    return DP[i];
  }
}
