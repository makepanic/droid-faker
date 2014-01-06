package de.rndm.droidFaker.model.tasks;

import android.content.Context;
import de.rndm.droidFaker.generators.HistoryGenerator;
import de.rndm.droidFaker.model.ReflectedTask;

import java.util.HashMap;

/**
 * User: rndm
 * Date: 04.01.14
 * Time: 20:36
 */
public class HistoryTask extends Task implements ReflectedTask {
    private String url;

    public HistoryTask() {
    }

    @Override
    public void init(HashMap<String, Object> map) {
        this.url = (String) map.get("url");
    }

    /**
     * Calls the {@link de.rndm.droidFaker.generators.HistoryGenerator} with object parameters
     * @param context useful object to access contentResolver or applicationContext
     */
    @Override
    public void run(Context context) {
        HistoryGenerator historyGenerator = new HistoryGenerator(context.getContentResolver());
        historyGenerator.insert(url);
    }
}
