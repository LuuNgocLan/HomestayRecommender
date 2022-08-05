package com.ltn.travel.module.main;

import android.content.Context;

public class MainPresenter implements IMainContract.IMainPresenter {

    private IMainContract.IMainView mIMainView;
    private Context mContext;

    public MainPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate(IMainContract.IMainView iBaseView) {
        this.mIMainView = iBaseView;
    }

    @Override
    public void onDestroy() {
        this.mIMainView = null;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {

    }


}
