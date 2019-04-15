package random_generate;

import java.awt.*;
import java.util.LinkedList;

public class AlgoVisualizer {

    private static final int DELAY = 5;
    private static int blockSize = 8;
    private static final int d[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private MazeData data;
    private AlgoFrame frame;

    public AlgoVisualizer(int N, int M){
        //初始化数据
        data = new MazeData(N, M);
        int sceneHeight = data.N() * blockSize;
        int sceneWidth = data.M() * blockSize;

        //初始化视图
        EventQueue.invokeLater(()->{
            frame = new AlgoFrame("Random Maze generator", sceneWidth, sceneHeight);

            new Thread(()->run()).start();
        });
    }

    private void run(){
        setData(-1, -1);

//        RandomQueue<Position> queue = new RandomQueue<>();
        RandomLinkedQueue<Position> queue = new RandomLinkedQueue<>();

        Position first = new Position(data.getEntranceX(), data.getEntranceY()+1);
        queue.add(first);
        data.visited[first.getX()][first.getY()] = true;
        data.openMist(first.getX(), first.getY());
        while (queue.size() != 0){
            Position curPos = queue.remove();
            for(int i = 0; i < 4; i++){
                int newX = curPos.getX() + d[i][0]*2;
                int newY = curPos.getY() + d[i][1]*2;

                if(data.inArea(newX, newY) && !data.visited[newX][newY]){
                    queue.add(new Position(newX, newY));
                    data.visited[newX][newY] = true;
                    data.openMist(newX, newY);
                    setData(curPos.getX()+d[i][0], curPos.getY()+d[i][1]);
                }
            }
        }

        setData(-1, -1);

    }

    private void setData(int x, int y){
        if(data.inArea(x, y))
            data.maze[x][y] = MazeData.ROAD;
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        new AlgoVisualizer(101, 101);
    }
}
