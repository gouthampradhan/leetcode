package stack;

import java.util.Stack;

/**
 * Created by gouthamvidyapradhan on 29/07/2017. Implement the following operations of a queue using
 * stacks.
 *
 * <p>push(x) -- Push element x to the back of queue. pop() -- Removes the element from in front of
 * queue. peek() -- Get the front element. empty() -- Return whether the queue is empty.
 *
 * <p>Notes: You must use only standard operations of a stack -- which means only push to top,
 * peek/pop from top, size, and is empty operations are valid. Depending on your language, stack may
 * not be supported natively. You may simulate a stack by using a list or deque (double-ended
 * queue), as long as you use only standard operations of a stack. You may assume that all
 * operations are valid (for example, no pop or peek operations will be called on an empty queue).
 */
public class MyQueue {

  private Stack<Integer> stack;

  public static void main(String[] args) throws Exception {
    MyQueue myQueue = new MyQueue();
    myQueue.push(5);
    myQueue.push(12);
    myQueue.push(7);
    myQueue.push(9);
    System.out.println(myQueue.peek());
    System.out.println(myQueue.pop());
    myQueue.push(56);
    myQueue.push(53);
    System.out.println(myQueue.pop());
  }

  /** Initialize your data structure here. */
  public MyQueue() {
    stack = new Stack<>();
  }

  /** Push element x to the back of queue. */
  public void push(int x) {
    stack.push(x);
  }

  /** Removes the element from in front of queue and returns that element. */
  public int pop() {
    Stack<Integer> auxStack = new Stack<>();
    while (!stack.isEmpty()) {
      auxStack.push(stack.pop());
    }
    int result = auxStack.pop();
    while (!auxStack.isEmpty()) {
      stack.push(auxStack.pop());
    }
    return result;
  }

  /** Get the front element. */
  public int peek() {
    Stack<Integer> auxStack = new Stack<>();
    while (!stack.isEmpty()) {
      auxStack.push(stack.pop());
    }
    int result = auxStack.peek();
    while (!auxStack.isEmpty()) {
      stack.push(auxStack.pop());
    }
    return result;
  }

  /** Returns whether the queue is empty. */
  public boolean empty() {
    return stack.isEmpty();
  }
}
