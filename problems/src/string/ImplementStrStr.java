package string;

/**
 * Created by gouthamvidyapradhan on 24/06/2017. Implement strStr().
 *
 * <p>Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part
 * of haystack.
 *
 * <p>Solution O(N ^ 2)
 */
public class ImplementStrStr {
  public static void main(String[] args) throws Exception {
    System.out.println(new ImplementStrStr().strStr("AABB", ""));
  }

  public int strStr(String haystack, String needle) {
    if (haystack.isEmpty() && needle.isEmpty()) return 0;
    if (needle.isEmpty()) return 0;
    for (int i = 0, l = haystack.length(); i < l; i++) {
      if (haystack.charAt(i) == needle.charAt(0)) {
        if (isEqual(haystack, needle, i)) return i;
      }
    }
    return -1;
  }

  private boolean isEqual(String haystack, String needle, int i) {
    int hL = haystack.length();
    int nL = needle.length();
    int j = 0;
    while (i < hL && j < nL) {
      if (haystack.charAt(i) != needle.charAt(j)) return false;
      i++;
      j++;
    }
    return j >= nL;
  }
}
