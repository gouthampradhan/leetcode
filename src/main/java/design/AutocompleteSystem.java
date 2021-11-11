package design;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 20/08/2017.
 *
 * <p>Design a search autocomplete system for a search engine. Users may input a sentence (at least
 * one word and end with a special character '#'). For each character they type except '#', you need
 * to return the top 3 historical hot sentences that have prefix the same as the part of sentence
 * already typed. Here are the specific rules:
 *
 * <p>The hot degree for a sentence is defined as the number of times a user typed the exactly same
 * sentence before. The returned top 3 hot sentences should be sorted by hot degree (The first is
 * the hottest one). If several sentences have the same degree of hot, you need to use ASCII-code
 * order (smaller one appears first). If less than 3 hot sentences exist, then just return as many
 * as you can. When the input is a special character, it means the sentence ends, and in this case,
 * you need to return an empty list. Your job is to implement the following functions:
 *
 * <p>The constructor function:
 *
 * <p>AutocompleteSystem(String[] sentences, int[] times): This is the constructor. The input is
 * historical data. Sentences is a string array consists of previously typed sentences. Times is the
 * corresponding times a sentence has been typed. Your system should record these historical data.
 *
 * <p>Now, the user wants to input a new sentence. The following function will provide the next
 * character the user types:
 *
 * <p>List<String> input(char c): The input c is the next character typed by the user. The character
 * will only be lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#').
 * Also, the previously typed sentence should be recorded in your system. The output will be the top
 * 3 historical hot sentences that have prefix the same as the part of sentence already typed.
 *
 * <p>
 *
 * <p>Example: Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"],
 * [5,3,2,2]) The system have already tracked down the following sentences and their corresponding
 * times: "i love you" : 5 times "island" : 3 times "ironman" : 2 times "i love leetcode" : 2 times
 * Now, the user begins another search:
 *
 * <p>Operation: input('i') Output: ["i love you", "island","i love leetcode"] Explanation: There
 * are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same
 * hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be
 * in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be
 * ignored.
 *
 * <p>Operation: input(' ') Output: ["i love you","i love leetcode"] Explanation: There are only two
 * sentences that have prefix "i ".
 *
 * <p>Operation: input('a') Output: [] Explanation: There are no sentences that have prefix "i a".
 *
 * <p>Operation: input('#') Output: [] Explanation: The user finished the input, the sentence "i a"
 * should be saved as a historical sentence in system. And the following input will be counted as a
 * new search.
 *
 * <p>Note: The input sentence will always start with a letter and end with '#', and only one blank
 * space will exist between two words. The number of complete sentences that to be searched won't
 * exceed 100. The length of each sentence including those in the historical data won't exceed 100.
 * Please use double-quote instead of single-quote when you write test cases even for a character
 * input. Please remember to RESET your class variables declared in class AutocompleteSystem, as
 * static/class variables are persisted across multiple test cases.
 *
 * <p>Solution: Maintain a Trie (slightly modified) data-structure to all the input sentences where
 * each node of the Trie is a node containing a hash-map of child character and node and a TreeSet
 * containing the sorted order of all the possible child sentences starting from the current node.
 * Maintain a cursor node 'curr' to indicate the current node of input, if the input character is
 * absent then simply mark curr as null indicating no further auto-complete terms possible. On the
 * other hand if the input character is present then simply pick the top 3 elements from the TreeSet
 * object of curr node. Finally, use a StringBuilder to accumulate all the input characters and when
 * a end of sentence is '#' is encountered simply update the trie with new sentence and degree.
 */
public class AutocompleteSystem {

  private Map<String, Integer> hotTextMap; // Maintain a hash-map of sentences and degree
  private Trie curr, trie, root;
  private StringBuilder
      currSentence; // StringBuilder class to maintain current input sentence (until '#')

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    String[] sentences = {"i love you", "island", "ironman", "i love leetcode"};
    int[] degree = {5, 3, 2, 2};
    AutocompleteSystem autocomplete = new AutocompleteSystem(sentences, degree);
    List<String> result = autocomplete.input('i');
    result.forEach(System.out::println);
    result = autocomplete.input(' ');
    result.forEach(System.out::println);
    result = autocomplete.input('a');
    result.forEach(System.out::println);
    result = autocomplete.input('#');
    result.forEach(System.out::println);
  }

  /**
   * Initialize the trie data-structure
   *
   * @param sentences array of sentences
   * @param times degree
   */
  public AutocompleteSystem(String[] sentences, int[] times) {
    hotTextMap = new HashMap<>();
    trie = new Trie();
    for (int i = 0; i < sentences.length; i++) {
      hotTextMap.put(sentences[i], times[i]);
      trie.insert(sentences[i]);
      trie.update(sentences[i]);
    }
    curr = trie;
    root = trie;
    currSentence = new StringBuilder();
  }

  /**
   * Accept input and return hot-text for the current char
   *
   * @param c char
   * @return List of top 3 hot-text
   */
  public List<String> input(char c) {
    List<String> result = new ArrayList<>();
    if (c == '#') {
      String sentence = currSentence.toString();
      if (hotTextMap.containsKey(sentence)) {
        // already a known sentence hence only update in necessary
        hotTextMap.put(sentence, hotTextMap.get(sentence) + 1);
        trie.update(sentence);
      } else {
        // insert a new sentence and update the degree
        hotTextMap.put(sentence, 1);
        trie.insert(sentence);
        trie.update(sentence);
      }
      currSentence = new StringBuilder(); // reset StringBuilder
      curr = root; // point to root of the trie
    } else {
      if (curr != null) {
        if (curr.containsChild(c)) {
          List<String> hotText = curr.getSubtrie(c).getTop3HotText();
          hotText
              .stream()
              .forEach((x) -> result.add(currSentence.toString() + x)); // each node only returns
          // the hot-text for the current and child nodes hence we have to attach the prefix string
          curr = curr.getSubtrie(c);
        } else {
          curr =
              null; // as soon as we encounter a empty node then set current to null indicating no
                    // further
          // auto-complete terms are available
        }
      }
      currSentence.append(c);
    }
    return result;
  }

  /** Class HotText to store the text and degree */
  private class HotText {
    private String text;
    private int degree;

    HotText(String text, int degree) {
      this.text = text;
      this.degree = degree;
    }

    private String getText() {
      return text;
    }

    private int getDegree() {
      return degree;
    }
  }

  /** Class Trie */
  private class Trie {
    private Map<Character, Trie> map = new HashMap<>();
    private TreeSet<HotText> hotText =
        new TreeSet<>(
            (HotText o1, HotText o2) -> {
              int cmp = Integer.compare(o2.getDegree(), o1.getDegree());
              return cmp == 0 ? o1.getText().compareTo(o2.getText()) : cmp;
            });

    /**
     * Get hot-text
     *
     * @return HotText
     */
    public TreeSet<HotText> getHotText() {
      return hotText;
    }

    /**
     * Return top 3 hottext
     *
     * @return hot text
     */
    private List<String> getTop3HotText() {
      List<String> hotText = new ArrayList<>();
      if (this.getHotText() != null) {
        this.getHotText().stream().limit(3).forEach((x) -> hotText.add(x.getText()));
      }
      return hotText;
    }

    /** Inserts a word into the trie. */
    private void insert(String word) {
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
     * Update hottex and degree
     *
     * @param word word or sentence
     */
    private void update(String word) {
      if (word != null) {
        update(0, word, word.length());
      }
    }

    /**
     * Update trie
     *
     * @param i curr position
     * @param word sentence
     * @param length length
     * @return HotText
     */
    private HotText update(int i, String word, int length) {
      if (i < length) {
        char c = word.charAt(i);
        Trie subTrie = map.get(c);
        HotText subTrieHotText = subTrie.update(i + 1, word, length);
        HotText currHotText =
            new HotText(
                c + (subTrieHotText == null ? "" : subTrieHotText.getText()), hotTextMap.get(word));
        updateHotText(subTrie, currHotText);
        return currHotText;
      }
      return null;
    }

    /**
     * Hot text update
     *
     * @param hotText hotText object
     */
    private void updateHotText(Trie trie, HotText hotText) {
      trie.getHotText()
          .remove(new HotText(hotText.getText(), hotText.getDegree() - 1)); // remove already
      // contained hot-text and add new
      trie.getHotText().add(hotText);
    }

    /**
     * Contains child
     *
     * @param c char
     * @return true if it contains child, false otherwise
     */
    private boolean containsChild(char c) {
      return this.map.containsKey(c);
    }

    /**
     * Return child tree
     *
     * @param c char
     * @return child subTrie
     */
    private Trie getSubtrie(char c) {
      return this.map.get(c);
    }
  }
}
