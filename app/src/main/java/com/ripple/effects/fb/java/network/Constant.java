package com.ripple.effects.fb.java.network;


import com.ripple.effects.fb.java.BuildConfig;

public class Constant {

    public static enum API_MODE {
        staging, testing, production
    }

    public static final boolean sendCrashReport = false;
    public static API_MODE mode = BuildConfig.DEBUG ? API_MODE.testing : API_MODE.production;
    public static final String baseUrl = mode == API_MODE.testing ?
            "http://api.fb-testing.io/v1/" :
            "http://api.fb-live.io/v1/";
}
