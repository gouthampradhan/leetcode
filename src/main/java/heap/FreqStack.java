package heap;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 30/04/2019 Implement FreqStack, a class which simulates the
 * operation of a stack-like data structure.
 *
 * <p>FreqStack has two functions:
 *
 * <p>push(int x), which pushes an integer x onto the stack. pop(), which removes and returns the
 * most frequent element in the stack. If there is a tie for most frequent element, the element
 * closest to the top of the stack is removed and returned.
 *
 * <p>Example 1:
 *
 * <p>Input: ["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
 * [[],[5],[7],[5],[7],[4],[5],[],[],[],[]] Output: [null,null,null,null,null,null,null,5,7,5,4]
 * Explanation: After making six .push operations, the stack is [5,7,5,7,4,5] from bottom to top.
 * Then:
 *
 * <p>pop() -> returns 5, as 5 is the most frequent. The stack becomes [5,7,5,7,4].
 *
 * <p>pop() -> returns 7, as 5 and 7 is the most frequent, but 7 is closest to the top. The stack
 * becomes [5,7,5,4].
 *
 * <p>pop() -> returns 5. The stack becomes [5,7,4].
 *
 * <p>pop() -> returns 4. The stack becomes [5,7].
 *
 * <p>Note:
 *
 * <p>Calls to FreqStack.push(int x) will be such that 0 <= x <= 10^9. It is guaranteed that
 * FreqStack.pop() won't be called if the stack has zero elements. The total number of
 * FreqStack.push calls will not exceed 10000 in a single test case. The total number of
 * FreqStack.pop calls will not exceed 10000 in a single test case. The total number of
 * FreqStack.push and FreqStack.pop calls will not exceed 150000 across all test cases.
 *
 * <p>Solution: push O(log N) pop O(log N) Maintain a priority queue with a FreqNode where each
 * FreqNode contains a frequency and a stack with (value and pushCount). Each stack in a priority
 * queue contains same set of values. Every time when a value is to be added to the stack a new Node
 * is created and pushed to stack which contains the push value and pushCount.
 *
 * <p>Example: For the below push operation push 5, push 5, push 5, push 6, push 6, push 7 the state
 * of priority stack will be 3 : 5(0) -> 5(1) -> 5(2) 2 : 6(3) -> 6(4) 1 : 7(5)
 *
 * <p>When a push operation is invoked we have to identify in which stack the value has to go in
 * therefore maintain a hashmap with push-value as key and value contains the reference to FreqNode
 * in priority queue. Remove this FreqNode from priority queue and update the stack.
 *
 * <p>When a pop operation is invoked remove the FreqNode from the top of the priority queue and pop
 * the value from top of its stack.
 */
public class FreqStack {

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    FreqStack freqStack = new FreqStack();
    freqStack.push(5);
    freqStack.push(5);
    freqStack.push(5);
    freqStack.push(5);
    freqStack.push(6);
    System.out.println(freqStack.pop());
    freqStack.push(7);
    System.out.println(freqStack.pop());
    System.out.println(freqStack.pop());
    freqStack.push(1);
    freqStack.push(2);
    System.out.println(freqStack.pop());
    System.out.println(freqStack.pop());
    System.out.println(freqStack.pop());
    System.out.println(freqStack.pop());
    System.out.println(freqStack.pop());
  }

  class Node {
    int val, pos;

    Node(int val, int pushCount) {
      this.val = val;
      this.pos = pushCount;
    }
  }

  class FreqNode {
    int freq;
    Stack<Node> stack;

    FreqNode(int freq, Stack<Node> stack) {
      this.freq = freq;
      this.stack = stack;
    }

    public int getFreq() {
      return freq;
    }

    public int getTop() {
      return !stack.isEmpty() ? stack.peek().pos : -1;
    }

    public void push(Node e) {
      freq++;
      stack.push(e);
    }

    public Node pop() {
      freq--;
      return stack.pop();
    }
  }

  private PriorityQueue<FreqNode> priorityQueue;
  private Map<Integer, FreqNode> map;
  private int pushCount;

  public FreqStack() {
    priorityQueue =
        new PriorityQueue<>(
            (o1, o2) -> {
              if (o1.freq == o2.freq) {
                return Integer.compare(o2.getTop(), o1.getTop());
              } else {
                return Integer.compare(o2.freq, o1.freq);
              }
            });
    map = new HashMap<>();
    pushCount = 0;
  }

  public void push(int x) {
    pushCount++;
    Node node = new Node(x, pushCount);
    FreqNode freqNode;
    if (map.containsKey(x)) {
      freqNode = map.get(x);
      priorityQueue.remove(freqNode);
      freqNode.push(node);
    } else {
      Stack<Node> stack = new Stack<>();
      stack.push(node);
      freqNode = new FreqNode(1, stack);
      map.put(x, freqNode);
    }
    priorityQueue.offer(freqNode);
  }

  public int pop() {
    FreqNode freqNode = priorityQueue.poll();
    Node topNode = freqNode.pop();
    if (freqNode.freq == 0) {
      map.remove(topNode.val);
    } else {
      priorityQueue.offer(freqNode);
    }
    return topNode.val;
  }
}
