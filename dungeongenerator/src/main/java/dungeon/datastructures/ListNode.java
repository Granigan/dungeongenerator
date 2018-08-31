package dungeon.datastructures;

/**
 * Limited version of a linked list.
 * @author tgtapio
 */
public class ListNode {
    CoordinatesList values;
    ListNode next;
    int key;
    
    public ListNode(CoordinatesList current, int key) {
        this.values = current;
        this.key = key;
        next = null;
    }

    public CoordinatesList getValues() {
        return values;
    }

    public int getKey() {
        return key;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public ListNode getNext() {
        return next;
    }
    
    @Override
    public boolean equals(Object obj) {
        ListNode other = (ListNode) obj;
        return other.getKey() == this.getKey();
    }
    
    public boolean hasNext() {
        return next != null;
    }
    
    
    
}
