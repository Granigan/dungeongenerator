package dungeon.domain;

import dungeon.domain.DungeonMap;
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

    DungeonMap dm = new DungeonMap(3, 3, 100);

    public int[][] mapCreator(int filler, int size) {
        int[][] map = new int[size][size];
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                map[j][i] = filler;
            }
        }
        return map;
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
    public void initialiseTest1() {
        int[][] target = mapCreator(0, 3);
        target[1][1] = 1;
        dm.initialise();
        assertEquals(target, dm.getMap());
    }

    @Test
    public void toDebugStringTest1() {
        int[][] allInOne = mapCreator(1, 3);
        allInOne[1][1] = 5;
        allInOne[1][0] = 0;
        dm.setMap(allInOne);
        assertEquals("111\n#51\n111\n", dm.toDebugString());
    }

    @Test
    public void toStringTest1() {
        int[][] allInOne = mapCreator(1, 3);
        allInOne[1][1] = 5;
        allInOne[1][0] = 0;
        dm.setMap(allInOne);
        assertEquals("+++\n#.+\n+++\n", dm.toString());
    }

}
