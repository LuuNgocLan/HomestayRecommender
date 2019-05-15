package com.base.java.core.helper;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.widget.TextView;

public class TextViewHelper {
    public static StaticLayout getTextBound(Context context, float spTextSize, String data, int width) {
        return getTextBound(context, spTextSize, data, width, null);
    }

    public static StaticLayout getTextBound(Context context, float spTextSize, String data, int width, Typeface typeface) {
        TextPaint myTextPaint = new TextPaint();
        myTextPaint.setAntiAlias(true);
        if (typeface != null) {
            myTextPaint.setTypeface(typeface);
        }
        float scaledSizeInPixels = spTextSize * context.getResources().getDisplayMetrics().scaledDensity;
        myTextPaint.setTextSize(scaledSizeInPixels);
        myTextPaint.setColor(0xFF000000);
        Layout.Alignment alignment = Layout.Alignment.ALIGN_NORMAL;
        float spacingMultiplier = 1;
        float spacingAddition = 0;
        boolean includePadding = false;

        //noinspection deprecation
        return new StaticLayout(data, myTextPaint, width, alignment, spacingMultiplier, spacingAddition, includePadding);
    }

    public int getTextWidth(TextView view, String data) {
        return (int) view.getPaint().measureText(data);
    }
}
