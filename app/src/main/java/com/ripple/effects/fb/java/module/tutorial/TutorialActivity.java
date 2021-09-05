package com.ripple.effects.fb.java.module.tutorial;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.widget.TextView;

import com.base.java.mvp.BaseActivity;
import com.base.java.mvp.IBasePresenter;
import com.ripple.effects.fb.java.R;
import com.ripple.effects.fb.java.module.splash.SplashActivity;
import com.ripple.effects.fb.java.widget.indicator.CircleIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TutorialActivity extends BaseActivity implements ITutorialContract.ITutorialView {

    public static final String LOCATION_PERMISSION[] = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    public static final int REQUEST_CODE_ACCESS_LOCATION = 200;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.indicator)
    CircleIndicator mIndicator;
    @BindView(R.id.tv_close)
    TextView mTvClose;

    private ITutorialContract.ITutorialPresenter mITutorialPresenter;
    private TutorialAdapter mAdapter;

    private boolean isShowGPSDialog;

    @Override
    protected IBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        extractDataFromShare();

        mITutorialPresenter = new TutorialPresenter(this);
        mITutorialPresenter.onCreate(this);
        initView();
    }


    private void initView() {
        mAdapter = new TutorialAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mIndicator.setViewPager(mViewPager, mAdapter.getCount());
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixel) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        mTvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSplashScreen();
            }
        });
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_tutorial;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mITutorialPresenter != null) {
            mITutorialPresenter.onDestroy();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void goToSplashScreen() {
        if (isFinishing()) {
            return;
        }

        Intent splashIntent = new Intent(this, SplashActivity.class);
        startActivity(splashIntent);
        finish();
    }

    private void extractDataFromShare() {

    }

    public void initGpsService() {
        if (isShowGPSDialog) {
            return;
        } else {
            isShowGPSDialog = true;
        }
        if (!checkGrantedLocationPermission()) {
            return;
        }
    }

    private boolean checkGrantedLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    LOCATION_PERMISSION,
                    REQUEST_CODE_ACCESS_LOCATION);
            return false;
        }
        return true;
    }

    private class TutorialAdapter extends FragmentStatePagerAdapter {

        TutorialAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return TutorialFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Parcelable saveState() {
            Bundle bundle = (Bundle) super.saveState();
            if (bundle != null) {
                bundle.putParcelableArray("states", null);
            }
            return bundle;
        }
    }
}
