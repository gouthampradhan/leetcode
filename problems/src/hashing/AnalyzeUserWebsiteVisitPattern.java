package hashing;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 23/10/2019 We are given some website visits: the user with name
 * username[i] visited the website website[i] at time timestamp[i].
 *
 * <p>A 3-sequence is a list of websites of length 3 sorted in ascending order by the time of their
 * visits. (The websites in a 3-sequence are not necessarily distinct.)
 *
 * <p>Find the 3-sequence visited by the largest number of users. If there is more than one
 * solution, return the lexicographically smallest such 3-sequence.
 *
 * <p>Example 1:
 *
 * <p>Input: username = ["joe","joe","joe","james","james","james","james","mary","mary","mary"],
 * timestamp = [1,2,3,4,5,6,7,8,9,10], website =
 * ["home","about","career","home","cart","maps","home","home","about","career"] Output:
 * ["home","about","career"] Explanation: The tuples in this example are: ["joe", 1, "home"] ["joe",
 * 2, "about"] ["joe", 3, "career"] ["james", 4, "home"] ["james", 5, "cart"] ["james", 6, "maps"]
 * ["james", 7, "home"] ["mary", 8, "home"] ["mary", 9, "about"] ["mary", 10, "career"] The
 * 3-sequence ("home", "about", "career") was visited at least once by 2 users. The 3-sequence
 * ("home", "cart", "maps") was visited at least once by 1 user. The 3-sequence ("home", "cart",
 * "home") was visited at least once by 1 user. The 3-sequence ("home", "maps", "home") was visited
 * at least once by 1 user. The 3-sequence ("cart", "maps", "home") was visited at least once by 1
 * user.
 *
 * <p>Note:
 *
 * <p>3 <= N = username.length = timestamp.length = website.length <= 50 1 <= username[i].length <=
 * 10 0 <= timestamp[i] <= 10^9 1 <= website[i].length <= 10 Both username[i] and website[i] contain
 * only lowercase characters. It is guaranteed that there is at least one user who visited at least
 * 3 websites. No user visits two websites at the same time.
 */
public class AnalyzeUserWebsiteVisitPattern {
  private class VisitCount {
    String site;
    int count;

    VisitCount(String site, int count) {
      this.site = site;
      this.count = count;
    }

    public String getSite() {
      return site;
    }

    public int getCount() {
      return count;
    }
  }

  private class WebsiteTime {
    String website;
    int time;

    WebsiteTime(String website, int time) {
      this.website = website;
      this.time = time;
    }

    public int getTime() {
      return time;
    }
  }

  Map<String, Set<String>> userVisitCount;

  public static void main(String[] args) {
    String[] userName = {
      "joe", "joe", "joe", "james", "james", "james", "james", "mary", "mary", "mary"
    };
    int[] time = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    String[] website = {
      "home", "about", "career", "home", "cart", "maps", "home", "home", "about", "career"
    };
    List<String> result =
        new AnalyzeUserWebsiteVisitPattern().mostVisitedPattern(userName, time, website);
    System.out.println(result);
  }

  public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
    userVisitCount = new HashMap<>();
    for (int i = 0; i < username.length; i++) {
      for (int j = i + 1; j < username.length; j++) {
        if (username[i].equals(username[j])) {
          for (int k = j + 1; k < username.length; k++) {
            if (username[i].equals(username[j]) && username[j].equals(username[k])) {
              List<WebsiteTime> visits =
                  Arrays.asList(
                      new WebsiteTime(website[i], timestamp[i]),
                      new WebsiteTime(website[j], timestamp[j]),
                      new WebsiteTime(website[k], timestamp[k]));
              visits.sort(Comparator.comparingInt(WebsiteTime::getTime));
              String concatinatedWebsite =
                  String.join(
                      "-", visits.get(0).website, visits.get(1).website, visits.get(2).website);
              userVisitCount.putIfAbsent(concatinatedWebsite, new HashSet<>());
              userVisitCount.get(concatinatedWebsite).add(username[i]);
            }
          }
        }
      }
    }
    List<VisitCount> visitCounts = new ArrayList<>();
    for (String k : userVisitCount.keySet()) {
      visitCounts.add(new VisitCount(k, userVisitCount.get(k).size()));
    }
    visitCounts.sort(
        Comparator.comparingInt(VisitCount::getCount)
            .reversed()
            .thenComparing(VisitCount::getSite));
    VisitCount visitCount = visitCounts.get(0);
    String[] result = visitCount.getSite().split("-");
    return Arrays.asList(result[0], result[1], result[2]);
  }
}
