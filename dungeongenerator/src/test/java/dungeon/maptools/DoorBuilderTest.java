package dungeon.maptools;

import dungeon.datastructures.Coordinates;
import dungeon.datastructures.CoordinatesList;
import dungeon.datastructures.TestGenerator;
import dungeon.interfaces.RandomGenerator;
import java.util.HashMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tgtapio
 */
public class DoorBuilderTest {

    HashMap<Integer, CoordinatesList> roomWalls;
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

    @Before
    public void setUp() {
        db = new DoorBuilder(1, 1);
        db.setRoomWalls(roomWalls);
        db.setRoomCount(5);
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
    public void connectsTwoSegmentsTest4() {
        int[][] map = mapCreator(0, 5);
        map[1][1] = 2;
        map[1][3] = 3;
        map[3][1] = 1;
        assertFalse(db.connectsTwoSegments(map, new Coordinates(1, 2)));
    }

    @Test
    public void connectsTwoSegmentsTest5() {
        int[][] map = mapCreator(0, 5);
        map[1][3] = 3;
        map[3][3] = 3;
        assertFalse(db.connectsTwoSegments(map, new Coordinates(3, 2)));
    }

    @Test
    public void connectsTwoSegmentsTest6() {
        int[][] map = mapCreator(0, 5);
        map[1][1] = 3;
        map[1][3] = 3;
        assertFalse(db.connectsTwoSegments(map, new Coordinates(2, 1)));
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
        roomWalls.put(3, new CoordinatesList());
        roomWalls.get(3).add(new Coordinates(2, 1));
        roomWalls.get(3).add(new Coordinates(2, 2));
        roomWalls.get(3).add(new Coordinates(2, 3));
        db = new DoorBuilder(1, 1);
        db.setRoomWalls(roomWalls);
        db.setRoomCount(3);
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
        roomWalls.put(3, new CoordinatesList());
        roomWalls.get(3).add(new Coordinates(2, 1));
        roomWalls.get(3).add(new Coordinates(2, 2));
        roomWalls.get(3).add(new Coordinates(2, 3));
        db = new DoorBuilder(1, 1);
        db.setRoomWalls(roomWalls);
        db.setRoomCount(3);
        int[][] map = mapCreator(0, 5);
        map[1][1] = 3;
        map[3][3] = 4;
        assertNull(db.findConnectingWall(map, 3));
    }

    @Test
    public void findAndPlaceDoorsTest1() {
        roomWalls = new HashMap<>();
        roomWalls.put(2, new CoordinatesList());
        roomWalls.get(2).add(new Coordinates(1, 2));
        roomWalls.get(2).add(new Coordinates(2, 2));

        roomWalls.put(3, new CoordinatesList());
        roomWalls.get(3).add(new Coordinates(2, 3));
        roomWalls.get(3).add(new Coordinates(2, 2));

        roomWalls.put(4, new CoordinatesList());
        roomWalls.get(4).add(new Coordinates(2, 1));
        roomWalls.get(4).add(new Coordinates(2, 2));

        roomWalls.put(5, new CoordinatesList());
        roomWalls.get(5).add(new Coordinates(3, 2));
        roomWalls.get(5).add(new Coordinates(2, 2));

        db = new DoorBuilder(1, 1);
        db.setRoomWalls(roomWalls);
        db.setRoomCount(5);

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

    @Test
    public void findAndPlaceDoorsTest2() {
        roomWalls = new HashMap<>();
        roomWalls.put(2, new CoordinatesList());
        roomWalls.get(2).add(new Coordinates(1, 2));
        roomWalls.get(2).add(new Coordinates(2, 2));
        roomWalls.get(2).add(new Coordinates(3, 2));

        TestGenerator r = new TestGenerator();

// max 3 doors, odds 1/2, random says yes
        db = new DoorBuilder(3, 2);
        db.setRoomWalls(roomWalls);
        db.setRoomCount(2);
        db.setR((RandomGenerator) r);

        int[][] map = mapCreator(0, 5);
        map[1][1] = 2;
        map[1][2] = 2;
        map[1][3] = 2;
        map[3][1] = 3;
        map[3][2] = 3;
        map[3][3] = 3;

        int[][] target = mapCreator(0, 5);
        target[1][1] = 2;
        target[1][2] = 2;
        target[1][3] = 2;
        target[2][1] = 1;
        target[2][2] = 1;
        target[2][3] = 1;
        target[3][1] = 3;
        target[3][2] = 3;
        target[3][3] = 3;

        assertArrayEquals(target, db.findAndPlaceDoors(map));

    }

    @Test
    public void findAndPlaceDoorsTest3() {
        roomWalls = new HashMap<>();
        roomWalls.put(2, new CoordinatesList());
        roomWalls.get(2).add(new Coordinates(1, 2));
        roomWalls.get(2).add(new Coordinates(2, 2));
        roomWalls.get(2).add(new Coordinates(3, 2));

        TestGenerator r = new TestGenerator();
        // random chooses door squares in order, adds one, denies the third one
        int[] testNumbers = {0, 1, 0, 0, 1, 1}; 
        r.setNumbers(testNumbers);

        db = new DoorBuilder(3, 2);
        db.setR((RandomGenerator) r);
        db.setRoomWalls(roomWalls);
        db.setRoomCount(2);

        int[][] map = mapCreator(0, 5);
        map[1][1] = 2;
        map[1][2] = 2;
        map[1][3] = 2;
        map[3][1] = 3;
        map[3][2] = 3;
        map[3][3] = 3;

        int[][] target = mapCreator(0, 5);
        target[1][1] = 2;
        target[1][2] = 2;
        target[1][3] = 2;
        target[2][1] = 1;
        target[2][2] = 0;
        target[2][3] = 0;
        target[3][1] = 3;
        target[3][2] = 3;
        target[3][3] = 3;

        assertArrayEquals(target, db.findAndPlaceDoors(map));

    }

}
