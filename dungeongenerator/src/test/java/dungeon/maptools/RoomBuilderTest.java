package dungeon.maptools;

import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
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
        rb.setRoomWalls(new HashMap<>());
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
    public void checkCollisionTest1() {
        int[][] collision = mapCreator(1, 1);
        assertTrue(rb.checkCollision(collision, 0, 0));
    }

    @Test
    public void checkCollisionTest2() {
        int[][] noCollision = mapCreator(0, 1);
        assertFalse(rb.checkCollision(noCollision, 0, 0));
    }

}
