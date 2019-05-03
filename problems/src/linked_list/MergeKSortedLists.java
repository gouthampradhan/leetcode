package linked_list;

/**
 * Created by gouthamvidyapradhan on 11/03/2017. Merge k sorted linked lists and return it as one
 * sorted list. Analyze and describe its complexity.
 */
public class MergeKSortedLists {
  public static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }
  }

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    ListNode node2 = new ListNode(-1);
    ListNode node3 = new ListNode(5);
    ListNode node4 = new ListNode(11);

    ListNode node6 = new ListNode(6);
    ListNode node10 = new ListNode(10);

    /*ListNode node1 = new ListNode(2);
    ListNode node5 = new ListNode(3);
    ListNode node7 = new ListNode(7);
    ListNode node26 = new ListNode(26);
    ListNode node111 = new ListNode(111);


    ListNode node11 = new ListNode(1);
    ListNode node9 = new ListNode(9);
    ListNode node10 = new ListNode(10);
    ListNode node12 = new ListNode(12);
    ListNode node119 = new ListNode(119);
    */

    node2.next = node3;
    node3.next = node4;

    node6.next = node10;

    /*node1.next = node5;
    node5.next = node7;
    node7.next = node26;
    node26.next = node111;

    node11.next = node9;
    node9.next = node10;
    node10.next = node12;
    node12.next = node119;*/
    ListNode[] list = new ListNode[4];
    list[0] = null;
    list[1] = node2;
    list[2] = null;
    list[3] = node6;
    ListNode result = new MergeKSortedLists().mergeKLists(list);
  }

  public ListNode mergeKLists(ListNode[] lists) {
    if (lists.length == 0) return null;
    if (lists.length == 1) return lists[0];
    return merge(lists, 0, lists.length - 1);
  }

  private ListNode merge(ListNode[] lists, int s, int e) {
    if (s == e) return lists[s];
    int m = s + (e - s) / 2;
    ListNode left = merge(lists, s, m);
    ListNode right = merge(lists, m + 1, e);
    ListNode prev, temp;
    ListNode headNode = new ListNode(0);
    headNode.next = left;
    prev = headNode;
    if (left == null && right == null) return null;
    else if (left == null) return right;
    else if (right == null) return left;
    while (left != null && right != null) {
      if (left.val > right.val) {
        temp = right;
        right = right.next;
        prev.next = temp;
        temp.next = left;
        prev = prev.next;
      } else {
        left = left.next;
        prev = prev.next;
      }
    }
    if (left == null && right != null) prev.next = right;
    return headNode.next;
  }
}
