package com.base.java.core.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public final class AnimationUtils {

    public static final int DURATION_PLAY_BUTTON = 600;
    public static final int DURATION_RIPPLE = 300;

    // Ripple animation for play button doesn't work in ToolbarLayout, using this kind of animation instead of
    public static void animationRipple(final View view, final int delay) {
        int cx = view.getWidth() / 2;
        int cy = view.getHeight() / 2;

        int finalRadius = Math.max(view.getWidth(), view.getHeight());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);

            anim.setInterpolator(new AccelerateDecelerateInterpolator());
            anim.setStartDelay(delay);
            anim.setDuration(1200);
            view.setVisibility(VISIBLE);

            anim.start();

            view.animate().alpha(0f).setDuration(300).setStartDelay(200 + delay)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            view.setAlpha(1f);
                            view.setVisibility(GONE);
                        }
                    });
        }
    }

    // Ripple animation for play button doesn't work in ToolbarLayout, using this kind of animation instead of
    public static void animationRipple(final View view, final int delay, int duration) {
        int cx = view.getWidth() / 2;
        int cy = view.getHeight() / 2;

        int finalRadius = Math.max(view.getWidth(), view.getHeight());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);

            anim.setInterpolator(new AccelerateDecelerateInterpolator());
            anim.setStartDelay(delay);
            anim.setDuration(duration);
            view.setVisibility(VISIBLE);

            anim.start();

            view.animate().alpha(0f).setDuration(duration / 3).setStartDelay(duration / 6 + delay)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            view.setAlpha(1f);
                            view.setVisibility(GONE);
                        }
                    });
        }
    }

    public static void animateFlip2Views(final View viewStart, final View viewEnd) {
        viewStart.animate().scaleX(0).setDuration(150)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        viewStart.setVisibility(View.INVISIBLE);
                        viewEnd.setVisibility(VISIBLE);
                        viewEnd.animate().scaleX(1f).setDuration(150).setInterpolator(new DecelerateInterpolator(1))
                                .start();
                    }
                }).start();
    }

    public static void animationRippleInside(final View view, int duration) {
        view.animate().cancel();
        view.setAnimation(null);
        int cx = view.getWidth() / 2;
        int cy = view.getHeight() / 2;

        int finalRadius = Math.min(view.getWidth(), view.getHeight()) / 2;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);

            anim.setInterpolator(new AccelerateDecelerateInterpolator());
            anim.setDuration(duration);
            anim.start();
            view.setVisibility(VISIBLE);

            view.animate().setInterpolator(new AccelerateDecelerateInterpolator()).alpha(0f).setDuration(duration)
                    .setListener(new SimpleAnimate() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            view.setVisibility(GONE);
                            view.setAlpha(1f);
                        }
                    }).start();
        }
    }
}
