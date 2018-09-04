package dungeon.datastructures;

/**
 * Limited version of a linked list.
 *
 * @author tgtapio
 */
public class ListNode {

    CoordinatesList value;
    ListNode next;
    boolean isDeleted;
    int key;

    public ListNode(int key, CoordinatesList current) {
        this.value = current;
        this.key = key;
        next = null;
        isDeleted = false;
    }

    public CoordinatesList getValue(int key) {
        if (this.key == key && !this.isDeleted) {
            return this.value;
        }
        if (next != null) {
            return this.getNext().getValue(key);
        }

        return null;
    }

    /**
     * Finds and replaces the first deleted node with given information. If no
     * deleted node is found, a new one is generated at the end of the list.
     *
     * @param key of the value
     * @param value is a list of coordinates
     * @return true if a new node is created, false if old, deleted one is
     * replaced.
     */
    public boolean addValue(int key, CoordinatesList value) {
        if (this.isDeleted) {
            this.replace(key, value);
            return false;
        } else if (this.getNext() == null) {
            this.setNext(new ListNode(key, value));
            return true;
        } else {
            return this.getNext().addValue(key, value);
        }
    }

    private void replace(int key, CoordinatesList value) {
        this.key = key;
        this.value = value;
    }

    public CoordinatesList removeValue(int key) {
        if (this.key == key && !this.isDeleted) {
            deleteNode();
            return this.value;
        }
        if (next != null) {
            return this.getNext().getValue(key);
        }
        return null;
    }

    private void deleteNode() {
        isDeleted = true;
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

    public void setValue(CoordinatesList value) {
        this.value = value;
    }

    public CoordinatesList getValue() {
        return value;
    }

    
}
