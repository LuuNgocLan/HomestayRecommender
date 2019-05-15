package com.base.java.core.utils;

import android.animation.Animator;
import android.view.View;

public class AnimateUtils {
    private static final int animateDuration = 300;

    public static boolean translateIn(View view, int width) {
        if (view.getVisibility() == View.GONE) {
            view.setTranslationX(width);
            view.setAlpha(1f);
            view.setVisibility(View.VISIBLE);
            view.animate().translationX(0).setDuration(animateDuration).setListener(new SimpleAnimate()).start();
            return true;
        }
        return false;
    }

    public static boolean translateOut(final View view, int width) {
        if (view.getVisibility() == View.VISIBLE) {
            view.setTranslationX(0);
            view.animate().translationX(-width).setDuration(animateDuration).setListener(new SimpleAnimate() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    view.setVisibility(View.GONE);
                }
            }).start();
            return true;
        }
        return false;
    }

    public static boolean fadeIn(View view) {
        view.setAlpha(0f);
        view.setTranslationX(0);
        view.setVisibility(View.VISIBLE);
        view.animate().alpha(1f).setDuration(animateDuration).setListener(new SimpleAnimate()).start();
        return true;
    }

    public static boolean fadeOut(final View view) {
        view.animate().alpha(0f).setDuration(animateDuration).setListener(new SimpleAnimate() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }
        }).start();
        return true;
    }
}
