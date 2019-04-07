package Sort_Visualization.QuickSortVisualization;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public class AlgoVisualizer {

    private static int DELAY = 40;
    private QuickSortData data;        // 数据
    private AlgoFrame frame;    // 视图

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N, QuickSortData.Type dataType){

        // 初始化数据
        data = new QuickSortData(N, sceneHeight, dataType);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {
        this(sceneWidth, sceneHeight, N, QuickSortData.Type.Default);
    }

    // 动画逻辑
    private void run(){
        setData(-1, -1, -1, -1, -1);
        quickSort(0, data.N()-1);
        setData(0, data.N()-1, -1, -1, -1);
    }

    private void quickSort(int l, int r){
        if(l > r)
            return;
        if(l == r){
            setData(l, r, l, -1, -1);
            return;
        }
        setData(l, r, -1, -1, -1);
        int p = partition(l, r);
        quickSort(l, p-1);
        quickSort(p+1, r);
    }

    private int partition(int l, int r){
        int p = (int) (Math.random()*(r-l+1)+l);
        setData(l, r, -1, p, -1);
        data.swap(l, p);
        setData(l, r, -1, l, -1);
        int v = data.get(l);
        setData(l, r, -1, l, -1);

        int j = l;
        for(int i = j+1; i <= r; i++){
            setData(l, r, -1, l, i);
            if(data.get(i) < v) {
                j++;
                data.swap(i, j);
                setData(l, r, -1, l, i);
            }
        }
        data.swap(l, j);
        setData(l, r, j, -1, -1);
        return j;
    }

    private void setData(int l, int r, int fixedPiovt, int curPiovt, int curElement){
        data.l = l;
        data.r = r;
        if(fixedPiovt != -1)
            data.fixedPivot[fixedPiovt] = true;
        data.curPivot = curPiovt;
        data.curElement = curElement;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100;
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N, QuickSortData.Type.Identical);
    }
}
