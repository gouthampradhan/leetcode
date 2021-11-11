package stack;

import java.util.Stack;

/**
 * Created by gouthamvidyapradhan on 08/03/2017. Design a stack that supports push, pop, top, and
 * retrieving the minimum element in constant time.
 *
 * <p>push(x) -- Push element x onto stack. pop() -- Removes the element on top of the stack. top()
 * -- Get the top element. getMin() -- Retrieve the minimum element in the stack. Example: MinStack
 * minStack = new MinStack(); minStack.push(-2); minStack.push(0); minStack.push(-3);
 * minStack.getMin(); --> Returns -3. minStack.pop(); minStack.top(); --> Returns 0.
 * minStack.getMin(); --> Returns -2.
 */
public class MinStack {
  class Node {
    int value, min;

    Node(int value, int min) {
      this.value = value;
      this.min = min;
    }
  }

  private Stack<Node> stack = new Stack<>();

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    MinStack minStack = new MinStack();
    minStack.push(-2);
    minStack.push(0);
    minStack.push(-3);
    System.out.println(minStack.getMin());
    minStack.pop();
    System.out.println(minStack.top());
    System.out.println(minStack.getMin());
  }

  public MinStack() {}

  public void push(int x) {
    Node node;
    if (!stack.isEmpty()) {
      Node top = stack.peek();
      node = new Node(x, Math.min(top.min, x));
    } else {
      node = new Node(x, x);
    }
    stack.push(node);
  }

  public void pop() {
    stack.pop();
  }

  public int top() {
    return stack.peek().value;
  }

  public int getMin() {
    return stack.peek().min;
  }
}
