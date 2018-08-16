package dungeon.maptools;

import dungeon.datastructures.Coordinates;
import dungeon.datastructures.HomemadeRandom;
import java.util.ArrayList;
import java.util.HashMap;

public class DoorBuilder {

    private HashMap<Integer, ArrayList<Coordinates>> roomWalls;
    private int roomCount;
    private int segmentCount;
    private ArrayList<Integer> segments;
    private HomemadeRandom r;

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
            int mazeId) {
        this.roomWalls = roomWalls;
        this.roomCount = roomCount;
        r = new HomemadeRandom();
        segmentCount = 0;

        segments = new ArrayList<>();
        while (mazeId > 1) {
            segments.add(mazeId);
            segmentCount++;
            mazeId--;
        }
    }

    /**
     * Connects each room to the corridor network.
     *
     * @param map being worked on
     * @return map being worked on
     */
    public int[][] findAndPlaceDoors(int[][] map) {
        for (int roomId = 2; roomId <= roomCount; roomId++) {
            map = placeDoor(map, findConnectingWall(map, roomId), roomId);
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
        System.out.println("ERROR: room had no connecting wall! "
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

    /**
     * For debug and testing, produces a string of wall coordinates.
     *
     * @return details of each wall in placed during room generation, excluding
     * the outer walls
     */
    /*    public String wallCoordinates() {
        String output = "";
        int i = 2;
        while (roomWalls.containsKey(i)) {
            output += "Room " + i + ":\nWalls: ";
            for (Coordinates c : roomWalls.get(i)) {
                output += c.toString() + " ";
            }
            output += "\n";
            i++;
        }

        return output;
    }
     */
    public ArrayList<Integer> getSegments() {
        return segments;
    }

}
