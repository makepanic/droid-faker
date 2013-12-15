package de.rndm.droidFaker.model;

public class Config {
    private int seed;
    private int contacts;
    private int sms;
    private int calls;
    private int websites;
    private int bookmarks;
    private int history;
    private int search;
    private int wifi;

    @Override
    public String toString() {
        return "Config{" +
                "seed='" + seed + '\'' +
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
