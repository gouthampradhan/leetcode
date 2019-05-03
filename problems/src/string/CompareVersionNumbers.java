package string;

import java.util.StringTokenizer;

/**
 * Created by pradhang on 7/11/2017. Compare two version numbers version1 and version2. If version1
 * > version2 return 1, if version1 < version2 return -1, otherwise return 0.
 *
 * <p>You may assume that the version strings are non-empty and contain only digits and the .
 * character. The . character does not represent a decimal point and is used to separate number
 * sequences. For instance, 2.5 is not "two and a half" or "half way to version three", it is the
 * fifth second-level revision of the second first-level revision.
 *
 * <p>Here is an example of version numbers ordering:
 *
 * <p>0.1 < 1.1 < 1.2 < 13.37
 */
public class CompareVersionNumbers {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    System.out.println(new CompareVersionNumbers().compareVersion("1.11.1", "1.11"));
  }

  public int compareVersion(String version1, String version2) {
    StringTokenizer st1 = new StringTokenizer(version1, ".");
    StringTokenizer st2 = new StringTokenizer(version2, ".");
    while (st1.hasMoreTokens() & st2.hasMoreTokens()) {
      int token1 = Integer.parseInt(st1.nextToken());
      int token2 = Integer.parseInt(st2.nextToken());
      if (token1 > token2) return 1;
      else if (token2 > token1) return -1;
    }
    if (st1.countTokens() > st2.countTokens()) {
      while (st1.hasMoreTokens()) {
        if (Integer.parseInt(st1.nextToken()) > 0) return 1;
      }
    } else if (st2.countTokens() > st1.countTokens()) {
      while (st2.hasMoreTokens()) {
        if (Integer.parseInt(st2.nextToken()) > 0) return -1;
      }
    }
    return 0;
  }
}
