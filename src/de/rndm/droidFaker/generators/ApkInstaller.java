package de.rndm.droidFaker.generators;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;

/**
 * User: rndm
 */
public class ApkInstaller {
    private final Context context;

    public ApkInstaller(Context context) {
        this.context = context;
    }

    public void installApk(String path){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(path)), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }
}
