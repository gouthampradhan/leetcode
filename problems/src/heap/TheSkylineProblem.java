package heap;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 13/09/2017.
 *
 * <p>A city's skyline is the outer contour of the silhouette formed by all the buildings in that
 * city when viewed from a distance. Now suppose you are given the locations and height of all the
 * buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed
 * by these buildings collectively (Figure B).
 *
 * <p>
 *
 * <p>See below link for image. https://leetcode.com/problems/the-skyline-problem/description/
 *
 * <p>
 *
 * <p>Buildings Skyline Contour The geometric information of each building is represented by a
 * triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right
 * edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤
 * INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles
 * grounded on an absolutely flat surface at height 0.
 *
 * <p>For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7
 * 15], [5 12 12], [15 20 10], [19 24 8] ] .
 *
 * <p>The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2,
 * y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a
 * horizontal line segment. Note that the last key point, where the rightmost building ends, is
 * merely used to mark the termination of the skyline, and always has zero height. Also, the ground
 * in between any two adjacent buildings should be considered part of the skyline contour.
 *
 * <p>For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12
 * 0], [15 10], [20 8], [24, 0] ].
 *
 * <p>Notes:
 *
 * <p>The number of buildings in any input list is guaranteed to be in the range [0, 10000]. The
 * input list is already sorted in ascending order by the left x position Li. The output list must
 * be sorted by the x position. There must be no consecutive horizontal lines of equal height in the
 * output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the
 * three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5],
 * [12 7], ...]
 *
 * <p>Solution: 1. Sort array of points. Each point here is either a start of a rectangle or end of
 * a rectangle. 2. Maintain a priority queue of rectangles ordered by increasing order of height, if
 * height of two rectangle is same then, order by left most start index. 3. For each point starting
 * from left-most point: 3.a. Add all the rectangles which starts at this point. 3.b. Remove all the
 * rectangles which ends at this point. Keep a max of height for each rectangle removed. 3.c. If the
 * current priority queue is empty then, include current point (x, 0) to the result set. This
 * indicates this was the last rectangle and after this there is a gap of at least 1 unit.
 *
 * <p>If the max calculated in step b is greater than current max then, include current x and max
 * height from priority queue to the result set. This indicates one of the larger rectangle's right
 * edge intersects with a smaller one.
 *
 * <p>If the max calculated in stop b is smaller then check if the peek element in priority queue
 * has the left edge value equal to current point. If so, then this indicates that a new larger
 * rectangle starts from this point therefore add this point to the result set. 4. Return the result
 * set
 */
public class TheSkylineProblem {

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[][] A = {
      {0, 30, 30}, {2, 9, 10}, {3, 7, 15}, {4, 8, 10}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}
    };
    // int[][] A = {{2,9,10}, {3,9,11}, {4,9,12}, {5,9,13}};
    List<int[]> result = new TheSkylineProblem().getSkyline(A);
    result.forEach(
        x -> {
          System.out.println(x[0] + " " + x[1]);
        });
  }

  public List<int[]> getSkyline(int[][] buildings) {
    PriorityQueue<Rectangle> pq =
        new PriorityQueue<>(
            Comparator.comparing(Rectangle::getH)
                .reversed()
                .thenComparing(
                    Rectangle
                        ::getX1)); // order by height, if height is same then, order by left most
                                   // starting edge.
    List<int[]> result = new ArrayList<>();
    Set<Integer> set = new HashSet<>();
    for (int[] p : buildings) {
      set.add(p[0]);
      set.add(p[1]);
    }
    List<Integer> points = new ArrayList<>();
    points.addAll(set);
    points.sort(Integer::compare);

    for (int i = 0, j = 0, l = points.size(); i < l; i++) {
      int curr = points.get(i);

      for (int k = j;
          k < buildings.length;
          k++) { // add all the rectangles that begin at this point
        int[] rectangle = buildings[k];
        if (rectangle[0] == curr) {
          pq.offer(new Rectangle(rectangle[0], rectangle[1], rectangle[2]));
        } else if (rectangle[0] > curr) {
          j = k;
          break;
        }
      }
      int max = Integer.MIN_VALUE;
      while (!pq.isEmpty()) { // remove all the rectangles that end at this point
        if (pq.peek().getX2() == curr) {
          Rectangle top = pq.poll();
          max = Math.max(max, top.getH());
        } else if (pq.peek().getX2() < curr) {
          pq.poll();
        } else {
          break;
        }
      }
      if (pq.isEmpty()) {
        result.add(
            makeNewPoint(
                curr,
                0)); // This is the last rectangle after this there is a gap of at least one unit
      } else {
        if (max > pq.peek().getH()) {
          result.add(
              makeNewPoint(
                  curr,
                  pq.peek()
                      .getH())); // one of the larger rectangle's right edge intersects with a
                                 // smaller one
        } else if (max < pq.peek().getH() && pq.peek().getX1() == curr) {
          result.add(
              makeNewPoint(curr, pq.peek().getH())); // new larger rectangle begins at this point
        }
      }
    }
    return result;
  }

  private int[] makeNewPoint(int x, int y) {
    int[] point = new int[2];
    point[0] = x;
    point[1] = y;
    return point;
  }

  class Rectangle {
    private int x1, x2, h;

    Rectangle(int x1, int x2, int h) {
      this.x1 = x1;
      this.x2 = x2;
      this.h = h;
    }

    public int getH() {
      return h;
    }

    public int getX2() {
      return x2;
    }

    public int getX1() {
      return x1;
    }
  }
}
