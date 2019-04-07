package Sort_Visualization.HeapSortVisualization;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public class AlgoVisualizer {

    private static int DELAY = 40;
    private HeapSortData data;        // 数据
    private AlgoFrame frame;    // 视图

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N){

        // 初始化数据
        data = new HeapSortData(N, sceneHeight);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run(){
        setData(data.N());
        // 建堆
        for(int i = (data.N()-1-1)/2; i >= 0; i--){
            shiftDown(data.N(), i);
        }

        // 堆排序
        for(int i = data.N()-1; i > 0; i--){
            data.swap(0, i);
            shiftDown(i, 0);
            setData(i);
        }
        setData(0);
    }

    private void shiftDown(int n, int k){
        while (2*k+1 < n){
            int j = 2*k+1;
            if(j+1 < n && data.get(j+1) > data.get(j))
                j += 1;
            if(data.get(k) >= data.get(j))
                break;
            data.swap(k, j);
            setData(data.heapIndex);
            k = j;
        }
    }

    private void setData(int heapIndex){
        data.heapIndex = heapIndex;
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
