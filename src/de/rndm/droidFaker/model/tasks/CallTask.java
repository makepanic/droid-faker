package de.rndm.droidFaker.model.tasks;

import android.content.Context;
import de.rndm.droidFaker.generators.bookmark.BookmarkGenerator;
import de.rndm.droidFaker.generators.calls.CallsGenerator;
import de.rndm.droidFaker.model.ReflectedTask;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Christian Schulz - mkp
 * Date: 04.01.14
 * Time: 20:36
 */
public class CallTask extends Task implements ReflectedTask {
    private String number;
    private long date;
    private long duration;
    private String cachedName;
    private String cachedNumberLabel;

    public CallTask() {
    }

    @Override
    public void init(HashMap<String, Object> map) {
        this.number = (String) map.get("number");
        this.cachedName = (String) map.get("name");
        this.cachedNumberLabel = (String) map.get("numberLabel");
        this.date = Long.valueOf((String) map.get("date"));
        this.duration = Long.valueOf((String) map.get("duration"));
    }

    @Override
    public void run(Context context) {
        CallsGenerator callsGenerator = new CallsGenerator(context.getContentResolver());
        callsGenerator.insert(number, date, duration, cachedName, cachedNumberLabel);
    }
}
