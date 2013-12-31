package de.rndm.droidFaker.fixtures;

import java.util.Random;

/**
 * Created by mkp on 31.12.13.
 */
public class Fixture {
    private String[] fixtures;

    public String getString(Random random){
        return fixtures[random.nextInt(fixtures.length)];
    }

    public Fixture(String[] fixtures) {
        this.fixtures = fixtures;
    }
}
