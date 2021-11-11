package depth_first_search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gouthamvidyapradhan on 18/01/2018. Clone an undirected graph. Each node in the graph
 * contains a label and a list of its neighbors.
 *
 * <p>OJ's undirected graph serialization: Nodes are labeled uniquely.
 *
 * <p>We use # as a separator for each node, and , as a separator for node label and each neighbor
 * of the node. As an example, consider the serialized graph {0,1,2#1,2#2,2}.
 *
 * <p>The graph has a total of three nodes, and therefore contains three parts as separated by #.
 *
 * <p>First node is labeled as 0. Connect node 0 to both nodes 1 and 2. Second node is labeled as 1.
 * Connect node 1 to node 2. Third node is labeled as 2. Connect node 2 to node 2 (itself), thus
 * forming a self-cycle. Visually, the graph looks like the following:
 *
 * <p>1 / \ / \ 0 --- 2 / \ \_/
 *
 * <p>Solution: O(V + E) maintain a hashmap of reference nodes and build the graph by dfs
 */
public class CloneGraph {

  static class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;

    UndirectedGraphNode(int x) {
      label = x;
      neighbors = new ArrayList<>();
    }
  }

  private Map<Integer, UndirectedGraphNode> map;
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    UndirectedGraphNode node = new UndirectedGraphNode(0);
    UndirectedGraphNode node1 = new UndirectedGraphNode(1);
    UndirectedGraphNode node2 = new UndirectedGraphNode(2);
    node.neighbors.add(node1);
    node.neighbors.add(node2);

    node1.neighbors.add(node);
    node1.neighbors.add(node2);

    node2.neighbors.add(node);
    node2.neighbors.add(node1);
    node2.neighbors.add(node2);
    UndirectedGraphNode result = new CloneGraph().cloneGraph(node);
    // print result
  }

  public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    if (node == null) return null;
    map = new HashMap<>();
    UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
    dfs(node, clone);
    return clone;
  }

  private void dfs(UndirectedGraphNode original, UndirectedGraphNode clone) {
    map.put(clone.label, clone);
    List<UndirectedGraphNode> oChildren = original.neighbors; // original child nodes
    List<UndirectedGraphNode> cChildren = clone.neighbors; // clone child nodes
    for (UndirectedGraphNode oChild : oChildren) {
      if (map.containsKey(oChild.label)) {
        // already visited node
        cChildren.add(map.get(oChild.label));
      } else {
        // a new node
        UndirectedGraphNode newChildClone = new UndirectedGraphNode(oChild.label);
        cChildren.add(newChildClone);
        dfs(oChild, newChildClone);
      }
    }
  }
}
