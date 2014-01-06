package de.rndm.droidFaker.model;

import java.io.File;

/**
 * Class uses a file path and stores a {@link java.io.File} representing the path
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
