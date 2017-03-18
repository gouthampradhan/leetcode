/**
 * Created by gouthamvidyapradhan on 18/03/2017.
 * Accepted
 */
public class RotateFunction
{
    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        int[] a = {4, 3, 2, 6};
        System.out.println(new RotateFunction().maxRotateFunction(a));
    }

    public int maxRotateFunction(int[] A)
    {
        if(A.length == 0 || A.length == 1) return 0;
        int max = Integer.MIN_VALUE;
        int l = A.length;
        int sum = 0, prodSum = 0;
        for(int i = 0; i < l; i ++)
        {
            prodSum += (A[i] * i);
            sum += A[i];
        }
        max = Math.max(max, prodSum);
        for(int i = 0; i < l - 1; i ++)
        {
            prodSum = (prodSum - sum + A[i] + ((l - 1) * A[i]));
            max = Math.max(max, prodSum);
        }
        return max;
    }
}
