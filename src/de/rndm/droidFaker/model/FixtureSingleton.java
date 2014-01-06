package de.rndm.droidFaker.model;

import de.rndm.droidFaker.model.FixturesHolder;

/**
 * Singleton that allows global access to all {@link de.rndm.droidFaker.fixtures.Fixture} using a {@link de.rndm.droidFaker.model.FixturesHolder}
 */
public class FixtureSingleton {
    private static FixturesHolder instance = null;

    private FixtureSingleton(FixturesHolder fixtures){
        instance = fixtures;
    }

    public static FixturesHolder getInstance(){
        return instance;
    }

    public static FixturesHolder init(FixturesHolder fixtures){
        instance = fixtures;
        return instance;
    }
}
