package com.giftedcat.httplib.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class DtLog {

    enum LogLevel {
        DEBUG,
        INFO,
        WARNING,
        ERROR;
    }

    public DtLog() {
    }

    public static int d(String tag, String msg) {
        println(tag, msg, LogLevel.DEBUG);
        return 0;
    }

    public static int e(String tag, String msg) {
        println(tag, msg, LogLevel.ERROR);
        return 0;
    }

    public static int i(String tag, String msg) {
        println(tag, msg, LogLevel.INFO);
        return 0;
    }

    public static int w(String tag, String msg) {
        println(tag, msg, LogLevel.WARNING);
        return 0;
    }

    @SuppressLint("LongLogTag")
    private static void println(String tag, String msg, LogLevel level) {
        if (tag == null) {
            tag = "DtLog error, TAG is null.";
        }

        if (msg == null) {
            msg = "DtLOG error, message is null.";
        }

        switch (level) {
            case DEBUG:
                Log.d(tag, msg);
                break;
            case INFO:
                Log.i(tag, msg);
                break;
            case WARNING:
                Log.w(tag, msg);
                break;
            case ERROR:
                Log.e(tag, msg);
            default:
                break;
        }

    }

    public static void show(Context context, String tag, String msg, int duration) {
        Toast.makeText(context, tag + ": " + msg, duration).show();
    }

    public static void showMessage(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void showMessageShort(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}
