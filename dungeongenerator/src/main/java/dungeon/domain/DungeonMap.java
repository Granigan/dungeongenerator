package dungeon.domain;

import dungeon.datastructures.HomemadeRandom;
import dungeon.interfaces.MazeBuilding;
import dungeon.interfaces.RoomBuilding;
import dungeon.maptools.DoorBuilder;
import dungeon.maptools.MazeBuilder;
import dungeon.maptools.RoomBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The map object that hosts the tool objects for creating the dungeon.
 *
 * @author tgtapio
 */
public class DungeonMap {

    private final int height;
    private final int width;
    private int roomAttempts;
    private int[][] map;
    private RoomBuilding rb;
    private MazeBuilding mb;
    private HomemadeRandom r;
    private int maxDoorsPerRoom;
    private int multipleDoorsOdd;

    /**
     * Constructor that stores the map size and creates the map object. Also
     * creates a RoomBuilder to be used for creating rooms.
     *
     * @param height of the map, outer walls included
     * @param width of the map, outer walls included
     * @param roomAttempts at placing a room into the map
     * @param maxDoorsPerRoom each room can have up to this many doors
     * @param multipleDoorsOdd chance of creating an extra door is 1/this
     */
    public DungeonMap(int height, int width, int roomAttempts, int maxDoorsPerRoom,
                    int multipleDoorsOdd) {
        this.height = height;
        this.width = width;
        this.roomAttempts = roomAttempts;
        this.maxDoorsPerRoom = maxDoorsPerRoom;
        this.multipleDoorsOdd = multipleDoorsOdd;
        this.map = new int[height][width];
        r = new HomemadeRandom();
        rb = new RoomBuilder();
        mb = new MazeBuilder(height, width, -1);
    }

    /**
     * Runs the initialise process that uses RoomBuilder to set up walls (0) and
     * empty space (1).
     *
     */
    public void initialise() {
        map = rb.initMap(map, height, width, roomAttempts);
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
        while (attempts > 0) {
            map = rb.addRoom(map, minRoomSize + r.nextInt(maxRoomRandom), minRoomSize + r.nextInt(maxRoomRandom));
            attempts--;
        }
    }

    /**
     * Method to run the MazeBuilder in steps. Creates a perfect mb, filling
 the empty space left after placing the rooms.
     *
     */
    public void createMaze() {
        mb.setRoomCount(rb.getRoomCount());
        mb.setMazeId(rb.getRoomCount());
        
        while (mb.findFirstEmpty(map)) {
            map = mb.placeCorridorWithWalls(map);
            map = mb.findNextCorridorSquare(map);
        }
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
        return rb.getRoomCount() - 1;
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
        DoorBuilder doors = new DoorBuilder(rb.getRoomWalls(), rb.getRoomCount(),
                mb.getMazeId(), maxDoorsPerRoom, multipleDoorsOdd);
        map = doors.findAndPlaceDoors(map);
    }

    /**
     * Fills the deadend corridors up, leaving only one corridor system that
     * connects all rooms.
     *
     */
    public void fillDeadends() {
        map = mb.sealDeadends(map);
    }

    public int getCorridorSegments() {
        return mb.getMazeId() - rb.getRoomCount();
    }

    public void setRb(RoomBuilding rb) {
        this.rb = rb;
    }

    public void setRoomAttempts(int roomAttempts) {
        this.roomAttempts = roomAttempts;
    }

    public void setMb(MazeBuilding mb) {
        this.mb = mb;
    }
    
}
