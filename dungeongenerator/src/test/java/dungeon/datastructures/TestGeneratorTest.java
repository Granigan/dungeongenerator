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
public class TestGeneratorTest {

    TestGenerator tg = new TestGenerator();

    public TestGeneratorTest() {
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
    public void nextIntTest1() {
        assertEquals(0, tg.nextInt());
    }

    @Test
    public void nextIntTest2() {
        for (int i = 0; i < 56; i++) {
            tg.nextInt();
        }
        assertEquals(0, tg.nextInt());

    }

    @Test
    public void nextIntMaxTest1() {
        assertEquals(0, tg.nextInt(-100));
    }

    @Test
    public void nextIntTestInt2() {
        for (int i = 0; i < 56; i++) {
            tg.nextInt();
        }
        assertEquals(0, tg.nextInt(0));

    }

}
