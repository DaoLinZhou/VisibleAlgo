public class MineSweeperData {

    public static final String blockImageURL = "Mine-Sweeper/resources/block.png";
    public static final String flagImageURL = "Mine-Sweeper/resources/flag.png";
    public static final String mineImageURL = "Mine-Sweeper/resources/mine.png";
    public static String numberImageURL(int num){
        if(num < 0 || num >= 8)
            throw new IllegalArgumentException("No such a number image!");
        return "Mine-Sweeper/resources/" + num + ".png";
    }

    private int N, M;
    private boolean[][] mines;
    private int[][] numbers;
    public boolean[][] open;
    public boolean[][] flags;

    public MineSweeperData(int N, int M, int mineNum){
        if(N <= 0 || M <= 0)
            throw new IllegalArgumentException("N, M should be larger than 0");
        if(mineNum <= 0 || mineNum > N*M)
            throw new IllegalArgumentException("Mine number is illegal");

        this.N = N;
        this.M = M;
        mines = new boolean[N][M];
        open = new boolean[N][M];
        flags = new boolean[N][M];
        numbers = new int[N][M];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++){
                mines[i][j] = false;
                open[i][j] = false;
                flags[i][j] = false;
                numbers[i][j] = 0;
            }
        generateMine(mineNum);
        calculateNumbers();
    }



    public  int N(){
        return N;
    }

    public int M(){
        return M;
    }

    public boolean isMine(int x, int y){
        if(!inArea(x,y))
            throw new IllegalArgumentException("out of index in isMine function");
        return mines[x][y];
    }

    public int getNumbers(int x, int y){
        if(!inArea(x,y))
            throw new IllegalArgumentException("out of index in getNumbers function");
        return numbers[x][y];
    }

    public boolean inArea(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    private void generateMine(int mineNumber){
        for(int i = 0; i < mineNumber; i++){
            int x = i / M;
            int y = i % M;
            mines[x][y] = true;
        }
        for(int i = N*M-1; i >= 0; i--){
            int iX = i / M;
            int iY = i % M;

            int randNum = (int) (Math.random()*(i+1));
            int randX = randNum / M;
            int randY = randNum % M;
            swap(iX, iY, randX, randY);
        }
    }

    private void swap(int x1, int y1, int x2, int y2){
        boolean t = mines[x1][y1];
        mines[x1][y1] = mines[x2][y2];
        mines[x2][y2] = t;
    }

    private void calculateNumbers(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(mines[i][j])
                    numbers[i][j] = -1;
                numbers[i][j] = 0;
                for(int ii = i-1; ii <= i+1; ii++)
                    for(int jj = j-1; jj <= j+1; jj++)
                        if(inArea(ii, jj) && mines[ii][jj])
                            numbers[i][j]++;

            }
        }
    }

    public void open(int x, int y) {
        if(!inArea(x, y))
            throw new IllegalArgumentException("out of index in open function");
        if(isMine(x, y))
            throw new IllegalArgumentException("can not open an mine block in open function");

        open[x][y] = true;
        if(numbers[x][y] > 0)
            return;
        for(int i = x-1; i <= x+1; i++){
            for(int j = y-1; j <= y+1; j++) {
                if(inArea(i, j) && !open[i][j] && !mines[i][j])
                    open(i, j);
            }
        }
    }
}
