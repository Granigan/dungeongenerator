package dungeon.maptools;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class DungeonMap {

    private final int height;
    private final int width;
    private int[][] map;
    private int roomCount;

    /**
     * Constructor for the map object.
     *
     * @param height of the map (y)
     * @param width of the map (x)
     */
    public DungeonMap(int height, int width) {
        this.height = height;
        this.width = width;
        this.map = new int[height][width];
        this.roomCount = 0;
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

    /**
     * Loop for adding rooms to the map. Rooms are checked for collision and
     * their size is randomised based on the parameters given.
     *
     * @param attempts of room placement **NOT THE ACTUAL AMOUNT OF ROOMS *
     * @param minRoomSize defines the minimum dimension length for the room,
     * should not be less than 3 to avoid rooms with no floor.
     * @param maxRoomRandom defines x in the 0-x to be added to each room
     * dimension
     */
    public void addRooms(int attempts, int minRoomSize, int maxRoomRandom) {
        Random r = new Random();
        while (attempts > 0) {
            addRoom(minRoomSize + r.nextInt(maxRoomRandom), minRoomSize + r.nextInt(maxRoomRandom));
            attempts--;
        }
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
        roomCount++;
    }

    /**
     * Checks to see if the square in the given coordinates is 'empty', i.e. 0.
     * Returns true if square is empty, false if square is 'wall', i.e. 1.
     *
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
     * Creates a string of the map.
     *
     * @return map as a string
     */
    @Override
    public String toString() {
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
     * Mostly for convenience, useful for reports or analysis or just to avoid
     * counting the rooms on the screen.
     *
     * @return amount of rooms in the map
     */
    public int getRoomCount() {
        return roomCount;
    }

    /**
     * Saves the map to a file on disk, under project directory with name
     * 'generated_map.txt'.
     *
     * @throws IOException problem with writing to disk
     */
    public void saveMap() throws IOException {
        FileWriter fw = new FileWriter("generated_map.txt");
        PrintWriter pw = new PrintWriter(fw);
        pw.print(toString());
        pw.close();
    }

    /**
     * Sets a specific map, for testing use.
     *
     * @param map to test with
     */
    public void setMap(int[][] map) {
        this.map = map;
    }

    /**
     * Gets the map, for testing use.
     *
     * @return the map arrays as int[][]
     */
    public int[][] getMap() {
        return map;
    }

}
