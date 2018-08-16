package dungeon.maptools;

import dungeon.datastructures.Coordinates;
import java.util.ArrayList;
import java.util.HashMap;

public class DoorBuilder {

    private HashMap<Integer, ArrayList<Coordinates>> roomWalls;
    private int roomCount;
    private int segmentCount;
    private ArrayList<Integer> segments;

    public DoorBuilder(HashMap<Integer, ArrayList<Coordinates>> roomWalls, int roomCount,
            int mazeId) {
        this.roomWalls = roomWalls;
        this.roomCount = roomCount;
        segmentCount = 0;

        segments = new ArrayList<>();
        while (mazeId > 1) {
            segments.add(mazeId);
            segmentCount++;
            mazeId--;
        }
    }

    public int[][] placeDoors(int[][] map) {
        
        return map;
    }

//    public Coordinates findConnectingWall(int[][] map, int segmentId) {
//        Coordinates doorCoords;
//        
//        
//        return doorCoords;
//    }

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
