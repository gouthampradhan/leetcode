package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 19/11/2019 You have some coins. The i-th coin has a probability
 * prob[i] of facing heads when tossed.
 *
 * <p>Return the probability that the number of coins facing heads equals target if you toss every
 * coin exactly once.
 *
 * <p>Example 1:
 *
 * <p>Input: prob = [0.4], target = 1 Output: 0.40000 Example 2:
 *
 * <p>Input: prob = [0.5,0.5,0.5,0.5,0.5], target = 0 Output: 0.03125
 */
public class TossStrangeCoins {
  public static void main(String[] args) {
    double[] A = {0.4, 0.4};
    System.out.println(new TossStrangeCoins().probabilityOfHeads(A, 1));
  }

  public double probabilityOfHeads(double[] prob, int target) {
    double[][] DP = new double[target + 1][prob.length];
    DP[0][0] = 1 - prob[0];
    DP[1][0] = prob[0];
    for (int c = 1; c < prob.length; c++) {
      for (int t = 0; t <= target; t++) {
        if (t == 0) DP[t][c] = DP[t][c - 1] * (1 - prob[c]);
        else DP[t][c] = DP[t][c - 1] * (1 - prob[c]) + DP[t - 1][c - 1] * (prob[c]);
      }
    }
    return DP[target][prob.length - 1];
  }
}
