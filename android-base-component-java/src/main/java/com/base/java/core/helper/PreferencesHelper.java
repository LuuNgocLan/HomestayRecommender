package com.base.java.core.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public final class PreferencesHelper {

    public static final String TOKEN = "TOKEN";
    private static final String PREF_NAME = "APP_PREF";
    private static final int MODE = Context.MODE_PRIVATE;
    public static final String CURRENCY = "CURRENCY";
    public static final String LANGUAGE = "LANGUAGE";
    public static final String MEASURE = "MEASURE";
    public static final String ISlOGGED = "IS_LOGGED";

    public static void writeBoolean(Context context, String key, boolean value) {
        getEditor(context).putBoolean(key, value).commit();
    }

    public static boolean readBoolean(Context context, String key,
                                      boolean defValue) {
        return getPreferences(context).getBoolean(key, defValue);
    }

    public static void writeInteger(Context context, String key, int value) {
        getEditor(context).putInt(key, value).commit();

    }

    public static int readInteger(Context context, String key, int defValue) {
        return getPreferences(context).getInt(key, defValue);
    }

    public static void writeString(Context context, String key, String value) {
        getEditor(context).putString(key, value).commit();

    }

    public static String readString(Context context, String key, String defValue) {
        return getPreferences(context).getString(key, defValue);
    }

    public static void writeFloat(Context context, String key, float value) {
        getEditor(context).putFloat(key, value).commit();
    }

    public static float readFloat(Context context, String key, float defValue) {
        return getPreferences(context).getFloat(key, defValue);
    }

    public static void writeLong(Context context, String key, long value) {
        getEditor(context).putLong(key, value).commit();
    }

    public static long readLong(Context context, String key, long defValue) {
        return getPreferences(context).getLong(key, defValue);
    }

    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, MODE);
    }

    public static SharedPreferences getPreferences(Context context, String prefName, int mode) {
        return context.getSharedPreferences(prefName, mode);
    }

    public static Editor getEditor(Context context) {
        return getPreferences(context).edit();
    }

}
