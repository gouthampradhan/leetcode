package design;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * Created by gouthamvidyapradhan on 20/03/2017. Design and implement a data structure for Least
 * Frequently Used (LFU) cache. It should support the following operations: get and put.
 *
 * <p>get(key) - Get the value (will always be positive) of the key if the key exists in the cache,
 * otherwise return -1. put(key, value) - Set or insert the value if the key is not already present.
 * When the cache reaches its capacity, it should invalidate the least frequently used item before
 * inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more
 * keys that have the same frequency), the least recently used key would be evicted.
 *
 * <p>Follow up: Could you do both operations in O(1) time complexity?
 *
 * <p>Example:
 *
 * <p>LFUCache cache = new LFUCache( 2 /* capacity
 */
/*)

       cache.put(1, 1);
       cache.put(2, 2);
       cache.get(1);       // returns 1
       cache.put(3, 3);    // evicts key 2
       cache.get(2);       // returns -1 (not found)
       cache.get(3);       // returns 3.
       cache.put(4, 4);    // evicts key 1.
       cache.get(1);       // returns -1 (not found)
       cache.get(3);       // returns 3
       cache.get(4);       // returns 4
*/
public class LFUCache {
  private class Node {
    int frequency;
    Node prev;
    Node next;
    LinkedHashSet<Integer> hashSet;

    Node(int frequency, LinkedHashSet<Integer> hashSet) {
      this.frequency = frequency;
      this.hashSet = hashSet;
      prev = null;
      next = null;
    }
  }

  private int capacity;
  private int currentSize;
  private Map<Integer, Integer> cache;
  private Map<Integer, Node> fMap; // frequency
  private Node head;

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    LFUCache cache1 = new LFUCache(2);
    cache1.put(1, 1);
    cache1.put(2, 2);
    System.out.println(cache1.get(1));
    cache1.put(3, 3);
    System.out.println(cache1.get(2));
    // System.out.println(cache1.get(3));
    cache1.put(4, 4);
    System.out.println(cache1.get(1));
    System.out.println(cache1.get(3));
    System.out.println(cache1.get(4));
    System.out.println(cache1.get(1));
    System.out.println(cache1.get(4));
    System.out.println(cache1.get(2));
    cache1.put(4, 4);
    cache1.put(5, 4);
    cache1.put(1, 9);
    cache1.put(7, 1);
    cache1.put(4, 2);
    System.out.println(cache1.get(1));
    System.out.println(cache1.get(4));
    System.out.println(cache1.get(7));
    // System.out.println(cache1.get(3));
    // System.out.println(cache1.get(4));
  }

  public LFUCache(int capacity) {
    currentSize = 0;
    this.capacity = capacity;
    cache = new HashMap<>();
    fMap = new HashMap<>();
  }

  /**
   * Remove node and delink
   *
   * @param node Node
   */
  private void popNode(Node node) {
    if (node.prev != null && node.next != null) {
      node.prev.next = node.next;
      node.next.prev = node.prev;
    } else if (node.prev == null) {
      node.next.prev = null;
      node.next = null;
    } else {
      node.prev.next = null;
      node.prev = null;
    }
  }

  /**
   * Get value
   *
   * @param key key
   * @return value
   */
  public int get(int key) {
    if (!cache.containsKey(key)) return -1;
    fMap.put(key, update(key));
    return cache.get(key);
  }

  /**
   * Update fMap
   *
   * @param key key
   */
  private Node update(int key) {
    Node node = fMap.get(key);
    node.hashSet.remove(key);
    Node newNode;
    if (node.next == null) {
      newNode = makeNewNode(key, node.frequency + 1);
      node.next = newNode;
      newNode.prev = node;
    } else if (node.next.frequency == node.frequency + 1) {
      node.next.hashSet.add(key);
      newNode = node.next;
    } else {
      newNode = makeNewNode(key, node.frequency + 1);
      node.next.prev = newNode;
      newNode.next = node.next;
      newNode.prev = node;
      node.next = newNode;
    }
    if (node.equals(head)) incrementHead();
    else if (node.hashSet.isEmpty()) popNode(node);
    return newNode;
  }

  /**
   * Make new node
   *
   * @param key key
   * @param frequency frequency
   * @return Node
   */
  private Node makeNewNode(int key, int frequency) {
    LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
    linkedHashSet.add(key);
    return new Node(frequency, linkedHashSet);
  }

  /**
   * Add new head
   *
   * @param key key
   * @param frequency frequency
   */
  private Node addHead(int key, int frequency) {
    if (head == null) head = makeNewNode(key, frequency);
    else if (head.frequency > frequency) {
      Node node = makeNewNode(key, frequency);
      node.next = head;
      head.prev = node;
      head = node;
    } else head.hashSet.add(key);
    return head;
  }

  /** Increment head */
  private void incrementHead() {
    if (head.hashSet.isEmpty()) {
      head = head.next;
      if (head != null) {
        head.prev.next = null;
        head.prev = null;
      }
    }
  }

  /**
   * Put key value pair
   *
   * @param key key
   * @param value value
   */
  public void put(int key, int value) {
    if (capacity != 0) {
      if (cache.containsKey(key)) {
        fMap.put(key, update(key)); // update existing
        cache.put(key, value);
      } else {
        if (currentSize == capacity) {
          evict();
          cache.put(key, value);
          fMap.put(key, addHead(key, 1));
        } else {
          fMap.put(key, addHead(key, 1)); // add new head
          cache.put(key, value);
          currentSize++;
        }
      }
    }
  }

  /** Evict the node with least frequency */
  private void evict() {
    int key = head.hashSet.iterator().next();
    head.hashSet.remove(key);
    cache.remove(key);
    fMap.remove(key);
    incrementHead();
  }
}
