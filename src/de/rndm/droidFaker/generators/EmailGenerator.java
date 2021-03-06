package de.rndm.droidFaker.generators;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import de.rndm.droidFaker.model.FixtureSingleton;
import de.rndm.droidFaker.model.FixtureType;
import de.rndm.droidFaker.fixtures.Fixture;
import de.rndm.droidFaker.fixtures.Text;
import de.rndm.droidFaker.generators.DataGenerator;

import java.util.Random;

/**
 * User: rndm
 */
public class EmailGenerator implements DataGenerator {
    private static final String PREF_EMAIL = "email.count";

    private final Context ctx;

    public EmailGenerator(Context ctx) {
        this.ctx = ctx;
    }

    /**
     * starts an intent for the email client with prefilled fields
     * @param receiver email address
     * @param subject email subject
     * @param text email body
     */
    public void insert(String receiver, String subject, String text){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{receiver});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT   , text);
        try {
            ctx.startActivity(Intent.createChooser(intent, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ctx, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Generates a given amount of email intents
     * @param random Random object
     * @param amount number of objects to insert
     */
    @Override
    public void generate(Random random, int amount) {
        Fixture emailFixture = FixtureSingleton.getInstance().getFixture(FixtureType.EMAIL);

        for (int i = 0; i < amount; i++) {
            insert( emailFixture.getString(random),
                    Text.getText(random, 15),
                    Text.getText(random, 80));
        }
    }

    /**
     * does nothing because we dont have access to emails in an external application
     */
    @Override
    public void reset() {
        // no reset because intent
    }
}
