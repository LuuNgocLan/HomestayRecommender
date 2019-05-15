package com.ripple.effects.fb.java.module.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.base.java.IBaseListener;
import com.base.java.mvp.BaseActivity;
import com.base.java.mvp.IBasePresenter;
import com.ripple.effects.fb.java.R;
import com.ripple.effects.fb.java.module.base.ParentContainerFragment;
import com.ripple.effects.fb.java.module.discover.AllSpotsFragment;
import com.ripple.effects.fb.java.module.favorite.FavoriteFragment;
import com.ripple.effects.fb.java.module.discover.DiscoverFragment;
import com.ripple.effects.fb.java.module.main.adapter.ViewPagerAdapter;
import com.ripple.effects.fb.java.module.popular.MapFragment;
import com.ripple.effects.fb.java.module.profile.ProfileFragment;
import com.ripple.effects.fb.java.module.setting.SettingFragment;
import com.ripple.effects.fb.java.widget.CustomTab;
import com.ripple.effects.fb.java.widget.MainViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ripple.effects.fb.java.module.base.ParentContainerFragment.ParentContainerRoot.PARENT_TAB_0;
import static com.ripple.effects.fb.java.module.base.ParentContainerFragment.ParentContainerRoot.PARENT_TAB_1;
import static com.ripple.effects.fb.java.module.base.ParentContainerFragment.ParentContainerRoot.PARENT_TAB_2;
import static com.ripple.effects.fb.java.module.base.ParentContainerFragment.ParentContainerRoot.PARENT_TAB_3;

public class MainActivity extends BaseActivity implements IMainContract.IMainView, IBaseListener {

    @BindView(R.id.mvp_main)
    MainViewPager mMainViewPager;
    @BindView(R.id.tab)
    CustomTab mCustomTab;

    private IMainContract.IMainPresenter mIMainPresenter;
    private ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initViewPager();
        setEvent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mIMainPresenter != null) {
            mIMainPresenter.onStart();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mIMainPresenter != null) {
            mIMainPresenter.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected IBasePresenter initPresenter() {
        return mIMainPresenter = new MainPresenter(this);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    private Fragment getCurrentFragment() {
        if (mViewPagerAdapter != null)
            return mViewPagerAdapter.getItem(mMainViewPager.getCurrentItem());
        return null;
    }

    private Fragment getCurrentFragment(int position) {
        if (mViewPagerAdapter != null)
            return mViewPagerAdapter.getItem(position);
        return null;
    }

    private void initViewPager() {
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerAdapter.addFrag(ParentContainerFragment.newInstance(this, PARENT_TAB_0), getString(R.string.tab_home));
        mViewPagerAdapter.addFrag(ParentContainerFragment.newInstance(this, PARENT_TAB_1), getString(R.string.tab_popular));
        mViewPagerAdapter.addFrag(ParentContainerFragment.newInstance(this, PARENT_TAB_2), getString(R.string.tab_favorite));
        mViewPagerAdapter.addFrag(ParentContainerFragment.newInstance(this, PARENT_TAB_3), getString(R.string.tab_save));
        mMainViewPager.setAdapter(mViewPagerAdapter);
        mMainViewPager.setSwipeLocked(true);
        mMainViewPager.setOffscreenPageLimit(mViewPagerAdapter.getCount());
        mCustomTab.setupWithViewPager(mMainViewPager);
    }

    private void setEvent() {
        mCustomTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(final TabLayout.Tab tab) {
                updateTabSelected(tab.getPosition());
            }

            @Override
            public void onTabUnselected(final TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(final TabLayout.Tab tab) {
                handleTabReselected();
            }
        });
    }

    private void handleTabReselected() {
        Fragment fragment = getCurrentFragment();
        if (fragment instanceof ParentContainerFragment) {
            boolean isRoot = ((ParentContainerFragment) getCurrentFragment()).popToRoot();
            if (isRoot) {
                ((ParentContainerFragment) fragment).scrollTopRootScreen();
            } else {
                ((ParentContainerFragment) getCurrentFragment()).popToRoot();
            }

        }
    }

    private void updateTabSelected(int currentTab) {
        ParentContainerFragment parentContainerFragment = (ParentContainerFragment) mViewPagerAdapter.getItem(currentTab);
        Fragment fragment = parentContainerFragment.getCurrentFragment();
        if (fragment instanceof DiscoverFragment) {
            ((DiscoverFragment) fragment).requestLoadHomeData();
        } else if (fragment instanceof FavoriteFragment) {
//            ((FavoriteFragment) fragment).requestLoadFavoriteData();
        } else if (fragment instanceof MapFragment) {
            ((MapFragment) fragment).requestLoadPopularData();
        } else if (fragment instanceof ProfileFragment) {
            ((ProfileFragment) fragment).requestLoadSaveData();
        }
    }

    @Override
    public void onBackPressed() {
        boolean isAllowBack = true;
        Fragment fragment = getCurrentFragment();
        if (fragment instanceof ParentContainerFragment) {
            isAllowBack = ((ParentContainerFragment) fragment).allowBackForMain();
        }

        if (isAllowBack) {
            if (mMainViewPager.getCurrentItem() != 0) {
                mMainViewPager.setCurrentItem(0);
            } else {
                super.onBackPressed();
            }
        }
    }

}
