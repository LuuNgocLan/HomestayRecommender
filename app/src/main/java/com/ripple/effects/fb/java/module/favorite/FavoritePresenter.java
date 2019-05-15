package com.ripple.effects.fb.java.module.favorite;

import android.content.Context;
import android.os.Handler;

import com.base.java.mvp.IBaseView;

public class FavoritePresenter implements IFavoriteContract.IFavoritePresenter {

    private IFavoriteContract.IFavoriteView mIFavoriteView;
    private Context mContext;
    private boolean isRefresh;

    public FavoritePresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate(IBaseView iBaseView) {
        this.mIFavoriteView = (IFavoriteContract.IFavoriteView) iBaseView;
        isRefresh = true;
    }

    @Override
    public void onDestroy() {
        this.mIFavoriteView = null;
    }

    @Override
    public void onStart() {
        fetchData();
    }

    @Override
    public void fetchData() {
        if (!isRefresh) return;
        if (mIFavoriteView != null) {

        }
    }
}
