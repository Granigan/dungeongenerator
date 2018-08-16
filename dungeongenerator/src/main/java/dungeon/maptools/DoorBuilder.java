package dungeon.maptools;

import dungeon.datastructures.Coordinates;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DoorBuilder {

    private HashMap<Integer, ArrayList<Coordinates>> roomWalls;
    private int roomCount;
    private int segmentCount;
    private ArrayList<Integer> segments;
    private Random r;

    public DoorBuilder(HashMap<Integer, ArrayList<Coordinates>> roomWalls, int roomCount,
            int mazeId) {
        this.roomWalls = roomWalls;
        this.roomCount = roomCount;
        r = new Random();
        segmentCount = 0;

        segments = new ArrayList<>();
        while (mazeId > 1) {
            segments.add(mazeId);
            segmentCount++;
            mazeId--;
        }
    }

    public int[][] findAndPlaceDoors(int[][] map) {
        for(int roomId = 2; roomId <= roomCount; roomId++) {
            map = placeDoor(map, findConnectingWall(map, roomId), roomId);
        }
        return map;
    }

    public Coordinates findConnectingWall(int[][] map, int segmentId) {
        ArrayList<Coordinates> walls = roomWalls.get(segmentId);
        while(!walls.isEmpty()) {
            Coordinates c = walls.remove(r.nextInt(walls.size()));
            if (connectsTwoSegments(map, c)) {
                System.out.println("connector found at " + c.getX() + ", " + c.getY());
                return c;
            }
        }
        return null;
    }

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
    public String wallCoordinates() {
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

    public ArrayList<Integer> getSegments() {
        return segments;
    }

}
