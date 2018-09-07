package dungeon.datastructures;

/**
 * A limited HashMap that accepts integers as keys, and CoordinatesLists as
 * values. It supports put(), remove(), containsKey(), get(), isEmpty(), size(),
 * toString(), has private methods extendList(), findHash(), insertNode(), and
 * help additional debug/test methods getList() and getAmountOfNodes().
 *
 * @author tgtapio
 */
public class IndexOfLists {

    private ListNode[] list;
    private ListNode[] newList;
    private int amountOfNodes;

    /**
     * Constructor starts the list size at 25 and sets amount of nodes to 0.
     *
     */
    public IndexOfLists() {
        list = new ListNode[25];
        amountOfNodes = 0;
    }

    /**
     * Adds a new key (integer)/value(CoordinatesList set to the list.
     *
     * @param key unique integer id for the value
     * @param value matches the unique integer key, CoordinatesList
     */
    public void put(int key, CoordinatesList value) {
        double capacity = 1.0 * amountOfNodes / list.length;

        if (capacity > 0.75) {
            extendList();
        }

        int hashKey = findHash(key);

        if (list[hashKey] == null) {
            list[hashKey] = new ListNode(key, value);
        } else {
            list[hashKey].addValue(key, value);
        }
        amountOfNodes++;
    }

    /**
     * Creates a trivial hash from an integer: integer^2 * 313 % current length
     * of the list.
     *
     * @param key requiring hash
     * @return hash for the key
     */
    private int findHash(int key) {
        int hash = key * key * 313 % list.length;
        return hash;
    }

    /**
     * Creates a trivial hash from an integer: integer^2 * 313 % given length.
     * Used when extending the list.
     *
     * @param key requiring hash
     * @param listLength length of the new list, used as modulus
     * @return hash for the key
     */
    private int findHash(int key, int listLength) {
        int hash = key * key * 313 % listLength;
        return hash;
    }

    /**
     * Removes key value from the map, and returns the value associated to the
     * key.
     *
     * @param key integer id
     * @return CoordinatesList
     */
    public CoordinatesList remove(int key) {
        return list[findHash(key)].removeValue(key);
    }

    /**
     * Returns true if given integer id is found as a key in the map.
     *
     * @param key to be looked for
     * @return true if key was found
     */
    public boolean containsKey(int key) {
        int hashKey = findHash(key);
        if (list[hashKey] == null) {
            return false;
        }
        return list[findHash(key)].listContains(key);
    }

    /**
     * Finds and returns the value for a given integer id. If key is not found,
     * returns null.
     *
     * @param key integer id to be found
     * @return value as CoordinatesList
     */
    public CoordinatesList get(int key) {
        return list[findHash(key)].getValue(key);
    }

    /**
     * Used to extend the hashmap when capacity is too high. Doubles the size
     * and transfers the key/value sets to the new version, with a news hash.
     *
     */
    private void extendList() {
        newList = new ListNode[list.length * 2];
        int oldLength = list.length;

        for (int i = 0; i < oldLength; i++) {
            if (list[i] != null) {
                ListNode xferNode = list[i];
                insertNode(xferNode);
                while (true) {
                    insertNode(xferNode);
                    if (xferNode.hasNext()) {
                        xferNode = xferNode.getNext();
                    } else {
                        break;
                    }
                }
            }
        }
        list = newList;
    }

    /**
     * Help method to transfer key/value sets during the extension process.
     *
     * @param xferNode
     */
    private void insertNode(ListNode xferNode) {
        int newLength = newList.length;
        int newHash = findHash(xferNode.getKey(), newLength);
        if (newList[newHash] == null) {
            newList[newHash] = new ListNode(xferNode.getKey(), xferNode.getValue());
        } else {
            newList[newHash].addValue(xferNode.getKey(), xferNode.getValue());
        }

    }

    /**
     * Returns true if the hashmap has no nodes.
     *
     * @return true if the map is empty.
     */
    public boolean isEmpty() {
        return amountOfNodes == 0;
    }

    /**
     * Returns length of the list, i.e. maximum amount of buckets including
     * empty ones.
     *
     * @return length of the list
     */
    public int size() {
        return list.length;
    }

    @Override
    public String toString() {
        String output = "";
        int max = size();
        for (int i = 0; i < max; i++) {
            output += list[i].toString() + " ";
        }
        return output;
    }

    public ListNode[] getList() {
        return list;
    }

    public int getAmountOfNodes() {
        return amountOfNodes;
    }

}
