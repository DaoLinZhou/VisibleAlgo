import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgoVisualizer {

    private static int blockSide = 32;
    private static int DELAY = 40;
    private MineSweeperData data;        // 数据
    private AlgoFrame frame;    // 视图

    public AlgoVisualizer(int N, int M, int mineNumber){

        // 初始化数据
        data = new MineSweeperData(N, M, mineNumber);
        int sceneWidth = M * blockSide;
        int sceneHeight = N * blockSide;
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Mine Sweeper", sceneWidth, sceneHeight);
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> run()).start();
        });
    }

    // 动画逻辑
    private void run(){
        setData(false, -1, -1);
    }

    private class AlgoMouseListener extends MouseAdapter{
        @Override
        public void mouseReleased(MouseEvent e) {
            e.translatePoint(
                    -(frame.getBounds().width - frame.getCanvasWidth()),
                    -(frame.getBounds().height - frame.getCanvasHeight())
            );
            Point point = e.getPoint();

            int w = frame.getCanvasWidth() / data.M();
            int h = frame.getCanvasHeight() / data.N();

            int x = point.y / h;
            int y = point.x / w;

            if(SwingUtilities.isLeftMouseButton(e))
                setData(true, x, y);
            else
                setData(false, x, y);
        }
    }

    private void setData(boolean isLeftClick, int x, int y){
        if(data.inArea(x, y)){
            if(isLeftClick)
                if(data.isMine(x, y)){
                    //game over
                    data.open[x][y] = true;
                }else {
                    data.open(x, y);
                }
            else
                data.flags[x][y] = !data.flags[x][y];
        }

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }


    public static void main(String[] args) {

        int N = 20;
        int M = 20;
        int mineNum = 50;
        AlgoVisualizer visualizer = new AlgoVisualizer(N, M, mineNum);
    }
}
