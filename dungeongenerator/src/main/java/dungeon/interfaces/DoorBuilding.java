package dungeon.interfaces;

import dungeon.datastructures.Coordinates;
import dungeon.datastructures.HomemadeCoordinatesList;
import java.util.HashMap;

/**
 *
 * @author tgtapio
 */
public interface DoorBuilding {

    public int[][] findAndPlaceDoors(int[][] map);

    public Coordinates findConnectingWall(int[][] map, int roomId);

    public boolean connectsTwoSegments(int[][] map, Coordinates coords);

    public int[][] placeDoor(int[][] map, Coordinates coords, int segmentId);

    public void setR(RandomGenerator r);

    public void setRoomWalls(HashMap<Integer, HomemadeCoordinatesList> roomWalls);
    
    public void setRoomCount(int roomCount);
}
