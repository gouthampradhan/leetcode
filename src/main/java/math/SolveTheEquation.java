package math;

/**
 * Created by gouthamvidyapradhan on 16/02/2018. Solve a given equation and return the value of x in
 * the form of string "x=#value". The equation contains only '+', '-' operation, the variable x and
 * its coefficient.
 *
 * <p>If there is no solution for the equation, return "No solution".
 *
 * <p>If there are infinite solutions for the equation, return "Infinite solutions".
 *
 * <p>If there is exactly one solution for the equation, we ensure that the value of x is an
 * integer.
 *
 * <p>Example 1: Input: "x+5-3+x=6+x-2" Output: "x=2" Example 2: Input: "x=x" Output: "Infinite
 * solutions" Example 3: Input: "2x=x" Output: "x=0" Example 4: Input: "2x+3x-6x=x+2" Output: "x=-1"
 * Example 5: Input: "x=x+2" Output: "No solution"
 *
 * <p>Solution: Solve the left and right part separately and then sum up the results.
 */
public class SolveTheEquation {

  int xL = 0, xR = 0, tL = 0, tR = 0;

  public static void main(String[] args) throws Exception {
    System.out.println(new SolveTheEquation().solveEquation("x=x+2"));
  }

  public String solveEquation(String equation) {
    String[] parts = equation.split("=");
    solve(parts[0], true);
    solve(parts[1], false);
    long right = (long) tR - tL;
    long left = (long) xL - xR;
    if (left == 0 && right == 0) {
      return "Infinite solutions";
    } else if (left == 0) {
      return "No solution";
    } else if (right == 0) {
      return "x=0";
    } else {
      return "x=" + (right / left);
    }
  }

  private void solve(String s, boolean isLeft) {
    String num = "";
    int xSum = 0;
    int rest = 0;
    boolean isNeg = false;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == '-') {
        if (!num.isEmpty()) {
          xSum = calculate(num, isNeg, xSum, rest)[0];
          rest = calculate(num, isNeg, xSum, rest)[1];
        }
        isNeg = true;
        num = "";
      } else if (c == '+') {
        if (!num.isEmpty()) {
          xSum = calculate(num, isNeg, xSum, rest)[0];
          rest = calculate(num, isNeg, xSum, rest)[1];
        }
        isNeg = false;
        num = "";
      } else {
        num += c;
      }
    }
    if (!num.isEmpty()) {
      xSum = calculate(num, isNeg, xSum, rest)[0];
      rest = calculate(num, isNeg, xSum, rest)[1];
    }
    if (isLeft) {
      xL = xSum;
      tL = rest;
    } else {
      xR = xSum;
      tR = rest;
    }
  }

  private int[] calculate(String num, boolean isNeg, int xSum, int rest) {
    int[] A = new int[2];
    if (num.contains("x")) {
      num = num.substring(0, num.length() - 1);
      if (isNeg) {
        xSum -= num.isEmpty() ? 1 : Integer.parseInt(num);
      } else {
        xSum += num.isEmpty() ? 1 : Integer.parseInt(num);
      }

    } else {
      if (isNeg) {
        rest -= Integer.parseInt(num);
      } else {
        rest += Integer.parseInt(num);
      }
    }
    A[0] = xSum;
    A[1] = rest;
    return A;
  }
}
