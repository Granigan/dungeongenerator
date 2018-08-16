package dungeon.datastructures;

/**
 * Provides pseudorandom positive integers.
 *
 * @author tgtapio
 */
public class HomemadeRandom {

    long seed;

    /**
     * Constructor for randomizer.
     */
    public HomemadeRandom() {
        seed = System.currentTimeMillis() * 3;
    }

    /**
     * Returns a pseudorandom number [0..param[.
     * @param max highest possible value + 1
     * @return a number from range including zero and excluding max
     */
    public int randomPositiveBoundInteger(int max) {

        return (int) ((seed - System.currentTimeMillis()) % max);
    }

}
