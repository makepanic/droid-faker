package de.rndm.droidFaker;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.*;
import de.rndm.droidFaker.generators.bookmark.BookmarkGenerator;
import de.rndm.droidFaker.generators.calls.CallsGenerator;
import de.rndm.droidFaker.generators.contact.ContactGenerator;
import de.rndm.droidFaker.generators.sms.SmsGenerator;
import de.rndm.droidFaker.generators.web.WebGenerator;
import de.rndm.droidFaker.generators.wifi.WifiSettingsGenerator;
import de.rndm.droidFaker.model.*;

import java.util.Date;
import java.util.Random;

public class MainActivity extends Activity {

    private static final int RESULT_SETTINGS = 1;

    private Button buttonGenerateData;
    private Button buttonResetData;
    private EditText seedText;
    private AppPreferences appPreferences;
    private ViewAnimator viewAnimator;
    private TextView execTask;
    private WebView webView;

    private long seed = 0;

    private ContactGenerator contactGenerator;
    private SmsGenerator smsGenerator;
    private CallsGenerator callsGenerator;
    private WifiSettingsGenerator wifiSettingsGenerator;
    private WebGenerator webGenerator;
    private BookmarkGenerator bookmarkGenerator;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        appPreferences = new AppPreferences(this);


        Date seedDate = new Date();
        seedText = (EditText) findViewById(R.id.seed);
        seedText.setText("" + seedDate.getTime());

        webView = (WebView) findViewById(R.id.webView);
        viewAnimator = (ViewAnimator) findViewById(R.id.viewAnimator);
        execTask = (TextView) findViewById(R.id.execTask);
        buttonGenerateData = (Button) findViewById(R.id.buttonCreate);
        buttonResetData = (Button) findViewById(R.id.buttonReset);

        contactGenerator = new ContactGenerator(getContentResolver());
        smsGenerator = new SmsGenerator(getContentResolver());
        callsGenerator = new CallsGenerator(getContentResolver());
        bookmarkGenerator = new BookmarkGenerator(getContentResolver());
        wifiSettingsGenerator = new WifiSettingsGenerator(this);

        webGenerator = new WebGenerator(webView);

        final Animation inAnim = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        final Animation outAnim = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);

        viewAnimator.setInAnimation(inAnim);
        viewAnimator.setOutAnimation(outAnim);

        buttonGenerateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncTask<Void, Void, Random>(){
                    @Override
                    protected void onPreExecute() {
                        execTask.setText("Daten werden erstellt.");
                        viewAnimator.showNext();
                    }
                    @Override
                    protected Random doInBackground(Void... voids) {
                        seed = Long.valueOf(seedText.getText().toString());
                        Random random = new Random(seed);
                        contactGenerator.generate(random, appPreferences.getInteger(ContactSettings.PREF_COUNT, 100));
                        smsGenerator.generate(random, appPreferences.getInteger(SmsSettings.PREF_COUNT, 100));
                        callsGenerator.generate(random, appPreferences.getInteger(CallsSettings.PREF_COUNT, 100));
                        bookmarkGenerator.generate(random, appPreferences.getInteger(BookmarkSettings.PREF_COUNT, 10));
//                        wifiSettingsGenerator.generate(random, appPreferences.getInteger(WifiSettings.PREF_COUNT, 2));
                        return random;
                    }
                    @Override
                    protected void onPostExecute(Random random) {
                        webGenerator.generate(random, appPreferences.getInteger(WebSettings.PREF_COUNT, 10), new Callback() {
                            @Override
                            public void callingBack(Object... params) {

                                Toast.makeText(getApplicationContext(), "Daten erfolgreich erstellt",
                                        Toast.LENGTH_LONG).show();
                                viewAnimator.showPrevious();

                            }
                        });

                    }
                }.execute();
            }
        });

        buttonResetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncTask<Void, Void, Void>(){
                    @Override
                    protected void onPreExecute() {
                        execTask.setText("Daten werden gelöscht.");
                        viewAnimator.showNext();
                    }
                    @Override
                    protected Void doInBackground(Void... voids) {
                        contactGenerator.reset();
                        smsGenerator.reset();
                        callsGenerator.reset();
                        bookmarkGenerator.reset();
                        return null;
                    }
                    @Override
                    protected void onPostExecute(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Daten erfolgreich gelöscht",
                                Toast.LENGTH_LONG).show();
                        viewAnimator.showPrevious();
                    }
                }.execute();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                Intent i = new Intent(this, SettingsActivity.class);
                startActivityForResult(i, RESULT_SETTINGS);
                break;
        }
        return true;
    }
}
