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
public class ListNodeTest {

    ListNode node1;
    ListNode node2;
    ListNode node3;
    ListNode node4;
    CoordinatesList cl;

    public ListNodeTest() {
    }

    @Before
    public void setUp() {
        node1 = new ListNode(1, new CoordinatesList());
        node2 = new ListNode(2, new CoordinatesList());
        node3 = new ListNode(3, new CoordinatesList());
        node4 = new ListNode(3, new CoordinatesList());
        cl = new CoordinatesList();
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        cl.add(new Coordinates(0, 0));
    }

    @Test
    public void listContainsTest1() {
        assertFalse(node1.listContains(0));
    }

    @Test
    public void listContainsTest2() {
        assertTrue(node1.listContains(1));
    }

    @Test
    public void listContainsTest3() {
        assertTrue(node1.listContains(2));
    }

    @Test
    public void listContainsTest4() {
        node2.removeValue(2);
        assertFalse(node1.listContains(2));
    }

    @Test
    public void hasNextTest1() {
        assertTrue(node1.hasNext());
    }

    @Test
    public void hasNextTest2() {
        assertFalse(node4.hasNext());
    }

    @Test
    public void equalsTest1() {
        assertFalse(node1.equals(node4));
    }

    @Test
    public void equalsTest2() {
        assertTrue(node3.equals(node4));
    }

    @Test
    public void replaceTest1() {
        node1.removeValue(1);
        node1.addValue(10, cl);
        assertEquals(node1.getKey(), 10);
        assertEquals(node1.getValue(), cl);
    }

    @Test
    public void setValueTest1() {
        node1.setValue(cl);
        assertEquals(node1.getValue(), cl);
    }

    @Test
    public void getValueTest1() {
        node1.removeValue(2);
        node3.setValue(cl);
        assertEquals(cl, node1.getValue(3));
    }

    @Test
    public void getValueTest2() {
        node1.removeValue(2);
        assertNull(node1.getValue(30));
    }

    @Test
    public void getValueTest3() {
        node1.removeValue(1);
        assertNull(node1.getValue(1));
    }

    @Test
    public void removeValueTest1() {
        assertNull(node1.removeValue(30));
    }

    @Test
    public void removeValueTest2() {
        node1.removeValue(1);
        assertNull(node1.removeValue(1));
    }

    @Test
    public void removeValueTest3() {
        node1.removeValue(1);
        node1.removeValue(2);
        assertTrue(node2.isIsDeleted());
    }

    @Test
    public void removeValueTest4() {
        node1.removeValue(3);
        node1.removeValue(3);
        assertNull(node1.removeValue(3));
    }

    @Test
    public void addValueTest1() {
        node1.removeValue(1);
        assertFalse(node1.addValue(10, cl));
        assertEquals(node1.getValue(10), cl);
    }

    @Test
    public void addValueTest2() {
        assertTrue(node1.addValue(10, cl));
        assertEquals(node1.getValue(10), cl);
    }

}
