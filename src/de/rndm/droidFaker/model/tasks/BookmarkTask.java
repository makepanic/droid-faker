package de.rndm.droidFaker.model.tasks;

import android.content.Context;
import de.rndm.droidFaker.generators.BookmarkGenerator;
import de.rndm.droidFaker.model.ReflectedTask;

import java.util.HashMap;

/**
 * User: rndm
 * Date: 04.01.14
 * Time: 20:36
 */
public class BookmarkTask extends Task implements ReflectedTask {
    private String title;
    private String url;

    public BookmarkTask() {
    }

    /**
     * Initializes the title and url field
     * @param map map of parameters that are available from the json object
     */
    @Override
    public void init(HashMap<String, Object> map) {
        this.title = (String) map.get("title");
        this.url = (String) map.get("url");
    }

    /**
     * Calls the {@link de.rndm.droidFaker.generators.BookmarkGenerator} with object parameters
     * @param context useful object to access contentResolver or applicationContext
     */
    @Override
    public void run(Context context) {
        BookmarkGenerator bookmarkGenerator = new BookmarkGenerator(context.getContentResolver());
        bookmarkGenerator.insert(title, url);
    }
}
