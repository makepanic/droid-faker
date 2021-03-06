package de.rndm.droidFaker.model;

/**
 * User: rndm
 * Date: 31.12.13
 * Time: 00:33
*/

/**
 * Fixture type that represents fields from the config file
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
