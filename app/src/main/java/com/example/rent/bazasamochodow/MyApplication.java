package com.example.rent.bazasamochodow;

import android.app.Application;

import com.facebook.stetho.*;


/**
 * Created by RENT on 2017-03-25.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        boolean isInDebug = BuildConfig.DEBUG;
        Stetho.initializeWithDefaults(this);
    }
}
