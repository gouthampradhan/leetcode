package depth_first_search;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 02/12/2017. There is a new alien language which uses the latin
 * alphabet. However, the order among letters are unknown to you. You receive a list of non-empty
 * words from the dictionary, where words are sorted lexicographically by the rules of this new
 * language. Derive the order of letters in this language.
 *
 * <p>Example 1: Given the following words in dictionary,
 *
 * <p>[ "wrt", "wrf", "er", "ett", "rftt" ] The correct order is: "wertf".
 *
 * <p>Example 2: Given the following words in dictionary,
 *
 * <p>[ "z", "x" ] The correct order is: "zx".
 *
 * <p>Example 3: Given the following words in dictionary,
 *
 * <p>[ "z", "x", "z" ] The order is invalid, so return "".
 *
 * <p>Note: You may assume all letters are in lowercase. You may assume that if a is a prefix of b,
 * then a must appear before b in the given dictionary. If the order is invalid, return an empty
 * string. There may be multiple valid order of letters, return any one of them is fine.
 *
 * <p>Solution: Build a graph with with character links and perform a topological sort. Topological
 * sort can be performed only on a DAG hence if there is a cycle immediately return empty string
 */
public class AlienDictionary {

  private Map<Character, List<Character>> graph;
  private Set<Character> done;
  private Set<Character> visited;
  private Stack<Character> toposort;

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    String[] words = {"z", "x", "z"};
    System.out.println(new AlienDictionary().alienOrder(words));
  }

  public String alienOrder(String[] words) {
    graph = new HashMap<>();
    done = new HashSet<>();
    visited = new HashSet<>();
    toposort = new Stack<>();
    boolean[] A = new boolean[26];
    for (int i = 0; i < words.length - 1; i++) {
      for (int j = 0, l = Math.min(words[i].length(), words[i + 1].length()); j < l; j++) {
        if (words[i].charAt(j) != words[i + 1].charAt(j)) {
          graph.putIfAbsent(words[i].charAt(j), new ArrayList<>());
          graph.get(words[i].charAt(j)).add(words[i + 1].charAt(j));
          break;
        }
      }
    }

    for (String w : words) {
      for (int i = 0, l = w.length(); i < l; i++) {
        A[w.charAt(i) - 'a'] = true;
      }
    }

    for (char c : graph.keySet()) {
      if (!done.contains(c)) {
        if (!dfs(c)) return "";
      }
    }

    StringBuilder sb = new StringBuilder();
    while (!toposort.isEmpty()) {
      sb.append(toposort.pop());
    }

    // Add remaining elements. This can come in any order.
    String result = sb.toString();
    StringBuilder remaining = new StringBuilder();
    for (int i = 0; i < 26; i++) {
      if (A[i]) {
        char c = (char) (i + 'a');
        boolean found = false;
        for (char r : result.toCharArray()) {
          if (r == c) {
            found = true;
            break;
          }
        }
        if (!found) {
          remaining.append(c);
        }
      }
    }
    return result.concat(remaining.toString().trim());
  }

  /**
   * Dfs to toposort
   *
   * @param u
   * @return
   */
  private boolean dfs(char u) {
    done.add(u);
    visited.add(u);
    List<Character> children = graph.get(u);
    if (children != null) {
      for (char c : children) {
        if (visited.contains(c)) return false; // check cycle
        if (!done.contains(c)) {
          boolean status = dfs(c);
          if (!status) return false;
        }
      }
    }
    toposort.push(u);
    visited.remove(u);
    return true;
  }
}
