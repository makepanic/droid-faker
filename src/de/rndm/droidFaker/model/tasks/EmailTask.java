package de.rndm.droidFaker.model.tasks;

import android.content.Context;
import de.rndm.droidFaker.fixtures.StringFixture;
import de.rndm.droidFaker.generators.calls.CallsGenerator;
import de.rndm.droidFaker.generators.email.EmailGenerator;
import de.rndm.droidFaker.model.ReflectedTask;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Christian Schulz - mkp
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

    @Override
    public void run(Context context) {
        EmailGenerator emailGenerator = new EmailGenerator(context);
        emailGenerator.insert(receiver, subject, text);
    }
}
