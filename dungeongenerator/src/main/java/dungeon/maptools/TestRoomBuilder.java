package dungeon.maptools;

import dungeon.datastructures.CoordinatesList;
import dungeon.interfaces.RandomGenerator;
import dungeon.interfaces.RoomBuilding;
import java.util.HashMap;

/**
 * Test class to replace RoomBuilder().
 *
 * @author tgtapio
 */
public class TestRoomBuilder implements RoomBuilding {

    private int i;

    @Override
    public int[][] initMap(int[][] map, int height, int width, int roomAttempts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int[][] addRoom(int[][] map, int minRoomSize, int maxRoomRandom) {
        map[0][0] = i;
        i++;
        map[1][0] = maxRoomRandom;
        map[0][1] = minRoomSize;
        return map;
    }

    @Override
    public int[][] buildFloors(int[][] map, int x, int y, int rwidth, int rheight) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int[][] buildWalls(int[][] map, int x, int y, int rwidth, int rheight) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void storeWallCoordinates(int x, int y, int roomId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean emptySquare(int[][] map, int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setRoomCount(int roomCount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getRoomCount() {
        return -1;
    }

    @Override
    public void setR(RandomGenerator r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setHeight(int height) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setWidth(int width) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<Integer, CoordinatesList> getRoomWalls() {
        HashMap<Integer, CoordinatesList> output = new HashMap();
        output.put(-1, new CoordinatesList());
        return output;
    }

}
