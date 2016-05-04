package com.troy.sendcard.util;

import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chenlongfei on 16/4/17.
 */
public class FileUtil {
    public static File createImageFile() throws IOException {
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File imageFile = File.createTempFile(simpleDateFormat, "jpg", storageDir);
        return imageFile;
    }
}
