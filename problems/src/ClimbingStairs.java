/**
 * Created by gouthamvidyapradhan on 01/04/2017.
 */
public class ClimbingStairs
{
    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {

    }

    public int climbStairs(int n)
    {
        if(n == 0 || n == 1) return 1;
        int[] A = new int[n + 1];
        A[n] = 1;
        A[n - 1] = 1;
        for(int i = n - 2; i >= 0; i --)
            A[i] = A[i + 1] +  A[i + 2];
        return A[0];
    }

}
