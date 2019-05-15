package com.ripple.effects.fb.java.module.popular;


import com.base.java.mvp.IBasePresenter;
import com.base.java.mvp.IBaseView;

public interface IMapContract {

    interface IMapView
            extends IBaseView {
        void showLoading();

        void hideLoading();

        void showMessage();
    }

    interface IMapPresenter
            extends IBasePresenter {
        void onStart();

        void fetchData();
    }
}
