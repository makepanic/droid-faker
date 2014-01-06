package de.rndm.droidFaker.model.tasks;

import android.content.Context;
import de.rndm.droidFaker.generators.ContactGenerator;
import de.rndm.droidFaker.model.ReflectedTask;

import java.util.HashMap;

/**
 * User: rndm
 * Date: 04.01.14
 * Time: 20:36
 */
public class ContactTask extends Task implements ReflectedTask {
    private String number;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String postCode;
    private String country;
    private String company;
    private String title;
    private String nickname;

    public ContactTask() {
    }

    @Override
    public void init(HashMap<String, Object> map) {
        this.number = (String) map.get("number");
        this.firstName = (String) map.get("firstName");
        this.lastName = (String) map.get("lastName");
        this.street = (String) map.get("street");
        this.city = (String) map.get("city");
        this.postCode = (String) map.get("postCode");
        this.country = (String) map.get("country");
        this.company = (String) map.get("company");
        this.title = (String) map.get("title");
        this.nickname = (String) map.get("nickname");
    }

    @Override
    public void run(Context context) {
        ContactGenerator contactGenerator = new ContactGenerator(context.getContentResolver());
        contactGenerator.insert(number,firstName,lastName,street,city,postCode,country, company, title, nickname);
    }
}
