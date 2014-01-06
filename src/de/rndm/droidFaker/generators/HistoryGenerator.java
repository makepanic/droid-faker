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

    public void insert(String url){
        Browser.updateVisitedHistory(cr, url, true);
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
        Browser.clearHistory(cr);
    }
}
