package de.rndm.droidFaker.generators;

import android.content.ContentResolver;
import android.provider.Browser;
import de.rndm.droidFaker.model.FixtureSingleton;
import de.rndm.droidFaker.model.FixtureType;
import de.rndm.droidFaker.fixtures.Fixture;
import de.rndm.droidFaker.generators.DataGenerator;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: mkp
 * Date: 27.10.13
 * Time: 22:47
 * To change this template use File | Settings | File Templates.
 */
public class HistoryGenerator implements DataGenerator {

    private final ContentResolver cr;

    public HistoryGenerator(ContentResolver cr) {
        this.cr = cr;
    }

    /**
     * Inserts a new visited website to the browser history
     * @param url website url
     */
    public void insert(String url){
        Browser.updateVisitedHistory(cr, url, true);
    }

    /**
     * Generates a given amount of random browser history items
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
     * Clears the browser history
     */
    @Override
    public void reset() {
        Browser.clearHistory(cr);
    }
}
