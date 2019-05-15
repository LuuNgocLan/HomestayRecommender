package com.ripple.effects.fb.java.module.discover;

import android.content.Context;
import android.os.Handler;

import com.base.java.mvp.IBaseView;

public class DiscoverPresenter implements IDiscoverContract.IDiscoverPresenter {

    private Context mContext;
    private IDiscoverContract.IDiscoverView mIHomeView;
    private boolean isRefresh;

    public DiscoverPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate(IBaseView iBaseView) {
        this.mIHomeView = (IDiscoverContract.IDiscoverView) iBaseView;
        isRefresh = true;
    }

    @Override
    public void onDestroy() {
        this.mIHomeView = null;
    }


    @Override
    public void onStart() {
        fetchData();
    }

    @Override
    public void fetchData() {
        if (!isRefresh) return;
        if (mIHomeView != null) {
            mIHomeView.showLoading();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mIHomeView.hideLoading();
                    mIHomeView.showMessage();
                    isRefresh = false;
                }
            }, 3000);
            mIHomeView.loadData();
        }
    }
}
