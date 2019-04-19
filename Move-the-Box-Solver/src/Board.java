import sun.invoke.empty.Empty;

/**
 * Create by Daolin
 * on 04 / 2019
 */
public class Board {

    private int N, M;
    private char[][] data;
    private Board preBoard;
    private String swapString;

    public static final char EMPTY = '.';

    public Board(String[] lines){
        if(lines == null)
            throw new IllegalArgumentException("lines can not be null in Board");

        N = lines.length;
        if(N == 0)
            throw new IllegalArgumentException("lines can not be empty");
        M = lines[0].length();

        data = new char[N][M];
        for(int i = 0; i < N; i++){
            if(lines[i].length() != M)
                throw new IllegalArgumentException("All line's length should equal to M");
            for(int j = 0; j < M; j++){
                data[i][j] = lines[i].charAt(j);
            }
        }
    }

    public Board(Board board, Board preBoard, String swapString) {
        if(board == null)
            throw new IllegalArgumentException("board can not be null in Board constructor");
        this.M = board.M;
        this.N = board.N;
        this.data = new char[N][M];

        for(int i = 0; i < N; i++)
            for(int j = 0; j < M; j++)
                this.data[i][j] = board.data[i][j];
        this.preBoard = preBoard;
        this.swapString = swapString;
    }

    public Board(Board board){
        this(board, null, "");
    }

    public int N(){return N;}
    public int M(){return M;}

    public boolean inArea(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public char getData(int x, int y){
        if(!inArea(x, y))
            throw new IllegalArgumentException("x, y are out of index in getData");
        return data[x][y];
    }

    public void print(){
        for(int i = 0; i < N; i++)
            System.out.println(String.valueOf(data[i]));
    }

    public boolean isWin(){
        for(int i = 0; i < N; i++)
            for(int j = 0; j < M; j++)
                if(data[i][j] != EMPTY)
                    return false;
        printSwapInfo();
        return true;
    }

    public void swap(int x, int y, int newX, int newY) {
        if(!inArea(x, y) || !inArea(x, y))
            throw new IllegalArgumentException("x, y are out of index in swap");
        char temp = data[x][y];
        data[x][y] = data[newX][newY];
        data[newX][newY] = temp;
        return;
    }

    public void run() {
        //match & drop
        do{
            drop();
        }while (match());
    }

    private static int[][] d = {{0, 1}, {1, 0}};
    private boolean match() {
        boolean isMatched = false;
        boolean tag[][] = new boolean[N][M];
        for(int x = 0; x < N; x ++)
            for(int y = 0; y < M; y++)
                if(data[x][y] != EMPTY){
                    for(int i = 0; i < 2; i++){
                        int newX1 = x + d[i][0];
                        int newY1 = y + d[i][1];
                        int newX2 = newX1 + d[i][0];
                        int newY2 = newY1 + d[i][1];
                        if(inArea(newX1, newY1) && inArea(newX2, newY2) &&
                                data[newX1][newY1] == data[x][y] &&
                                data[newX2][newY2] == data[x][y] ){
                            tag[x][y] = true;
                            tag[newX1][newY1] = true;
                            tag[newX2][newY2] = true;

                            isMatched = true;
                        }
                    }
                }

        for (int x = 0; x < N; x ++)
            for(int y = 0; y < M; y++)
                if(tag[x][y])
                    data[x][y] = EMPTY;

        return isMatched;
    }

    private void drop() {
        for(int j = 0; j < M; j++){
            int cur = N - 1;
            for(int i = N - 1; i >= 0; i--)
                if(data[i][j] != EMPTY){
                    swap(i, j, cur, j);
                    cur--;
                }
        }
        return;
    }

    private void printSwapInfo() {
        if(preBoard != null)
            preBoard.printSwapInfo();
        System.out.println(swapString);
        return;
    }
}
