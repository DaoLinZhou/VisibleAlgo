package depth_first_recursion;

/**
 * Create by Daolin
 * on 04 / 2019
 */
public class MazeData {

    public static final char ROAD = ' ';
    public static final char WALL = '#';

    private int N, M;
    public char[][] maze;
    public boolean[][] visited;

    private int entranceX, entranceY;
    private int exitX, exitY;

    public MazeData(int N, int M) {
        //尝试另一种判断奇偶数的方法
        if((N & 1) != 1 || M % 2 == 0)
            throw new IllegalArgumentException("Our Maze's width and height should be odd number");
        this.N = N;
        this.M = M;

        maze = new char[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++)
            for(int j = 0; j < M; j++){
                if(i % 2 == 1 && j % 2 ==1)
                    maze[i][j] = ROAD;
                else
                    maze[i][j] = WALL;
                visited[i][j] = false;
            }
        entranceX = 1;
        entranceY = 0;
        exitX = N - 2;
        exitY = M - 1;

        maze[entranceX][entranceY] = ROAD;
        maze[exitX][exitY] = ROAD;
    }

    public boolean inArea(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public int N() {
        return N;
    }

    public int M() {
        return M;
    }

    public int getEntranceX() {
        return entranceX;
    }

    public int getEntranceY() {
        return entranceY;
    }

    public int getExitX() {
        return exitX;
    }

    public int getExitY() {
        return exitY;
    }
}
