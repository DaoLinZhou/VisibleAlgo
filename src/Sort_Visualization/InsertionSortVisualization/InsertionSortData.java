package Sort_Visualization.InsertionSortVisualization;

import java.util.Arrays;

/**
 * Create by Daolin
 * on 04 / 2019
 */
public class InsertionSortData {

    public enum Type{
        Default,
        NearlyOrdered;
    }

    private int[] numbers;
    public int orderedIndex = -1;   // [0, orderedIndex) 是有序的
    public int currentIndex = -1;   // 当前元素的索引

    public InsertionSortData(int N, int randomBound, Type dataType){
        numbers = new int[N];
        for(int i = 0; i < N; i++)
            numbers[i] = (int) (Math.random() * randomBound) + 1;
        if(dataType == Type.NearlyOrdered){
            Arrays.sort(numbers);
            int swapTime = (int) (0.02*N);
            for(int i = 0; i < swapTime; i++){
                int a = (int) (Math.random() * N);
                int b = (int) (Math.random() * N);
                swap(a, b);
            }
        }
    }

    public InsertionSortData(int N, int randomBound){
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
