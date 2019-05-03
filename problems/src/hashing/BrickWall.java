package hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gouthamvidyapradhan on 19/01/2018. There is a brick wall in front of you. The wall is
 * rectangular and has several rows of bricks. The bricks have the same height but different width.
 * You want to draw a vertical line from the top to the bottom and cross the least bricks.
 *
 * <p>The brick wall is represented by a list of rows. Each row is a list of integers representing
 * the width of each brick in this row from left to right.
 *
 * <p>If your line go through the edge of a brick, then the brick is not considered as crossed. You
 * need to find out how to draw the line to cross the least bricks and return the number of crossed
 * bricks.
 *
 * <p>You cannot draw a line just along one of the two vertical edges of the wall, in which case the
 * line will obviously cross no bricks.
 *
 * <p>Example: Input: [[1,2,2,1], [3,1,2], [1,3,2], [2,4], [3,1,2], [1,3,1,1]] Output: 2
 * Explanation:
 *
 * <p>Note: The width sum of bricks in different rows are the same and won't exceed INT_MAX. The
 * number of bricks in each row is in range [1,10,000]. The height of wall is in range [1,10,000].
 * Total number of bricks of the wall won't exceed 20,000.
 *
 * <p>Solution: O(N) where N is the total number of bricks. Calculate the prefix sum for each row of
 * bricks and keep a max_count of each occurrence of prefix. The answer is total number of rows of
 * bricks - max_count
 */
public class BrickWall {

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    List<List<Integer>> wall = new ArrayList<>();
    List<Integer> row1 = new ArrayList<>();
    List<Integer> row2 = new ArrayList<>();
    List<Integer> row3 = new ArrayList<>();
    List<Integer> row4 = new ArrayList<>();
    List<Integer> row5 = new ArrayList<>();
    List<Integer> row6 = new ArrayList<>();
    row1.add(1);
    row1.add(2);
    row1.add(2);
    row1.add(1);
    row2.add(3);
    row2.add(1);
    row2.add(2);
    row3.add(1);
    row3.add(3);
    row3.add(2);
    row4.add(2);
    row4.add(4);
    row5.add(3);
    row5.add(1);
    row5.add(2);
    row6.add(1);
    row6.add(3);
    row6.add(1);
    row6.add(1);
    wall.add(row1);
    wall.add(row2);
    wall.add(row3);
    wall.add(row4);
    wall.add(row5);
    wall.add(row6);
    System.out.println(new BrickWall().leastBricks(wall));
  }

  public int leastBricks(List<List<Integer>> wall) {
    for (List<Integer> row : wall) {
      int prefix = 0;
      for (int i = 0, l = row.size(); i < l; i++) {
        prefix += row.get(i);
        row.set(i, prefix);
      }
    }
    int result = Integer.MIN_VALUE;
    Map<Integer, Integer> map = new HashMap<>();
    for (List<Integer> row : wall) {
      for (int i = 0, l = row.size(); i < l - 1; i++) {
        int prefix = row.get(i);
        if (map.containsKey(prefix)) {
          int plusOne = map.get(prefix) + 1;
          map.put(prefix, plusOne);
          result = Math.max(result, plusOne);
        } else {
          map.put(prefix, 1);
          result = Math.max(result, 1);
        }
      }
    }
    return (result == Integer.MIN_VALUE) ? wall.size() : wall.size() - result;
  }
}
