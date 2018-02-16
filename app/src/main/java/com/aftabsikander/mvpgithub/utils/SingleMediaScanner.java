package com.aftabsikander.mvpgithub.utils;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;

import java.io.File;

import timber.log.Timber;

/**
 * Created by aftabsikander on 2/16/2018.
 */

public class SingleMediaScanner implements MediaScannerConnectionClient {

    private MediaScannerConnection mMs;
    private File mFile;

    public SingleMediaScanner(Context context, File f) {
        mFile = f;
        mMs = new MediaScannerConnection(context, this);
        mMs.connect();
    }

    /**
     * Called to notify the client when a connection to the
     * MediaScanner service has been established.
     */
    @Override
    public void onMediaScannerConnected() {
        mMs.scanFile(mFile.getAbsolutePath(), null);
    }

    /**
     * Called to notify the client when the media scanner has finished
     * scanning a file.
     *
     * @param path the path to the file that has been scanned.
     * @param uri  the Uri for the file if the scanning operation succeeded
     */
    @Override
    public void onScanCompleted(String path, Uri uri) {
        Timber.i("ExternalStorage" + " Scanned " + path + ":");
        Timber.i("ExternalStorage" + " -> uri=" + uri);
        mMs.disconnect();

    }
}
