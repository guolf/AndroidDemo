package cn.guolf.androiddemo.util;

import android.util.Log;

import cn.guolf.androiddemo.BuildConfig;

/**
 * Created by glf on 2015/4/30.
 */
public class LogUtil {
    public static boolean openLog = BuildConfig.DEBUG;
    private static String TAG = "cn.guolf.androiddemo";

    public static void d(String msg) {
        if (openLog) {
            Log.d(TAG, msg);
        }
    }

    public static void v(String msg) {
        if (openLog) {
            Log.v(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (openLog) {
            Log.e(TAG, msg);
        }
    }

    public static void e(String msg, Exception e) {
        if (openLog) {
            Log.e(TAG, msg, e);
        }
    }

    public static void i(String msg) {
        if (openLog) {
            Log.i(TAG, msg);
        }
    }

    public static void w(String msg) {
        if (openLog) {
            Log.w(TAG, msg);
        }
    }

    public static void d(String TAG, String msg) {
        if (openLog) {
            Log.d(TAG, msg);
        }
    }

    public static void v(String TAG, String msg) {
        if (openLog) {
            Log.v(TAG, msg);
        }
    }

    public static void e(String TAG, String msg) {
        if (openLog) {
            Log.e(TAG, msg);
        }
    }

    public static void i(String TAG, String msg) {
        if (openLog) {
            Log.i(TAG, msg);
        }
    }

    public static void w(String TAG, String msg) {
        if (openLog) {
            Log.w(TAG, msg);
        }
    }
}
