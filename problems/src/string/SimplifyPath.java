package string;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * Created by gouthamvidyapradhan on 28/07/2017.
 *
 * <p>Given an absolute path for a file (Unix-style), simplify it.
 *
 * <p>For example, path = "/home/", => "/home" path = "/a/./b/../../c/", => "/c"
 *
 * <p>Corner Cases: Did you consider the case where path = "/../"? In this case, you should return
 * "/". Another corner case is the path might contain multiple slashes '/' together, such as
 * "/home//foo/". In this case, you should ignore redundant slashes and return "/home/foo".
 */
public class SimplifyPath {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    System.out.println(new SimplifyPath().simplifyPath("/home/"));
  }

  public String simplifyPath(String path) {
    if (path == null || path.isEmpty()) return "/";
    StringTokenizer st = new StringTokenizer(path, "/");
    Deque<String> dQueue = new ArrayDeque<>();
    while (st.hasMoreTokens()) {
      String token = st.nextToken();
      if (token.trim().equals("..")) {
        if (!dQueue.isEmpty()) dQueue.pop();
      } else if (token.trim().equals(".")) {
        // ignore
      } else dQueue.push(token);
    }
    if (dQueue.isEmpty()) return "/";
    StringBuilder finalStr = new StringBuilder();
    while (!dQueue.isEmpty()) {
      finalStr.append("/").append(dQueue.removeLast());
    }
    return finalStr.toString();
  }
}
