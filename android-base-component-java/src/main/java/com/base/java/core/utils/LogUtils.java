package com.base.java.core.utils;

import android.util.Log;

import com.base.java.BuildConfig;
import com.base.java.core.CommonManager;

public class LogUtils {

    private static final boolean DEBUG = BuildConfig.DEBUG;
    private static final String DEBUG_TAG = "COMMON-COMPONENT";

    public static void d(String tag, String msg) {
        try {
            if (DEBUG && CommonManager.getInstance().isShowDebug()) {
                String fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
                String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
                String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
                int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();

                Log.d(DEBUG_TAG + " " + className + "." + methodName + "():" + lineNumber + " " + tag + " ", msg + "");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void d(String msg) {
        try {
            if (DEBUG && CommonManager.getInstance().isShowDebug()) {
                String fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
                String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
                String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
                int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();

                Log.d(DEBUG_TAG + " " + className + "." + methodName + "():" + lineNumber + " ", msg + "");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void i(String tag, String msg) {
        try {
            if (DEBUG && CommonManager.getInstance().isShowDebug()) {
                String fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
                String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
                String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
                int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();

                Log.d(DEBUG_TAG + " " + className + "." + methodName + "():" + lineNumber + " " + tag + " ", msg + "");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void e(String tag, String msg) {
        try {
            if (DEBUG && CommonManager.getInstance().isShowDebug()) {
                String fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
                String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
                String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
                int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();

                Log.e(DEBUG_TAG + " " + className + "." + methodName + "():" + lineNumber + " " + tag + " ", msg + "");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void i(String msg) {
        try {
            if (DEBUG && CommonManager.getInstance().isShowDebug()) {
                String fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
                String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
                String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
                int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();

                Log.i(DEBUG_TAG + " " + className + "." + methodName + "():" + lineNumber + " ", msg + "");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void e(String msg) {
        try {
            if (DEBUG && CommonManager.getInstance().isShowDebug()) {
                String fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
                String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
                String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
                int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();

                Log.e(DEBUG_TAG + " " + className + "." + methodName + "():" + lineNumber + " ", msg + "");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
