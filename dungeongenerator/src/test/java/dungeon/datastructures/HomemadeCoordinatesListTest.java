package dungeon.datastructures;

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
public class HomemadeCoordinatesListTest {

    private HomemadeCoordinatesList list;
    Coordinates coords;

    public HomemadeCoordinatesListTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        list = new HomemadeCoordinatesList();
        coords = new Coordinates(4, 2);
    }

    public HomemadeCoordinatesList fillList() {
        HomemadeCoordinatesList flist = new HomemadeCoordinatesList();
        for (int i = 0; i < 250; i++) {
            flist.add(coords);
        }
        return flist;
    }

    @After
    public void tearDown() {
    }

    @Test
    public void constructorTest1() {
        assertEquals(list.getHighestUsedIndex(), -1);
    }

    @Test
    public void addTest1() {
        assertEquals(list.getHighestUsedIndex(), -1);
        list.add(coords);
        assertEquals(list.getHighestUsedIndex(), 0);
    }

    @Test
    public void getTest1() {
        list.add(coords);
        assertEquals(coords, list.get(0));
    }

    @Test
    public void removeTest1() {
        list.add(coords);
        assertEquals(coords, list.remove(0));
        assertTrue(list.isEmpty());
    }

    @Test
    public void toStringTest1() {
        list.add(coords);
        assertEquals("4,2 ", list.toString());
    }

    @Test
    public void toStringTest2() {
        list.add(coords);
        list.add(new Coordinates(0, 0));
        assertEquals("4,2 0,0 ", list.toString());
    }

    @Test
    public void isEmptyTest1() {
        assertTrue(list.isEmpty());
    }

    @Test
    public void isEmptyTest2() {
        list.add(new Coordinates(0, 0));
        assertFalse(list.isEmpty());
    }

    @Test
    public void sizeTest1() {
        assertEquals(0, list.size());
        assertTrue(list.size() == list.getHighestUsedIndex() + 1);
    }

    @Test
    public void sizeTest2() {
        list.add(coords);
        assertEquals(1, list.size());
        assertTrue(list.size() == list.getHighestUsedIndex() + 1);
    }

    @Test
    public void extendTest1() {
        HomemadeCoordinatesList flist = fillList();
        flist.add(coords);
        assertEquals(251, flist.size());
    }

    @Test
    public void shiftBackTest1() {
        list.add(coords);
        list.add(coords);
        list.add(new Coordinates(0, 0));
        list.remove(1);
        Coordinates[] target = new Coordinates[250];
        target[0] = coords;
        target[1] = new Coordinates(0, 0);
        target[2] = new Coordinates(0, 0); // won't be null, since old data is not wiped before overwrite
        assertArrayEquals(target, list.getList());
    }
}
