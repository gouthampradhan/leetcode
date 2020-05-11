package depth_first_search;

import java.util.*;

/** Created by gouthamvidyapradhan on 29/01/2020 */
public class FloodFill {
  final int[] R = {1, -1, 0, 0};
  final int[] C = {0, 0, -1, 1};

  public static void main(String[] args) {
    //
  }

  boolean done[][];

  public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
    done = new boolean[image.length][image[0].length];
    int[][] copy = new int[image.length][image[0].length];
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        copy[i][j] = image[i][j];
      }
    }
    dfs(copy, sr, sc, image[sr][sc], newColor);
    return copy;
  }

  private void dfs(int[][] image, int r, int c, int c1, int c2) {
    done[r][c] = true;
    image[r][c] = c2;
    for (int i = 0; i < 4; i++) {
      int newR = r + R[i];
      int newC = c + C[i];
      if (newR >= 0
          && newC >= 0
          && newR < image.length
          && newC < image[0].length
          && image[newR][newC] == c1
          && !done[newR][newC]) {
        dfs(image, newR, newC, c1, c2);
      }
    }
  }
}
