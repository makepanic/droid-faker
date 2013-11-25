package de.rndm.droidFaker.generators.web;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import de.rndm.droidFaker.Callback;
import de.rndm.droidFaker.fixtures.Url;
import de.rndm.droidFaker.generators.AsyncGenerator;
import de.rndm.droidFaker.generators.DataGenerator;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: mkp
 * Date: 24.11.13
 * Time: 14:28
 * To change this template use File | Settings | File Templates.
 */
public class WebGenerator implements DataGenerator {

    public static final String PREF_COUNT = "web.count";

    private Context context;

    public WebGenerator(Context context) {
        this.context = context;
    }

    @Override
    public void generate(Random random, int amount) {
        for (int i = 0; i < amount; i++) {
            Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse(Url.getOne(random)));
            context.startActivity(viewIntent);
        }
    }

    @Override
    public void reset() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
