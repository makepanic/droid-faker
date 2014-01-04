package de.rndm.droidFaker;

import de.rndm.droidFaker.model.FixturesHolder;

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
