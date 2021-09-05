package com.base.java.core.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.base.java.core.helper.PreferencesHelper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

import com.auth0.android.jwt.JWT;

public class InstallUtils {

    private static final String PREF_UNIQUE_ID = "PREF_UNIQUE_ID";
    private static final String PREF_ACCESS_TOKEN = "PREF_ACCESS_TOKEN";

    private static String mUniqueID;
    private static String mAccessToken;
    private static String mAppSignature;

    public synchronized static String getUUID(Context context) {
        if (TextUtils.isEmpty(mUniqueID)) {
            SharedPreferences sharedPrefs = context.getSharedPreferences(PREF_UNIQUE_ID, Context.MODE_PRIVATE);
            mUniqueID = sharedPrefs.getString(PREF_UNIQUE_ID, null);
            if (TextUtils.isEmpty(mUniqueID)) {
                mUniqueID = UUID.randomUUID().toString();
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString(PREF_UNIQUE_ID, mUniqueID);
                editor.commit();
            }
        }
        return mUniqueID;
    }

    public synchronized static String getAccessToken(Context context) {
        if (TextUtils.isEmpty(mAccessToken)) {
            SharedPreferences sharedPrefs = context.getSharedPreferences(PREF_ACCESS_TOKEN, Context.MODE_PRIVATE);
            mAccessToken = sharedPrefs.getString(PREF_ACCESS_TOKEN, null);
        }
        return mAccessToken;
    }

    public synchronized static String getAppSignature(Context context, String prefixSignature) {
        if (TextUtils.isEmpty(mAppSignature)) {
            mAppSignature = encodeMD5(prefixSignature + getUUID(context));
        }
        return mAppSignature;
    }


    public static String getDeviceToken(@NonNull final Context context) {
        return PreferencesHelper.readString(context, "deviceName", "");
    }

    public static void setAccessToken(Context context, String accessToken) {
        mAccessToken = accessToken;
        SharedPreferences sharedPrefs = context.getSharedPreferences(PREF_ACCESS_TOKEN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(PREF_ACCESS_TOKEN, accessToken);
        editor.commit();
    }

    public static String encodeMD5(String str) {
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest
                    .getInstance("MD5");
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean isTokenExpire(final String accessToken){
        String local = accessToken;
        boolean isExpired = true;
        if (!TextUtils.isEmpty(local)) {
            local = local.replace("Bearer ", "");
            JWT jwt = new JWT(local);
            isExpired = jwt.isExpired(1); // 10 seconds leeway
            Date expiresAt = jwt.getExpiresAt();
            LogUtils.d("isExpired : " + isExpired);
            LogUtils.d("expiresAt : " + expiresAt);
            return isExpired;
        }
        LogUtils.d("isExpired : " + isExpired);
        return isExpired;
    }
}
