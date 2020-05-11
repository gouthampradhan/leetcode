package array;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 05/12/2019 Given two arrays arr1 and arr2, the elements of arr2
 * are distinct, and all elements in arr2 are also in arr1.
 *
 * <p>Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in
 * arr2. Elements that don't appear in arr2 should be placed at the end of arr1 in ascending order.
 *
 * <p>Example 1:
 *
 * <p>Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6] Output: [2,2,2,1,4,3,3,9,6,7,19]
 *
 * <p>Constraints:
 *
 * <p>arr1.length, arr2.length <= 1000 0 <= arr1[i], arr2[i] <= 1000 Each arr2[i] is distinct. Each
 * arr2[i] is in arr1.
 */
public class RelativeSortArray {
  public static void main(String[] args) {
    //
  }

  public int[] relativeSortArray(int[] arr1, int[] arr2) {
    List<Integer> notPresent = new ArrayList<>();
    Map<Integer, Integer> map = new HashMap<>();
    Set<Integer> set = new HashSet<>();
    for (int i : arr2) {
      set.add(i);
    }
    for (int i : arr1) {
      map.putIfAbsent(i, 0);
      map.put(i, map.get(i) + 1);
    }
    List<Integer> result = new ArrayList<>();
    for (int i : arr2) {
      int count = map.get(i);
      for (int j = 0; j < count; j++) {
        result.add(i);
      }
    }
    for (int k : map.keySet()) {
      if (!set.contains(k)) {
        int count = map.get(k);
        for (int i = 0; i < count; i++) {
          notPresent.add(k);
        }
      }
    }
    notPresent.sort(Comparator.comparingInt(o -> o));
    result.addAll(notPresent);
    int[] resA = new int[result.size()];
    for (int i = 0; i < result.size(); i++) {
      resA[i] = result.get(i);
    }
    return resA;
  }
}
