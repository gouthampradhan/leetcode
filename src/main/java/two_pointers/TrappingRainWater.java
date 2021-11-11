package two_pointers;

/**
 * Created by gouthamvidyapradhan on 08/03/2017. Given n non-negative integers representing an
 * elevation map where the width of each bar is 1, compute how much water it is able to trap after
 * raining.
 *
 * <p>For example, Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 */
public class TrappingRainWater {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
    System.out.println(new TrappingRainWater().trap(height));
  }

  private int trap(int[] height) {
    if (height.length == 0) return 0;

    int[] left = new int[height.length];
    int[] right = new int[height.length];
    int max = 0;
    left[0] = 0;
    right[height.length - 1] = 0;

    int total = 0;

    for (int i = 1, l = height.length; i < l; i++) {
      left[i] = Math.max(max, height[i - 1]);
      max = left[i];
    }
    max = 0;
    for (int i = height.length - 2; i >= 0; i--) {
      right[i] = Math.max(max, height[i + 1]);
      max = right[i];
    }
    for (int i = 0, l = height.length; i < l; i++) {
      int min = Math.min(left[i], right[i]);
      if (min > height[i]) {
        total += (min - height[i]);
      }
    }
    return total;
  }
}
