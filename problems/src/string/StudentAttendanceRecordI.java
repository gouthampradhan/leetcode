package string;

/**
 * Created by gouthamvidyapradhan on 10/05/2019 You are given a string representing an attendance
 * record for a student. The record only contains the following three characters: 'A' : Absent. 'L'
 * : Late. 'P' : Present. A student could be rewarded if his attendance record doesn't contain more
 * than one 'A' (absent) or more than two continuous 'L' (late).
 *
 * <p>You need to return whether the student could be rewarded according to his attendance record.
 *
 * <p>Example 1: Input: "PPALLP" Output: True Example 2: Input: "PPALLL" Output: False
 *
 * <p>Solution O(N) Simple linear check
 */
public class StudentAttendanceRecordI {
  public static void main(String[] args) {}

  public boolean checkRecord(String s) {
    int count = 0;
    for (int c : s.toCharArray()) {
      if (c == 'A') {
        count++;
      }
      if (count > 1) return false;
    }
    if (s.contains("LLL")) return false;
    return true;
  }
}
