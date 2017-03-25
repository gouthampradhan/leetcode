import java.util.*;

/**
 * Created by gouthamvidyapradhan on 25/03/2017.
 * Accepted
 */
public class SortCharByFrequency
{
    class Freq
    {
        int i;
        int c;
    }

    private int[] buff = new int[256];
    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        System.out.println(new SortCharByFrequency().frequencySort("askdfkasdkfasdkljfklasdjfkl"));
    }

    public String frequencySort(String s)
    {
        if(s == null || s.isEmpty()) return s;
        Arrays.fill(buff, 0);
        StringBuilder sb = new StringBuilder();
        for(int i = 0, l = s.length(); i < l; i ++)
            buff[s.charAt(i)]++;

        List<Freq> fList = new ArrayList<>();
        for(int i = 0; i < 256; i ++)
        {
            if(buff[i] > 0)
            {
                Freq f = new Freq();
                f.i = i;
                f.c = buff[i];
                fList.add(f);
            }
        }

        Collections.sort(fList, (o1, o2) -> Integer.compare(o2.c, o1.c));

        for(Freq f : fList)
        {
            char c = (char)f.i;
            int freq = f.c;
            while(freq-- > 0)
                sb.append(c);
        }
        return sb.toString();
    }
}
