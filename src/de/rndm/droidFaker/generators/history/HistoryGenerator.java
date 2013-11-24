package de.rndm.droidFaker.generators.history;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.provider.Browser;
import android.util.Log;
import de.rndm.droidFaker.fixtures.Url;
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

    private ContentResolver cr;

    public HistoryGenerator(ContentResolver cr) {
        this.cr = cr;
    }

    private void insert(Random random){
        Browser.updateVisitedHistory(cr, Url.getOne(random), true);
    }

    @Override
    public void generate(Random random, int amount) {

        for (int i = 0; i < amount; i++) {
            insert(random);
        }
    }

    @Override
    public void reset() {
        Browser.clearHistory(cr);
    }
}
