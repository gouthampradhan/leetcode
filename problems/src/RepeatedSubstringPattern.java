/**
 * Created by gouthamvidyapradhan on 25/03/2017.
 * Accepted
 */
public class RepeatedSubstringPattern
{
    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        System.out.println(new RepeatedSubstringPattern().repeatedSubstringPattern("a"));
    }

    public boolean repeatedSubstringPattern(String s)
    {
        boolean found;
        for(int i = 1, l = s.length(); i < l; i ++)
        {
            found = true;
            String subI = s.substring(0, i);
            int j = i;
            if(j >= l) return false;
            while(j < l)
            {
                if((j + i) >= l + 1)
                    return false;
                String subJ = s.substring(j , j + i);
                if(!subI.equals(subJ))
                {
                    found = false;
                    break;
                }
                j += i;
            }
            if(found) return true;
        }
        return false;
    }
}
