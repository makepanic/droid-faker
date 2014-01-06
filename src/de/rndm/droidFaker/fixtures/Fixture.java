package de.rndm.droidFaker.fixtures;

import java.util.Random;

/**
 * User: rndm
 */
public class Fixture {
    private final String[] fixtures;

    public String getString(Random random){
        return fixtures[random.nextInt(fixtures.length)];
    }

    public Fixture(String[] fixtures) {
        this.fixtures = fixtures;
    }
}
