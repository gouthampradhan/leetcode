package binary_search;

/**
 * Created by gouthamvidyapradhan on 23/08/2019 Koko loves to eat bananas. There are N piles of
 * bananas, the i-th pile has piles[i] bananas. The guards have gone and will come back in H hours.
 *
 * <p>Koko can decide her bananas-per-hour eating speed of K. Each hour, she chooses some pile of
 * bananas, and eats K bananas from that pile. If the pile has less than K bananas, she eats all of
 * them instead, and won't eat any more bananas during this hour.
 *
 * <p>Koko likes to eat slowly, but still wants to finish eating all the bananas before the guards
 * come back.
 *
 * <p>Return the minimum integer K such that she can eat all the bananas within H hours.
 *
 * <p>Example 1:
 *
 * <p>Input: piles = [3,6,7,11], H = 8 Output: 4 Example 2:
 *
 * <p>Input: piles = [30,11,23,4,20], H = 5 Output: 30 Example 3:
 *
 * <p>Input: piles = [30,11,23,4,20], H = 6 Output: 23
 *
 * <p>Note:
 *
 * <p>1 <= piles.length <= 10^4 piles.length <= H <= 10^9 1 <= piles[i] <= 10^9
 *
 * <p>Solution: O(N x log Max(piles[i])) Binary search for the minimum possible value between (1 and
 * max(piles[i]))
 */
public class KokoEatingBananas {
  public static void main(String[] args) {
    int[] A = {312884470};
    System.out.println(new KokoEatingBananas().minEatingSpeed(A, 968709470));
  }

  public int minEatingSpeed(int[] piles, int H) {
    int max = 0;
    for (int i = 0; i < piles.length; i++) {
      max = Math.max(max, piles[i]);
    }
    if (H == piles.length) return max;
    int h = max, l = 1;
    int answer = H;
    while (l <= h) {
      int m = l + (h - l) / 2;
      boolean status = check(piles, H, m);
      if (status) {
        answer = m;
        h = m - 1;
      } else {
        l = m + 1;
      }
    }
    return answer;
  }

  private boolean check(int[] piles, int H, int k) {
    for (int p : piles) {
      if (p <= k) {
        H--;
      } else {
        int q = p / k;
        if ((p % k) > 0) {
          q++;
        }
        H -= q;
      }
      if (H < 0) {
        return false;
      }
    }
    return true;
  }
}
