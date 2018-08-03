package dungeon.map;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class DungeonMap {

    private final int height;
    private final int width;
    private int[][] map;

    public DungeonMap(int height, int width) {
        this.height = height;
        this.width = width;
        this.map = new int[height][width];
    }

    /**
     * Initialises the map by filling it with empty space "1" and creates the
     * outer walls "0".
     */
    public void initMap() {
        for (int y = 0; y < height; y++) {
            map[y][0] = 0;
            map[y][width - 1] = 0;
        }
        for (int x = 0; x < width; x++) {
            map[0][x] = 0;
            map[height - 1][x] = 0;
        }
        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                map[y][x] = 1;
            }
        }
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    /**
     * Attempts to place a room on the map by first creating a parameter sized
     * room, then choosing random coordinates for it on the map. If it collides
     * with another room or wall, the room is not placed.
     *
     * First iteration is to check each square for collision. This probably can
     * be optimised later on. Also something to consider: should the room's
     * starting location be fully random inside the map area, or limited so that
     * it will always at least fit inside the outer walls. For now, going with
     * the option to limit starting location so that the walls won't be a
     * problem. This I think will be easier, though it means the slight change
     * in how often addRoom will be able to fit the room in.
     *
     * @param rwidth width of the room to be added
     * @param rheight height of the room to be added
     */
    public void addRoom(int rwidth, int rheight) {
        Random r = new Random();
        int x = r.nextInt(width - rwidth);
        int y = r.nextInt(height - rheight);
        boolean noCollision = true;

        for (int j = 0; j < rheight; j++) {
            for (int i = 0; i < rwidth; i++) {
                if (!checkCollision(x + i, y + j)) {
                    noCollision = false;
                    break;
                }
            }
        }
        if (noCollision) {
            buildWalls(x, y, rwidth, rheight);
        }
    }

    /**
     * Builds walls for a room starting from coordinate x,y.
     *
     * @param x starting coordinate
     * @param y starting coordinate
     * @param rwidth width of the room, i.e. length of the x walls
     * @param rheight height of the room, i.e. length of the y walls
     */
    public void buildWalls(int x, int y, int rwidth, int rheight) {
        for (int j = 0; j < rheight; j++) {
            map[j + y][x] = 0;
            map[j + y][x + rwidth - 1] = 0;
        }
        for (int i = 0; i < rwidth; i++) {
            map[y][i + x] = 0;
            map[y + rheight - 1][i + x] = 0;
        }
    }

    /**
     * Checks to see if the square in the given coordinates is 'empty', i.e. 0.
     * Returns true if square is empty, false if square is 'wall', i.e. 1.
     *
     * @param map of the room
     * @param x coordinate
     * @param y coordinate
     * @return true if square is free
     */
    public boolean checkCollision(int x, int y) {
        if (map[y][x] == 1) {
            return true;
        }
        return false;
    }

    /**
     * Creates a string of the map, and prints it.
     *
     * @return map as a string
     */
    public String mapToString() {
        String output = "";
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (map[y][x] == 0) {
                    output += "#";
                } else {
                    output += ".";
                }
            }
            output += "\n";
        }
        return output;
    }

    /**
     * Saves the map to a file on disk, under project directory with name
     * 'generated_map.txt'.
     *
     * @param map in string format
     * @throws IOException problem with writing to disk
     */
    public void saveMap() throws IOException {
        FileWriter fw = new FileWriter("generated_map.txt");
        PrintWriter pw = new PrintWriter(fw);
        pw.print(mapToString());
        pw.close();
    }
}
