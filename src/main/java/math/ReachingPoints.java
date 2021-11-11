package math;

/**
 * Created by gouthamvidyapradhan on 14/07/2018. A move consists of taking a point (x, y) and
 * transforming it to either (x, x+y) or (x+y, y).
 *
 * <p>Given a starting point (sx, sy) and a target point (tx, ty), return True if and only if a
 * sequence of moves exists to transform the point (sx, sy) to (tx, ty). Otherwise, return False.
 *
 * <p>Examples: Input: sx = 1, sy = 1, tx = 3, ty = 5 Output: True Explanation: One series of moves
 * that transforms the starting point to the target is: (1, 1) -> (1, 2) (1, 2) -> (3, 2) (3, 2) ->
 * (3, 5)
 *
 * <p>Input: sx = 1, sy = 1, tx = 2, ty = 2 Output: False
 *
 * <p>Input: sx = 1, sy = 1, tx = 1, ty = 1 Output: True
 *
 * <p>Note:
 *
 * <p>sx, sy, tx, ty will all be integers in the range [1, 10^9].
 *
 * <p>Solution: Start from the target, reduce the target value to start value. If at any stage the
 * target value goes below start value then there exist no solution hence return false.
 */
public class ReachingPoints {

  class Pair {
    int x, y;

    Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    System.out.println(new ReachingPoints().reachingPoints(1, 1, 153, 10));
  }

  public boolean reachingPoints(int sx, int sy, int tx, int ty) {
    Pair target = new Pair(tx, ty);
    Pair start = new Pair(sx, sy);
    while (true) {
      if (start.x == target.x && start.y == target.y) {
        return true;
      } else if (start.x > target.x || start.y > target.y || target.x == target.y) {
        return false;
      } else if (start.x == target.x) {
        int t = target.y - start.y;
        return (t % target.x) == 0;
      } else if (start.y == target.y) {
        int t = target.x - start.x;
        return (t % target.y) == 0;
      } else {
        if (target.x > target.y) {
          int[] R = reduce(target.x, target.y);
          target.x = R[0];
          target.y = R[1];
        } else {
          int[] R = reduce(target.y, target.x);
          target.x = R[1];
          target.y = R[0];
        }
      }
    }
  }

  private int[] reduce(int x, int y) {
    int t = x - y;
    int q = t / y;
    x -= (y * q);
    if ((t % y) != 0) {
      x -= y;
    }
    return new int[] {x, y};
  }
}
