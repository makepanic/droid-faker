package de.rndm.droidFaker.generators;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.provider.Browser;
import android.util.Log;
import de.rndm.droidFaker.fixtures.Number;
import de.rndm.droidFaker.model.FixtureSingleton;
import de.rndm.droidFaker.model.FixtureType;
import de.rndm.droidFaker.fixtures.*;
import de.rndm.droidFaker.generators.DataGenerator;

import java.util.Random;

/**
 * User: rndm
 * Date: 27.10.13
 * Time: 22:47
 */
public class BookmarkGenerator implements DataGenerator {

    private final ContentResolver cr;

    public BookmarkGenerator(ContentResolver cr) {
        this.cr = cr;
    }

    /**
     * Inserts a new bookmark using title and url
     * @param title bookmark title
     * @param url bookmark url
     */
    public void insert(String title, String url){
        final ContentValues bookmarkValues = new ContentValues();
        bookmarkValues.put(Browser.BookmarkColumns.TITLE, title);
        bookmarkValues.put(Browser.BookmarkColumns.URL, url);
        bookmarkValues.put(Browser.BookmarkColumns.BOOKMARK, 1);

        cr.insert(Browser.BOOKMARKS_URI, bookmarkValues);
    }

    /**
     * Generates a given amount of random bookmarks
     * @param random Random object
     * @param amount number of bookmarks
     */
    @Override
    public void generate(Random random, int amount) {
        Log.i("CallsGenerator", "generate " + amount);
        Fixture fixture = FixtureSingleton.getInstance().getFixture(FixtureType.URL);

        for (int i = 0; i < amount; i++) {
            insert(fixture.getString(random), fixture.getString(random));
        }
        Number.getOne(null, 10);
    }

    /**
     * Deletes all bookmarks
     */
    @Override
    public void reset() {
        try {
            cr.delete(Browser.BOOKMARKS_URI, "bookmark >= 0", null);

        } catch(IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
