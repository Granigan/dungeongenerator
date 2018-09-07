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
public class OwnRandomTest {

    OwnRandom r = new OwnRandom();

    public OwnRandomTest() {
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
    public void constructorModulusTest1() {
        assertTrue(r.getSeed() <= Integer.MAX_VALUE);
        assertTrue(r.getSeed() >= 0);
    }
    
    @Test
    public void nextIntTest1() {
        long random = r.nextInt();
        assertTrue(random <= Integer.MAX_VALUE);
        assertTrue(random >= 0);
    }

    @Test
    public void nextIntBoundTest1() {
        long random = r.nextInt(10);
        assertTrue(random < 10);
        assertTrue(random >= 0);
    }

    @Test
    public void nextIntBoundTest2() {
        long random = r.nextInt(5);
        assertTrue(random < 5);
        assertTrue(random >= 0);
    }

    @Test
    public void nextIntBoundTest3() {
        long random = r.nextInt(2);
        assertTrue(random < 2);
        assertTrue(random >= 0);
    }

    @Test
    public void nextIntBoundTest4() {
        long random = r.nextInt(1);
        assertEquals(0, random);
    }

}
