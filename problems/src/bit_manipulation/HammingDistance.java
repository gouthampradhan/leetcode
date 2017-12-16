package bit_manipulation;

/**
 * Created by gouthamvidyapradhan on 16/12/2017.
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

 Given two integers x and y, calculate the Hamming distance.

 Note:
 0 ≤ x, y < 231.

 Example:

 Input: x = 1, y = 4

 Output: 2

 Explanation:
 1   (0 0 0 1)
 4   (0 1 0 0)
 ↑   ↑

 The above arrows point to positions where the corresponding bits are different.

 Solution O(1): XOR (x, y) and count the number of bits set

 */
public class HammingDistance {

    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{

    }

    public int hammingDistance(int x, int y) {
        int z = (x ^ y);
        int count = 0;
        for(int i = 0; i < 31; i++){
            if((z & (1 << i)) > 0){
                count++;
            }
        }
        return count;
    }
}
