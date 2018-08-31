package dungeon.maptools;

import dungeon.datastructures.Coordinates;
import dungeon.datastructures.CoordinatesList;
import java.util.ArrayList;
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
public class MazeBuilderTest {
    
    MazeBuilder mb;

    public int[][] mapCreator(int filler, int size) {
        int[][] map = new int[size][size];
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                map[j][i] = filler;
            }
        }
        return map;
    }

    public MazeBuilderTest() {
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
    public void findFirstEmptyTest1() {
        mb = new MazeBuilder(3, 3, 3);
        int[][] midZero = mapCreator(5, 3);
        midZero[1][1] = 1;
        mb.findFirstEmpty(midZero);
        assertEquals(1, mb.getX());
        assertEquals(1, mb.getY());
    }
    
    @Test
    public void findFirstEmptyTest2() {
        mb = new MazeBuilder(3, 3, 3);
        int[][] lastSquare = mapCreator(5, 3);
        lastSquare[2][2] = 1;
        mb.findFirstEmpty(lastSquare);
        assertEquals(2, mb.getX());
        assertEquals(2, mb.getY());
    }

    @Test
    public void findFirstEmptyTest3() {
        mb = new MazeBuilder(3, 3, 3);
        int[][] rightCorner = mapCreator(5, 3);
        rightCorner[0][2] = 1;
        mb.findFirstEmpty(rightCorner);
        assertEquals(2, mb.getX());
        assertEquals(0, mb.getY());
    }

    
    @Test
    public void placeWallTest1() {
        mb = new MazeBuilder(3, 3, 5);
        int[][] northIsTrue = mapCreator(0, 3);
        mb.setX(1);
        mb.setY(1);
        northIsTrue[0][1] = 1;
        assertEquals(0, mb.placeWall(northIsTrue, new Coordinates(0, -1))[0][1]);
    }

    @Test
    public void placeWallTest2() {
        mb = new MazeBuilder(3, 3, 5);
        int[][] southIsFalse = mapCreator(0, 3);
        mb.setX(1);
        mb.setY(1);
        southIsFalse[2][1] = 7;
        assertEquals(7, mb.placeWall(southIsFalse, new Coordinates(0, 1))[2][1]);
    }

    @Test
    public void placeWallTest3() {
        mb = new MazeBuilder(3, 3, 5);
        int[][] westIsFalse = mapCreator(0, 3);
        mb.setX(1);
        mb.setY(1);
        westIsFalse[1][0] = 7;
        assertEquals(7, mb.placeWall(westIsFalse, new Coordinates(-1, 0))[1][0]);
    }

    @Test
    public void placeWallTest4() {
        mb = new MazeBuilder(3, 3, 5);
        int[][] eastIsTrue = mapCreator(0, 3);
        mb.setX(1);
        mb.setY(1);
        eastIsTrue[2][1] = 1;
        assertEquals(0, mb.placeWall(eastIsTrue, new Coordinates(1, 0))[1][2]);
    }
    
    @Test
    public void placeCorridorWithWallsTest1() {
        mb = new MazeBuilder(3, 3, 5);
        int[][] allOnes = mapCreator(1, 3);
        mb.setX(1);
        mb.setY(1);
        mb.placeCorridorWithWalls(allOnes);
        
        int[][] target = mapCreator(1, 3);
        target[1][1] = 5;
        target[0][1] = 0;
        target[1][0] = 0;
        target[1][2] = 0;
        target[2][1] = 0;
        
        Assert.assertArrayEquals(target, mb.placeCorridorWithWalls(allOnes));
    }
    
    @Test
    public void checkIfCorridorNotYetConnectedTest1() {
        mb = new MazeBuilder(3, 3, 5);
        int[][] allOnes = mapCreator(1, 3);
        mb.setX(1);
        mb.setY(1);
        assertFalse(mb.checkIfSquareConnectsToOneCorridor(allOnes));
    }
    
    @Test
    public void checkIfCorridorNotYetConnectedTest2() {
        mb = new MazeBuilder(3, 3, 5);
        int[][] allOnes = mapCreator(2, 3);
        mb.setX(1);
        mb.setY(1);
        assertFalse(mb.checkIfSquareConnectsToOneCorridor(allOnes));
    }
    
    @Test
    public void checkIfCorridorNotYetConnectedTest3() {
        mb = new MazeBuilder(3, 3, 5);
        int[][] allOnes = mapCreator(1, 3);
        allOnes[0][1] = 2;
        mb.setX(1);
        mb.setY(1);
        assertTrue(mb.checkIfSquareConnectsToOneCorridor(allOnes));
    }
    
    @Test
    public void countSurroundingWallsTest1() {
        mb = new MazeBuilder(3, 3, 5);
        int[][] threeWalls = mapCreator(0, 3);
        threeWalls[0][1] = 2;
        assertEquals(3, mb.countSurroundingWalls(threeWalls, 1, 1));
    }

    @Test
    public void countSurroundingWallsTest2() {
        mb = new MazeBuilder(3, 3, 5);
        int[][] oneWall = mapCreator(2, 3);
        oneWall[0][1] = 0;
        assertEquals(1, mb.countSurroundingWalls(oneWall, 1, 1));
    }
    
    @Test
    public void toDebugStringTest1() {
        mb = new MazeBuilder(2, 2, 5);
        int[][] allInOne = mapCreator(1, 2);
        allInOne[1][1] = 5;
        allInOne[1][0] = 0;
        assertEquals("11\n#5\n", mb.toDebugString(allInOne));
    }
    
    @Test
    public void findNextCorridorSquareTest1() {
        // neighbouringWalls is empty
        mb = new MazeBuilder(3, 3, 5);
        int[][] allZeroes = mapCreator(0, 3);

        Assert.assertArrayEquals(allZeroes, mb.findNextCorridorSquare(allZeroes));
    }
    
    @Test
    public void findNextCorridorSquareTest2() {
        // neighbouringWalls is empty
        mb = new MazeBuilder(3, 3, 5);
        mb.setX(1);
        mb.setY(0);
        CoordinatesList nWalls = new CoordinatesList();
        nWalls.add(new Coordinates(1, 1));
        mb.setNeighbouringWalls(nWalls);
        
        int[][] allTwos = mapCreator(2, 3);
        
        assertEquals(1, mb.getNeighbouringWalls().size());
        Assert.assertArrayEquals(allTwos, mb.findNextCorridorSquare(allTwos));
        assertEquals(1, mb.getX());
        assertEquals(1, mb.getY());
        assertEquals(0, mb.getNeighbouringWalls().size());
    }
    
    @Test
    public void sealDeadendsTest1() {
        mb = new MazeBuilder(5, 5, 1);
        int[][] map = mapCreator(0, 5);
        map[1][1] = 5;
        map[2][1] = 5;
        map[3][1] = 5;
        map[1][2] = 5;
        map[3][2] = 5;
        map[1][3] = 5;
        map[3][3] = 5;
        int[][] target = mapCreator(0, 5);
        
        Assert.assertArrayEquals(target, mb.sealDeadends(map));
    }
}
