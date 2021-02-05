package com.giftedcat.demo.application;

import android.app.Application;

public class MainApplication extends Application {

    private static MainApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static MainApplication sharedInstance() {
        return mInstance;
    }

}
