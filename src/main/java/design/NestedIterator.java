package design;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by gouthamvidyapradhan on 30/11/2017.
 *
 * <p>Given a nested list of integers, implement an iterator to flatten it.
 *
 * <p>Each element is either an integer, or a list -- whose elements may also be integers or other
 * lists.
 *
 * <p>Example 1: Given the list [[1,1],2,[1,1]],
 *
 * <p>By calling next repeatedly until hasNext returns false, the order of elements returned by next
 * should be: [1,1,2,1,1].
 *
 * <p>Example 2: Given the list [1,[4,[6]]],
 *
 * <p>By calling next repeatedly until hasNext returns false, the order of elements returned by next
 * should be: [1,4,6].
 */
public class NestedIterator implements Iterator<Integer> {

  private List<Integer> result;
  private int curr, size;

  // This is the interface that allows for creating nested lists.
  // You should not implement it, or speculate about its implementation
  public interface NestedInteger {
    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
  }

  public NestedIterator(List<NestedInteger> nestedList) {
    this.result = new ArrayList<>();
    curr = 0;
    flatten(result, nestedList);
    size = result.size();
  }

  @Override
  public Integer next() {
    if (curr < size) {
      return result.get(curr++);
    }
    return -1;
  }

  @Override
  public boolean hasNext() {
    return curr < size;
  }

  public static void main(String[] args) {}

  private void flatten(List<Integer> flatList, List<NestedInteger> nestedList) {
    for (NestedInteger n : nestedList) {
      if (n.isInteger()) {
        flatList.add(n.getInteger());
      } else {
        flatten(flatList, n.getList());
      }
    }
  }
}
