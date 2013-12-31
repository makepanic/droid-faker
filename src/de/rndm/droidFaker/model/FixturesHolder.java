package de.rndm.droidFaker.model;

import de.rndm.droidFaker.FixtureType;
import de.rndm.droidFaker.fixtures.Fixture;

/**
 * Created by mkp on 31.12.13.
 */
public class FixturesHolder {
    private Fixture city;
    private Fixture company;
    private Fixture country;
    private Fixture name;
    private Fixture nickname;
    private Fixture street;
    private Fixture title;
    private Fixture url;
    private Fixture ssid;

    public FixturesHolder(Fixture city, Fixture company, Fixture country, Fixture name, Fixture nickname, Fixture street, Fixture title, Fixture url, Fixture ssid) {
        this.city = city;
        this.company = company;
        this.country = country;
        this.name = name;
        this.nickname = nickname;
        this.street = street;
        this.title = title;
        this.url = url;
        this.ssid = ssid;
    }

    public Fixture getFixture(FixtureType what){
        Fixture result = null;

        switch (what){
            case CITY:
                result = this.city;
                break;
            case COMPANY:
                result = this.company;
                break;
            case COUNTRY:
                result = this.country;
                break;
            case NAME:
                result = this.name;
                break;
            case NICKNAME:
                result = this.nickname;
                break;
            case STREET:
                result = this.street;
                break;
            case TITLE:
                result = this.title;
                break;
            case URL:
                result = this.url;
                break;
            case SSID:
                result = this.ssid;
                break;
        }

        return result;
    }
}
