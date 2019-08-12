package hashing;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 06/08/2019 Given a chemical formula (given as a string), return
 * the count of each atom.
 *
 * <p>An atomic element always starts with an uppercase character, then zero or more lowercase
 * letters, representing the name.
 *
 * <p>1 or more digits representing the count of that element may follow if the count is greater
 * than 1. If the count is 1, no digits will follow. For example, H2O and H2O2 are possible, but
 * H1O2 is impossible.
 *
 * <p>Two formulas concatenated together produce another formula. For example, H2O2He3Mg4 is also a
 * formula.
 *
 * <p>A formula placed in parentheses, and a count (optionally added) is also a formula. For
 * example, (H2O2) and (H2O2)3 are formulas.
 *
 * <p>Given a formula, output the count of all elements as a string in the following form: the first
 * name (in sorted order), followed by its count (if that count is more than 1), followed by the
 * second name (in sorted order), followed by its count (if that count is more than 1), and so on.
 *
 * <p>Example 1: Input: formula = "H2O" Output: "H2O" Explanation: The count of elements are {'H':
 * 2, 'O': 1}. Example 2: Input: formula = "Mg(OH)2" Output: "H2MgO2" Explanation: The count of
 * elements are {'H': 2, 'Mg': 1, 'O': 2}. Example 3: Input: formula = "K4(ON(SO3)2)2" Output:
 * "K4N2O14S4" Explanation: The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}. Note:
 *
 * <p>All atom names consist of lowercase letters, except for the first character which is
 * uppercase. The length of formula will be in the range [1, 1000]. formula will only consist of
 * letters, digits, and round parentheses, and is a valid formula as defined in the problem.
 *
 * <p>Solution O(N ^ 2) Recursively solve each substring within round braces as subformula. Consider
 * each subformula as right subformula and each each subformula before the round braces as left
 * subformula - sum up value of both left and right subformula to get the answer.
 */
public class NumberOfAtoms {
  public static void main(String[] args) {
    String result = new NumberOfAtoms().countOfAtoms("K4(((K4)K4)2)2");
    System.out.println(result);
  }

  public String countOfAtoms(String formula) {
    Map<String, Integer> atomCountResult = new NumberOfAtoms().countOfAtoms(formula, 0);
    List<String> sortedKeys = new ArrayList<>(atomCountResult.keySet());
    sortedKeys.sort(Comparator.naturalOrder());
    StringBuilder result = new StringBuilder();
    for (String k : sortedKeys) {
      int count = atomCountResult.get(k);
      if (count > 1) {
        result.append(k).append(count);
      } else result.append(k);
    }
    return result.toString();
  }

  private Map<String, Integer> countOfAtoms(String formula, int startPos) {
    Map<String, Integer> left = new HashMap<>();
    StringBuilder atom = new StringBuilder();
    StringBuilder atomCount = new StringBuilder();
    for (int i = startPos; i < formula.length(); ) {
      char c = formula.charAt(i);
      if (c >= 'A' && c <= 'Z') {
        if (atom.length() > 0) {
          int count = 1;
          if (atomCount.length() > 0) {
            count = Integer.parseInt(atomCount.toString());
          }
          String atomKey = atom.toString();
          if (left.containsKey(atomKey)) {
            left.put(atomKey, left.get(atomKey) + count);
          } else left.put(atom.toString(), count);
          atom = new StringBuilder();
          atomCount = new StringBuilder();
        }
        atom.append(c);
        i++;
      } else if (c >= 'a' && c <= 'z') {
        atom.append(c);
        i++;
      } else if (c >= '0' && c <= '9') {
        atomCount.append(c);
        i++;
      } else {
        // this is equal to '('
        if (atom.length() > 0) {
          int count = 1;
          if (atomCount.length() > 0) {
            count = Integer.parseInt(atomCount.toString());
          }
          String atomKey = atom.toString();
          if (left.containsKey(atomKey)) {
            left.put(atomKey, left.get(atomKey) + count);
          } else left.put(atom.toString(), count);
          atom = new StringBuilder();
          atomCount = new StringBuilder();
        }
        int j = i, count = 0;
        for (int l = formula.length(); j < l; j++) {
          if (formula.charAt(j) == '(') {
            count++;
          } else if (formula.charAt(j) == ')') {
            count--;
          }
          if (count == 0) break;
        }
        Map<String, Integer> right = countOfAtoms(formula.substring(i + 1, j), 0);
        j++;
        StringBuilder rightAtomCount = new StringBuilder();
        for (int l = formula.length(); j < l; j++) {
          if (formula.charAt(j) >= '0' && formula.charAt(j) <= '9') {
            rightAtomCount.append(formula.charAt(j));
          } else break;
        }
        if (rightAtomCount.length() > 0) {
          int mulFactor = Integer.parseInt(rightAtomCount.toString());
          for (String k : right.keySet()) {
            right.put(k, right.get(k) * mulFactor);
          }
        }
        left = merge(left, right);
        i = j;
      }
    }
    if (atom.length() > 0) {
      int count = 1;
      if (atomCount.length() > 0) {
        count = Integer.parseInt(atomCount.toString());
      }
      String atomKey = atom.toString();
      if (left.containsKey(atomKey)) {
        left.put(atomKey, left.get(atomKey) + count);
      } else left.put(atom.toString(), count);
    }
    return left;
  }

  private Map<String, Integer> merge(Map<String, Integer> left, Map<String, Integer> right) {
    for (String k : left.keySet()) {
      if (right.containsKey(k)) {
        right.put(k, right.get(k) + left.get(k));
      } else right.put(k, left.get(k));
    }
    return right;
  }
}
