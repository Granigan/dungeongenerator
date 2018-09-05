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

    public Crawler() {
        visitedValue = -1;
        queue = new CoordinatesList();
    }
    
    public void addFirst(Coordinates first) {
        queue.add(first);
    }

    public int[][] crawl(int[][] map) {
        while (!queue.isEmpty()) {
            Coordinates square = queue.remove(0);
            if (map[square.getY()][square.getX()] > 0) {
                crawlOne(square.getX(), square.getY(), map);
            }
        }
        return map;
    }

    private int[][] crawlOne(int x, int y, int[][] map) {
        map[y][x] = visitedValue;
        checkNeighbours(x, y, map);
        return map;
    }

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
