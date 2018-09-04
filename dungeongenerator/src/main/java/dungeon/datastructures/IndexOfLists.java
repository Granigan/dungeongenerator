package dungeon.datastructures;

/**
 *
 * @author tgtapio
 */
public class IndexOfLists {

    private ListNode[] list;
    private ListNode[] newList;
    private int amountOfNodes;

    public IndexOfLists() {
        list = new ListNode[50];
        amountOfNodes = 0;
    }

    public void put(int key, CoordinatesList value) {
        double capacity = 1.0 * amountOfNodes / list.length;

        if (capacity > 0.75) {
            extendList();
        }

        if (list[findHash(key)].addValue(key, value)) {
            amountOfNodes++;
        }

    }
    
    

    private int findHash(int key) {
        int hash = key * key * 313 % list.length;
        return hash;
    }

    private int findHash(int key, int listLength) {
        int hash = key * key * 313 % listLength;
        return hash;
    }

    public CoordinatesList remove(int key) {
        return list[findHash(key)].removeValue(key);
    }

    public boolean containsKey(int key) {
        return list[findHash(key)] != null;
    }

    public CoordinatesList get(int key) {
        return list[findHash(key)].getValue(key);
    }

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

    private void insertNode(ListNode xferNode) {
        int newLength = newList.length;
        int newHash = findHash(xferNode.getKey(), newLength);
        if (newList[newHash] == null) {
            newList[newHash] = new ListNode(xferNode.getKey(), xferNode.getValue());
        } else {
            newList[newHash].addValue(xferNode.getKey(), xferNode.getValue());
        }

    }

    public boolean isEmpty() {
        return amountOfNodes == 0;
    }

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
