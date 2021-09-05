package com.ripple.effects.fb.java.module.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.base.java.IBaseListener;
import com.base.java.mvp.BaseContainerFragment;
import com.base.java.mvp.IBasePresenter;
import com.ripple.effects.fb.java.R;
import com.ripple.effects.fb.java.module.favorite.FavoriteFragment;
import com.ripple.effects.fb.java.module.discover.DiscoverFragment;
import com.ripple.effects.fb.java.module.popular.MapFragment;
import com.ripple.effects.fb.java.module.profile.ProfileFragment;


public class ParentContainerFragment
        extends BaseContainerFragment {

    private static final String ARGUMENT_PARENT_ROOT = "ARGUMENT_PARENT_ROOT";

    private ParentContainerRoot mRoot;
    private IBaseListener mIBaseListener;

    public static ParentContainerFragment newInstance(IBaseListener iBaseListener, ParentContainerRoot root) {
        ParentContainerFragment fragment = new ParentContainerFragment();
        fragment.setIBaseListener(iBaseListener);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARGUMENT_PARENT_ROOT, root);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRoot = (ParentContainerRoot) getArguments().get(ARGUMENT_PARENT_ROOT);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    @Override
    protected IBasePresenter initPresenter() {
        return null;
    }

    private void init() {
        if (mRoot == null) {
            return;
        }
        switch (mRoot) {
            case PARENT_TAB_0:
                addChildFragment(DiscoverFragment.newInstance(mIBaseListener), false, false);
                break;
            case PARENT_TAB_1:
                addChildFragment(MapFragment.newInstance(mIBaseListener), false, false);
                break;
            case PARENT_TAB_2:
                addChildFragment(FavoriteFragment.newInstance(mIBaseListener), false, false);
                break;
            case PARENT_TAB_3:
                addChildFragment(ProfileFragment.newInstance(mIBaseListener), false, false);
                break;
        }
    }

    @Override
    public Fragment getCurrentFragment() {
        return super.getCurrentFragment();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.layout_parent_container;
    }

    @Override
    public int getResLayoutFrameId() {
        return R.id.frame_layout_container;
    }

    public void setIBaseListener(IBaseListener IBaseListener) {
        mIBaseListener = IBaseListener;
    }

    public enum ParentContainerRoot {
        PARENT_TAB_0(0),
        PARENT_TAB_1(1),
        PARENT_TAB_2(2),
        PARENT_TAB_3(3);

        ParentContainerRoot(int i) {
            this.value = i;
        }

        private int value;

        public int getValue() {
            return value;
        }
    }

    public void scrollTopRootScreen() {
        Fragment fragment = getCurrentFragment();
        if (fragment instanceof DiscoverFragment) {
            ((DiscoverFragment) fragment).smoothScrollToTop();
        } else if (fragment instanceof MapFragment) {
            ((MapFragment) fragment).smoothScrollToTop();
        } else if (fragment instanceof FavoriteFragment) {
            ((FavoriteFragment) fragment).smoothScrollToTop();
        } else if (fragment instanceof ProfileFragment) {
            ((ProfileFragment) fragment).smoothScrollToTop();
        }
    }

}
