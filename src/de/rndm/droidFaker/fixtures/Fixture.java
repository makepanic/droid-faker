package de.rndm.droidFaker.fixtures;

import java.util.Random;

/**
 * General Fixture class that returns a random String in an Array.
 * The Array is set using the constructor.
 */
public class Fixture {
    private final String[] fixtures;

    /**
     * Returns a randomly chosen item from the String array.
     * @param random Random object that is used to select a random index
     * @return chosen String
     */
    public String getString(Random random){
        return fixtures[random.nextInt(fixtures.length)];
    }

    /**
     * @param fixtures Array of possible values
     */
    public Fixture(String[] fixtures) {
        this.fixtures = fixtures;
    }
}
