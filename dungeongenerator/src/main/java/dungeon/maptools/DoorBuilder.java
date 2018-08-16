package dungeon.maptools;

import dungeon.datastructures.Coordinates;
import java.util.ArrayList;
import java.util.HashMap;

public class DoorBuilder {

    private HashMap<Integer, ArrayList<Coordinates>> roomWalls;

    public DoorBuilder(HashMap<Integer, ArrayList<Coordinates>> roomWalls) {
        this.roomWalls = roomWalls;
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

}
