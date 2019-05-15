package com.ripple.effects.fb.java.network;

import android.content.Context;

import com.base.java.core.ModuleConfig;

public class NetworkConfig extends ModuleConfig {
    private Context mContext;
    private String mBaseURL;
    private String mEmail;
    private String mPassword;

    public NetworkConfig(Context context) {
        this.mContext = context;
    }

    public void setUserConfig(String baseURL, String email, String passWord) {
        this.mBaseURL = baseURL;
        this.mEmail = email;
        this.mPassword = passWord;
    }

    @Override
    protected void moduleConfig() {
        new NetworkDataCenter.Builder(mContext)
                .setEmail(mEmail)
                .setBaseURL(mBaseURL)
                .setPassword(mPassword)
                .build();
    }
}
