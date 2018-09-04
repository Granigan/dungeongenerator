package dungeon.interfaces;

import dungeon.datastructures.CoordinatesList;
import dungeon.datastructures.IndexOfLists;
import java.util.HashMap;

/**
 *
 * @author tgtapio
 */
public interface RoomBuilding {

    public int[][] initMap(int[][] map, int height, int width, int roomAttempts);

    public int[][] addRoom(int[][] map, int rwidth, int rheight);

    public int[][] buildFloors(int[][] map, int x, int y, int rwidth, int rheight);

    public int[][] buildWalls(int[][] map, int x, int y, int rwidth, int rheight);

    public void storeWallCoordinates(int x, int y, int roomId);

    public boolean emptySquare(int[][] map, int x, int y);

    public void setRoomCount(int roomCount);

    public int getRoomCount();

//    public HashMap<Integer, CoordinatesList> getRoomWalls();
    public IndexOfLists getRoomWalls();

    public void setR(RandomGenerator r);

    public void setHeight(int height);

    public void setWidth(int width);
}
