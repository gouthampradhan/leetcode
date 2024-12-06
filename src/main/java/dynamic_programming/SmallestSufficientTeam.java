/* (C) 2024 YourCompanyName */
package dynamic_programming;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 04/06/2020 In a project, you have a list of required skills
 * req_skills, and a list of people. The i-th person people[i] contains a list of skills that person
 * has.
 *
 * <p>Consider a sufficient team: a set of people such that for every required skill in req_skills,
 * there is at least one person in the team who has that skill. We can represent these teams by the
 * index of each person: for example, team = [0, 1, 3] represents the people with skills people[0],
 * people[1], and people[3].
 *
 * <p>Return any sufficient team of the smallest possible size, represented by the index of each
 * person.
 *
 * <p>You may return the answer in any order. It is guaranteed an answer exists.
 *
 * <p>Example 1:
 *
 * <p>Input: req_skills = ["java","nodejs","reactjs"], people =
 * [["java"],["nodejs"],["nodejs","reactjs"]] Output: [0,2] Example 2:
 *
 * <p>Input: req_skills = ["algorithms","math","java","reactjs","csharp","aws"], people =
 * [["algorithms","math","java"],["algorithms","math","reactjs"],["java","csharp","aws"],["reactjs","csharp"],["csharp","math"],["aws","java"]]
 * Output: [1,2]
 *
 * <p>Constraints:
 *
 * <p>1 <= req_skills.length <= 16 1 <= people.length <= 60 1 <= people[i].length,
 * req_skills[i].length, people[i][j].length <= 16 Elements of req_skills and people[i] are
 * (respectively) distinct. req_skills[i][j], people[i][j][k] are lowercase English letters. Every
 * skill in people[i] is a skill in req_skills. It is guaranteed a sufficient team exists.
 */
public class SmallestSufficientTeam {

  public static void main(String[] args) {
    String[] req = {"java", "nodejs", "reactjs"};
    List<List<String>> people = new ArrayList<>();
    people.add(Arrays.asList("java"));
    people.add(Arrays.asList("nodejs"));
    people.add(Arrays.asList("nodejs", "reactjs"));
    int[] R = new SmallestSufficientTeam().smallestSufficientTeam(req, people);
    for (int r : R) {
      System.out.print(r + " ");
    }
    System.out.println();
  }

  private int allSkills;
  Map<Integer, Integer> peopleSkillSet;
  Map<String, Integer> skillMap;
  final int MAX_SKILLS = 63;
  final int CHOOSE_PEOPLE = 64;

  public int[] smallestSufficientTeam(String[] reqSkills, List<List<String>> people) {
    skillMap = new HashMap<>();
    peopleSkillSet = new HashMap<>();
    int i = 0;
    for (String s : reqSkills) {
      skillMap.put(s, i++);
    }
    for (i = 0; i < people.size(); i++) {
      for (String s : people.get(i)) {
        int skillIndex = skillMap.get(s);
        int skills = peopleSkillSet.getOrDefault(i, 0);
        skills = (skills | (1 << skillIndex));
        peopleSkillSet.put(i, skills);
      }
    }
    int S = ((int) (Math.pow(2, reqSkills.length)) + 1);
    int[][] DP = new int[S][people.size()];
    allSkills = (1 << reqSkills.length) - 1;
    for (i = 0; i < DP.length; i++) {
      for (int j = 0; j < DP[0].length; j++) {
        DP[i][j] = -1;
      }
    }
    int n = dp(0, 0, DP);
    n &= MAX_SKILLS;
    if (n == Integer.MAX_VALUE) return new int[0];
    List<Integer> answer = new ArrayList<>();
    i = 0;
    for (int j = 0; j < people.size(); j++) {
      if (((DP[i][j] & MAX_SKILLS) == n) && (DP[i][j] & CHOOSE_PEOPLE) > 0) {
        i |= (peopleSkillSet.getOrDefault(j, 0));
        answer.add(j);
        n--;
      }
      if (n == 0) break;
    }
    int[] result = new int[answer.size()];
    for (int a = 0; a < result.length; a++) {
      result[a] = answer.get(a);
    }
    return result;
  }

  private int dp(int i, int skill, int[][] DP) {
    if (i >= DP[0].length) {
      if (skill >= allSkills) {
        return 0;
      } else return Integer.MAX_VALUE;
    }
    if (skill == allSkills) return 0;
    else if (DP[skill][i] != -1) return DP[skill][i];
    else {
      int withOut = dp(i + 1, skill, DP);
      int with = dp(i + 1, (skill | peopleSkillSet.getOrDefault(i, 0)), DP);
      with += with != Integer.MAX_VALUE ? 1 : 0;
      if (Math.min(with, withOut) == Integer.MAX_VALUE) {
        DP[skill][i] = Integer.MAX_VALUE;
      } else
        DP[skill][i] =
            ((with & MAX_SKILLS) < (withOut & MAX_SKILLS))
                ? ((with & MAX_SKILLS) | CHOOSE_PEOPLE)
                : (withOut & MAX_SKILLS);
      return DP[skill][i];
    }
  }
}
