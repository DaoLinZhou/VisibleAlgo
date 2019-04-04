package Sort_Visualization.SelectionSortData;

import java.awt.*;

public class AlgoVisualizer {

    private static int DELAY = 40;
    private SelectionSortData data;        // 数据
    private AlgoFrame frame;    // 视图

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N){

        // 初始化数据
        data = new SelectionSortData(N, sceneHeight);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Select Sort Visualization", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run(){
        setData(0, -1, -1);

        for(int i = 0; i < data.N(); i++){
            // 寻找[i, n)区间里最小的索引
            int minIndex = i;
            setData(i, -1, minIndex);

            for(int j = i + 1; j < data.N(); j++) {
                setData(i, j, minIndex);
                if (data.get(j) < data.get(minIndex)){
                    minIndex = j;
                    setData(i, j, minIndex);
                }
            }
            data.swap(i, minIndex);
            setData(i+1, -1, -1);
        }
        setData(data.N(), -1, -1);
    }

    private void setData(int orderedIndex, int currentMinIndex, int currentCompareIndex){
        data.orderedIndex = orderedIndex;
        data.currentMinIndex = currentMinIndex;
        data.currentCompareIndex = currentCompareIndex;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }



    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100;
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }
}
