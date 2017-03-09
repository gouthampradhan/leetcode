import java.util.HashSet;
import java.util.Set;

/**
 * Created by gouthamvidyapradhan on 09/03/2017.
 * Accepted
 */
public class LongestSubstringWitoutRepeats
{
    Set<Character> set = new HashSet<>();
    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        System.out.println(new LongestSubstringWitoutRepeats().lengthOfLongestSubstring("asdfad"));
    }

    private int lengthOfLongestSubstring(String s)
    {
        if(s == null || s.isEmpty()) return 0;
        int count = 0;
        int max = Integer.MIN_VALUE; String subStr = "";
        for(int i = 0, l = s.length(); i < l; i ++)
        {
            char c = s.charAt(i);
            if(!set.contains(c))
            {
                set.add(c);
                subStr = subStr + c;
                max = Math.max(max, ++count);
            }
            else
            {
                int tempCnt = 0, j = 0, sl = subStr.length();
                for(; j < sl; j ++)
                {
                    tempCnt++;
                    if(subStr.charAt(j) == c) break;
                    set.remove(subStr.charAt(j));
                }
                if(j == (sl - 1))
                {
                    subStr = "";
                    count = 0;
                    set.clear();
                }
                else
                {
                    subStr = subStr.substring(j + 1, sl);
                    count = count - tempCnt;
                }
                count += 1;
                subStr = subStr + c;
                set.add(c);
            }
        }
        return max;
    }
}
