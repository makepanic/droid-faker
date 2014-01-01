package de.rndm.droidFaker.model;

import java.io.File;

/**
 * Created by mkp on 01.01.14.
 */
public class FilePath {
    private File file;
    private String stringPath;

    public FilePath(String stringPath) {
        this.stringPath = stringPath;
        this.file = new File(stringPath);
    }

    public File toFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return stringPath;
    }

    public void setStringPath(String stringPath) {
        this.stringPath = stringPath;
    }
}
