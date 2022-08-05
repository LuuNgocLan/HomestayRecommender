package com.ltn.travel.module.favorite;

import android.content.Context;

import com.ltn.travel.models.data.DataCenter;

public class FavoritePresenter implements IFavoriteContract.IFavoritePresenter, DataCenter.OnGetDataListener {

    private IFavoriteContract.IFavoriteView mIFavoriteView;
    private Context mContext;
    private boolean isRefresh;
    private DataCenter mDataCenter;

    public FavoritePresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate(IFavoriteContract.IFavoriteView iBaseView) {
        this.mIFavoriteView = iBaseView;
        isRefresh = true;
        mDataCenter = DataCenter.getInstance();
        mDataCenter.setOnGetDataListener(this);
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
            mIFavoriteView.onSuccess(mDataCenter.getFavorites());
        }
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError() {
        if (mIFavoriteView != null) {
            mIFavoriteView.onError();
        }
    }
}
