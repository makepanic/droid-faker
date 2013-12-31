package de.rndm.droidFaker;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import de.rndm.droidFaker.generators.bookmark.BookmarkGenerator;
import de.rndm.droidFaker.generators.calls.CallsGenerator;
import de.rndm.droidFaker.generators.contact.ContactGenerator;
import de.rndm.droidFaker.generators.history.HistoryGenerator;
import de.rndm.droidFaker.generators.search.SearchGenerator;
import de.rndm.droidFaker.generators.sms.SmsGenerator;
import de.rndm.droidFaker.generators.web.WebGenerator;
import de.rndm.droidFaker.generators.wifi.WifiSettingsGenerator;
import de.rndm.droidFaker.model.*;

import java.io.File;
import java.util.*;

public class MainActivity extends Activity {

    private static final String CFG_FILE = "droid-faker.json";
    private static final String CFG_DIR = "droid-faker";
    private static final int RESULT_SETTINGS = 1;

    private Button buttonGenerateData;
    private Button buttonResetData;
    private Button buttonConfig;
    private EditText seedText;
    private AppPreferences appPreferences;
    private ViewAnimator viewAnimator;
    private TextView execTask;
    private Spinner scenarioSpinner;

    private long seed = 0;

    private ContactGenerator contactGenerator;
    private SmsGenerator smsGenerator;
    private CallsGenerator callsGenerator;
    private WifiSettingsGenerator wifiSettingsGenerator;
    private WebGenerator webGenerator;
    private BookmarkGenerator bookmarkGenerator;
    private HistoryGenerator historyGenerator;
    private SearchGenerator searchGenerator;

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
        seedText.setText(appPreferences.getString("seed", "" + seedDate.getTime()));

        viewAnimator = (ViewAnimator) findViewById(R.id.viewAnimator);
        execTask = (TextView) findViewById(R.id.execTask);
        buttonGenerateData = (Button) findViewById(R.id.buttonCreate);
        buttonResetData = (Button) findViewById(R.id.buttonReset);
        buttonConfig = (Button) findViewById(R.id.buttonConfigFile);
        scenarioSpinner = (Spinner) findViewById(R.id.scenarioSpinner);

        String extState = Environment.getExternalStorageState();
        if(!extState.equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(getApplicationContext(), "SDCard nicht gemounted", Toast.LENGTH_LONG).show();
        }
        else {
            final String cfgPath;
            File sd = Environment.getExternalStorageDirectory();
            cfgPath = sd.getAbsolutePath() + "/" + CFG_DIR + "/";

            File cfg = new File(cfgPath);

            if (cfg.exists()) {
                final List<String> list = Arrays.asList(cfg.list());

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                scenarioSpinner.setAdapter(dataAdapter);
                scenarioSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String currentFilePath = cfgPath + list.get(i);
                        Log.i("itemSelect", "selected " + i);
                        ConfigFile configFile = new ConfigFile(currentFilePath));
                        configFile.load();
                        configFile.applyConfig(appPreferences);
                        seedText.setText("" + appPreferences.getInteger("seed"));
                        Toast.makeText(getApplicationContext(), "loading config file from " + currentFilePath, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            } else {
                Toast.makeText(getApplicationContext(), "Keine Config file gefunden " + cfgPath, Toast.LENGTH_LONG).show();
            }
        }

        contactGenerator = new ContactGenerator(getContentResolver());
        smsGenerator = new SmsGenerator(getContentResolver());
        callsGenerator = new CallsGenerator(getContentResolver());
        bookmarkGenerator = new BookmarkGenerator(getContentResolver());
        historyGenerator = new HistoryGenerator(getContentResolver());
        searchGenerator = new SearchGenerator(getContentResolver());
        wifiSettingsGenerator = new WifiSettingsGenerator(this);
        webGenerator = new WebGenerator(this);

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
                        historyGenerator.generate(random, appPreferences.getInteger(HistorySettings.PREF_COUNT, 10));
                        searchGenerator.generate(random, appPreferences.getInteger(SearchSettings.PREF_COUNT, 10));
                        wifiSettingsGenerator.generate(random, appPreferences.getInteger(WifiSettings.PREF_COUNT, 2));
                        webGenerator.generate(random, appPreferences.getInteger(WebGenerator.PREF_COUNT, 10));
                        return random;
                    }
                    @Override
                    protected void onPostExecute(final Random random) {
                        Toast.makeText(getApplicationContext(), "Daten erfolgreich erstellt", Toast.LENGTH_LONG).show();
                        viewAnimator.showPrevious();
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
                        historyGenerator.reset();
                        searchGenerator.reset();
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
