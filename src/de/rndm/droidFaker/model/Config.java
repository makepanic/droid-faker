package de.rndm.droidFaker.model;

import de.rndm.droidFaker.fixtures.*;

public class Config {
    private Fixtures fixtures;
    private int seed;
    private int contacts;
    private int sms;
    private int calls;
    private int websites;
    private int bookmarks;
    private int history;
    private int search;
    private int wifi;
    private FixturesHolder fixturesHolder;

    @Override
    public String toString() {
        return "Config{" +
                "fixtures=" + fixtures +
                ", seed=" + seed +
                ", contacts=" + contacts +
                ", sms=" + sms +
                ", calls=" + calls +
                ", websites=" + websites +
                ", bookmarks=" + bookmarks +
                ", history=" + history +
                ", search=" + search +
                ", wifi=" + wifi +
                '}';
    }

    public FixturesHolder buildFixturesHolder(){
        if (fixturesHolder == null){
            fixturesHolder = new FixturesHolder(
                    new Fixture(fixtures.getCity()),
                    new Fixture(fixtures.getCompany()),
                    new Fixture(fixtures.getCountry()),
                    new Fixture(fixtures.getName()),
                    new Fixture(fixtures.getNickname()),
                    new Fixture(fixtures.getStreet()),
                    new Fixture(fixtures.getTitle()),
                    new Fixture(fixtures.getUrl()),
                    new Fixture(fixtures.getSsid())
            );
        }

        return fixturesHolder;
    }

    public Fixtures getFixtures() {
        return fixtures;
    }

    public void setFixtures(Fixtures fixtures) {
        this.fixtures = fixtures;
    }

    public int getWifi() {
        return wifi;
    }

    public void setWifi(int wifi) {
        this.wifi = wifi;
    }

    public int getCalls() {
        return calls;
    }

    public void setCalls(int calls) {
        this.calls = calls;
    }

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public int getContacts() {
        return contacts;
    }

    public void setContacts(int contacts) {
        this.contacts = contacts;
    }

    public int getSms() {
        return sms;
    }

    public void setSms(int sms) {
        this.sms = sms;
    }

    public int getWebsites() {
        return websites;
    }

    public void setWebsites(int websites) {
        this.websites = websites;
    }

    public int getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(int bookmarks) {
        this.bookmarks = bookmarks;
    }

    public int getHistory() {
        return history;
    }

    public void setHistory(int history) {
        this.history = history;
    }

    public int getSearch() {
        return search;
    }

    public void setSearch(int search) {
        this.search = search;
    }
}
