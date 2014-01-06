package de.rndm.droidFaker.model.tasks;

import android.content.Context;
import de.rndm.droidFaker.generators.EmailGenerator;
import de.rndm.droidFaker.model.ReflectedTask;

import java.util.HashMap;

/**
 * User: rndm
 * Date: 04.01.14
 * Time: 20:36
 */
public class EmailTask extends Task implements ReflectedTask {
    private String receiver;
    private String subject;
    private String text;

    public EmailTask() {
    }

    @Override
    public void init(HashMap<String, Object> map) {
        this.receiver = (String) map.get("receiver");
        this.subject = (String) map.get("subject");
        this.text = (String) map.get("text");
    }

    /**
     * Calls the {@link de.rndm.droidFaker.generators.EmailGenerator} with object parameters
     * @param context useful object to access contentResolver or applicationContext
     */
    @Override
    public void run(Context context) {
        EmailGenerator emailGenerator = new EmailGenerator(context);
        emailGenerator.insert(receiver, subject, text);
    }
}
