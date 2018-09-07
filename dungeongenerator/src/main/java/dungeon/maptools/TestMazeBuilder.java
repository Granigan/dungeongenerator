package dungeon.maptools;

import dungeon.datastructures.Coordinates;
import dungeon.interfaces.MazeBuilding;

/**
 * Test class to replace MazeBuilder().
 * 
 * @author tgtapio
 */
public class TestMazeBuilder implements MazeBuilding {

    int i = 0;

    @Override
    public boolean findFirstEmpty(int[][] map) {
        if (i == 0) {
            i++;
            return true;
        }
        i = 0;
        return false;

    }

    @Override
    public int[][] placeCorridorWithWalls(int[][] map) {
        map[0][0] = 5;
        return map;
    }

    @Override
    public int[][] findNextCorridorSquare(int[][] map) {
        map[1][1] = 5_000_000;
        return map;
    }

    @Override
    public int[][] sealDeadends(int[][] map) {
        map[2][1] = 0;
        map[1][2] = 0;
        return map;
    }

    @Override
    public boolean checkIfSquareConnectsToOneCorridor(int[][] map) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int countSurroundingWalls(int[][] map, int i, int j) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toDebugString(int[][] map) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setX(int x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getX() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setY(int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getY() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getMazeId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMazeId(int mazeId) {
    }

    @Override
    public void setRoomCount(int roomCount) {

    }

    @Override
    public int[][] placeWall(int[][] map, Coordinates direction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
