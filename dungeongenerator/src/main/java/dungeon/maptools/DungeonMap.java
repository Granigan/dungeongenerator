package dungeon.maptools;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * The map object that hosts the tool objects for creating the dungeon
 *
 * @author tgtapio
 */
public class DungeonMap {

    private final int height;
    private final int width;
    private int[][] map;
    private RoomBuilder rooms;
    private MazeBuilder maze;
    private int mazeId;

    public DungeonMap(int height, int width) {
        this.height = height;
        this.width = width;
        this.map = new int[height][width];
        rooms = new RoomBuilder();
        
    }

    public void initialise() {
        map = rooms.initMap(map, height, width);
    }

    /**
     * Loop for adding rooms to the map. Rooms are checked for collision and
     * their size is randomised based on the parameters given.
     *
     * @param attempts of room placement **NOT THE ACTUAL AMOUNT OF ROOMS**
     *
     * @param minRoomSize defines the minimum dimension length for the room,
     * should not be less than 3 to avoid rooms with no floor.
     * @param maxRoomRandom defines x in the 0-x to be added to each room
     * dimension
     */
    public void addRooms(int attempts, int minRoomSize, int maxRoomRandom) {
        Random r = new Random();
        while (attempts > 0) {
            map = rooms.addRoom(map, minRoomSize + r.nextInt(maxRoomRandom), minRoomSize + r.nextInt(maxRoomRandom));
            attempts--;
        }
    }
    
    public void createMaze() {
        maze = new MazeBuilder(height, width, rooms.getRoomCount());
        while(maze.findFirstEmpty(map)) {
            map = maze.placeCorridorWithWalls(map);
            map = maze.findNextCorridorSquare(map);            
        }
        map = maze.sealDeadEnds(map);
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
    
    public String toDebugString() {
        String output = "";
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (map[y][x] == 0) {
                    output += "#";
                } else {
                    output += "" + map[y][x];
                }
            }
            output += "\n";
        }
        return output;
    }
    
    public int getAddedRoomCount() {
        return rooms.getRoomCount() - 1;
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

}
