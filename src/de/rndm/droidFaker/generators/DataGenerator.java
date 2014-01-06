package de.rndm.droidFaker.generators;

import java.util.Random;

/**
 * User: rndm
 * Date: 20.10.13
 * Time: 17:24
 */
public interface DataGenerator {
    /**
     * Generates a given amount of something
     * @param random Random object
     * @param amount number of objects to insert
     */
    public void generate(Random random, int amount);

    /**
     * deletes everything from something
     */
    public void reset();
}
