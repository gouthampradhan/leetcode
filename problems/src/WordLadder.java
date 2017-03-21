import java.util.*;

/**
 * Created by gouthamvidyapradhan on 21/03/2017.
 * Accepted
 */
public class WordLadder
{

    class State
    {
        String word;
        int len;
        State(String word, int len)
        {
            this.word = word;
            this.len = len;
        }
    }

    private static Queue<State> queue = new ArrayDeque<>();
    private static Set<String> dictionary = new HashSet<>();
    private static final String CONST = "abcdefghijklmnopqrstuvwxyz";
    private static Set<String> done = new HashSet<>();
    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        List<String> list = new ArrayList<>();
        list.add("hot");
        list.add("dot");
        list.add("dog");
        list.add("lot");
        list.add("log");
        list.add("cog");
        System.out.println(new WordLadder().ladderLength("hit", "cog", list));
    }


    public int ladderLength(String beginWord, String endWord, List<String> wordList)
    {
        dictionary.addAll(wordList);
        queue.offer(new State(beginWord, 0));
        done.add(beginWord);
        while(!queue.isEmpty())
        {
            State head = queue.poll();
            if(head.word.equals(endWord))
                return head.len + 1;
            for(int i = 0, l = CONST.length(); i < l; i ++ )
            {
                StringBuilder word = new StringBuilder(head.word);
                for(int j = 0, ln = word.length(); j < ln; j ++ )
                {
                    char old = word.charAt(j);
                    word.replace(j, j + 1, String.valueOf(CONST.charAt(i)));
                    if(!done.contains(word.toString()))
                    {
                        if(dictionary.contains(word.toString()))
                        {
                            done.add(word.toString());
                            queue.offer(new State(word.toString(), head.len + 1));
                        }
                    }
                    word.replace(j, j + 1, String.valueOf(old));
                }
            }
        }
        return 0;
    }

}
