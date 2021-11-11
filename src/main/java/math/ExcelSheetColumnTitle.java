package math;

/**
 * Created by gouthamvidyapradhan on 12/08/2017. Given a positive integer, return its corresponding
 * column title as appear in an Excel sheet.
 *
 * <p>For example:
 *
 * <p>1 -> A 2 -> B 3 -> C ... 26 -> Z 27 -> AA 28 -> AB
 */
public class ExcelSheetColumnTitle {

  private static final String CONST = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    System.out.println(new ExcelSheetColumnTitle().convertToTitle(52));
  }

  public String convertToTitle(int n) {
    StringBuilder ans = new StringBuilder();
    while (n > 0) {
      int mod = n % 26;
      n /= 26;
      if (mod == 0) {
        ans.append('Z');
        n -= 1;
      } else {
        ans.append(CONST.charAt(mod - 1));
      }
    }
    return ans.reverse().toString();
  }
}
