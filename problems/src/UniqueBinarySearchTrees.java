/**
 * Created by gouthamvidyapradhan on 31/03/2017.
 * Accepted
 */
public class UniqueBinarySearchTrees
{
    int[] dp;
    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) throws Exception
    {
        System.out.println(new UniqueBinarySearchTrees().numTrees(5));
    }

    public int numTrees(int n)
    {
        dp = new int[n + 1];
        dp[0] = 1;
        return dp(n);
    }

    private int dp(int n)
    {
        if(dp[n] != 0) return dp[n];
        for(int i = 1; i <= n; i ++)
        {
            dp[n] += dp(n - i) * dp(n - (n - i) - 1);
        }
        return dp[n];
    }
}
