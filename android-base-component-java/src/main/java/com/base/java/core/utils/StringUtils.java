package com.base.java.core.utils;

public class StringUtils {

    public static String generateValidUrl(String key) {
        if (key == null || key.isEmpty())
            return "";

//        if (key.contains("http://") || key.contains("https://")) {
//            return key;
//        }
//        return BuildConfig.PHOTO_DOMAIN + key;
        return key;
    }
}
