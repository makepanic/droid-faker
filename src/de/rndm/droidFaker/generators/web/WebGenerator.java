package de.rndm.droidFaker.generators.web;

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

    public void insert(String url){
        Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse(url));
        context.startActivity(viewIntent);
    }

    @Override
    public void generate(Random random, int amount) {
        Fixture urlFixture = FixtureSingleton.getInstance().getFixture(FixtureType.URL);

        for (int i = 0; i < amount; i++) {
            insert(urlFixture.getString(random));
        }
    }

    @Override
    public void reset() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
