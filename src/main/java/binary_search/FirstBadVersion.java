package binary_search;

/**
 * Created by gouthamvidyapradhan on 29/11/2017.
 *
 * <p>You are a product manager and currently leading a team to develop a new product.
 * Unfortunately, the latest version of your product fails the quality check. Since each version is
 * developed based on the previous version, all the versions after a bad version are also bad.
 *
 * <p>Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which
 * causes all the following ones to be bad.
 *
 * <p>You are given an API bool isBadVersion(version) which will return whether version is bad.
 * Implement a function to find the first bad version. You should minimize the number of calls to
 * the API.
 */
public class FirstBadVersion {
  public static void main(String[] args) throws Exception {
    System.out.println(new FirstBadVersion().firstBadVersion(2126753390));
  }

  public int firstBadVersion(int n) {
    int low = 0, high = n;
    while (low < high) {
      int mid = (low + high) >>> 1;
      if (isBadVersion(mid)) {
        high = mid;
      } else low = mid + 1;
    }
    return high;
  }

  private boolean isBadVersion(int n) {
    if (n >= 1702766719) return true;
    return false;
  }
}
