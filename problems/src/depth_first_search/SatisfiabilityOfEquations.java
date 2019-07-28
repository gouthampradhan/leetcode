package depth_first_search;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 25/07/2019 Given an array equations of strings that represent
 * relationships between variables, each string equations[i] has length 4 and takes one of two
 * different forms: "a==b" or "a!=b". Here, a and b are lowercase letters (not necessarily
 * different) that represent one-letter variable names.
 *
 * <p>Return true if and only if it is possible to assign integers to variable names so as to
 * satisfy all the given equations.
 *
 * <p>Example 1:
 *
 * <p>Input: ["a==b","b!=a"] Output: false Explanation: If we assign say, a = 1 and b = 1, then the
 * first equation is satisfied, but not the second. There is no way to assign the variables to
 * satisfy both equations. Example 2:
 *
 * <p>Input: ["b==a","a==b"] Output: true Explanation: We could assign a = 1 and b = 1 to satisfy
 * both equations. Example 3:
 *
 * <p>Input: ["a==b","b==c","a==c"] Output: true Example 4:
 *
 * <p>Input: ["a==b","b!=c","c==a"] Output: false Example 5:
 *
 * <p>Input: ["c==c","b==d","x!=z"] Output: true
 *
 * <p>Note:
 *
 * <p>1 <= equations.length <= 500 equations[i].length == 4 equations[i][0] and equations[i][3] are
 * lowercase letters equations[i][1] is either '=' or '!' equations[i][2] is '='
 *
 * <p>Solution: O(N) For all the equations which are of the form 'a==b' form a graph of connected
 * components. Start assigning values to each of the connected components. All the nodes in the
 * connected components should have the same value assigned - If any of the connected components
 * fails this criteria then return false.
 */
public class SatisfiabilityOfEquations {
  public static void main(String[] args) {
    String[] input = {"c==c", "f!=a", "f==b", "b==c"};
    System.out.println(new SatisfiabilityOfEquations().equationsPossible(input));
  }

  private Set<Character> done;
  private Map<Character, Integer> valueMap;
  private int count = 0;

  public boolean equationsPossible(String[] equations) {
    Map<Character, List<Character>> graph = new HashMap<>();
    done = new HashSet<>();
    valueMap = new HashMap<>();
    for (String eq : equations) {
      if (eq.charAt(1) == '=') {
        graph.putIfAbsent(eq.charAt(0), new ArrayList<>());
        graph.get(eq.charAt(0)).add(eq.charAt(3));
        graph.putIfAbsent(eq.charAt(3), new ArrayList<>());
        graph.get(eq.charAt(3)).add(eq.charAt(0));
      }
    }
    for (char c : graph.keySet()) {
      if (!done.contains(c)) {
        dfs(c, graph, ++count);
      }
    }

    for (String eq : equations) {
      if (eq.charAt(1) == '!') {
        char a = eq.charAt(0);
        char b = eq.charAt(3);
        if (a == b) return false;
        if (valueMap.containsKey(a) && valueMap.containsKey(b)) {
          if (valueMap.get(a).intValue() == valueMap.get(b).intValue()) {
            return false;
          }
        }
      }
    }
    return true;
  }

  private boolean dfs(char node, Map<Character, List<Character>> graph, int value) {
    done.add(node);
    valueMap.put(node, value);
    List<Character> children = graph.get(node);
    if (!children.isEmpty()) {
      for (char c : children) {
        if (!done.contains(c)) {
          boolean status = dfs(c, graph, value);
          if (!status) {
            return status;
          }
        } else {
          if (valueMap.get(c) != value) {
            return false;
          }
        }
      }
    }
    return true;
  }
}
