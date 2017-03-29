/**
 * Created by gouthamvidyapradhan on 25/03/2017.
 * Accepted
 */
public class ThirdMaximumNumber
{
    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        int[] a = {1, 2};
        System.out.println(new ThirdMaximumNumber().thirdMax(a));
    }

    public int thirdMax(int[] nums)
    {
        long[] max =  {Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE};
        int count = 0;
        for (int num : nums) {
            for (int j = 0; j < 3; j++) {
                if (max[j] > num) continue;
                else if (max[j] == num) break;
                int k = j;
                long temp1, temp2;
                temp1 = num;
                count++;
                while (k < 3) {
                    temp2 = max[k];
                    max[k] = temp1;
                    temp1 = temp2;
                    k++;
                }
                break;
            }
        }
        System.out.println(Integer.MIN_VALUE);
        return (count >= 3)? (int)max[2] : (int)max[0];
    }
}
