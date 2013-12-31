package de.rndm.droidFaker.model;

import java.util.Arrays;

/**
 * Created by mkp on 31.12.13.
 */
public class Fixtures {
    private String[] city;
    private String[] company;
    private String[] country;
    private String[] name;
    private String[] nickname;
    private String[] street;
    private String[] title;
    private String[] url;
    private String[] ssid;

    @Override
    public String toString() {
        return "Fixtures{" +
                "city=" + Arrays.toString(city) +
                ", company=" + Arrays.toString(company) +
                ", country=" + Arrays.toString(country) +
                ", name=" + Arrays.toString(name) +
                ", nickname=" + Arrays.toString(nickname) +
                ", street=" + Arrays.toString(street) +
                ", title=" + Arrays.toString(title) +
                ", url=" + Arrays.toString(url) +
                ", ssid=" + Arrays.toString(ssid) +
                '}';
    }

    public String[] getCity() {
        return city;
    }

    public void setCity(String[] city) {
        this.city = city;
    }

    public String[] getCompany() {
        return company;
    }

    public void setCompany(String[] company) {
        this.company = company;
    }

    public String[] getCountry() {
        return country;
    }

    public void setCountry(String[] country) {
        this.country = country;
    }

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public String[] getNickname() {
        return nickname;
    }

    public void setNickname(String[] nickname) {
        this.nickname = nickname;
    }

    public String[] getStreet() {
        return street;
    }

    public void setStreet(String[] street) {
        this.street = street;
    }

    public String[] getTitle() {
        return title;
    }

    public void setTitle(String[] title) {
        this.title = title;
    }

    public String[] getUrl() {
        return url;
    }

    public void setUrl(String[] url) {
        this.url = url;
    }

    public String[] getSsid() {
        return ssid;
    }

    public void setSsid(String[] ssid) {
        this.ssid = ssid;
    }
}
