package com.base.java.core.helper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

public final class DialogHelper {

    public static Dialog createSimpleDialog(Context context, String title, String message, String button) {
        return createSimpleDialog(context, title, message, button, null);
    }

    public static Dialog createSimpleDialog(Context context, String title, String message, String button, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(button, listener);
        return alertDialog.create();
    }

    public static Dialog createSimpleDialog(Context context,
                                            int titleResource,
                                            int messageResource,
                                            int buttonResource) {

        return createSimpleDialog(context,
                context.getString(titleResource),
                context.getString(messageResource),
                context.getString(buttonResource), null);
    }

    public static ProgressDialog createProgressDialog(Context context, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        return progressDialog;
    }

    public static ProgressDialog createProgressDialog(Context context,
                                                      int messageResource) {
        return createProgressDialog(context, context.getString(messageResource));
    }

}

