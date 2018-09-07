package dungeon.datastructures;

/**
 * A limited linked list that's used by the IndexOfLists structure. It stores
 * Coordinates objects as value, and points to the following ListNode to create
 * the list. It supports getValue(), listContains(), addValue(), removeValue(),
 * with a host of private and help methods.
 *
 * @author tgtapio
 */
public class ListNode {

    CoordinatesList value;
    ListNode next;
    boolean isDeleted;
    int key;

    /**
     * Constructor for linked list node, requires integer key and
     * CoordinatesList value set. Used by IndexOfLists.
     *
     * @param key integer id
     * @param current value as CoordinatesList
     */
    public ListNode(int key, CoordinatesList current) {
        this.value = current;
        this.key = key;
        next = null;
        isDeleted = false;
    }

    /**
     * Recursively finds the value matching the given key from the list.
     *
     * @param key integer id for the value
     * @return matching value as CoordinatesList
     */
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
     * Recursively checks the list for the given key.
     *
     * @param key integer id
     * @return true if key exists, false if not
     */
    public boolean listContains(int key) {
        if (this.key == key && !this.isDeleted) {
            return true;
        }
        if (next != null) {
            return this.getNext().listContains(key);
        }
        return false;
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
            this.isDeleted = false;
            return false;
        } else if (this.getNext() == null) {
            this.setNext(new ListNode(key, value));
            return true;
        } else {
            return this.getNext().addValue(key, value);
        }
    }

    /**
     * Replaces key and value for the node, used to replace information in
     * deleted nodes.
     *
     * @param key new key as int
     * @param value new value as CoordinatesList
     */
    private void replace(int key, CoordinatesList value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Recursively goes through the list until given key is found, deletes the
     * node and returns the value.
     *
     * @param key integer id
     * @return value as Coordinates list
     */
    public CoordinatesList removeValue(int key) {
        if (this.key == key && !this.isDeleted) {
            deleteNode();
            return this.value;
        }
        if (next != null) {
            return this.getNext().removeValue(key);
        }
        return null;
    }

    /**
     * Marks node as deleted.
     * 
     */
    private void deleteNode() {
        isDeleted = true;
    }

    public int getKey() {
        return key;
    }

    /**
     * Links the node to the next node on the list.
     * 
     * @param next will be added as the next ListNode
     */
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

    /**
     * Used to check if this is the last node in the list.
     * 
     * @return true if there's a next node.
     */
    public boolean hasNext() {
        return next != null;
    }

    public void setValue(CoordinatesList value) {
        this.value = value;
    }

    public CoordinatesList getValue() {
        return value;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }
    
}
