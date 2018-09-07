package dungeon.maptools;

import dungeon.datastructures.Coordinates;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author tgtapio
 */
public class CrawlerTest {

    Crawler crawler;

    public CrawlerTest() {
        crawler = new Crawler();
    }

    public int[][] mapCreator(int filler, int size) {
        int[][] map = new int[size][size];
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                map[j][i] = filler;
            }
        }
        return map;
    }

    @Before
    public void setUp() {
    }

    @Test
    public void crawlTest1() {
        int[][] map = mapCreator(1, 3);
        Assert.assertArrayEquals(map, crawler.crawl(map));
    }

    @Test
    public void crawlTest2() {
        int[][] map = mapCreator(0, 3);
        map[1][1] = 1;
        int[][] target = mapCreator(0, 3);
        target[1][1] = -1;
        crawler.addFirst(new Coordinates(1, 1));
        Assert.assertArrayEquals(target, crawler.crawl(map));
    }
    
    @Test
    public void crawlTest3() {
        int[][] map = mapCreator(0, 5);
        map[1][2] = 1;
        map[2][1] = 1;
        map[2][2] = 1;
        map[2][3] = 1;
        map[3][2] = 1;
        int[][] target = mapCreator(0, 5);
        target[1][2] = -1;
        target[2][1] = -1;
        target[2][2] = -1;
        target[2][3] = -1;
        target[3][2] = -1;
        crawler.addFirst(new Coordinates(2, 2));
        Assert.assertArrayEquals(target, crawler.crawl(map));
    }
    
    @Test
    public void crawlTest4() {
        int[][] map = mapCreator(0, 3);
        int[][] target = mapCreator(0, 3);
        crawler.addFirst(new Coordinates(1, 1));
        Assert.assertArrayEquals(target, crawler.crawl(map));
    }

}
