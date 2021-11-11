package string;

/**
 * Created by gouthamvidyapradhan on 09/12/2017.
 *
 * <p>Given two strings S and T, determine if they are both one edit distance apart.
 */
public class OneEditDistance {

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    System.out.println(new OneEditDistance().isOneEditDistance("abxd", "adxb"));
  }

  public boolean isOneEditDistance(String s, String t) {
    if (Math.abs(s.length() - t.length()) > 1 || s.equals(t)) return false;
    if (s.length() > t.length()) {
      return hasDiffOne(s, t, false);
    } else if (t.length() > s.length()) {
      return hasDiffOne(t, s, false);
    } else {
      return hasDiffOne(s, t, true);
    }
  }

  private boolean hasDiffOne(String s, String t, boolean sameLength) {
    int count = 0, i = 0, j = 0;
    for (int l1 = s.length(), l2 = t.length(); i < l1 && j < l2; ) {
      if (s.charAt(i) == t.charAt(j)) {
        i++;
        j++;
      } else {
        if (count > 0) return false;
        count++;
        if (sameLength) {
          i++;
          j++;
        } else {
          i++;
        }
      }
    }
    if (i == j) {
      return true;
    } else {
      return (s.charAt(s.length() - 1) == t.charAt(t.length() - 1));
    }
  }
}
