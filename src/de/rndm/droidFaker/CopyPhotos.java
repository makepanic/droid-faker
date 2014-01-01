package de.rndm.droidFaker;

import android.os.Environment;
import android.util.Log;
import android.widget.ArrayAdapter;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import de.rndm.droidFaker.model.FilePath;
import de.rndm.droidFaker.model.Filter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mkp on 01.01.14.
 */
public class CopyPhotos {
    public static void copyInDir(FilePath srcDir){
        if (srcDir.toFile().exists()) {
            String srcDirString = srcDir.toString();
            List<String> filesInDir = Arrays.asList(srcDir.toFile().list());

            for(String file : filesInDir){
                String fullPath = srcDirString + file;
                CopyPhotos.copyOne(new FilePath(fullPath), new FilePath(file));
            }
        }
    }

    public static void copyOne(FilePath srcFilePath, FilePath srcFilename){
        // /storage/sdcard0/Pictures
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);

        String outputPath = storageDir + "/" + srcFilename.toString();

        InputStream in = null;
        OutputStream out = null;

        try {
            in = new FileInputStream(srcFilePath.toString());
            out = new FileOutputStream(storageDir + "/" + srcFilename);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;

            // write the output file (You have now copied the file)
            out.flush();
            out.close();
            out = null;

        }  catch (FileNotFoundException fnfe1) {
            Log.e("tag", fnfe1.getMessage());
        }
        catch (Exception e) {
            Log.e("tag", e.getMessage());
        }

    }
}
