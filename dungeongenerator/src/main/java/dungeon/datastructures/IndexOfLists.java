package dungeon.datastructures;

/**
 * An extending index of CoordinatesLists. Supports add(), isEmpty(), size(), get().
 *
 * @author tgtapio
 */
public class IndexOfLists {

    private CoordinatesList[] list;
    private int highestUsedIndex;

    /**
     * Initialises the list to hold up to 250 objects, and sets highest used
     * index to -1.
     */
    public IndexOfLists() {
        list = new CoordinatesList[250];
        highestUsedIndex = -1;
    }

    /**
     * Adds the given Coordinates() object to the end of the list. Calls to
     * extends the list if full.
     *
     * @param coords is added to the end of the list.
     */
    public void add(CoordinatesList coords) {
        highestUsedIndex++;
        if (highestUsedIndex < list.length) {
            list[highestUsedIndex] = coords;
        } else {
            list = extendList(list);
            list[highestUsedIndex] = coords;
        }
    }

    /**
     * Creates a new array, double the size of the old one. and copies the
     * information from the old array, thus extending the size of the list.
     *
     * @param oldList list that's full of entries
     * @return new list with twice the size
     */
    private CoordinatesList[] extendList(CoordinatesList[] oldList) {
        CoordinatesList[] newList = new CoordinatesList[oldList.length * 2];
        int oldLength = oldList.length;

        for (int i = 0; i < oldLength; i++) {
            newList[i] = oldList[i];
        }

        return newList;
    }

    /**
     * Used to check if list has any objects.
     *
     * @return true if list is empty.
     */
    public boolean isEmpty() {
        return highestUsedIndex == -1;
    }

    /**
     * Gives the amount of objects on the list.
     *
     * @return amount of objects.
     */
    public int size() {
        return highestUsedIndex + 1;
    }

    /**
     * Returns the Coordinates object with index i.
     *
     * @param i index of the wanted object.
     * @return the wanted object.
     */
    public CoordinatesList get(int i) {
        return list[i];
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

    public CoordinatesList[] getList() {
        return list;
    }

    public int getHighestUsedIndex() {
        return highestUsedIndex;
    }
    
}
