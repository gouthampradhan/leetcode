package depth_first_search;

/**
 * Created by gouthamvidyapradhan on 24/06/2018. An image is represented by a binary matrix with 0
 * as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one
 * black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one
 * of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all
 * black pixels.
 *
 * <p>Example:
 *
 * <p>Input: [ "0010", "0110", "0100" ] and x = 0, y = 2
 *
 * <p>Output: 6
 *
 * <p>Solution: O(n x m) do a dfs and keep track of min and max length-breadth. Return the product
 * of l x b
 */
public class SmallestRectangleEnclosingBlackPixels {
  private final int[] R = {1, -1, 0, 0};
  private final int[] C = {0, 0, -1, 1};
  private boolean[][] done;
  private int maxR, minR, minC, maxC;

  public static void main(String[] args) {
    char[][] A = {{'0', '0', '1', '1'}, {'0', '1', '1', '0'}, {'0', '1', '0', '0'}};
    System.out.println(new SmallestRectangleEnclosingBlackPixels().minArea(A, 0, 2));
  }

  public int minArea(char[][] image, int x, int y) {
    done = new boolean[image.length][image[0].length];
    maxR = 0;
    maxC = 0;
    minR = Integer.MAX_VALUE;
    minC = Integer.MAX_VALUE;
    maxR = Math.max(maxR, x);
    minR = Math.min(minR, x);

    maxC = Math.max(maxC, y);
    minC = Math.min(minC, y);
    dfs(image, x, y);
    return ((maxR - minR) + 1) * ((maxC - minC) + 1);
  }

  private void dfs(char[][] image, int r, int c) {
    done[r][c] = true;
    for (int i = 0; i < 4; i++) {
      int newR = r + R[i];
      int newC = c + C[i];
      if (newR >= 0
          && newR < image.length
          && newC >= 0
          && newC < image[0].length
          && !done[newR][newC]) {
        if (image[newR][newC] == '1') {
          maxR = Math.max(maxR, newR);
          minR = Math.min(minR, newR);

          maxC = Math.max(maxC, newC);
          minC = Math.min(minC, newC);
          dfs(image, newR, newC);
        }
      }
    }
  }
}
