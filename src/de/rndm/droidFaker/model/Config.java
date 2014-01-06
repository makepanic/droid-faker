package de.rndm.droidFaker.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Config {
    private HashMap<String, String[]> fixtures;
    private ArrayList<HashMap<String, Object>> tasks;
    private int seed;
    private int contacts;
    private int sms;
    private int calls;
    private int websites;
    private int bookmarks;
    private int history;
    private int search;
    private int wifi;
    private int email;

    private TaskHolder taskHolder;
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
                ", email=" + email +
                ", fixturesHolder=" + fixturesHolder +
                '}';
    }

    public FixturesHolder buildFixturesHolder(){
        if (fixturesHolder == null){
            fixturesHolder = new FixturesHolder(fixtures);
        }

        return fixturesHolder;
    }

    public TaskHolder buildTaskHolder(){
        if (taskHolder == null){
            taskHolder = new TaskHolder(tasks);
        }

        return taskHolder;
    }

    public int getEmail() {
        return email;
    }

    public void setEmail(int email) {
        this.email = email;
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
