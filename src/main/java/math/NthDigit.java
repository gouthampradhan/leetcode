package math;

/** Created by gouthamvidyapradhan on 05/11/2019 */
public class NthDigit {
  public static void main(String[] args) {
    System.out.println(new NthDigit().findNthDigit(1000000000));
  }

  public int findNthDigit(int n) {
    if (n >= 1 && n <= 9) return n;
    long sum = 0L;
    for (int i = 0; ; i++) {
      long pow = (9 * (new Double(Math.pow(10, i)).longValue())) * (i + 1);
      sum += pow;
      if (sum >= n) {
        long diff = (long) n - (sum - pow);
        long num = diff / (i + 1);
        long mod = diff % (i + 1);
        long result = new Double(Math.pow(10, i)).intValue() + (num - 1) + (mod > 0 ? 1 : 0);
        String resultStr = String.valueOf(result);
        return (mod == 0)
            ? Integer.parseInt(String.valueOf(resultStr.charAt(resultStr.length() - 1)))
            : Integer.parseInt(String.valueOf(resultStr.charAt((int) mod - 1)));
      }
    }
  }
}
