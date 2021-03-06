package breadth_first_non_recursive;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

/**
 * Create by Daolin
 * on 04 / 2019
 */
public class MazeData {

    public static final char ROAD = ' ';
    public static final char WALL = '#';

    private int entranceX, entranceY;
    private int exitX, exitY;

    private int N, M;
    private char[][] maze;

    public boolean[][] visited;
    public boolean[][] path;
    public boolean[][] result;

    public MazeData(String fileName){
        if(fileName == null)
            throw new IllegalArgumentException("Filename should not be empty");

        Scanner scanner = null;
        try {
            File file = new File(fileName);
            if(!file.exists())
                throw new IllegalArgumentException("File "+fileName+" doesn't exist");
            FileInputStream fis = new FileInputStream(file);
            scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");
            //读取第一行
            String nmLine = scanner.nextLine();
            String[] nm = nmLine.trim().split("\\s+");

            N = Integer.parseInt(nm[0]);
            M = Integer.parseInt(nm[1]);

            maze = new char[N][M];
            visited = new boolean[N][M];
            path = new boolean[N][M];
            result = new boolean[N][M];
            //读取后续 N 行
            for (int i = 0; i < N; i++){
                String line = scanner.nextLine();
                //每行保证有M个字符
                if(line.length() != M)
                    throw new IllegalArgumentException("Maze file "+ fileName+ " may have problem");
                for(int j = 0; j < M; j++) {
                    maze[i][j] = line.charAt(j);
                    visited[i][j] = false;
                    path[i][j] = false;
                    result[i][j] = false;
                }
            }
        }catch ( Exception e ){
            e.printStackTrace();
        }finally {
            if(scanner != null)
                scanner.close();
        }
        entranceX = 1;
        entranceY = 0;
        exitX = N - 2;
        exitY = M - 1;
    }

    public int N() {
        return N;
    }

    public int M() {
        return M;
    }

    public int getEntranceX() {
        return entranceX;
    }

    public int getEntranceY() {
        return entranceY;
    }

    public int getExitX() {
        return exitX;
    }

    public int getExitY() {
        return exitY;
    }

    public char getMaze(int i, int j){
        if(!inArea(i, j))
            throw new IllegalArgumentException("i or j is out of index in getMaze");
        return maze[i][j];
    }

    public boolean inArea(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public void print(){
        System.out.println(N+" "+M);
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++)
                System.out.print(maze[i][j]);
            System.out.println();
        }
        return;
    }


}
