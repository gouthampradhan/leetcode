import java.util.HashSet;
import java.util.Set;

/**
 * Created by pradhang on 3/28/2017.
 * Accepted
 */
public class SetMatrixZeroes
{
    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        int[][] matrix = {{0, 8, 7}, {9, 0, 8}, {9, 9, 0}};

        new SetMatrixZeroes().setZeroes(matrix);
    }

    public void setZeroes(int[][] matrix)
    {
        Set<Integer> row = new HashSet<>();
        Set<Integer> col = new HashSet<>();
        int m = matrix.length;
        int n = matrix[0].length;
        for(int i = 0; i < m; i ++)
        {
            for(int j = 0; j < n; j ++)
            {
                if(matrix[i][j] == 0)
                {
                    row.add(i);
                    col.add(j);
                }
            }
        }

        for(int r : row)
        {
            for(int j = 0; j < n; j++)
            {
                matrix[r][j] = 0;
            }
        }

        for(int c : col)
        {
            for(int i = 0; i < m; i++)
            {
                matrix[i][c] = 0;
            }
        }
    }
}
