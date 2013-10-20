package de.rndm.droidFaker.model;

import de.rndm.droidFaker.AppPreferences;

/**
 * Created with IntelliJ IDEA.
 * User: mkp
 * Date: 20.10.13
 * Time: 18:53
 * To change this template use File | Settings | File Templates.
 */
public interface SettingsModel {
    public void persist(AppPreferences preferences);
}
