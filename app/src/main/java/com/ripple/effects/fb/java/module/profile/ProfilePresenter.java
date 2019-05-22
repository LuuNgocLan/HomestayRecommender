package com.ripple.effects.fb.java.module.profile;

import android.content.Context;

import com.base.java.mvp.IBaseView;

public class ProfilePresenter implements IProfileContract.IProfilePresenter {

    private IProfileContract.IProfileView mISaveView;
    private Context mContext;

    public ProfilePresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate(IProfileContract.IProfileView iBaseView) {
        this.mISaveView = (IProfileContract.IProfileView) iBaseView;
    }

    @Override
    public void onDestroy() {
        this.mISaveView = null;
    }
}
