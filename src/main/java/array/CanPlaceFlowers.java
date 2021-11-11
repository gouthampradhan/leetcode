package array;

/**
 * Created by gouthamvidyapradhan on 10/06/2017. Accepted
 *
 * <p>Suppose you have a long flowerbed in which some of the plots are planted and some are not.
 * However, flowers cannot be planted in adjacent plots - they would compete for water and both
 * would die.
 *
 * <p>Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means
 * not empty), and a number n, return if n new flowers can be planted in it without violating the
 * no-adjacent-flowers rule.
 *
 * <p>Example 1: Input: flowerbed = [1,0,0,0,1], n = 1 Output: True Example 2: Input: flowerbed =
 * [1,0,0,0,1], n = 2 Output: False Note: The input array won't violate no-adjacent-flowers rule.
 * The input array size is in the range of [1, 20000]. n is a non-negative integer which won't
 * exceed the input array size.
 */
public class CanPlaceFlowers {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] n = {1, 0, 0, 0, 1};
    System.out.println(new CanPlaceFlowers().canPlaceFlowers(n, 1));
  }

  public boolean canPlaceFlowers(int[] flowerbed, int n) {

    int[] T = new int[flowerbed.length + 4];
    for (int i = 0, j = 2; i < flowerbed.length; i++) T[j++] = flowerbed[i];
    T[0] = 1;
    T[T.length - 1] = 1;
    int total = 0, count = 0;
    for (int i = 1; i < T.length; i++) {
      if (T[i] == 0) count++;
      else {
        if ((count % 2) == 0) total += ((count / 2) - 1);
        else total += (count / 2);
        count = 0; // reset
      }
    }
    return (total >= n);
  }
}
