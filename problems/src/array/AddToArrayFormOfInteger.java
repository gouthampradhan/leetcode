package heap;
import java.math.BigInteger;
import java.util.*;
/**
 * Created by gouthamvidyapradhan on 25/07/2019
 */
public class Task1 {
  public static void main(String[] args) {
    //
  }

    public List<Integer> addToArrayForm(int[] A, int K) {
        StringBuilder sb = new StringBuilder();
        for(int a : A){
            sb.append(a);
        }
        BigInteger big  = new BigInteger(sb.toString());
        BigInteger result = big.add(BigInteger.valueOf(K));
        String resultStr = result.toString();
        List<Integer> list = new ArrayList<>();
        for(char a : resultStr.toCharArray()){
            list.add(Integer.parseInt(String.valueOf(a)));
        }
        return list;
    }
}
