package dungeon.maptools;

import dungeon.datastructures.Coordinates;
import dungeon.datastructures.HomemadeCoordinatesList;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tgtapio
 */
public class DoorBuilderTest {

    HashMap<Integer, HomemadeCoordinatesList> roomWalls;
    DoorBuilder db;

    public int[][] mapCreator(int filler, int size) {
        int[][] map = new int[size][size];
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                map[j][i] = filler;
            }
        }
        return map;
    }

    public DoorBuilderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        db = new DoorBuilder(roomWalls, 5, 5, 1, 1);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void connectsTwoSegmentsTest1() {
        int[][] map = mapCreator(0, 5);
        map[1][1] = 2;
        map[1][3] = 3;
        assertTrue(db.connectsTwoSegments(map, new Coordinates(2, 1)));
    }

    @Test
    public void connectsTwoSegmentsTest2() {
        int[][] map = mapCreator(0, 5);
        map[1][1] = 2;
        map[3][1] = 3;
        assertTrue(db.connectsTwoSegments(map, new Coordinates(1, 2)));
    }

    @Test
    public void connectsTwoSegmentsTest3() {
        int[][] map = mapCreator(0, 5);
        map[1][1] = 2;
        map[1][3] = 3;
        assertFalse(db.connectsTwoSegments(map, new Coordinates(2, 2)));
    }

    @Test
    public void placeDoorTest1() {
        int[][] map = mapCreator(0, 3);
        int[][] target = mapCreator(0, 3);
        target[1][1] = 1;
        Assert.assertArrayEquals(target, db.placeDoor(map, new Coordinates(1, 1), 5));
    }

    @Test
    public void findConnectingWallTest1() {
        roomWalls = new HashMap<>();
        roomWalls.put(3, new HomemadeCoordinatesList());
        roomWalls.get(3).add(new Coordinates(2, 1));
        roomWalls.get(3).add(new Coordinates(2, 2));
        roomWalls.get(3).add(new Coordinates(2, 3));
        db = new DoorBuilder(roomWalls, 3, 0, 1, 1);
        int[][] map = mapCreator(0, 5);
        map[2][1] = 3;
        map[2][3] = 4;
        Coordinates coords = db.findConnectingWall(map, 3);
        assertEquals(2, coords.getX());
        assertEquals(2, coords.getY());
    }

    @Test
    public void findConnectingWallTest2() {
        roomWalls = new HashMap<>();
        roomWalls.put(3, new HomemadeCoordinatesList());
        roomWalls.get(3).add(new Coordinates(2, 1));
        roomWalls.get(3).add(new Coordinates(2, 2));
        roomWalls.get(3).add(new Coordinates(2, 3));
        db = new DoorBuilder(roomWalls, 3, 0, 1, 1);
        int[][] map = mapCreator(0, 5);
        map[1][1] = 3;
        map[3][3] = 4;
        assertNull(db.findConnectingWall(map, 3));
    }

    @Test
    public void findAndPlaceDoorsTest1() {
        roomWalls = new HashMap<>();
        roomWalls.put(2, new HomemadeCoordinatesList());
        roomWalls.get(2).add(new Coordinates(1, 2));
        roomWalls.get(2).add(new Coordinates(2, 2));

        roomWalls.put(3, new HomemadeCoordinatesList());
        roomWalls.get(3).add(new Coordinates(2, 3));
        roomWalls.get(3).add(new Coordinates(2, 2));
        
        roomWalls.put(4, new HomemadeCoordinatesList());
        roomWalls.get(4).add(new Coordinates(2, 1));
        roomWalls.get(4).add(new Coordinates(2, 2));

        roomWalls.put(5, new HomemadeCoordinatesList());
        roomWalls.get(5).add(new Coordinates(3, 2));
        roomWalls.get(5).add(new Coordinates(2, 2));
        
        db = new DoorBuilder(roomWalls, 5, 0, 1, 1);
        
        int[][] map = mapCreator(0, 5);
        map[1][1] = 2;
        map[3][1] = 3;
        map[1][3] = 4;
        map[3][3] = 5;
        
        int[][] target = mapCreator(0, 5);
        target[1][1] = 2;
        target[3][1] = 3;
        target[1][3] = 4;
        target[3][3] = 5;
        target[2][1] = 1;
        target[2][3] = 1;
        target[1][2] = 1;
        target[3][2] = 1;
        
        
        Assert.assertArrayEquals(target, db.findAndPlaceDoors(map));
        
    }

}
