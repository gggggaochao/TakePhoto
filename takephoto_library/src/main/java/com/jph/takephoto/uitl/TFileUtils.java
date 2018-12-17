package com.jph.takephoto.uitl;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;

/**
 * Author: crazycodeboy
 * Date: 2016/11/5 0007 20:10
 * Version:4.0.0
 * 技术博文：http://www.devio.org/
 * GitHub:https://github.com/crazycodeboy
 * Eamil:crazycodeboy@gmail.com
 */
public class TFileUtils {
    private static final String TAG = "TFileUtils";
    private static String DEFAULT_DISK_CACHE_DIR = "cache";

    private static String sCachePath = null;

    public static void setCachePath(String cachePath) {
        sCachePath = cachePath;
    }

    public static File getPhotoCacheDir(Context context, File file) {
        File cacheDir = context.getCacheDir();
        if (!TextUtils.isEmpty(sCachePath )) {
            cacheDir = new File(sCachePath);
        }
        if (cacheDir != null) {
            File mCacheDir = new File(cacheDir, DEFAULT_DISK_CACHE_DIR);
            if (!mCacheDir.mkdirs() && (!mCacheDir.exists() || !mCacheDir.isDirectory())) {
                return file;
            } else {
                return new File(mCacheDir, file.getName());
            }
        }
        if (Log.isLoggable(TAG, Log.ERROR)) {
            Log.e(TAG, "default disk cache dir is null");
        }
        return file;
    }

    public static void delete(String path) {
        try {
            if (path == null) {
                return;
            }
            File file = new File(path);
            if (!file.delete()) {
                file.deleteOnExit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
