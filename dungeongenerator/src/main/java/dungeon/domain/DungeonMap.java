package dungeon.domain;

import dungeon.datastructures.Coordinates;
import dungeon.datastructures.OwnRandom;
import dungeon.interfaces.DoorBuilding;
import dungeon.interfaces.MazeBuilding;
import dungeon.interfaces.RandomGenerator;
import dungeon.interfaces.RoomBuilding;
import dungeon.maptools.Crawler;
import dungeon.maptools.DoorBuilder;
import dungeon.maptools.MazeBuilder;
import dungeon.maptools.RoomBuilder;

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
    private RandomGenerator r;
    private DoorBuilding db;
    private final int maxDoorsPerRoom;
    private final int multipleDoorsOdd;

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
        r = new OwnRandom();
        rb = new RoomBuilder();
        mb = new MazeBuilder(height, width, -1);
        db = new DoorBuilder(maxDoorsPerRoom, multipleDoorsOdd);
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
     * Method to run the MazeBuilder in steps. Creates a perfect maze, filling
     * the empty space left after placing the rooms.
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
                    output += "█";
                } else if (map[y][x] == 1) {
                    output += "+";
                } else {
                    output += " ";
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

    public boolean runCrawlerTest() {
        Coordinates first = findFirstEmpty(map);

        int[][] testMap = copyMap(map);

        Crawler crawler = new Crawler();
        crawler.addFirst(first);
        testMap = crawler.crawl(testMap);
        
        return compareArrays(map, testMap);
    }

    public boolean compareArrays(int[][] map, int[][] testMap) {
        int lengthX = map[0].length;
        int lengthY = map.length;
        if (lengthY != testMap.length || lengthX != testMap[0].length) {
            return false;
        }

        for (int i = 0; i < lengthY; i++) {
            for (int j = 0; j < lengthX; j++) {
                if (map[i][j] < 1 && testMap[i][j] != 0) {
                    return false;
                }
                if (map[i][j] >= 1 && testMap[i][j] != -1) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[][] copyMap(int[][] oldMap) {
        int lengthY = oldMap.length;
        int lengthX = oldMap[0].length;
        int[][] newMap = new int[lengthY][lengthX];
        for (int i = 0; i < lengthY; i++) {
            for (int j = 0; j < lengthX; j++) {
                newMap[i][j] = oldMap[i][j];
            }
        }
        return newMap;
    }

    public Coordinates findFirstEmpty(int[][] map) {
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if (map[j][i] > 1) {
                    return new Coordinates(i, j);
                }
            }
        }
        return null;
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
     * Runs the DoorBuilder to connect segments (rooms and corridors) to one
     * network.
     *
     */
    public void placeDoors() {
        db.setRoomWalls(rb.getRoomWalls());
        db.setRoomCount(rb.getRoomCount());
        map = db.findAndPlaceDoors(map);
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

    public void setDb(DoorBuilding db) {
        this.db = db;
    }

}
