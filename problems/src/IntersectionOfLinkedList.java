/**
 * Created by gouthamvidyapradhan on 24/03/2017.
 * Accepted
 */
public class IntersectionOfLinkedList
{
    static class ListNode
    {
        int val;
        ListNode next;
        ListNode(int x)
        {
            val = x;
            next = null;
        }
    }

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) throws Exception
    {
        ListNode one1 = new ListNode(1);
        ListNode one2 = new ListNode(2);
        ListNode one3 = new ListNode(3);
        ListNode one4 = new ListNode(4);
        ListNode one5 = new ListNode(5);
        one1.next = one2;
        one2.next = one3;
        one3.next = one4;
        one4.next = one5;

        ListNode two1 = new ListNode(1);
        ListNode two2 = new ListNode(2);
        ListNode two3 = new ListNode(3);
        two1.next = two2;
        two2.next = two3;
        two3.next = one5;
        ListNode node = new IntersectionOfLinkedList().getIntersectionNode(one1, two1);
        System.out.println(node.val);
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB)
    {
        ListNode first = headA;
        ListNode second = headB;
        int countA = 0, countB = 0;
        while(first != null)
        {
            first = first.next;
            countA++;
        }
        while(second != null)
        {
            second = second.next;
            countB++;
        }
        first = headA;
        second = headB;
        if(countA > countB)
        {
            int diff = countA - countB;
            countA = 0;
            while(countA < diff)
            {
                first = first.next;
                countA++;
            }

        }
        else if(countB > countA)
        {
            int diff = countB - countA;
            countB = 0;
            while(countB < diff)
            {
                second = second.next;
                countB++;
            }
        }
        ListNode join = null;
        while(first != null && second != null && !first.equals(second))
        {
            first = first.next;
            second = second.next;
        }
        if(first != null && second != null && first.equals(second))
            join = first;
        return  join;
    }

}
