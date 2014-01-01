package de.rndm.droidFaker.generators.email;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import de.rndm.droidFaker.FixtureSingleton;
import de.rndm.droidFaker.FixtureType;
import de.rndm.droidFaker.fixtures.Fixture;
import de.rndm.droidFaker.fixtures.Text;
import de.rndm.droidFaker.generators.DataGenerator;

import java.util.Random;

/**
 * Created by mkp on 01.01.14.
 */
public class EmailGenerator implements DataGenerator {
    private static final String PREF_EMAIL = "email.count";

    private Context ctx;

    public EmailGenerator(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public void generate(Random random, int amount) {
        Fixture emailFixture = FixtureSingleton.getInstance().getFixture(FixtureType.EMAIL);

        for (int i = 0; i < amount; i++) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{emailFixture.getString(random)});
            intent.putExtra(Intent.EXTRA_SUBJECT, Text.getText(random, 15));
            intent.putExtra(Intent.EXTRA_TEXT   , Text.getText(random, 80));
            try {
                ctx.startActivity(Intent.createChooser(intent, "Send mail..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(ctx, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void reset() {

    }
}
