package de.rndm.droidFaker.model.tasks;

import android.content.Context;
import de.rndm.droidFaker.generators.CallsGenerator;
import de.rndm.droidFaker.model.ReflectedTask;

import java.util.HashMap;

/**
 * User: rndm
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

    /**
     * Initializes the number, cachedName, cachedNumberLabel, date and duration field
     * @param map map of parameters that are available from the json object
     */
    @Override
    public void init(HashMap<String, Object> map) {
        this.number = (String) map.get("number");
        this.cachedName = (String) map.get("name");
        this.cachedNumberLabel = (String) map.get("numberLabel");
        this.date = Long.valueOf((String) map.get("date"));
        this.duration = Long.valueOf((String) map.get("duration"));
    }

    /**
     * Calls the {@link de.rndm.droidFaker.generators.CallsGenerator} with object parameters
     * @param context useful object to access contentResolver or applicationContext
     */
    @Override
    public void run(Context context) {
        CallsGenerator callsGenerator = new CallsGenerator(context.getContentResolver());
        callsGenerator.insert(number, date, duration, cachedName, cachedNumberLabel);
    }
}
