package com.ripple.effects.fb.java.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ImageViewCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public final class ViewUtils {

    public static final float DISABLE_BUTTON_ALPHA = 0.5f;
    public static final float ENABLE_BUTTON_ALPHA = 1f;

    public static void preventFastTouching(final View view) {
        view.setEnabled(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setEnabled(true);
            }
        }, 700);
    }

    public static void delaySetAlphaEnable(final View view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setAlpha(ENABLE_BUTTON_ALPHA);
            }
        }, 100);
    }

    public static int getTextWidth(TextView textView, String text) {
        Rect bounds = new Rect();
        Paint textPaint = textView.getPaint();
        textPaint.getTextBounds(text, 0, text.length(), bounds);
        return bounds.width();
    }

    public static int getTextHeight(TextView textView, String text) {
        Rect bounds = new Rect();
        Paint textPaint = textView.getPaint();
        textPaint.getTextBounds(text, 0, text.length(), bounds);
        return bounds.height();
    }

    public static void delayEnableViewAfterTouch(final View view) {
        delayEnableViewAfterTouch(view, 700);
    }

    public static void delayEnableViewAfterTouch(final View view, int duration) {
        view.setClickable(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setClickable(true);
            }
        }, duration);
    }

    public static void setTintColor(Context context, ImageView imageView, int color) {
        ImageViewCompat.setImageTintList(imageView, ColorStateList.valueOf(ContextCompat.getColor(context, color)));
    }

    public static void setTintColor(ImageView imageView, @ColorInt int color) {
        ImageViewCompat.setImageTintList(imageView, ColorStateList.valueOf(color));
    }
}
