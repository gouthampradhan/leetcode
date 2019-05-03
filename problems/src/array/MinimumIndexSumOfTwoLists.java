package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gouthamvidyapradhan on 11/04/2018. Suppose Andy and Doris want to choose a restaurant
 * for dinner, and they both have a list of favorite restaurants represented by strings.
 *
 * <p>You need to help them find out their common interest with the least list index sum. If there
 * is a choice tie between answers, output all of them with no order requirement. You could assume
 * there always exists an answer.
 *
 * <p>Example 1: Input: ["Shogun", "Tapioca Express", "Burger King", "KFC"] ["Piatti", "The Grill at
 * Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"] Output: ["Shogun"] Explanation: The only
 * restaurant they both like is "Shogun". Example 2: Input: ["Shogun", "Tapioca Express", "Burger
 * King", "KFC"] ["KFC", "Shogun", "Burger King"] Output: ["Shogun"] Explanation: The restaurant
 * they both like and have the least index sum is "Shogun" with index sum 1 (0+1). Note: The length
 * of both lists will be in the range of [1, 1000]. The length of strings in both lists will be in
 * the range of [1, 30]. The index is starting from 0 to the list length minus 1. No duplicates in
 * both lists.
 *
 * <p>Solution:O(N) maintain index of each restaurant in a list using a HashMap, find the min sum of
 * indices and list all the restaurants which match the min sum of indices
 */
public class MinimumIndexSumOfTwoLists {
  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) throws Exception {
    String[] A1 = {"Shogun", "Tapioca Express", "Burger King", "KFC"};
    String[] A2 = {"Tapioca Express", "Shogun", "Burger King"};
    String[] ans = new MinimumIndexSumOfTwoLists().findRestaurant(A1, A2);
    for (String s : ans) {
      System.out.println(s);
    }
  }

  public String[] findRestaurant(String[] list1, String[] list2) {
    int min = Integer.MAX_VALUE;
    List<String> result = new ArrayList<>();
    if (list2.length == 0) return new String[0];
    Map<String, Integer> index = new HashMap<>();
    for (int i = 0; i < list2.length; i++) {
      index.put(list2[i], i);
    }
    for (int i = 0; i < list1.length; i++) {
      String s = list1[i];
      if (index.containsKey(s)) {
        int sum = i + index.get(s);
        min = Math.min(min, sum);
      }
    }

    for (int i = 0; i < list1.length; i++) {
      String s = list1[i];
      if (index.containsKey(s)) {
        int sum = i + index.get(s);
        if (sum == min) {
          result.add(s);
        }
      }
    }
    String[] resArr = new String[result.size()];
    result.toArray(resArr);
    return resArr;
  }
}
