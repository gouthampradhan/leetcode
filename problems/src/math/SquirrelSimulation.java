package math;

/**
 * Created by gouthamvidyapradhan on 25/04/2019 There's a tree, a squirrel, and several nuts.
 * Positions are represented by the cells in a 2D grid. Your goal is to find the minimal distance
 * for the squirrel to collect all the nuts and put them under the tree one by one. The squirrel can
 * only take at most one nut at one time and can move in four directions - up, down, left and right,
 * to the adjacent cell. The distance is represented by the number of moves. Example 1:
 *
 * <p>Input: Height : 5 Width : 7 Tree position : [2,2] Squirrel : [4,4] Nuts : [[3,0], [2,5]]
 * Output: 12 Explanation: ​​​​​ Note:
 *
 * <p>All given positions won't overlap. The squirrel can take at most one nut at one time. The
 * given positions of nuts have no order. Height and width are positive integers. 3 <= height *
 * width <= 10,000. The given positions contain at least one nut, only one tree and one squirrel.
 *
 * <p>Solution O(N) Calculate the Manhattan Distance from each of the nut to the tree and double
 * this value - Let this value be D. We are doubling here to simulate going from tree to a nut and
 * back. Now, to get the minimum distance - for each distance d from squirrel position to nut
 * position do, Min(D + d - (distance from this nut to tree))
 */
public class SquirrelSimulation {
  public static void main(String[] args) {
    int height = 5;
    int width = 7;
    int[] tree = {2, 2};
    int[] squirrel = {4, 4};
    int[][] nuts = {{3, 0}, {2, 5}};
    System.out.println(new SquirrelSimulation().minDistance(height, width, tree, squirrel, nuts));
  }

  public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
    int dist = 0;
    for (int[] n : nuts) {
      dist += Math.abs(n[0] - tree[0]) + Math.abs(n[1] - tree[1]);
    }
    dist *= 2;
    int ans = Integer.MAX_VALUE;
    for (int[] n : nuts) {
      int nutDist = Math.abs(n[0] - squirrel[0]) + Math.abs(n[1] - squirrel[1]);
      ans = Math.min(ans, dist - (Math.abs(n[0] - tree[0]) + Math.abs(n[1] - tree[1])) + nutDist);
    }
    return ans;
  }
}
