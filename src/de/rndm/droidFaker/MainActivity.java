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
import android.widget.*;
import de.rndm.droidFaker.generators.calls.CallsGenerator;
import de.rndm.droidFaker.generators.contact.ContactGenerator;
import de.rndm.droidFaker.generators.sms.SmsGenerator;
import de.rndm.droidFaker.model.CallsSettings;
import de.rndm.droidFaker.model.ContactSettings;
import de.rndm.droidFaker.model.SmsSettings;

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

    private long seed = 0;

    private ContactGenerator contactGenerator;
    private SmsGenerator smsGenerator;
    private CallsGenerator callsGenerator;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        appPreferences = new AppPreferences(this);

        contactGenerator = new ContactGenerator(getContentResolver());
        smsGenerator = new SmsGenerator(getContentResolver());
        callsGenerator = new CallsGenerator(getContentResolver());

        Date seedDate = new Date();
        seedText = (EditText) findViewById(R.id.seed);
        seedText.setText("" + seedDate.getTime());

        viewAnimator = (ViewAnimator) findViewById(R.id.viewAnimator);
        execTask = (TextView) findViewById(R.id.execTask);

        final Animation inAnim = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        final Animation outAnim = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);

        viewAnimator.setInAnimation(inAnim);
        viewAnimator.setOutAnimation(outAnim);

        buttonGenerateData = (Button) findViewById(R.id.buttonCreate);
        buttonResetData = (Button) findViewById(R.id.buttonReset);

        buttonGenerateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncTask<Void, Void, Void>(){
                    @Override
                    protected void onPreExecute() {
                        execTask.setText("Daten werden erstellt.");
                        viewAnimator.showNext();
                    }
                    @Override
                    protected Void doInBackground(Void... voids) {
                        seed = Long.valueOf(seedText.getText().toString());
                        Random random = new Random(seed);
                        contactGenerator.generate(random, appPreferences.getInteger(ContactSettings.PREF_COUNT, 100));
                        smsGenerator.generate(random, appPreferences.getInteger(SmsSettings.PREF_COUNT, 100));
                        callsGenerator.generate(random, appPreferences.getInteger(CallsSettings.PREF_COUNT, 100));
                        return null;
                    }
                    @Override
                    protected void onPostExecute(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Daten erfolgreich erstellt",
                                Toast.LENGTH_LONG).show();
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
