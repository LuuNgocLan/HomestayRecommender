package com.ripple.effects.fb.java.module.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.ProgressBar;

import com.base.java.mvp.BaseActivity;
import com.base.java.mvp.IBasePresenter;
import com.ripple.effects.fb.java.R;
import com.ripple.effects.fb.java.module.main.MainActivity;

import butterknife.BindView;

public class SplashActivity
        extends BaseActivity
        implements ISplashContract.ISplashView {

    @BindView(R.id.activity_splash_progress_bar)
    ProgressBar mProgressBar;

    private ISplashContract.ISplashPresenter mISplashPresenter;

    @Override
    protected IBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mISplashPresenter = new SplashPresenter(SplashActivity.this);
        mISplashPresenter.onCreate(this);
        goToMainActivity();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mISplashPresenter.onDestroy();
    }

    @Override
    public void goToMainActivity() {
        new Handler().postDelayed(() -> {
            Intent mainIntent = new Intent(this, MainActivity.class);

            if (getIntent() != null && getIntent().getExtras() != null) {
                mainIntent.putExtras(getIntent().getExtras());
            }

            startActivity(mainIntent);
            finish();
        }, 1000);
    }

    @Override
    public void showErrorView(final String message) {

    }

}
