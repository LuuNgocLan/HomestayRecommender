package com.ripple.effects.fb.java.module.detail;

import android.content.Context;

import com.base.java.mvp.IBaseView;

public class DetailPresenter implements IDetailContract.IDetailPresenter {

    private Context mContext;
    private IDetailContract.IDetailView mIEmptyView;

    public DetailPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate(IBaseView iBaseView) {
        this.mIEmptyView = (IDetailContract.IDetailView) iBaseView;
    }

    @Override
    public void onDestroy() {
        this.mIEmptyView = null;
    }
}
