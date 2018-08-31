package dungeon.maptools;

import dungeon.datastructures.Coordinates;
import dungeon.datastructures.CoordinatesList;
import dungeon.datastructures.IndexOfLists;
import dungeon.datastructures.OwnRandom;
import dungeon.interfaces.RandomGenerator;
import dungeon.interfaces.RoomBuilding;
import java.util.HashMap;

/**
 * Includes methods to build rooms by placing walls and floors. Also used to
 * initialise the map.
 *
 * @author tgtapio
 */
public class RoomBuilder implements RoomBuilding {

    private int roomCount;
    private int height;
    private int width;
    private RandomGenerator r;
    private HashMap<Integer, CoordinatesList> roomWalls;
//    private IndexOfLists roomWalls;

    /**
     * Constructor, sets roomCount to zero.
     */
    public RoomBuilder() {
        this.roomCount = 0;
        r = new OwnRandom();
    }

    /**
     * Initialises the map by filling it with empty space "1" and creates the
     * outer walls "0".
     *
     * Initialises roomWalls int[][][] for storing the walls of each room. Sizes
     * are amount of rooms (segments) = (5+attempts)*2 to cover all possible
     * rooms and have plenty of room for separated corridor segments. y and x
     * are equal to the size of the whole map, which is by default more than the
     * size of the biggest possible room.
     *
     * @param map being worked on
     * @param height of the map, walls included
     * @param width of the map, walls included
     * @param roomAttempts number of room placement attempts
     * @return map being worked on
     */
    @Override
    public int[][] initMap(int[][] map, int height, int width, int roomAttempts) {
        this.height = height;
        this.width = width;
        roomWalls = new HashMap<>();
//        roomWalls = new IndexOfLists();
        roomCount++;

        map = buildWalls(map, 0, 0, width, height);
        map = buildFloors(map, 0, 0, width, height);

        return map;
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
     * @param map being worked on
     * @param rwidth width of the room to be added
     * @param rheight height of the room to be added
     * @return map being worked on
     */
    @Override
    public int[][] addRoom(int[][] map, int rwidth, int rheight) {
        int x = r.nextInt(width - 1 - rwidth) + 1;
        int y = r.nextInt(height - 1 - rheight) + 1;
        boolean noCollision = true;

        for (int j = 0; j < rheight; j++) {
            for (int i = 0; i < rwidth; i++) {
                if (!emptySquare(map, x + i, y + j)) {
                    noCollision = false;
                    break;
                }
            }
        }
        if (noCollision) {
            roomCount++;

            map = buildWalls(map, x, y, rwidth, rheight);
            map = buildFloors(map, x, y, rwidth, rheight);
        }
        return map;
    }

    /**
     * Numbers the floor squares, getting the room id from global roomCount
     * variable. roomCount is incremented in the addRoom method, allowing this
     * to be used in other context as well.
     *
     * @param map being worked on
     * @param x starting coordinate for the WALLS, floor begins at x+1, y+1
     * @param y starting coordinate for the WALLS, floor begins at x+1, y+1
     * @param rwidth of the room, INCLUDING walls
     * @param rheight of the room, INCLUDING walls
     * @return map being worked on
     */
    @Override
    public int[][] buildFloors(int[][] map, int x, int y, int rwidth, int rheight) {
        int roomNumber = roomCount;
        for (int j = 1; j < rheight - 1; j++) {
            for (int i = 1; i < rwidth - 1; i++) {
                map[j + y][i + x] = roomNumber;
            }
        }
        return map;
    }

    /**
     * Builds walls for a room starting from coordinate x,y.
     *
     * @param map being worked on
     * @param x starting coordinate
     * @param y starting coordinate
     * @param rwidth width of the room, i.e. length of the x walls
     * @param rheight height of the room, i.e. length of the y walls
     * @return map being worked on
     */
    @Override
    public int[][] buildWalls(int[][] map, int x, int y, int rwidth, int rheight) {
        for (int j = 0; j < rheight; j++) {
            map[j + y][x] = 0;
            storeWallCoordinates(x, j + y, roomCount);
            map[j + y][x + rwidth - 1] = 0;
            storeWallCoordinates(x + rwidth - 1, j + y, roomCount);
        }
        for (int i = 0; i < rwidth; i++) {
            map[y][i + x] = 0;
            storeWallCoordinates(i + x, y, roomCount);
            map[y + rheight - 1][i + x] = 0;
            storeWallCoordinates(i + x, y + rheight - 1, roomCount);
        }
        return map;
    }

    /**
     * Adds a coordinates and id of a room wall square into the roomWalls
     * structure.
     *
     * @param x coordinate of the wall
     * @param y coordinate of the wall
     * @param roomId id for the segment
     */
    @Override
    public void storeWallCoordinates(int x, int y, int roomId) {
        if (!roomWalls.containsKey(roomId)) {
            roomWalls.put(roomId, new CoordinatesList());
//        if(roomWalls.size() +1 < roomId) {
//            
//        }

        }
        roomWalls.get(roomId).add(new Coordinates(x, y));
    }

    /**
     * Checks to see if the square in the given coordinates is 'empty', i.e. 1.
     * Returns true if square is empty, false if square is 'wall' (0), or
     * belongs to another room (>1).
     *
     * @param map being worked on
     * @param x coordinate
     * @param y coordinate
     * @return true if square is free
     */
    @Override
    public boolean emptySquare(int[][] map, int x, int y) {
        if (map[y][x] == 1) {
            return true;
        }
        return false;
    }

    @Override
    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    /**
     * Mostly for convenience, useful for reports or analysis or just to avoid
     * counting the rooms on the screen.
     *
     * @return [amount of rooms in the map + 1]: empty map is 'room 1'
     */
    @Override
    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomWalls(HashMap<Integer, CoordinatesList> roomWalls) {
        this.roomWalls = roomWalls;
    }

    @Override
    public HashMap<Integer, CoordinatesList> getRoomWalls() {
        return roomWalls;
    }
    
    @Override
    public void setR(RandomGenerator r) {
        this.r = r;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

}
