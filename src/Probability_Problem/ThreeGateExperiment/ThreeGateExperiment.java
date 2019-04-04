package Probability_Problem.ThreeGateExperiment;

/**
 * Create by Daolin
 * on 04 / 2019
 */
public class ThreeGateExperiment {

    private int N;

    public ThreeGateExperiment(int n) {
        if(n <= 0)
            throw new IllegalArgumentException("N must larger than 0");
        N = n;
    }

    public void run(boolean changeDoor){
        int wins = 0;
        for(int i = 0; i < N; i++)
            if(play(changeDoor))
                wins++;
        System.out.println(changeDoor ? "Change Door" : "Not Change Door");
        System.out.println("winning rate:" + (double) wins/N);
    }

    private boolean play(boolean changeDoor){
        // Door 0, 1, 2
        int prizeDoor = (int) (Math.random() * 3);
        int playerChoice = (int) (Math.random() * 3);

        if (playerChoice == prizeDoor)
            return ! changeDoor;
        else
            return changeDoor;
    }

    public static void main(String[] args) {
        int N = 1000_0000;
        ThreeGateExperiment exp = new ThreeGateExperiment(N);
        exp.run(true);
        System.out.println();
        exp.run(false);
    }

}
