import java.util.Arrays;

/**
 * Created by gouthamvidyapradhan on 22/03/2017.
 */
public class CoinChange2
{
    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        int[] coins = {1,2,5};
        System.out.println(new CoinChange2().change(5, coins));
    }

    public int change(int amount, int[] coins)
    {
        int[][] dp = new int[coins.length][amount + 1];
        for(int i = 0, l = coins.length; i < l; i ++)
            Arrays.fill(dp[i], -1);
        return dp(dp, 0, coins, coins.length, amount);
    }

    private int dp(int[][] dp, int i, int[] coins, int l, int n)
    {
        if(n == 0) return 1;
        else if(i >= l) return 0;
        if(n < 0) return 0;
        if(dp[i][n] != -1) return dp[i][n];
        dp[i][n] = dp(dp, i + 1, coins, l, n) + dp(dp, i, coins, l, n - coins[i]);
        return dp[i][n];
    }

}
