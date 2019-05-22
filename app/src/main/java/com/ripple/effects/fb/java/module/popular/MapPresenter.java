package com.ripple.effects.fb.java.module.popular;

import android.content.Context;
import android.os.Handler;

import com.base.java.mvp.IBaseView;

public class MapPresenter implements IMapContract.IMapPresenter {

    private IMapContract.IMapView mIPopularView;
    private Context mContext;
    private boolean isRefresh;

    public MapPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate(IMapContract.IMapView iBaseView) {
        this.mIPopularView = iBaseView;
        isRefresh = true;
    }

    @Override
    public void onDestroy() {
        this.mIPopularView = null;
    }

    @Override
    public void onStart() {
        fetchData();
    }

    @Override
    public void fetchData() {
        if (!isRefresh) return;
        if (mIPopularView != null) {
            mIPopularView.showLoading();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mIPopularView.hideLoading();
                    mIPopularView.showMessage();
                    isRefresh = false;
                }
            }, 3000);
        }
    }
}
