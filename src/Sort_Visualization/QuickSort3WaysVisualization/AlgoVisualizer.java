package Sort_Visualization.QuickSort3WaysVisualization;

import java.awt.*;

public class AlgoVisualizer {

    private static int DELAY = 40;
    private ThreeWaysQuickSortData data;        // 数据
    private AlgoFrame frame;    // 视图

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N, ThreeWaysQuickSortData.Type dataType){

        // 初始化数据
        data = new ThreeWaysQuickSortData(N, sceneHeight, dataType);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Two Ways Quick Sort", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {
        this(sceneWidth, sceneHeight, N, ThreeWaysQuickSortData.Type.Default);
    }

    // 动画逻辑
    private void run(){
        setData(-1, -1, -1, -1, -1, -1);
        quickSort3Ways(0, data.N()-1);
        setData(-1, -1, -1, -1, -1, -1);
    }

    private void quickSort3Ways(int l, int r){
        if(l > r)
            return;
        if(l == r){
            setData(l, r, l, -1, -1, -1);
            return;
        }
        int p = (int) (Math.random()*(r-l+1) + l);
        setData(l, r, -1, p, -1, -1);
        data.swap(l, p);
        int v = data.get(l);
        setData(l, r, -1, l, -1, -1);

        //三路快排
        int lt = l;         // arr[l+1...lt] < v
        int gt = r+1;       // arr[gt...r] > v
        int i = l+1;        // arr[lt+1...i) == v
        setData(l, r, -1, l, lt, gt);

        while(i < gt){
            if(data.get(i) < v){
                data.swap(i, lt+1);
                i++;
                lt++;
            }else if(data.get(i) > v){
                data.swap(i, gt-1);
                gt--;
            }else
                i++;
            setData(l, r, -1, l, i, gt);
        }
        data.swap(l, lt);
        setData(l, r, lt, -1, -1, -1);
        quickSort3Ways(l, lt-1);
        quickSort3Ways(gt, r);
    }

    private void setData(int l, int r, int fixedPivot, int curPivot, int curL, int curR){
        data.l = l;
        data.r = r;
        if(fixedPivot != -1) {
            data.fixedPivot[fixedPivot] = true;
            int i = fixedPivot;
            while (i < data.N() && data.get(i) == data.get(fixedPivot)){
                data.fixedPivot[i] = true;
                i++;
            }
        }
        data.curPivot = curPivot;
        data.curL = curL;
        data.curR = curR;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100;
        //AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N);
        //AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N, ThreeWaysQuickSortData.Type.Identical);
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N, ThreeWaysQuickSortData.Type.NearlyOrdered);
    }
}
