package de.rndm.droidFaker.generators.web;

import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import de.rndm.droidFaker.Callback;
import de.rndm.droidFaker.fixtures.Url;
import de.rndm.droidFaker.generators.AsyncGenerator;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: mkp
 * Date: 24.11.13
 * Time: 14:28
 * To change this template use File | Settings | File Templates.
 */
public class WebGenerator implements AsyncGenerator {
    WebView webView;
    WebViewClient webViewClient;

    public WebGenerator(WebView webView) {
        this.webView = webView;
        this.webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        // enable js
        webView.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    public void generate(Random random, int amount, final Callback callback) {


        // dirty hack to get a integer inside the webView callback
        final int[] currentUrl = {0};
        final ArrayList<String> urls = new ArrayList<String>();

        webViewClient = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, android.graphics.Bitmap favicon) {
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.i("onPageFinished", url);

                ++currentUrl[0];

                if(currentUrl[0] < urls.size()) {

                    // has more pages
                    Log.i("next", urls.get(currentUrl[0]));
                    view.loadUrl(urls.get(currentUrl[0]));
                } else {

                    // no more pages, call callback
                    callback.callingBack();
                }
            }
        };

        webView.setWebViewClient(webViewClient);

        for(int i = 0; i < amount; i++) {
            urls.add(Url.getOne(random));
        }

        webView.loadUrl(urls.get(currentUrl[0]));
    }

    @Override
    public void reset() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
