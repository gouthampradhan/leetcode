/**
 * Created by gouthamvidyapradhan on 22/02/2017.
 * Accepted
 */
public class Test
{
    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        int[] n = {2, 12, 12};
        System.out.println(summation(n));
    }

    static void StairCase(int n)
    {
        for(int i = 1; i <= n; i ++)
        {
            for(int k = 0; k < (n - i); k++)
            {
                System.out.print(" ");
            }
            for(int j = 0; j < i; j++)
            {
                System.out.print("#");
            }
            System.out.println();
        }

    }

    static int summation(int[] numbers)
    {
        int n = numbers[0];
        int sum = 0;
        for(int i = 1; i <= n; i ++)
        {
            sum += numbers[i];
        }
        return sum;
    }
}
