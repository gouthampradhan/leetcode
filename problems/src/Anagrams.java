import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gouthamvidyapradhan on 25/02/2017.
 * Accepted
 */
public class Anagrams
{
    int[] TC = new int[256];
    int[] PC = new int[256];
    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        List<Integer> result = new Anagrams().findAnagrams("abab", "ab");
        result.forEach(System.out::print);
    }

    public List<Integer> findAnagrams(String s, String p)
    {
        List<Integer> result = new ArrayList<>();
        int pLen = p.length();
        if(pLen > s.length()) return result;
        Arrays.fill(TC, 0);
        Arrays.fill(PC, 0);
        for(int i = 0; i < pLen; i ++)
        {
            TC[s.charAt(i)]++;
            PC[p.charAt(i)]++;
        }

        int i = pLen;
        for(int l = s.length(); i < l; i ++)
        {
            if(compare())
                result.add(i - pLen);

            TC[s.charAt(i)]++;
            TC[s.charAt(i - pLen)]--;
        }
        if(compare())
            result.add(i - pLen);

        return result;
    }

    private boolean compare()
    {
        for(int i = 0; i < 256; i ++)
        {
            if(TC[i] != PC[i])
                return false;
        }
        return true;
    }

}
