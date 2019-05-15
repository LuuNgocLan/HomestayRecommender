package com.base.java.core.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

public final class BitmapUtils {

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void blur(@NonNull final Context context, final Bitmap bkg, final View view, final float radius) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Bitmap overlay = Bitmap.createBitmap((view.getMeasuredWidth()),
                        (view.getMeasuredHeight()), Bitmap.Config.ARGB_8888);
                Bitmap otBitmap = Bitmap.createBitmap(overlay);
                Canvas canvas = new Canvas(overlay);
                canvas.translate(-view.getLeft(), -view.getTop());
                canvas.drawBitmap(bkg, 0, 0, null);
                RenderScript rs = RenderScript.create(context);
                Allocation overlayAlloc = Allocation.createFromBitmap(
                        rs, overlay);
                Allocation
                        overlayAlloc1 = Allocation.createFromBitmap(
                        rs, otBitmap);
                ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(
                        rs, Element.U8_4(rs));
                blur.setInput(overlayAlloc);
                blur.setRadius(radius);
                blur.forEach(overlayAlloc1);
                overlayAlloc1.copyTo(otBitmap);
                view.setBackground(new BitmapDrawable(
                        context.getResources(), otBitmap));

                rs.destroy();
            }
        });
    }

    public static void blur(final Context context, final Bitmap bitmap, final ImageView imageView) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bm = blur(context, bitmap, 12);
                imageView.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(bm);
                    }
                });

            }
        }).start();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static Bitmap blur(Context context, Bitmap otBitmap, float radius) {
        RenderScript rs = RenderScript.create(context);
        Allocation overlayAlloc = Allocation.createFromBitmap(rs, otBitmap);
        Allocation overlayAlloc1 = Allocation.createFromBitmap(rs, otBitmap);
        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        blur.setInput(overlayAlloc);
        blur.setRadius(radius);
        blur.forEach(overlayAlloc1);
        overlayAlloc1.copyTo(otBitmap);
        rs.destroy();
        return otBitmap;
    }
}
