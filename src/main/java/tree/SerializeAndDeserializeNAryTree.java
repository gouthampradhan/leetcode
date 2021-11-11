package tree;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by gouthamvidyapradhan on 16/03/2019 Serialization is the process of converting a data
 * structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
 * or transmitted across a network connection link to be reconstructed later in the same or another
 * computer environment.
 *
 * <p>Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree
 * in which each node has no more than N children. There is no restriction on how your
 * serialization/deserialization algorithm should work. You just need to ensure that an N-ary tree
 * can be serialized to a string and this string can be deserialized to the original tree structure.
 *
 * <p>For example, you may serialize the following 3-ary tree
 *
 * <p>1 /|\ 3 2 4 /\ 5 6
 *
 * <p>as [1 [3[5 6] 2 4]]. You do not necessarily need to follow this format, so please be creative
 * and come up with different approaches yourself.
 *
 * <p>Note:
 *
 * <p>N is in the range of [1, 1000] Do not use class member/global/static variables to store
 * states. Your serialize and deserialize algorithms should be stateless.
 *
 * <p>Solution: To encode recursively iterate through each node and build a root and its children as
 * 3[5,6] where 3 is the root and 5, 6 are its children. To decode, build the root node first and
 * then recursively build its children.
 */
public class SerializeAndDeserializeNAryTree {

  static class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val, List<Node> _children) {
      val = _val;
      children = _children;
    }
  }

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    Node n1 = new Node(5, new ArrayList<>());
    Node n2 = new Node(6, Arrays.asList(n1));
    Node n3 = new Node(2, Arrays.asList(n2));
    Node n4 = new Node(4, Arrays.asList(n3));
    Node n5 = new Node(3, Arrays.asList(n4));
    Node root = new Node(1, Arrays.asList(n5));
    SerializeAndDeserializeNAryTree serializer = new SerializeAndDeserializeNAryTree();
    String result = serializer.serialize(root);
    Node rootNode = serializer.deserialize(result);
    System.out.println(result);
    System.out.println(rootNode);
  }

  // Encodes a tree to a single string.
  public String serialize(Node root) {
    if (root != null) {
      String curr = String.valueOf(root.val);
      List<Node> children = root.children;
      return children != null
          ? curr
              + "["
              + children.stream().map(this::serialize).collect(Collectors.joining(","))
              + "]"
          : curr + "[]";
    } else {
      return "";
    }
  }

  // Decodes your encoded data to tree.
  public Node deserialize(String data) {
    char[] arr = data.toCharArray();
    StringBuilder num = new StringBuilder();
    Queue<String> queue = new ArrayDeque<>();
    for (char c : arr) {
      if (c >= '0' && c <= '9') {
        num.append(c);
      } else if (c == '[') {
        if (num.length() != 0) {
          queue.offer(num.toString());
          num = new StringBuilder();
        }
        queue.offer("[");
      } else {
        queue.offer(String.valueOf(c));
      }
    }
    if (queue.isEmpty()) return null;
    return decode(queue).get(0);
  }

  private List<Node> decode(Queue<String> elements) {
    List<Node> children = new ArrayList<>();
    while (!elements.isEmpty()) {
      String curr = elements.poll();
      if (curr.equals("[") || curr.equals(",")) {
      } else if (curr.equals("]")) {
        return children;
      } else {
        int num = Integer.parseInt(curr);
        Node currNode = new Node(num, decode(elements));
        children.add(currNode);
      }
    }
    return children;
  }
}
