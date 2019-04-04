package Probability_Problem.GetPI.Without_Rendering;

import Probability_Problem.GetPI.Circle;
import Probability_Problem.GetPI.MonteCarloPiData;

import java.awt.*;

/**
 * Create by Daolin
 * on 04 / 2019
 */
public class MonteCarloExperiment {

    private int squareSide;
    private int N;
    private int outputInterval = 100;

    public MonteCarloExperiment(int squareSide, int N) {
        if(squareSide <= 0 || N <= 0)
            throw new IllegalArgumentException("squareSide and N must larger than 0");
        this.squareSide = squareSide;
        this.N = N;
    }

    public void setOutputInterval(int interval){
        if(interval <= 0)
            throw new IllegalArgumentException("interval should larger than 0");
        this.outputInterval = interval;
    }

    public void run(){
        Circle circle = new Circle(squareSide/2, squareSide/2, squareSide/2);
        MonteCarloPiData data = new MonteCarloPiData(circle);
        for(int i = 0; i < N; i++){
            if(i % outputInterval == 0)
                System.out.println(data.estimatePI());
            int x = (int) (Math.random() * squareSide);
            int y = (int) (Math.random() * squareSide);
            data.addPoint(new Point(x, y));
        }
    }

    public static void main(String[] args) {
        int squareSide = 800;
        int N = 1000_0000;

        MonteCarloExperiment esp = new MonteCarloExperiment(squareSide, N);
        esp.setOutputInterval(10000);
        esp.run();

    }

}
