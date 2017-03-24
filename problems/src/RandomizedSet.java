import java.util.*;

/**
 * Created by gouthamvidyapradhan on 23/03/2017.
 * Accepted
 */
public class RandomizedSet
{
    private Map<Integer, Integer> map;
    private List<Integer> list;
    private Random random;
    /** Initialize your data structure here. */
    public RandomizedSet()
    {
        map = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
    }

    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        RandomizedSet rSet = new RandomizedSet();
        System.out.println(rSet.getRandom());
        System.out.println(rSet.insert(1));
        System.out.println(rSet.insert(2));
        System.out.println(rSet.insert(2));
        System.out.println(rSet.insert(3));
        System.out.println(rSet.remove(2));
        System.out.println(rSet.insert(2));
        System.out.println(rSet.getRandom());
        System.out.println(rSet.insert(234));
        System.out.println(rSet.insert(23));
        System.out.println(rSet.insert(22));
        System.out.println(rSet.getRandom());
        System.out.println(rSet.remove(245));
        System.out.println(rSet.remove(234));
        System.out.println(rSet.getRandom());
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val)
    {
        if(!map.keySet().contains(val))
        {
            int pos = list.size();
            map.put(val, pos);
            list.add(val);
            return true;
        }
        return false;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val)
    {
        if(map.containsKey(val))
        {
            int size = list.size();
            int posVal = map.get(val);
            if(posVal < (size - 1))
            {
                int last = list.get(size - 1);
                map.put(last, posVal);
                list.set(posVal, last);
            }
            map.remove(val);
            list.remove(size - 1);
            return true;
        }
        return false;
    }

    /** Get a random element from the set. */
    public int getRandom()
    {
        /*if(list.size() == 0) return 0;
        else if (list.size() == 1) return list.get(0);*/
        return list.get(random.nextInt(list.size() - 1));
    }

}
