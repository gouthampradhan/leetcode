/**
 * Created by gouthamvidyapradhan on 18/03/2017.
 * Accepted
 */
public class MergeTwoSortedList
{
    public static class ListNode
    {
        int val;
        ListNode next;
        ListNode(int x)
        {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) throws Exception
    {
        ListNode head = new ListNode(0);
        ListNode head1 = new ListNode(3);
        ListNode head2 = new ListNode(8);

        ListNode head3 = new ListNode(1);
        ListNode head4 = new ListNode(2);
        ListNode head5 = new ListNode(7);
        ListNode head6 = new ListNode(10);

        head.next = head1;
        head1.next = head2;

        head3.next = head4;
        head4.next = head5;
        head5.next = head6;

        ListNode newHead = new MergeTwoSortedList().mergeTwoLists(head, head3);
        ListNode link = newHead;
        while(link != null)
        {
            System.out.println(link.val);
            link = link.next;
        }
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2)
    {
        ListNode first, second;
        first = l1; second = l2;
        ListNode head = new ListNode(0);
        ListNode next = head;
        while(first != null && second != null)
        {
            if(first.val < second.val)
            {
                ListNode newNode = new ListNode(first.val);
                next.next = newNode;
                next = newNode;
                first = first.next;
            }
            else if(second.val < first.val)
            {
                ListNode newNode = new ListNode(second.val);
                next.next = newNode;
                next = newNode;
                second = second.next;
            }
            else
            {
                ListNode newNode = new ListNode(first.val);
                next.next = newNode;
                next = newNode;

                ListNode newNode1 = new ListNode(first.val);
                next.next = newNode1;
                next = newNode1;

                first = first.next;
                second = second.next;
            }
        }
        if(first != null)
        {
            while(first != null)
            {
                next.next = new ListNode(first.val);
                next = next.next;
                first = first.next;
            }
        }
        if(second != null)
        {
            while(second != null)
            {
                next.next = new ListNode(second.val);
                next = next.next;
                second = second.next;
            }
        }
        return head.next;
    }
}
