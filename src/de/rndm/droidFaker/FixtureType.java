package de.rndm.droidFaker;

/**
 * Created by mkp on 31.12.13.
 */
public enum FixtureType {
    CITY("city"), COMPANY("company"), COUNTRY("country"), NAME("name"), NICKNAME("nickname"), STREET("street"), TITLE("title"), URL("url"), SSID("ssid"), EMAIL("email");

    private final String text;
    private FixtureType(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
