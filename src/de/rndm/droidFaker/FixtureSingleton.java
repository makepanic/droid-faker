package de.rndm.droidFaker;

import android.util.Log;
import de.rndm.droidFaker.fixtures.Fixture;
import de.rndm.droidFaker.model.Fixtures;
import de.rndm.droidFaker.model.FixturesHolder;

/**
 * Created by mkp on 31.12.13.
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
