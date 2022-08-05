package com.ltn.travel.module.setting;

import android.content.Context;

public class SettingPresenter implements ISettingContract.ISettingPresenter {

    private Context mContext;
    private ISettingContract.ISettingView mISettingView;

    public SettingPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate(ISettingContract.ISettingView iBaseView) {
        this.mISettingView = (ISettingContract.ISettingView) iBaseView;
    }

    @Override
    public void onDestroy() {
        this.mISettingView = null;
    }
}
