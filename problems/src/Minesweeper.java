/**
 * Created by pradhang on 3/28/2017.
 * Accepted
 */
public class Minesweeper
{
    private static final int[] R = {1, 1, 1, 0, 0, -1, -1, -1};
    private static final int[] C = {-1, 0, 1, -1, 1, -1, 0, 1};
    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        char[][] board = {{'E','E','E','E','E'},{'E','E','M','E','E'},{'E','E','E','E','E'},{'E','E','E','E','E'}};
        int[] click = {3, 0};
        new Minesweeper().updateBoard(board, click);
        for (int i = 0; i < board.length; i ++)
            System.out.println(board[i]);
    }

    public char[][] updateBoard(char[][] board, int[] click)
    {
        int r = click[0];
        int c = click[1];
        dfs(board, r, c);
        return board;
    }

    private void dfs(char[][] board, int r, int c)
    {
        if(board[r][c] == 'M')
        {
            board[r][c] = 'X';
        }
        else
        {
            int mineCount = 0;
            for(int i = 0; i < 8; i ++)
            {
                int newR = r + R[i];
                int newC = c + C[i];
                if(newR >=0 && newC >= 0 && newR < board.length && newC < board[0].length &&
                        board[newR][newC] == 'M') //boundary check
                    mineCount++;
            }
            if(mineCount > 0)
                board[r][c] = (char)(mineCount + '0');
            else
            {
                board[r][c] = 'B';
                for(int i = 0; i < 8; i ++)
                {
                    int newR = r + R[i];
                    int newC = c + C[i];
                    if(newR >=0 && newC >= 0 && newR < board.length && newC < board[0].length &&
                            board[newR][newC] == 'E') //boundary check
                    {
                        dfs(board, newR, newC);
                    }
                }
            }
        }
    }
}

