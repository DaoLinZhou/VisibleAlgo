package depth_first_non_recursive;

/**
 * Create by Daolin
 * on 04 / 2019
 */
public class Position {

    private int x, y;
    private Position prev;

    public Position(int x, int y, Position prev) {
        this.x = x;
        this.y = y;
        this.prev = prev;
    }

    public Position(int x, int y){
        this(x, y, null);
    }

    public Position getPrev() {
        return prev;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
