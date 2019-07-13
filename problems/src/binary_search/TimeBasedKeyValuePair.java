package binary_search;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 07/05/2019 Create a timebased key-value store class TimeMap,
 * that supports two operations.
 *
 * <p>1. set(string key, string value, int timestamp)
 *
 * <p>Stores the key and value, along with the given timestamp. 2. get(string key, int timestamp)
 *
 * <p>Returns a value such that set(key, value, timestamp_prev) was called previously, with
 * timestamp_prev <= timestamp. If there are multiple such values, it returns the one with the
 * largest timestamp_prev. If there are no values, it returns the empty string ("").
 *
 * <p>Example 1:
 *
 * <p>Input: inputs = ["TimeMap","set","get","get","set","get","get"], inputs =
 * [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]] Output:
 * [null,null,"bar","bar",null,"bar2","bar2"] Explanation: TimeMap kv; kv.set("foo", "bar", 1); //
 * store the key "foo" and value "bar" along with timestamp = 1 kv.get("foo", 1); // output "bar"
 * kv.get("foo", 3); // output "bar" since there is no value corresponding to foo at timestamp 3 and
 * timestamp 2, then the only value is at timestamp 1 ie "bar" kv.set("foo", "bar2", 4);
 * kv.get("foo", 4); // output "bar2" kv.get("foo", 5); //output "bar2"
 *
 * <p>Example 2:
 *
 * <p>Input: inputs = ["TimeMap","set","set","get","get","get","get","get"], inputs =
 * [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
 * Output: [null,null,null,"","high","high","low","low"]
 *
 * <p>Note:
 *
 * <p>All key/value strings are lowercase. All key/value strings have length in the range [1, 100]
 * The timestamps for all TimeMap.set operations are strictly increasing. 1 <= timestamp <= 10^7
 * TimeMap.set and TimeMap.get functions will be called a total of 120000 times (combined) per test
 * case.
 *
 * <p>Solution O(log N) where N is the number of values for the same key (but different timestamp)
 * Idea is to use the HashMap to store the unique key and a TreeMap as value containing all
 * different timestamps and value. Use the floor function in treemap to get the value for a given
 * timestamp.
 */
public class TimeBasedKeyValuePair {

  private Map<String, TreeMap<Integer, String>> map;

  public TimeBasedKeyValuePair() {
    map = new HashMap<>();
  }

  public void set(String key, String value, int timestamp) {
    map.putIfAbsent(key, new TreeMap<>());
    TreeMap<Integer, String> treeMap = map.get(key);
    treeMap.put(timestamp, value);
  }

  public String get(String key, int timestamp) {
    if (!map.containsKey(key)) {
      return "";
    } else {
      TreeMap<Integer, String> treeMap = map.get(key);
      Map.Entry<Integer, String> entry = treeMap.floorEntry(timestamp);
      if (entry == null) {
        return "";
      } else {
        return entry.getValue();
      }
    }
  }

  public static void main(String[] args) {
    TimeBasedKeyValuePair task = new TimeBasedKeyValuePair();
    task.set("foo", "bar", 1);
    System.out.println(task.get("foo", 1));
    System.out.println(task.get("foo", 3));
    System.out.println(task.get("foo", 0));
    task.set("foo", "bar2", 4);
    System.out.println(task.get("foo", 3));
    System.out.println(task.get("foo", 4));
    System.out.println(task.get("foo", 5));
  }
}
