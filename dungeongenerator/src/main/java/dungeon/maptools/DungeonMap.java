package dungeon.maptools;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * The map object that hosts the tool objects for creating the dungeon.
 *
 * @author tgtapio
 */
public class DungeonMap {

    private final int height;
    private final int width;
    private final int roomAttempts;
    private int[][] map;
    private RoomBuilder rooms;
    private MazeBuilder maze;

    /**
     * Constructor that stores the map size and creates the map object. Also
     * creates a RoomBuilder to be used for creating rooms.
     *
     * @param height of the map, outer walls included
     * @param width of the map, outer walls included
     * @param roomAttempts at placing a room into the map
     */
    public DungeonMap(int height, int width, int roomAttempts) {
        this.height = height;
        this.width = width;
        this.roomAttempts = roomAttempts;
        this.map = new int[height][width];
        rooms = new RoomBuilder();

    }

    /**
     * Runs the initialise process that uses RoomBuilder to set up walls (0) and
     * empty space (1).
     *
     */
    public void initialise() {
        map = rooms.initMap(map, height, width, roomAttempts);
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

    /**
     * Method to run the MazeBuilder in steps. Creates a perfect maze, filling
     * the empty space left after placing the rooms.
     *
     */
    public void createMaze() {
        maze = new MazeBuilder(height, width, rooms.getRoomCount());
        while (maze.findFirstEmpty(map)) {
            map = maze.placeCorridorWithWalls(map);
            map = maze.findNextCorridorSquare(map);
        }
//        map = maze.sealDeadEnds(map);
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
                } else if (map[y][x] == 1) {
                    output += "+";
                } else {
                    output += ".";
                }
            }
            output += "\n";
        }
        return output;
    }

    /**
     * Creates a string of the map, showing segment ids instead of ASCII
     * graphics.
     *
     * @return map described with segment ids.
     */
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

    /**
     * Retrieves the current room count number from RoomBuilder, subtacts one to
     * get the actual number of rooms that were placed, and returns as int.
     *
     * @return number of segments, i.e. placed rooms + 1 for the original
     * dungeon floor.
     */
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

    /**
     * Runs the DoorBuilder to connect segments (rooms and corridors) to one
     * network.
     *
     */
    public void placeDoors() {
        DoorBuilder doors = new DoorBuilder(rooms.getRoomWalls(), rooms.getRoomCount(),
                maze.getMazeId());
        map = doors.findAndPlaceDoors(map);
    }

    /**
     * Fills the deadend corridors up, leaving only one corridor system that
     * connects all rooms.
     *
     */
    public void fillDeadends() {
        map = maze.sealDeadEnds(map);
    }

    public int getCorridorSegments() {
        return maze.getMazeId() - rooms.getRoomCount();
    }
}
