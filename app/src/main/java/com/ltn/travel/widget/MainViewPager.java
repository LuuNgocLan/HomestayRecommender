package com.ltn.travel.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class MainViewPager
        extends ViewPager {

    private boolean swipeLocked;

    public MainViewPager(@NonNull Context context) {
        super(context);
    }

    public MainViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean isSwipeLocked() {
        return swipeLocked;
    }

    public void setSwipeLocked(boolean swipeLocked) {
        this.swipeLocked = swipeLocked;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return !swipeLocked && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return !swipeLocked && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean canScrollHorizontally(int direction) {
        return !swipeLocked && super.canScrollHorizontally(direction);
    }
}
