package de.rndm.droidFaker.model.tasks;

import android.content.Context;
import de.rndm.droidFaker.generators.sms.SmsGenerator;
import de.rndm.droidFaker.model.ReflectedTask;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Christian Schulz - mkp
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

    @Override
    public void run(Context context) {
        SmsGenerator smsGenerator = new SmsGenerator(context.getContentResolver());
        smsGenerator.insert(from, text);
    }
}
