/**
 * Created by gouthamvidyapradhan on 17/03/2017.
 * Accepted
 */
public class BestTimeToBuyAndSellStocks
{
    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        int[] prices = {1, 1, 1, 1, 1};
        System.out.println(new BestTimeToBuyAndSellStocks().maxProfit(prices));
    }

    public int maxProfit(int[] prices)
    {
        if(prices.length == 0) return 0;
        int[] max = new int[prices.length];
        max[prices.length - 1] = prices[prices.length - 1];
        for(int i = prices.length - 2; i >= 0; i --)
        {
            max[i] = Math.max(prices[i], max[i + 1]);
        }
        int result = Integer.MIN_VALUE;
        for(int i = 0, l = max.length; i < l; i ++)
        {
            result = Math.max(result, max[i] - prices[i]);
        }
        return result;
    }
}
