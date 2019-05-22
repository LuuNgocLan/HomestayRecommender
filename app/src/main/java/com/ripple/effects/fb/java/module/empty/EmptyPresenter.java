package com.ripple.effects.fb.java.module.empty;

import android.content.Context;

import com.base.java.mvp.IBaseView;

public class EmptyPresenter implements IEmptyContract.IEmptyPresenter {

    private Context mContext;
    private IEmptyContract.IEmptyView mIEmptyView;

    public EmptyPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate(IEmptyContract.IEmptyView iBaseView) {
        this.mIEmptyView =  iBaseView;
    }

    @Override
    public void onDestroy() {
        this.mIEmptyView = null;
    }
}
