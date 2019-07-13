package stack;

/**
 * Created by gouthamvidyapradhan on 10/05/2019 Given a positive integer n, return the number of all
 * possible attendance records with length n, which will be regarded as rewardable. The answer may
 * be very large, return it after mod 109 + 7.
 *
 * <p>A student attendance record is a string that only contains the following three characters:
 *
 * <p>'A' : Absent. 'L' : Late. 'P' : Present. A record is regarded as rewardable if it doesn't
 * contain more than one 'A' (absent) or more than two continuous 'L' (late).
 *
 * <p>Example 1: Input: n = 2 Output: 8 Explanation: There are 8 records with length 2 will be
 * regarded as rewardable: "PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL" Only "AA" won't be
 * regarded as rewardable owing to more than one absent times. Note: The value of n won't exceed
 * 100,000.
 *
 * <p>Solution O(N): Start with a base case for a single days attendance record which is 'A', 'P'
 * and 'L' so, in total there are three possible records. Now, keep adding a new possible attendance
 * record to all the above three possibilities. Now, since we cannot have a combination such as 'AA'
 * we can maintain a variable with count of combinations which already has one 'A' in it and
 * similarly maintain a combination which already has two 'L's it it, only one 'L' and only 'P' in
 * it - keep incrementing the count of each of the variables when a new combination belongs to any
 * one of this. Continue upto n and sum up the all the variables and return the answer % 10 ^ 9 + 7
 */
public class StudentAttendanceRecordII {
  public static void main(String[] args) {
    System.out.println(new StudentAttendanceRecordII().checkRecord(5));
  }

  int mod = 1000000007;

  public int checkRecord(int n) {
    if (n == 0) return 1;
    int P = 1;
    int A = 1;
    int LA = 0, LX = 1, LLA = 0, LLX = 0;
    for (int i = n - 1; i > 0; i--) {
      int temP = (((P + LX) % mod) + LLX) % mod;
      int tempLX = P;
      int tempLA = A;
      int tempLLX = LX;
      int tempLLA = LA;
      int tempA =
          ((((((((((P + LX) % mod) + LLX) % mod) + A) % mod) + LA) % mod) + LLA)
              % mod); // A + LA + LLA
      // is because we can add P to each of these forming PA, PLA and PLLA
      P = temP;
      LX = tempLX;
      LA = tempLA;
      LLX = tempLLX;
      LLA = tempLLA;
      A = tempA;
    }
    return ((((((((((P + LX) % mod) + LA) % mod) + LLX) % mod) + LLA) % mod) + A) % mod);
  }
}
