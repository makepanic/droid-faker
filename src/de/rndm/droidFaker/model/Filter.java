package de.rndm.droidFaker.model;

import com.google.common.base.Predicate;

/**
 * Created by mkp on 01.01.14.
 */
public class Filter {
    public static Predicate<String> isApk = new Predicate<String>() {
        @Override public boolean apply(String string) {
            return (string.endsWith(".apk"));
        }
    };
}
