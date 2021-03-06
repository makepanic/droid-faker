package de.rndm.droidFaker.model.tasks;

import android.content.Context;
import de.rndm.droidFaker.generators.SmsGenerator;
import de.rndm.droidFaker.model.ReflectedTask;

import java.util.HashMap;

/**
 * User: rndm
 * Date: 04.01.14
 * Time: 20:36
 */
public class SmsTask extends Task implements ReflectedTask {
    private String text;
    private String from;

    public SmsTask() {
    }

    @Override
    public void init(HashMap<String, Object> map) {
        this.text = (String)map.get("text");
        this.from = (String) map.get("from");
    }

    /**
     * Calls the {@link de.rndm.droidFaker.generators.SmsGenerator} with object parameters
     * @param context useful object to access contentResolver or applicationContext
     */
    @Override
    public void run(Context context) {
        SmsGenerator smsGenerator = new SmsGenerator(context.getContentResolver());
        smsGenerator.insert(from, text);
    }
}
