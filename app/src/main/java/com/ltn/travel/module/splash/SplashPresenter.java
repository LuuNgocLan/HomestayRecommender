package com.ltn.travel.module.splash;

import android.content.Context;

import com.base.java.core.utils.NetworkUtils;

public class SplashPresenter
        implements ISplashContract.ISplashPresenter {

    private ISplashContract.ISplashView mISplashView;
    private Context mContext;

    public SplashPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void callApiRegister() {

    }

    @Override
    public void callApiRegisterFirebase() {
        if (!NetworkUtils.isNetworkConnected(mContext)) {
            mISplashView.goToMainActivity();
            return;
        }

    }

    @Override
    public void checkEplusConnectStatus() {
    }

    @Override
    public void onCreate(ISplashContract.ISplashView iBaseView) {
        mISplashView = iBaseView;
    }

    @Override
    public void onDestroy() {

    }
}
