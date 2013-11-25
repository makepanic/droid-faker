package de.rndm.droidFaker.generators.search;

import android.content.ContentResolver;
import android.provider.Browser;
import de.rndm.droidFaker.fixtures.Text;
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
public class SearchGenerator implements DataGenerator {

    private ContentResolver cr;

    public SearchGenerator(ContentResolver cr) {
        this.cr = cr;
    }

    private void insert(Random random){
        Browser.addSearchUrl(cr, Text.getText(random, 10) + " ");
    }

    @Override
    public void generate(Random random, int amount) {

        for (int i = 0; i < amount; i++) {
            insert(random);
        }
    }

    @Override
    public void reset() {
        Browser.clearSearches(cr);
    }
}