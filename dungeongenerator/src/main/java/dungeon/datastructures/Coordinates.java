package dungeon.datastructures;

/**
 * Object to store x and y coordinates.
 *
 * @author tgtapio
 */
public class Coordinates {

    private final int x;
    private final int y;

    /**
     * Default constructor, needs coordinates.
     * 
     * @param x coordinate
     * @param y coordinate
     */
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    @Override
    public String toString() {
        return "" + x + "," + y;
    }
}
