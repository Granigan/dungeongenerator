package dungeon.interfaces;

import dungeon.datastructures.Coordinates;
import dungeon.maptools.Direction;
import java.util.ArrayList;

/**
 *
 * @author tgtapio
 */
public interface MazeBuilding {

    public boolean findFirstEmpty(int[][] map);
    
    public int[][] placeCorridorWithWalls(int[][] map);
    
    public int[][] placeWall(int[][] map, Direction direction);
    
    public int[][] findNextCorridorSquare(int[][] map);
    
    public int[][] sealDeadends(int[][] map);
    
    public boolean checkIfSquareConnectsToOneCorridor(int[][] map);
    
    public int countSurroundingWalls(int[][] map, int i, int j);
    
    public String toDebugString(int[][] map);
    
    public void setX(int x);
    
    public int getX();
    
    public void setY(int y);
    
    public int getY();
    
    public ArrayList<Coordinates> getNeighbouringWalls();
    
    public void setNeighbouringWalls(ArrayList<Coordinates> neighbouringWalls);
    
    public int getMazeId();
    
    public void setMazeId(int mazeId);
    
    public void setRoomCount(int roomCount);
}
