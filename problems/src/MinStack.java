import java.util.Stack;

/**
 * Created by gouthamvidyapradhan on 08/03/2017.
 * Accepted
 */
public class MinStack
{
    class Node
    {
        int value, min;
        Node(int value, int min)
        {
            this.value = value;
            this.min = min;
        }
    }

    private Stack<Node> stack = new Stack<>();
    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }

    public MinStack()
    {

    }

    public void push(int x)
    {
        Node node;
        if(!stack.isEmpty())
        {
            Node top = stack.peek();
            node = new Node(x, Math.min(top.min, x));
        }
        else
        {
            node = new Node(x, x);
        }
        stack.push(node);
    }

    public void pop()
    {
        stack.pop();
    }

    public int top()
    {
        return stack.peek().value;
    }

    public int getMin()
    {
        return stack.peek().min;
    }

}
