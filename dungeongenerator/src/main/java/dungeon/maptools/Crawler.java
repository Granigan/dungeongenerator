package dungeon.maptools;

import dungeon.datastructures.Coordinates;
import dungeon.datastructures.CoordinatesList;

/**
 * A crawler that starts with the given coordinates and goes through the map,
 * finding every accessible square and changing its value to -1.
 *
 * @author tgtapio
 */
public class Crawler {

    CoordinatesList queue;
    int visitedValue;

    /**
     * Crawler used to test if all non-wall squares on the map can be reached.
     * Requires a clone of the map and sets all encountered non-wall squares to
     * -1.
     */
    public Crawler() {
        visitedValue = -1;
        queue = new CoordinatesList();
    }

    /**
     * Give crawler the starting location as Coordinates.
     *
     * @param first Coordinates for the starting location.
     */
    public void addFirst(Coordinates first) {
        queue.add(first);
    }

    /**
     * Set the crawler in motion on the given map, starting from the previously
     * defined starting location.
     *
     * @param map clone of the original map to be tested
     * @return map modified by crawler; all encountered non-wall squares set to
     * -1
     */
    public int[][] crawl(int[][] map) {
        while (!queue.isEmpty()) {
            Coordinates square = queue.remove(0);
            if (map[square.getY()][square.getX()] > 0) {
                crawlOne(square.getX(), square.getY(), map);
            }
        }
        return map;
    }

    /**
     * Help method used by crawl(). Finds neighbours and sets value to -1.
     *
     * @param x coordinate
     * @param y coordinate
     * @param map being worked on
     * @return map after changes
     */
    private int[][] crawlOne(int x, int y, int[][] map) {
        map[y][x] = visitedValue;
        checkNeighbours(x, y, map);
        return map;
    }

    /**
     * Checks four neighbours and adds them to the queue if they're not wall.
     *
     * @param x coordinate to check neighbours from
     * @param y coordinate to check neighbours from
     * @param map being worked on
     */
    private void checkNeighbours(int x, int y, int[][] map) {
        if (map[y + 1][x] > 0) {
            queue.add(new Coordinates(x, y + 1));
        }
        if (map[y - 1][x] > 0) {
            queue.add(new Coordinates(x, y - 1));
        }
        if (map[y][x + 1] > 0) {
            queue.add(new Coordinates(x + 1, y));
        }
        if (map[y][x - 1] > 0) {
            queue.add(new Coordinates(x - 1, y));
        }
    }

}
