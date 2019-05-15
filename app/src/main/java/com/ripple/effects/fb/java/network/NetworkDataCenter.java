package com.ripple.effects.fb.java.network;

import android.content.Context;
import android.text.TextUtils;

import java.util.HashMap;

public class NetworkDataCenter<T> {
    private static NetworkDataCenter sInstance;
    private Builder mBuilder;

    public NetworkDataCenter(Builder builder) {
        this.mBuilder = builder;
    }

    public static NetworkDataCenter getInstance(Builder builder) {
        if (sInstance == null) {
            sInstance = new NetworkDataCenter(builder);
        }
        return sInstance;
    }

    public static NetworkDataCenter getInstance() {
        return sInstance;
    }

    public void setApiAccessToken(String accessToken) {
        mBuilder.setAccessToken(accessToken);
    }

    public void setApiEmail(String email) {
        mBuilder.setEmail(email);
    }

    public void setApiPassword(String password) {
        mBuilder.setPassword(password);
    }

    public void updateHeaderConfig(String token, String email, String password) {
        setApiAccessToken(token);
        setApiEmail(email);
        setApiPassword(password);
    }

    public Builder getBuilder() {
        return mBuilder;
    }

    public static class Builder {
        private String mBaseURL;
        private String mAccessToken;
        private String mEmail;
        private String mPassword;
        private HashMap<String, String> mHeaders = new HashMap<>();
        private Context mContext;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setBaseURL(String baseURL) {
            this.mBaseURL = baseURL;
            return this;
        }

        public Builder setEmail(String email) {
            this.mEmail = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.mPassword = password;
            return this;
        }

        public void build() {
            NetworkDataCenter.getInstance(this);
        }

        public HashMap<String, String> getHeaders() {
            if (!TextUtils.isEmpty(mAccessToken)) {
                mHeaders.put(HeaderConfig.AUTHORIZATION, mAccessToken);
            } else {
                mHeaders.put(HeaderConfig.EMAIL, mEmail);
                mHeaders.put(HeaderConfig.PASSWORD, mPassword);
            }
            return mHeaders;
        }

        public void setAccessToken(String mAccessToken) {
            this.mAccessToken = mAccessToken;
        }

        public String getAccessToken() {
            return mAccessToken;
        }

        public String getBaseURL() {
            return mBaseURL;
        }
    }
}
