package com.base.java.mvp;


import androidx.fragment.app.Fragment;

public interface IBaseContainerFragment {
    void addChildFragment(Fragment fragment, boolean addToBackStack, boolean animate);

    Fragment getCurrentFragment();

    boolean popToRoot();
}
