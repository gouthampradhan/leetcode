package string;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 04/06/2019 Given a non-empty string containing an out-of-order
 * English representation of digits 0-9, output the digits in ascending order.
 *
 * <p>Note: Input contains only lowercase English letters. Input is guaranteed to be valid and can
 * be transformed to its original digits. That means invalid inputs such as "abc" or "zerone" are
 * not permitted. Input length is less than 50,000. Example 1: Input: "owoztneoer"
 *
 * <p>Output: "012" Example 2: Input: "fviefuro"
 *
 * <p>Output: "45"
 *
 * <p>Solution: O(N) General idea is to note some unique characters in english representation of a
 * digit such as 'x' 'x' can occur only in digit 6, similarly for 'z' it can occur only for digit 0
 * and likewise. Keep a character frequency hashmap and decrement the count as and when a new digit
 * is formed. Sort the digits and return a concatenated string.
 */
public class ReconstructOriginalDigitsFromEnglish {
  public static void main(String[] args) {
    System.out.println(
        new ReconstructOriginalDigitsFromEnglish()
            .originalDigits(
                "fviefurofviefurofviefurofviefurofviefurofviefurofviefurofviefurofviefurofviefuro"));
  }

  public String originalDigits(String s) {
    Map<Character, Integer> map = new HashMap<>();
    for (char c : s.toCharArray()) {
      map.putIfAbsent(c, 0);
      map.put(c, map.get(c) + 1);
    }
    Map<Integer, Integer> intMap = new HashMap<>();
    if (map.containsKey('x')) {
      update(map, intMap, 6, 'x', Arrays.asList('s', 'i', 'x'));
    }
    if (map.containsKey('g')) {
      update(map, intMap, 8, 'g', Arrays.asList('e', 'i', 'g', 'h', 't'));
    }
    if (map.containsKey('w')) {
      update(map, intMap, 2, 'w', Arrays.asList('t', 'w', 'o'));
    }
    if (map.containsKey('z')) {
      update(map, intMap, 0, 'z', Arrays.asList('z', 'e', 'r', 'o'));
    }
    if (map.containsKey('u')) {
      update(map, intMap, 4, 'u', Arrays.asList('f', 'o', 'u', 'r'));
    }
    if (map.containsKey('f')) {
      update(map, intMap, 5, 'f', Arrays.asList('f', 'i', 'v', 'e'));
    }
    if (map.containsKey('v')) {
      update(map, intMap, 7, 'v', Arrays.asList('s', 'e', 'v', 'e', 'n'));
    }
    if (map.containsKey('i')) {
      update(map, intMap, 9, 'i', Arrays.asList('n', 'i', 'n', 'e'));
    }
    if (map.containsKey('t')) {
      update(map, intMap, 3, 't', Arrays.asList('t', 'h', 'r', 'e', 'e'));
    }
    if (map.containsKey('o')) {
      update(map, intMap, 1, 'o', Arrays.asList('o', 'n', 'e'));
    }
    Set<Integer> keys = intMap.keySet();
    List<Integer> list = new ArrayList<>(keys);
    list.sort(Comparator.comparingInt(o -> o));
    StringBuilder sb = new StringBuilder();
    for (int i : list) {
      int count = intMap.get(i);
      while (count-- > 0) {
        sb.append(i);
      }
    }
    return sb.toString();
  }

  private void update(
      Map<Character, Integer> map,
      Map<Integer, Integer> intMap,
      int num,
      char id,
      List<Character> list) {
    if (map.containsKey(id)) {
      int count = map.get(id);
      intMap.put(num, count);
      while (count-- > 0) {
        for (char c : list) {
          map.put(c, map.get(c) - 1);
        }
      }
    }
  }
}
