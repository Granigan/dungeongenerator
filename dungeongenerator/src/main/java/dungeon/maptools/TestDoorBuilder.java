package dungeon.maptools;

import dungeon.datastructures.Coordinates;
import dungeon.datastructures.IndexOfLists;
import dungeon.interfaces.DoorBuilding;
import dungeon.interfaces.RandomGenerator;

/**
 * Test class to replace DoorBuilder().
 * 
 * @author tgtapio
 */
public class TestDoorBuilder implements DoorBuilding {

    int roomCount;
    IndexOfLists roomWalls;

    @Override
    public int[][] findAndPlaceDoors(int[][] map) {
        map[0][0] = roomCount;
        if (roomWalls != null) {
            map[1][1] = -1;
        }
        return map;
    }

    @Override
    public Coordinates findConnectingWall(int[][] map, int roomId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean connectsTwoSegments(int[][] map, Coordinates coords) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int[][] placeDoor(int[][] map, Coordinates coords, int segmentId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setR(RandomGenerator r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setRoomWalls(IndexOfLists roomWalls) {
        this.roomWalls = roomWalls;
    }

    @Override
    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

}
