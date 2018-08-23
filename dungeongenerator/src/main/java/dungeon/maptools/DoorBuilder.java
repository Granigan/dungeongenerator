package dungeon.maptools;

import dungeon.datastructures.Coordinates;
import dungeon.datastructures.HomemadeRandom;
import java.util.ArrayList;
import java.util.HashMap;

public class DoorBuilder {

    private HashMap<Integer, ArrayList<Coordinates>> roomWalls;
    private int roomCount;
    private HomemadeRandom r;
    private int multipleDoorsOdd; // odds grow with this number, 1 = no multiples
    private int maxDoorsPerRoom;

    /**
     * DoorBuilder has methods for finding locations and placing doors to
     * connect each room to the corridor network.
     *
     * @param roomWalls map of rooms and their walls
     * @param roomCount how many rooms are there in total
     * @param mazeId gives the id of last corridor segment and thus the highest
     * segment id
     */
    public DoorBuilder(HashMap<Integer, ArrayList<Coordinates>> roomWalls, int roomCount,
            int mazeId, int maxDoorsPerRoom, int multipleDoorsOdd) {
        this.roomWalls = roomWalls;
        this.roomCount = roomCount;
        this.maxDoorsPerRoom = maxDoorsPerRoom;
        this.multipleDoorsOdd = multipleDoorsOdd;
        r = new HomemadeRandom();
    }

    /**
     * Connects each room to the corridor network.
     *
     * @param map being worked on
     * @param multipleDoorsOdd if 1, no multiple doors, if 2, 50% 
     * @return map being worked on
     */
    public int[][] findAndPlaceDoors(int[][] map) {
        int roomId = 2;
        int doorsPlaced = 0;
        while (roomId <= roomCount) {
            
            map = placeDoor(map, findConnectingWall(map, roomId), roomId);
            doorsPlaced++;

            if (doorsPlaced >= maxDoorsPerRoom || r.nextInt(multipleDoorsOdd) != 0      ) {
                roomId++;
                doorsPlaced = 0;
            }
        }
        return map;
    }

    /**
     * Goes through the walls of a room in random order until one that connects
     * the room to a different segment is found.
     *
     * @param map being worked on
     * @param roomId room being worked on
     * @return Coordinates for the wall to replaced with a door
     */
    public Coordinates findConnectingWall(int[][] map, int roomId) {
        ArrayList<Coordinates> walls = roomWalls.get(roomId);
        while (!walls.isEmpty()) {
            Coordinates c = walls.remove(r.nextInt(walls.size()));
            if (connectsTwoSegments(map, c)) {
                return c;
            }
        }
        System.out.println("ERROR: room had no connecting wall! roomId: " + roomId
                + "This can be fixed by allowing rooms to be generated "
                + "next to each other and ensuring all segments are connected.");

        return null;
    }

    /**
     * Compares north and south squares and east and west squares, and returns
     * true if both are non-wall and of different segment id.
     *
     * @param map being worked on
     * @param coords to be investigated
     * @return true if n/s or e/w segments are non-wall and different values
     */
    public boolean connectsTwoSegments(int[][] map, Coordinates coords) {
        int x = coords.getX();
        int y = coords.getY();

        if (map[y - 1][x] > 1 && map[y + 1][x] > 1 && map[y - 1][x] != map[y + 1][x]) {
            return true;
        }

        if (map[y][x + 1] > 1 && map[y][x - 1] > 1 && map[y][x + 1] != map[y][x - 1]) {
            return true;
        }

        return false;
    }

    /**
     * Places a 'door' (floor) to given coordinates.
     *
     * @param map being worked on
     * @param coords of the square
     * @param segmentId id for the 'door'
     * @return map with the new door
     */
    public int[][] placeDoor(int[][] map, Coordinates coords, int segmentId) {
        map[coords.getY()][coords.getX()] = 1;
        return map;
    }

}
