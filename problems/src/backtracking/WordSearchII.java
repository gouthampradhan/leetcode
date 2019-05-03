package backtracking;

import java.util.*;

/**
 * Created by pradhang on 7/4/2017. Given a 2D board and a list of words from the dictionary, find
 * all words in the board.
 *
 * <p>Each word must be constructed from letters of sequentially adjacent cell, where "adjacent"
 * cells are those horizontally or vertically neighboring. The same letter cell may not be used more
 * than once in a word.
 *
 * <p>For example, Given words = ["oath","pea","eat","rain"] and board =
 *
 * <p>[ ['o','a','a','n'], ['e','t','a','e'], ['i','h','k','r'], ['i','f','l','v'] ] Return
 * ["eat","oath"]. Note: You may assume that all inputs are consist of lowercase letters a-z.
 */
public class WordSearchII {
  private final int[] R = {0, 0, -1, 1};
  private final int[] C = {-1, 1, 0, 0};
  boolean[][] visited;
  private Set<String> dictionary;

  public static void main(String[] args) throws Exception {
    char[][] board = {
      {'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}
    };
    String[] words = {"oath", "pea", "eat", "rain"};
    System.out.println(new WordSearchII().findWords(board, words));
  }

  public List<String> findWords(char[][] board, String[] words) {
    dictionary = new HashSet<>();
    Trie trie = new Trie();
    for (String w : words) {
      trie.insert(w);
      dictionary.add(w);
    }
    visited = new boolean[board.length][board[0].length];
    Set<String> resultSet = new HashSet<>();
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        dfs(i, j, board, resultSet, trie, String.valueOf(board[i][j]));
      }
    }
    return new ArrayList<>(resultSet);
  }

  private void dfs(int r, int c, char[][] board, Set<String> result, Trie trie, String s) {
    char newChar = board[r][c];
    Trie subTrie = trie.next(newChar);
    if (subTrie == null) return;
    visited[r][c] = true;
    if (dictionary.contains(s)) result.add(s);
    for (int i = 0; i < 4; i++) {
      int newR = r + R[i];
      int newC = c + C[i];
      if (newR >= 0 && newC >= 0 && newR < board.length && newC < board[0].length) {
        if (!visited[newR][newC]) {
          dfs(newR, newC, board, result, subTrie, s + board[newR][newC]);
        }
      }
    }
    visited[r][c] = false;
  }

  private class Trie {

    private Map<Character, Trie> map;

    /** Initialize your data structure here. */
    public Trie() {
      map = new HashMap<>();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
      if (word != null) {
        add(0, word, word.length());
      }
    }

    private void add(int i, String word, int length) {
      if (i < length) {
        char c = word.charAt(i);
        Trie subTrie = map.get(c);
        if (subTrie == null) {
          subTrie = new Trie();
          map.put(c, subTrie);
        }
        subTrie.add(i + 1, word, length);
      } else map.put(null, new Trie()); // use null to indicate end of string
    }

    /**
     * Get next Trie node
     *
     * @param c char c
     * @return return Trie
     */
    public Trie next(char c) {
      return this.map.get(c);
    }
  }
}
