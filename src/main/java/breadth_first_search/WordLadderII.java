package breadth_first_search;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 24/03/2017. Given two words (beginWord and endWord), and a
 * dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord,
 * such that:
 *
 * <p>Only one letter can be changed at a time Each transformed word must exist in the word list.
 * Note that beginWord is not a transformed word. For example,
 *
 * <p>Given: beginWord = "hit" endWord = "cog" wordList = ["hot","dot","dog","lot","log","cog"]
 * Return [ ["hit","hot","dot","dog","cog"], ["hit","hot","lot","log","cog"] ] Note: Return an empty
 * list if there is no such transformation sequence. All words have the same length. All words
 * contain only lowercase alphabetic characters. You may assume no duplicates in the word list. You
 * may assume beginWord and endWord are non-empty and are not the same.
 */
public class WordLadderII {

  private static Queue<String> queue = new ArrayDeque<>();
  private static Set<String> dictionary = new HashSet<>();
  private static final String CONST = "abcdefghijklmnopqrstuvwxyz";
  private static Map<String, Set<String>> graph = new HashMap<>();
  private static Map<String, Integer> minDistance = new HashMap<>();
  private static List<List<String>> result = new ArrayList<>();

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    List<String> dic =
        Arrays.asList(
            "kid", "tag", "pup", "ail", "tun", "woo", "erg", "luz", "brr", "gay", "sip", "kay",
            "per", "val", "mes", "ohs", "now", "boa", "cet", "pal", "bar", "die", "war", "hay",
            "eco", "pub", "lob", "rue", "fry", "lit", "rex", "jan", "cot", "bid", "ali", "pay",
            "col", "gum", "ger", "row", "won", "dan", "rum", "fad", "tut", "sag", "yip", "sui",
            "ark", "has", "zip", "fez", "own", "ump", "dis", "ads", "max", "jaw", "out", "btu",
            "ana", "gap", "cry", "led", "abe", "box", "ore", "pig", "fie", "toy", "fat", "cal",
            "lie", "noh", "sew", "ono", "tam", "flu", "mgm", "ply", "awe", "pry", "tit", "tie",
            "yet", "too", "tax", "jim", "san", "pan", "map", "ski", "ova", "wed", "non", "wac",
            "nut", "why", "bye", "lye", "oct", "old", "fin", "feb", "chi", "sap", "owl", "log",
            "tod", "dot", "bow", "fob", "for", "joe", "ivy", "fan", "age", "fax", "hip", "jib",
            "mel", "hus", "sob", "ifs", "tab", "ara", "dab", "jag", "jar", "arm", "lot", "tom",
            "sax", "tex", "yum", "pei", "wen", "wry", "ire", "irk", "far", "mew", "wit", "doe",
            "gas", "rte", "ian", "pot", "ask", "wag", "hag", "amy", "nag", "ron", "soy", "gin",
            "don", "tug", "fay", "vic", "boo", "nam", "ave", "buy", "sop", "but", "orb", "fen",
            "paw", "his", "sub", "bob", "yea", "oft", "inn", "rod", "yam", "pew", "web", "hod",
            "hun", "gyp", "wei", "wis", "rob", "gad", "pie", "mon", "dog", "bib", "rub", "ere",
            "dig", "era", "cat", "fox", "bee", "mod", "day", "apr", "vie", "nev", "jam", "pam",
            "new", "aye", "ani", "and", "ibm", "yap", "can", "pyx", "tar", "kin", "fog", "hum",
            "pip", "cup", "dye", "lyx", "jog", "nun", "par", "wan", "fey", "bus", "oak", "bad",
            "ats", "set", "qom", "vat", "eat", "pus", "rev", "axe", "ion", "six", "ila", "lao",
            "mom", "mas", "pro", "few", "opt", "poe", "art", "ash", "oar", "cap", "lop", "may",
            "shy", "rid", "bat", "sum", "rim", "fee", "bmw", "sky", "maj", "hue", "thy", "ava",
            "rap", "den", "fla", "auk", "cox", "ibo", "hey", "saw", "vim", "sec", "ltd", "you",
            "its", "tat", "dew", "eva", "tog", "ram", "let", "see", "zit", "maw", "nix", "ate",
            "gig", "rep", "owe", "ind", "hog", "eve", "sam", "zoo", "any", "dow", "cod", "bed",
            "vet", "ham", "sis", "hex", "via", "fir", "nod", "mao", "aug", "mum", "hoe", "bah",
            "hal", "keg", "hew", "zed", "tow", "gog", "ass", "dem", "who", "bet", "gos", "son",
            "ear", "spy", "kit", "boy", "due", "sen", "oaf", "mix", "hep", "fur", "ada", "bin",
            "nil", "mia", "ewe", "hit", "fix", "sad", "rib", "eye", "hop", "haw", "wax", "mid",
            "tad", "ken", "wad", "rye", "pap", "bog", "gut", "ito", "woe", "our", "ado", "sin",
            "mad", "ray", "hon", "roy", "dip", "hen", "iva", "lug", "asp", "hui", "yak", "bay",
            "poi", "yep", "bun", "try", "lad", "elm", "nat", "wyo", "gym", "dug", "toe", "dee",
            "wig", "sly", "rip", "geo", "cog", "pas", "zen", "odd", "nan", "lay", "pod", "fit",
            "hem", "joy", "bum", "rio", "yon", "dec", "leg", "put", "sue", "dim", "pet", "yaw",
            "nub", "bit", "bur", "sid", "sun", "oil", "red", "doc", "moe", "caw", "eel", "dix",
            "cub", "end", "gem", "off", "yew", "hug", "pop", "tub", "sgt", "lid", "pun", "ton",
            "sol", "din", "yup", "jab", "pea", "bug", "gag", "mil", "jig", "hub", "low", "did",
            "tin", "get", "gte", "sox", "lei", "mig", "fig", "lon", "use", "ban", "flo", "nov",
            "jut", "bag", "mir", "sty", "lap", "two", "ins", "con", "ant", "net", "tux", "ode",
            "stu", "mug", "cad", "nap", "gun", "fop", "tot", "sow", "sal", "sic", "ted", "wot",
            "del", "imp", "cob", "way", "ann", "tan", "mci", "job", "wet", "ism", "err", "him",
            "all", "pad", "hah", "hie", "aim", "ike", "jed", "ego", "mac", "baa", "min", "com",
            "ill", "was", "cab", "ago", "ina", "big", "ilk", "gal", "tap", "duh", "ola", "ran",
            "lab", "top", "gob", "hot", "ora", "tia", "kip", "han", "met", "hut", "she", "sac",
            "fed", "goo", "tee", "ell", "not", "act", "gil", "rut", "ala", "ape", "rig", "cid",
            "god", "duo", "lin", "aid", "gel", "awl", "lag", "elf", "liz", "ref", "aha", "fib",
            "oho", "tho", "her", "nor", "ace", "adz", "fun", "ned", "coo", "win", "tao", "coy",
            "van", "man", "pit", "guy", "foe", "hid", "mai", "sup", "jay", "hob", "mow", "jot",
            "are", "pol", "arc", "lax", "aft", "alb", "len", "air", "pug", "pox", "vow", "got",
            "meg", "zoe", "amp", "ale", "bud", "gee", "pin", "dun", "pat", "ten", "mob");
    new WordLadderII().findLadders("cet", "ism", dic);
  }

  public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
    dictionary.addAll(wordList);
    bfs(beginWord, endWord, wordList);
    List<String> path = new ArrayList<>();
    path.add(beginWord);
    dfs(beginWord, endWord, path);
    System.out.println(result);
    return result;
  }

  /**
   * Bfs
   *
   * @param beginWord begin word
   * @param endWord end word
   * @param wordList wordlist
   */
  private void bfs(String beginWord, String endWord, List<String> wordList) {
    queue.offer(beginWord);
    minDistance.put(beginWord, 0);
    while (!queue.isEmpty()) {
      String currWord = queue.poll();
      StringBuilder childSb = new StringBuilder(currWord);
      for (int j = 0, ln = childSb.length(); j < ln; j++) {
        for (int i = 0, l = CONST.length(); i < l; i++) {
          char old = childSb.charAt(j);
          childSb.replace(j, j + 1, String.valueOf(CONST.charAt(i)));
          String child = childSb.toString();
          if (dictionary.contains(child)) {
            if (minDistance.get(child) == null) {
              minDistance.put(child, minDistance.get(currWord) + 1);
              addChild(currWord, child);
              if (!child.equals(endWord)) queue.offer(child);
            } else {
              if (minDistance.get(child) == (minDistance.get(currWord) + 1))
                addChild(currWord, child);
            }
          }
          childSb.replace(j, j + 1, String.valueOf(old));
        }
      }
    }
  }

  /**
   * Add child
   *
   * @param parent parent
   * @param child child
   */
  private void addChild(String parent, String child) {
    Set<String> children = graph.get(parent);
    if (children == null) children = new HashSet<>();
    children.add(child);
    graph.put(parent, children);
  }

  /**
   * Dfs to build path
   *
   * @param currWord node
   * @param endWord endword
   * @param path path
   */
  private void dfs(String currWord, String endWord, List<String> path) {
    if (currWord.equals(endWord)) {
      result.add(new ArrayList<>(path));
    } else {
      Set<String> children = graph.get(currWord);
      if (children != null) {
        for (String c : children) {
          path.add(c);
          dfs(c, endWord, path);
          path.remove(path.size() - 1);
        }
      }
    }
  }
}
