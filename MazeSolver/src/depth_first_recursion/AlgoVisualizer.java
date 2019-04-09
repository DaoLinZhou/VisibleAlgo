package depth_first_recursion;

import java.awt.*;

public class AlgoVisualizer {

    private static int blockSide = 8;
    private static final int DELAY = 5;

    private MazeData data;
    private AlgoFrame frame;

    private static final int d[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public AlgoVisualizer(String mazefile){
        //初始化数据
        data = new MazeData(mazefile);

        int sceneHeight = data.N() * blockSide;
        int sceneWeight = data.M() * blockSide;

        //初始化视图
        EventQueue.invokeLater(()->{
            frame = new AlgoFrame("Maze Solver Visualization", sceneWeight, sceneHeight);

            new Thread(() -> run()).start();
        });
    }

    private void run(){
        setData(-1, -1, false);
        if(!go(data.getEntranceX(), data.getEntranceY()))
            System.out.println("The maze has no solution");
        setData(-1, -1, false);
    }

    //从x, y 的位置求解迷宫, 如果求解成功,返回true, 否则返回false
    private boolean go(int x, int y){
        if(!data.inArea(x, y))
            throw new IllegalArgumentException("x, y are out of index in go function");

        data.visited[x][y] = true;
        setData(x, y, true);
        if(x == data.getExitX() && y == data.getExitY())
            return true;

        for(int i = 0; i < 4; i++){
            int newX = x + d[i][0];
            int newY = y + d[i][1];
            if(data.inArea(newX, newY) &&
                    data.getMaze(newX, newY) == MazeData.ROAD &&
                    !data.visited[newX][newY]){
                if (go(newX, newY))
                    return true;
            }
        }
        setData(x, y, false);
        return false;
    }

    public void setData(int x, int y, boolean isPath) {
        if(data.inArea(x, y)) {
            data.path[x][y] = isPath;
        }
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        String mazeFile = "MazeSolver/maze_101_101.txt";
        AlgoVisualizer vis = new AlgoVisualizer(mazeFile);
    }
}
