package dynamic_programming;
/**
 * Created by gouthamvidyapradhan on 28/05/2019 Given several boxes with different colors
 * represented by different positive numbers. You may experience several rounds to remove boxes
 * until there is no box left. Each time you can choose some continuous boxes with the same color
 * (composed of k boxes, k >= 1), remove them and get k*k points. Find the maximum points you can
 * get.
 *
 * <p>Example 1: Input:
 *
 * <p>[1, 3, 2, 2, 2, 3, 4, 3, 1] Output: 23 Explanation: [1, 3, 2, 2, 2, 3, 4, 3, 1] ----> [1, 3,
 * 3, 4, 3, 1] (3*3=9 points) ----> [1, 3, 3, 3, 1] (1*1=1 points) ----> [1, 1] (3*3=9 points) ---->
 * [] (2*2=4 points) Note: The number of boxes n would not exceed 100.
 */
public class RemoveBoxes {

    int[][][] dp;
    public static void main(String[] args) {
        int[] boxes = {3, 3, 3};
    System.out.println(new RemoveBoxes().removeBoxes(boxes));
    }

    public int removeBoxes(int[] boxes) {
        dp = new int[boxes.length][boxes.length][boxes.length + 1];
        return calculate(0, boxes.length - 1, 1, boxes);
    }

    int calculate(int l, int r, int rep, int[] boxes){
        if(l == r) return rep * rep;
        else if(l > r) return 0;
        else if(dp[l][r][rep] != 0) return dp[l][r][rep];
        else{
            dp[l][r][rep] = calculate(l, r - 1, 1, boxes) + (rep * rep);
            for(int i = l; i < r; i ++){
                if(boxes[i] == boxes[r]){
                    dp[l][r][rep] = Math.max(dp[l][r][rep],
                            calculate(l, i, rep + 1, boxes) + calculate(i + 1, r - 1, 1, boxes));
                }
            }
            return dp[l][r][rep];
        }
    }
}
