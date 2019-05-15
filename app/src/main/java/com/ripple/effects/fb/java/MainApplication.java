package com.ripple.effects.fb.java;

import android.app.Application;

import com.base.java.core.CommonManager;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        CommonManager.init(this)
                .showDebug(true)
                .setAppId("Ripples Effect")
                .setAppSecret("Nothing")
                .build();
    }
}
