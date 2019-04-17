package Probability_Problem.ShuffleExp;


/**
 * Create by Daolin
 * on 04 / 2019
 */
public class ShuffleExp {

    private int N;
    private int n, m;

    //N次模拟, n 个雷区, m 个雷
    public ShuffleExp(int N, int n, int m){
        if(N <= 0)
            throw new IllegalArgumentException("N must be larger than 0");
        if(n < m)
            throw new IllegalArgumentException("n should >= m");
        this.N = N;
        this.n = n;
        this.m = m;
    }

    public void run(){
        int[] freq = new int[n];
        int[] arr = new int[n];
        for(int i = 0; i < N; i++){
            reset(arr);
            shuffle(arr);
            for(int j = 0; j < n; j++)
                freq[j] += arr[j];
        }

        for(int i = 0; i < n; i++){
            System.out.println(i + " : " + (double)freq[i] / N);
        }
    }

    private void shuffle(int[] arr){
//        for(int i = 0; i < n; i++){
//            //从 [i, n) 区间里随机选取元素
//            int x = (int) (Math.random() * (n-i)) + i;
//            swap(arr, i, x);
//        }
        for(int i = n-1; i >= 0; i--){
            //从 [0, i] 区间里随机选取元素
            int x = (int) (Math.random() * (i+1));
            swap(arr, i, x);
        }
    }

    private void swap(int[] arr, int x, int y){
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    private void reset(int[] arr){
        for (int i = 0; i < n; i++)
            arr[i] = i < m ? 1 : 0;
    }

    public static void main(String[] args) {
        int N = 1000_0000;
        int n = 10;
        int m = 5;

        ShuffleExp shuffleExp = new ShuffleExp(N, n, m);
        shuffleExp.run();
    }

}
