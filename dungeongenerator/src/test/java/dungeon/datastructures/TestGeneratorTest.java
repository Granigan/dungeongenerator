package dungeon.datastructures;

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
    
    @Test
    public void setITest1() {
        int[] numbers = {1, 2, 4, 8};
        tg.setNumbers(numbers);
        tg.setI(3);
        assertEquals(8, tg.nextInt());
    }

}
