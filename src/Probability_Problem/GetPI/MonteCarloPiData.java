package Probability_Problem.GetPI;

import java.awt.*;
import java.util.LinkedList;

/**
 * Create by Daolin
 * on 03 / 2019
 */
public class MonteCarloPiData {

    private int insideCircle = 0;
    private Circle circle;
    private LinkedList<Point> points;

    public MonteCarloPiData(Circle circle) {
        this.circle = circle;
        points = new LinkedList<Point>();
    }

    public Circle getCircle() {
        return circle;
    }

    public Point getPoint(int i){
        if(i < 0 || i >= points.size())
            throw new IllegalArgumentException("out of bound in getPoint");
        return points.get(i);
    }

    public int getPointsNum(){
        return points.size();
    }

    public void addPoint(Point p){
        points.add(p);
        if(circle.contains(p))
            insideCircle ++;
    }

    public double estimatePI(){
        if(points.size() == 0)
            return 0.0;
        int circleArea = insideCircle;
        int squareArea = points.size();
        return 4 * (double) circleArea / squareArea;
    }

}
