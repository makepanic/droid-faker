package de.rndm.droidFaker.model.tasks;

import android.content.Context;
import de.rndm.droidFaker.generators.bookmark.BookmarkGenerator;
import de.rndm.droidFaker.generators.sms.SmsGenerator;
import de.rndm.droidFaker.model.ReflectedTask;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Christian Schulz - mkp
 * Date: 04.01.14
 * Time: 20:36
 */
public class BookmarkTask extends Task implements ReflectedTask {
    private String title;
    private String url;

    public BookmarkTask() {
    }

    @Override
    public void init(HashMap<String, Object> map) {
        this.title = (String) map.get("title");
        this.url = (String) map.get("url");
    }

    @Override
    public void run(Context context) {
        BookmarkGenerator bookmarkGenerator = new BookmarkGenerator(context.getContentResolver());
        bookmarkGenerator.insert(title, url);
    }
}
