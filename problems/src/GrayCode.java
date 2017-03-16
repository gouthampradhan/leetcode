import java.util.*;

/**
 * Created by gouthamvidyapradhan on 16/03/2017.
 * Accepted
 */
public class GrayCode
{

    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        List<Integer> result = new GrayCode().grayCode(3);
    }

    public List<Integer> grayCode(int n)
    {
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i <= ((1 << n) - 1); i ++)
            result.add(i ^ (i >> 1));
        return result;
    }
}
