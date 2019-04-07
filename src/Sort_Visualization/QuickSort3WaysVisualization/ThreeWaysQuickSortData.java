package Sort_Visualization.QuickSort3WaysVisualization;

import java.util.Arrays;

/**
 * Create by Daolin
 * on 04 / 2019
 */
public class ThreeWaysQuickSortData {

    public enum Type{
        Default,
        NearlyOrdered,
        Identical
    }

    private int[] numbers;
    public int l, r;
    public int curPivot;
    public int curL, curR;
    public boolean[] fixedPivot;

    public ThreeWaysQuickSortData(int N, int randomBound, Type dataType){
        numbers = new int[N];
        fixedPivot = new boolean[N];

        int lBound = 1;
        int rBound = randomBound;
        if(dataType == Type.Identical){
            int avgNum = (lBound+rBound) / 2;
            lBound = avgNum;
            rBound = avgNum;
        }

        for(int i = 0; i < N; i++){
            numbers[i] = (int) (Math.random() * (rBound-lBound+1)) + lBound;
            fixedPivot[i] = false;
        }

        if(dataType == Type.NearlyOrdered){
            Arrays.sort(numbers);
            int swapTime = (int)(0.01*N);
            for (int i=0; i < swapTime; i++){
                int a = (int) (Math.random() * N);
                int b = (int) (Math.random() * N);
                swap(a, b);
            }
        }
    }

    public ThreeWaysQuickSortData(int N, int randomBound){
        this(N, randomBound, Type.Default);
    }

    public int N(){
        return numbers.length;
    }

    public int get(int index){
        if(index < 0 || index >= numbers.length)
            throw new IllegalArgumentException("index is out of bound");
        return numbers[index];
    }

    public void swap(int i, int j){
        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }


}
