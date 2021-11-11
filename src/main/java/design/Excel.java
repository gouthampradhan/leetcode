package design;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 16/09/2017.
 *
 * <p>Your task is to design the basic function of Excel and implement the function of sum formula.
 * Specifically, you need to implement the following functions:
 *
 * <p>Excel(int H, char W): This is the constructor. The inputs represents the height and width of
 * the Excel form. H is a positive integer, range from 1 to 26. It represents the height. W is a
 * character range from 'A' to 'Z'. It represents that the width is the number of characters from
 * 'A' to W. The Excel form content is represented by a height * width 2D integer array C, it should
 * be initialized to zero. You should assume that the first row of C starts from 1, and the first
 * column of C starts from 'A'.
 *
 * <p>
 *
 * <p>void Set(int row, char column, int val): Change the value at C(row, column) to be val.
 *
 * <p>
 *
 * <p>int Get(int row, char column): Return the value at C(row, column).
 *
 * <p>
 *
 * <p>int Sum(int row, char column, List of Strings : numbers): This function calculate and set the
 * value at C(row, column), where the value should be the sum of cells represented by numbers. This
 * function return the sum result at C(row, column). This sum formula should exist until this cell
 * is overlapped by another value or another sum formula.
 *
 * <p>numbers is a list of strings that each string represent a cell or a range of cells. If the
 * string represent a single cell, then it has the following format : ColRow. For example, "F7"
 * represents the cell at (7, F).
 *
 * <p>If the string represent a range of cells, then it has the following format : ColRow1:ColRow2.
 * The range will always be a rectangle, and ColRow1 represent the position of the top-left cell,
 * and ColRow2 represents the position of the bottom-right cell.
 *
 * <p>
 *
 * <p>Example 1: Excel(3,"C"); // construct a 3*3 2D array with all zero. // A B C // 1 0 0 0 // 2 0
 * 0 0 // 3 0 0 0
 *
 * <p>Set(1, "A", 2); // set C(1,"A") to be 2. // A B C // 1 2 0 0 // 2 0 0 0 // 3 0 0 0
 *
 * <p>Sum(3, "C", ["A1", "A1:B2"]); // set C(3,"C") to be the sum of value at C(1,"A") and the
 * values sum of the rectangle range whose top-left cell is C (1,"A") and bottom-right cell is
 * C(2,"B"). Return 4. // A B C // 1 2 0 0 // 2 0 0 0 // 3 0 0 4
 *
 * <p>Set(2, "B", 2); // set C(2,"B") to be 2. Note C(3, "C") should also be changed. // A B C // 1
 * 2 0 0 // 2 0 2 0 // 3 0 0 6 Note: You could assume that there won't be any circular sum
 * reference. For example, A1 = sum(B1) and B1 = sum(A1). The test cases are using double-quotes to
 * represent a character. Please remember to RESET your class variables declared in class Excel, as
 * static/class variables are persisted across multiple test cases.
 *
 * <p>Solution: Build a graph and for each cell keep track of forward and backward links. When a
 * cell is updated with a new value broadcast the new value to all the forward links and remove all
 * the forward links pointing to this particular cell.
 */
public class Excel {

  private Map<String, Set<String>> fwdEdges; // Forward links from cell
  private Map<String, List<String>> backEdge; // All backward links from cell
  private Map<String, Integer> count; // Keep track of number of times the cell is linked
  private int[][] grid; // excel grid

  /**
   * Initialize datastructure
   *
   * @param H row
   * @param W column
   */
  public Excel(int H, char W) {
    grid = new int[H][(Character.toUpperCase(W) - 'A') + 1];
    fwdEdges = new HashMap<>();
    backEdge = new HashMap<>();
    count = new HashMap<>();
  }

  public static void main(String[] args) throws Exception {
    Excel excel = new Excel(26, 'Z');
    excel.set(1, 'A', 1);
    excel.set(1, 'I', 1);
    String[] arr = {"A1:D6", "A1:G3", "A1:C12"};
    String[] arr1 = {"A1:D7", "D1:F10", "D3:I8", "I1:I9"};
    System.out.println(excel.get(1, 'A'));
    System.out.println(excel.sum(7, 'D', arr));
    System.out.println(excel.get(1, 'A'));
    System.out.println(excel.sum(10, 'G', arr1));
    System.out.println(excel.get(1, 'A'));
  }

  /**
   * Set value to the grid
   *
   * @param r row
   * @param c column
   * @param v value
   */
  public void set(int r, char c, int v) {
    setValue(r, c, v);
    removeForwardEdges(String.valueOf(c) + r);
  }

  private void setValue(int r, char c, int v) {
    int curr = grid[r - 1][Character.toUpperCase(c) - 'A'];
    grid[r - 1][Character.toUpperCase(c) - 'A'] = v;
    broadcast(v - curr, String.valueOf(c) + r);
  }

  /**
   * Remove all the links
   *
   * @param node node
   */
  private void removeForwardEdges(String node) {
    List<String> parents = backEdge.get(node);
    if (parents != null) {
      for (String p : parents) {
        Set<String> children = fwdEdges.get(p);
        if (children != null) {
          count.remove(p + ":" + node);
          children.remove(node);
        }
      }
    }
  }

  /**
   * Broadcast to all the links
   *
   * @param v current node
   * @param node node
   */
  private void broadcast(int v, String node) {
    Set<String> children = fwdEdges.get(node);
    if (children != null) {
      for (String c : children) {
        int order = count.get(node + ":" + c);
        grid[Integer.parseInt(c.substring(1)) - 1][c.charAt(0) - 'A'] += (v * order);
        broadcast(v, c);
      }
    }
  }

  public int get(int r, char c) {
    return grid[r - 1][c - 'A'];
  }

  /**
   * Sum range of cells
   *
   * @param r row
   * @param c column
   * @param strs Strings
   * @return integer sum
   */
  public int sum(int r, char c, String[] strs) {
    int sum = 0;
    // Remove all the forward and backward edges or links
    removeForwardEdges(c + String.valueOf(r));
    backEdge.remove(c + String.valueOf(r));
    Set<String> nodes = new HashSet<>();
    for (String s : strs) {
      String[] range = s.split(":");
      if (range.length > 1) {
        int startRow = Integer.parseInt(range[0].substring(1)) - 1;
        int startColumn = range[0].charAt(0) - 'A';

        int endRow = Integer.parseInt(range[1].substring(1)) - 1;
        int endColumn = range[1].charAt(0) - 'A';

        for (int i = startRow; i <= endRow; i++) {
          for (int j = startColumn; j <= endColumn; j++) {
            char newC = (char) ('A' + j);
            nodes.add(newC + String.valueOf(i + 1));
            sum += grid[i][j];
            String key = newC + String.valueOf(i + 1) + ":" + (c + String.valueOf(r));
            if (count.putIfAbsent(key, 1) != null) {
              count.put(key, (count.get(key) + 1));
            }
          }
        }
      } else {
        sum += grid[Integer.parseInt(range[0].substring(1)) - 1][range[0].charAt(0) - 'A'];
        nodes.add(range[0]);
        String key = range[0] + ":" + (c + String.valueOf(r));
        if (count.putIfAbsent(key, 1) != null) {
          count.put(key, count.get(key) + 1);
        }
      }
    }
    // set value
    setValue(r, c, sum);

    // make new forward-edges
    for (String n : nodes) {
      Set<String> children = fwdEdges.get(n);
      if (children == null) {
        children = new HashSet<>();
        fwdEdges.put(n, children);
      }
      children.add(c + String.valueOf(r));
    }

    // make new back-edges
    List<String> backEdges = new ArrayList<>();
    backEdges.addAll(nodes);
    backEdge.put(c + String.valueOf(r), backEdges);
    return sum;
  }
}
