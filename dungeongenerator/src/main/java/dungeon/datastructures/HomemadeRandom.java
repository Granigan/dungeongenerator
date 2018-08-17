package dungeon.datastructures;

import dungeon.interfaces.RandomGenerator;

/**
 * Provides pseudorandom positive integers. Uses the "Microsoft formula" from
 * https://rosettacode.org/wiki/Linear_congruential_generator for the magic
 * numbers, with first seed created from system time.
 *
 * @author tgtapio
 */
public class HomemadeRandom implements RandomGenerator {

    long seed;
    long multiplier;
    long increment;
    long modulus;
    long divider;

    /**
     * Constructor for random number generator. Seed is taken from system time
     * at creation.
     */
    public HomemadeRandom() {
        modulus = 2147483647;
        modulus++;
        seed = System.currentTimeMillis() % modulus;
        multiplier = 214013;
        increment = 2531011;
        divider = 65536;
    }

    /**
     * Linear congurential generator for random numbers.
     *
     * @return value from 0 to 32767
     */
    public int nextInt() {
        seed = (seed * multiplier + increment) % modulus;
        return (int) (seed / divider);
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

    public long getSeed() {
        return seed;
    }

}
