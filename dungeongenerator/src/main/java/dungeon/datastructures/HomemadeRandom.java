package dungeon.datastructures;

/**
 * Provides pseudorandom positive integers. Uses the "Microsoft formula" from
 * https://rosettacode.org/wiki/Linear_congruential_generator for the magic
 * numbers, with first created from system time.
 *
 * @author tgtapio
 */
public class HomemadeRandom {

    long seed;
    long multiplier;
    long increment;
    long modulus;

    /**
     * Constructor for randomizer.
     */
    public HomemadeRandom() {
        modulus = Integer.MAX_VALUE + 1;
        seed = System.nanoTime() & modulus;
        multiplier = 1103515245;
        increment = 12345;
    }

    public int nextInt() {
        seed = (seed * multiplier + increment) % modulus;
        return (int) seed;
    }

    /**
     * Returns a pseudorandom number [0..param[.
     *
     * @param max highest possible value + 1
     * @return a number from range including zero and excluding max
     */
    public int nextInt(int max) {

        return (int) (nextInt() % max);
    }

}
