package com.ripple.effects.fb.java.module.discover;

import com.base.java.mvp.IBasePresenter;
import com.base.java.mvp.IBaseView;

public interface IDiscoverContract {

    interface IDiscoverView extends IBaseView {
        void showLoading();

        void hideLoading();

        void showMessage();

        void loadData();
    }

    interface IDiscoverPresenter extends IBasePresenter {

        void onStart();

        void fetchData();
    }
}
