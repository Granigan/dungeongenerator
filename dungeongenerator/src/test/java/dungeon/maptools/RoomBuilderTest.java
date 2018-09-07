package dungeon.maptools;

import dungeon.datastructures.IndexOfLists;
import dungeon.datastructures.TestGenerator;
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
public class RoomBuilderTest {

    RoomBuilder rb = new RoomBuilder();

    public int[][] mapCreator(int filler, int size) {
        int[][] map = new int[size][size];
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                map[j][i] = filler;
            }
        }
        return map;
    }

    public RoomBuilderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        rb.setRoomWalls(new IndexOfLists());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void buildWallsTest1() {

        int[][] map = mapCreator(5, 3);
        int[][] target = mapCreator(0, 3);
        target[1][1] = 5;
        assertEquals(target, rb.buildWalls(map, 0, 0, 3, 3));
    }

    @Test
    public void buildFloorsTest1() {
        int[][] map = mapCreator(5, 3);
        int[][] target = mapCreator(5, 3);
        rb.setRoomCount(1);
        target[1][1] = 1;
        assertEquals(target, rb.buildFloors(map, 0, 0, 3, 3));
    }

    @Test
    public void initMapTest1() {
        int[][] map = mapCreator(5, 3);
        int[][] target = mapCreator(0, 3);
        rb.setRoomCount(0);
        target[1][1] = 1;
        assertEquals(target, rb.initMap(map, 3, 3, 100));
    }

    @Test
    public void emptySquareTest1() {
        int[][] collision = mapCreator(1, 1);
        assertTrue(rb.emptySquare(collision, 0, 0));
    }

    @Test
    public void emptySquareTest2() {
        int[][] noCollision = mapCreator(0, 1);
        assertFalse(rb.emptySquare(noCollision, 0, 0));
    }

    @Test
    public void addRoomTest1() {
        TestGenerator tg = new TestGenerator();
        rb.setR(tg);
        rb.setWidth(7);
        rb.setHeight(7);
        rb.setRoomCount(0);
        int[][] map = mapCreator(1, 7);

        int[][] target = mapCreator(1, 7);
        target[1][1] = 0;
        target[2][1] = 0;
        target[3][1] = 0;
        target[1][2] = 0;
        target[3][2] = 0;
        target[1][3] = 0;
        target[2][3] = 0;
        target[3][3] = 0;

        Assert.assertArrayEquals(target, rb.addRoom(map, 3, 3));

    }
}
