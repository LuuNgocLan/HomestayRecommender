package com.ripple.effects.fb.java.module.setting;

import android.content.Context;

import com.base.java.mvp.IBaseView;

public class SettingPresenter implements ISettingContract.ISettingPresenter {

    private Context mContext;
    private ISettingContract.ISettingView mISettingView;

    public SettingPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate(IBaseView iBaseView) {
        this.mISettingView = (ISettingContract.ISettingView) iBaseView;
    }

    @Override
    public void onDestroy() {
        this.mISettingView = null;
    }
}
