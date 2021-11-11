package depth_first_search;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 27/12/2017. Given a list accounts, each element accounts[i] is
 * a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements
 * are emails representing emails of the account.
 *
 * <p>Now, we would like to merge these accounts. Two accounts definitely belong to the same person
 * if there is some email that is common to both accounts. Note that even if two accounts have the
 * same name, they may belong to different people as people could have the same name. A person can
 * have any number of accounts initially, but all of their accounts definitely have the same name.
 *
 * <p>After merging the accounts, return the accounts in the following format: the first element of
 * each account is the name, and the rest of the elements are emails in sorted order. The accounts
 * themselves can be returned in any order.
 *
 * <p>Example 1: Input: accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John",
 * "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary",
 * "mary@mail.com"]] Output: [["John", 'john00@mail.com', 'john_newyork@mail.com',
 * 'johnsmith@mail.com'], ["John", "johnnybravo@mail .com"], ["Mary", "mary@mail.com"]] Explanation:
 * The first and third John's are the same person as they have the common email
 * "johnsmith@mail.com". The second John and Mary are different people as none of their email
 * addresses are used by other accounts. We could return these lists in any order, for example the
 * answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], ['John', 'john00@mail.com',
 * 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted. Note:
 *
 * <p>The length of accounts will be in the range [1, 1000]. The length of accounts[i] will be in
 * the range [1, 10]. The length of accounts[i][j] will be in the range [1, 30].
 *
 * <p>Solution: Consider each email_address as a vertex and link the emails of each account as
 * bidirectional edges and perform a dfs on connected components and return the email addresses of
 * connected components.
 */
public class AccountsMerge {

  private class Account {
    String email, name;

    Account(String email, String name) {
      this.email = email;
      this.name = name;
    }
  }

  private Map<Integer, Account> numMap; // for simplicity mapping each email to a unique integer
  private Map<String, Integer> emailMap;
  private Map<Integer, List<Integer>> graph;
  private BitSet done;
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    List<String> account1 = new ArrayList<>();
    List<List<String>> accounts = new ArrayList<>();
    account1.add("John");
    account1.add("johnsmith@mail.com");
    account1.add("john00@mail.com");
    accounts.add(account1);

    List<String> account2 = new ArrayList<>();
    account2.add("John");
    account2.add("johnnybravo@mail.com");
    accounts.add(account2);

    List<String> account3 = new ArrayList<>();
    account3.add("John");
    account3.add("johnsmith@mail.com");
    account3.add("john_newyork@mail.com");
    accounts.add(account3);

    List<String> account4 = new ArrayList<>();
    account4.add("Mary");
    account4.add("mary@mail.com");
    accounts.add(account4);

    List<List<String>> result = new AccountsMerge().accountsMerge(accounts);
    System.out.println(result);
  }

  public List<List<String>> accountsMerge(List<List<String>> accounts) {
    done = new BitSet();
    numMap = new HashMap<>();
    emailMap = new HashMap<>();
    graph = new HashMap<>();
    int index = -1;
    for (List<String> account : accounts) {
      String name = account.get(0);
      int prev = -1;
      for (int i = 1, l = account.size(); i < l; i++) {
        String email = account.get(i);
        int vertex;
        if (!emailMap.containsKey(email)) {
          vertex = ++index;
          emailMap.put(email, vertex);
          numMap.put(vertex, new Account(email, name));
        } else {
          vertex = emailMap.get(email);
        }
        graph.putIfAbsent(vertex, new ArrayList<>());
        if (i != 1) {
          // make bi-directional link
          graph.get(prev).add(vertex);
          graph.get(vertex).add(prev);
        }
        prev = vertex;
      }
    }
    List<List<String>> result = new ArrayList<>();
    for (int i : graph.keySet()) {
      if (!done.get(i)) {
        List<Integer> list = new ArrayList<>();
        List<String> account = new ArrayList<>();
        dfs(i, list);
        list.stream().forEach(x -> account.add(numMap.get(x).email));
        account.sort(String::compareTo);

        // Add account user name
        Account acc = numMap.get(list.get(0));
        account.add(0, acc.name);
        result.add(account);
      }
    }
    return result;
  }

  private void dfs(int i, List<Integer> list) {
    done.set(i);
    list.add(i);
    List<Integer> children = graph.get(i);
    if (children != null) {
      for (int c : children) {
        if (!done.get(c)) {
          dfs(c, list);
        }
      }
    }
  }
}
