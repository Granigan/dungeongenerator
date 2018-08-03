package dungeon.maptools;

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
public class DungeonMapTest {

    DungeonMap map = new DungeonMap(3, 3);
    int[][] filledWithOnes = new int[3][3];

    public int[][] fillWithOnes() {
        for(int j = 0; j < 3; j++) {
            for(int i = 0; i < 3; i++) {
                filledWithOnes[j][i] = 1;
            }
        }
        return filledWithOnes;
    }

    public DungeonMapTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void buildWallsOneByOneTest() {
        map.setMap(fillWithOnes());
        map.buildWalls(0, 0, 1, 1);
        assertEquals(0, map.getMap()[0][0]);
    }

    @Test
    public void buildWallsTwoByTwoTest() {
        map.setMap(fillWithOnes());
        map.buildWalls(0, 0, 2, 2);
        assertEquals(0, map.getMap()[1][1]);
    }

    @Test
    public void buildWallsThreeByThreeTest1() {
        map.setMap(fillWithOnes());
        map.buildWalls(0, 0, 3, 3);
        assertEquals(1, map.getMap()[1][1]);
    }

    @Test
    public void buildWallsThreeByThreeTest2() {
        map.setMap(fillWithOnes());
        map.buildWalls(0, 0, 3, 3);
        assertEquals(0, map.getMap()[2][0]);
    }

    @Test
    public void collisionTrueTest() {
        int[][] oneSquare = new int[1][1];
        oneSquare[0][0] = 1;
        map.setMap(oneSquare);

        assertTrue(map.checkCollision(0, 0));
    }

    @Test
    public void collisionFalseTest() {
        int[][] oneSquare = new int[1][1];
        oneSquare[0][0] = 0;
        map.setMap(oneSquare);

        assertFalse(map.checkCollision(0, 0));
    }

    @Test
    public void initMapFirstCornermustBeWallTest() {
        map.initMap();
        assertEquals(0, map.getMap()[0][0]);
    }

    @Test
    public void initMapLastCornermustBeWallTest() {
        map.initMap();
        assertEquals(0, map.getMap()[2][2]);
    }

    @Test
    public void initMapMiddleMustBeFloorTest() {
        map.initMap();
        assertEquals(1, map.getMap()[1][1]);
    }

    @Test
    public void toStringAllOnesTest(){
        map.setMap(fillWithOnes());
        assertEquals("...\n...\n...\n", map.toString());
    }
    
    @Test
    public void toStringJustBorders(){
        map.initMap();
        assertEquals("###\n#.#\n###\n", map.toString());
    }
    
}
