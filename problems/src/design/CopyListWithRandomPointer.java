package design;

/**
 * Created by gouthamvidyapradhan on 11/03/2017. A linked list is given such that each node contains
 * an additional random pointer which could point to any node in the list or null.
 *
 * <p>Return a deep copy of the list.
 */
public class CopyListWithRandomPointer {

  static class RandomListNode {
    int label;
    RandomListNode next, random;

    RandomListNode(int x) {
      this.label = x;
    }
  }

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    RandomListNode one = new RandomListNode(1);
    one.next = null;
    one.random = one;
    /*RandomListNode two = new RandomListNode(2);
    RandomListNode three = new RandomListNode(3);
    RandomListNode four = new RandomListNode(4);
    RandomListNode five = new RandomListNode(5);
    one.next = two;
    two.next = three;
    three.next = four;
    four.next = five;
    one.random = three;
    two.random = five;
    three.random = null;
    four.random = two;
    five.random = four;*/
    RandomListNode result = new CopyListWithRandomPointer().copyRandomList(one);
    System.out.println();
  }

  private RandomListNode copyRandomList(RandomListNode head) {
    if (head == null) return null;
    RandomListNode ite = head, next;
    while (ite != null) {
      next = ite.next;
      RandomListNode node = new RandomListNode(ite.label);
      ite.next = node;
      node.next = next;
      ite = next;
    }

    ite = head;
    while (ite != null) {
      if (ite.random != null) ite.next.random = ite.random.next;
      ite = ite.next.next;
    }

    ite = head;
    RandomListNode copyIte = ite.next, copyHead = ite.next;
    while (copyIte.next != null) {
      ite.next = copyIte.next;
      copyIte.next = ite.next.next;
      copyIte = copyIte.next;
      ite = ite.next;
    }
    ite.next = null;

    return copyHead;
  }
}
