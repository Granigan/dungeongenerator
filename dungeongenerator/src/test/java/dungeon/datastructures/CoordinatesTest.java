package dungeon.datastructures;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tgtapio
 */
public class CoordinatesTest {
    
    public CoordinatesTest() {
    }
    
    @Test
    public void toStringTest1() {
        Coordinates coords = new Coordinates(4, 2);
        assertEquals("4,2", coords.toString());
    }

    @Test
    public void equalsTest1() {
        Coordinates coords = new Coordinates(4, 2);
        Coordinates target = new Coordinates(4, 2);
        assertTrue(coords.equals(target));
    }

    @Test
    public void equalsTest2() {
        Coordinates coords = new Coordinates(4, 2);
        Coordinates target = new Coordinates(2, 4);
        assertFalse(coords.equals(target));
    }

    @Test
    public void equalsTest3() {
        Coordinates coords = new Coordinates(4, 2);
        Coordinates target = new Coordinates(4, 4);
        assertFalse(coords.equals(target));
    }

}
