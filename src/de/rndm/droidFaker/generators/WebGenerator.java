package de.rndm.droidFaker.generators;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import de.rndm.droidFaker.model.FixtureSingleton;
import de.rndm.droidFaker.model.FixtureType;
import de.rndm.droidFaker.fixtures.Fixture;
import de.rndm.droidFaker.generators.DataGenerator;

import java.util.Random;

/**
 * User: rndm
 * Date: 24.11.13
 * Time: 14:28
 * To change this template use File | Settings | File Templates.
 */
public class WebGenerator implements DataGenerator {

    private Context context;

    public WebGenerator(Context context) {
        this.context = context;
    }

    /**
     * Starts an intent that uses the webbrowser to visit a website
     * @param url website url
     */
    public void insert(String url){
        Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse(url));
        context.startActivity(viewIntent);
    }

    /**
     * Generates a given amount of website visits
     * @param random Random object
     * @param amount number of objects to insert
     */
    @Override
    public void generate(Random random, int amount) {
        Fixture urlFixture = FixtureSingleton.getInstance().getFixture(FixtureType.URL);

        for (int i = 0; i < amount; i++) {
            insert(urlFixture.getString(random));
        }
    }

    /**
     * does nothing because we dont have access to the webbrowser application
     */
    @Override
    public void reset() {
        // no reset because intent
    }
}
