package de.rndm.droidFaker.model.tasks;

import android.content.Context;
import de.rndm.droidFaker.generators.history.HistoryGenerator;
import de.rndm.droidFaker.generators.search.SearchGenerator;
import de.rndm.droidFaker.generators.web.WebGenerator;
import de.rndm.droidFaker.model.ReflectedTask;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Christian Schulz - mkp
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
