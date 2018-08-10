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
public class RoomBuilderTest {

    RoomBuilder rb = new RoomBuilder();
    int[][] filledWithOnes = new int[3][3];

    public int[][] fillWithOnes() {
        for(int j = 0; j < 3; j++) {
            for(int i = 0; i < 3; i++) {
                filledWithOnes[j][i] = 1;
            }
        }
        return filledWithOnes;
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
    }

    @After
    public void tearDown() {
    }

//    @Test
//    public void buildWallsOneByOneTest() {
//        rb.setMap(fillWithOnes());
//        rb.buildWalls(0, 0, 1, 1);
//        assertEquals(0, rb.getMap()[0][0]);
//    }
//
//    @Test
//    public void buildWallsTwoByTwoTest() {
//        rb.setMap(fillWithOnes());
//        rb.buildWalls(0, 0, 2, 2);
//        assertEquals(0, rb.getMap()[1][1]);
//    }
//
//    @Test
//    public void buildWallsThreeByThreeTest1() {
//        rb.setMap(fillWithOnes());
//        rb.buildWalls(0, 0, 3, 3);
//        assertEquals(1, rb.getMap()[1][1]);
//    }
//
//    @Test
//    public void buildWallsThreeByThreeTest2() {
//        rb.setMap(fillWithOnes());
//        rb.buildWalls(0, 0, 3, 3);
//        assertEquals(0, rb.getMap()[2][0]);
//    }
//
//    @Test
//    public void collisionTrueTest() {
//        int[][] oneSquare = new int[1][1];
//        oneSquare[0][0] = 1;
//        rb.setMap(oneSquare);
//
//        assertTrue(rb.checkCollision(0, 0));
//    }
//
//    @Test
//    public void collisionFalseTest() {
//        int[][] oneSquare = new int[1][1];
//        oneSquare[0][0] = 0;
//        rb.setMap(oneSquare);
//
//        assertFalse(rb.checkCollision(0, 0));
//    }
//
//    @Test
//    public void initMapFirstCornermustBeWallTest() {
//        rb.initMap();
//        assertEquals(0, rb.getMap()[0][0]);
//    }
//
//    @Test
//    public void initMapLastCornermustBeWallTest() {
//        rb.initMap();
//        assertEquals(0, rb.getMap()[2][2]);
//    }
//
//    @Test
//    public void initMapMiddleMustBeFloorTest() {
//        rb.initMap();
//        assertEquals(1, rb.getMap()[1][1]);
//    }
//
//    @Test
//    public void toStringAllOnesTest(){
//        rb.setMap(fillWithOnes());
//        assertEquals("...\n...\n...\n", rb.toString());
//    }
//    
//    @Test
//    public void toStringJustBorders(){
//        rb.initMap();
//        assertEquals("###\n#.#\n###\n", rb.toString());
//    }
    
}
