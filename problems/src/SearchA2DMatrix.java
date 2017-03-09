
/**
 * Created by gouthamvidyapradhan on 09/03/2017.
 * Accepted
 */
public class SearchA2DMatrix
{
    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        int[][] matrix =
                {
                        {1,3,5,7,9} ,     //1, 3, 5, 7, 9
                        {2,4,6,8,10},     //2, 4, 6, 8, 10
                        {11,13,15,17,19}, //11, 15, 17, 18, 19
                        {12,14,16,18,20}, //13, 20, 21, 22, 23
                        {21,22,23,24,25}  //14, 25, 26, 27, 28
                };

        System.out.println(new SearchA2DMatrix().searchMatrix(matrix, 11));
    }

    private boolean searchMatrix(int[][] matrix, int target)
    {
        if(matrix.length == 0) return false;
        int M = matrix.length;
        int N = matrix[0].length;
        int r = 0, c = N - 1;
        while(r < M && c >= 0)
        {
            if(matrix[r][c] == target) return true;
            else if(target < matrix[r][c]) --c;
            else if(target > matrix[r][c]) r++;
        }
        return false;
    }
}
