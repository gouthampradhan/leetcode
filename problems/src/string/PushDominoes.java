package string;

/**
 * Created by gouthamvidyapradhan on 24/07/2019 There are N dominoes in a line, and we place each
 * domino vertically upright.
 *
 * <p>In the beginning, we simultaneously push some of the dominoes either to the left or to the
 * right.
 *
 * <p>After each second, each domino that is falling to the left pushes the adjacent domino on the
 * left.
 *
 * <p>Similarly, the dominoes falling to the right push their adjacent dominoes standing on the
 * right.
 *
 * <p>When a vertical domino has dominoes falling on it from both sides, it stays still due to the
 * balance of the forces.
 *
 * <p>For the purposes of this question, we will consider that a falling domino expends no
 * additional force to a falling or already fallen domino.
 *
 * <p>Given a string "S" representing the initial state. S[i] = 'L', if the i-th domino has been
 * pushed to the left; S[i] = 'R', if the i-th domino has been pushed to the right; S[i] = '.', if
 * the i-th domino has not been pushed.
 *
 * <p>Return a string representing the final state.
 *
 * <p>Example 1:
 *
 * <p>Input: ".L.R...LR..L.." Output: "LL.RR.LLRRLL.." Example 2:
 *
 * <p>Input: "RR.L" Output: "RR.L" Explanation: The first domino expends no additional force on the
 * second domino. Note:
 *
 * <p>0 <= N <= 10^5 String dominoes contains only 'L', 'R' and '.' Solution: O(N)
 */
public class PushDominoes {
  public static void main(String[] args) {
    System.out.println(new PushDominoes().pushDominoes("RR.L"));
  }

  public String pushDominoes(String dominoes) {
    int R = -1, L = -1;
    char[] A = dominoes.toCharArray();
    for (int i = 0; i < A.length; i++) {
      if (A[i] == 'L') {
        if (R > L) {
          int d = (i - R);
          int st;
          st = R + d / 2;
          if ((d % 2) == 0) {
            A[st] = '.';
          }
          for (int j = st + 1; j < i; j++) {
            A[j] = 'L';
          }
        } else {
          for (int j = (L == -1 ? 0 : L); j < i; j++) {
            A[j] = 'L';
          }
        }
        L = i;
      } else {
        if (A[i] == 'R') {
          R = i;
        } else {
          if (R > L) {
            A[i] = 'R';
          }
        }
      }
    }
    return String.valueOf(A);
  }
}
