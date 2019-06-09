package com.ripple.effects.fb.java.module.tutorial;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ripple.effects.fb.java.R;

public class TutorialFragment extends Fragment {

    private final int[] PAGE_LAYOUT = {R.layout.fragment_tutorial_one, R.layout.fragment_tutorial_two,
            R.layout.fragment_tutorial_three};

    int currentPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(PAGE_LAYOUT[currentPage], container, false);
        return view;
    }

    public static TutorialFragment newInstance(int currentPage) {

        TutorialFragment f = new TutorialFragment();
        f.setCurrentPage(currentPage);
        return f;
    }


    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    void onCloseButtonTouched() {

    }
}