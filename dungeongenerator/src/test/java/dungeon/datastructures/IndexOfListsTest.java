package dungeon.datastructures;

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
public class IndexOfListsTest {
    IndexOfLists ix;
    CoordinatesList cl;
    
    public IndexOfListsTest() {
        ix = new IndexOfLists();
        cl = new CoordinatesList();
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
    public void getAmountofNodesTest1() {
        assertEquals(ix.getAmountOfNodes(), 0);
    }
    
    @Test
    public void getListTest1() {
        ListNode[] ln = new ListNode[25];
        Assert.assertArrayEquals(ix.getList(), ln);
    }
    
    @Test
    public void sizeTest1() {
        assertEquals(ix.size(), 25);
    }
    
    @Test
    public void isEmptyTest1() {
        assertTrue(ix.isEmpty());
    }
    
    @Test
    public void isEmptyTest2() {
        ix.put(0, cl);
        assertFalse(ix.isEmpty());
    }
    
    @Test
    public void removeTest1() {
        ix.put(0, cl);
        assertEquals(ix.remove(0), cl);
        assertTrue(ix.isEmpty());
    }
    
    @Test
    public void putTest1() {
        for(int i = 0; i < 30; i++) {
            ix.put(i, new CoordinatesList());
        }
        assertEquals(50, ix.size());
        assertEquals(30, ix.getAmountOfNodes());
    }
        
}
