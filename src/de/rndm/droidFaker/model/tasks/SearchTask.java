package de.rndm.droidFaker.model.tasks;

import android.content.Context;
import de.rndm.droidFaker.generators.WebGenerator;
import de.rndm.droidFaker.model.ReflectedTask;

import java.util.HashMap;

/**
 * User: rndm
 * Date: 04.01.14
 * Time: 20:36
 */
public class SearchTask extends Task implements ReflectedTask {
    private String url;

    public SearchTask() {
    }

    @Override
    public void init(HashMap<String, Object> map) {
        this.url = (String) map.get("url");
    }

    @Override
    public void run(Context context) {
        WebGenerator webGenerator = new WebGenerator(context);
        webGenerator.insert(url);
    }
}
