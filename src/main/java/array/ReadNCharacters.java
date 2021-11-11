package array;

/**
 * Created by gouthamvidyapradhan on 09/12/2017. The API: int read4(char *buf) reads 4 characters at
 * a time from a file.
 *
 * <p>The return value is the actual number of characters read. For example, it returns 3 if there
 * is only 3 characters left in the file.
 *
 * <p>By using the read4 API, implement the function int read(char *buf, int n) that reads n
 * characters from the file.
 *
 * <p>Note: The read function will only be called once for each test case.
 */
public class ReadNCharacters {

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {}

  /**
   * @param buf
   * @param n
   * @return
   */
  public int read(char[] buf, int n) {
    int i = 0;
    int toRead = Math.min(n, buf.length);
    while (i < toRead) {
      char[] temp = new char[4];
      int r = read4(temp);
      for (int j = 0; j < r && i < toRead; j++) {
        buf[i] = temp[j];
        i++;
      }
      if (r < 4) break;
    }
    return Math.min(i, toRead);
  }

  private int read4(char[] buf) {
    return 1; // return fake value just to resolve compilation error
  }
}
