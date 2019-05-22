package com.ripple.effects.fb.java.module.favorite;


import com.base.java.mvp.IBasePresenter;
import com.base.java.mvp.IBaseView;

public interface IFavoriteContract {

    interface IFavoriteView extends IBaseView {
        void showLoading();

        void hideLoading();

        void showMessage();
    }

    interface IFavoritePresenter extends IBasePresenter<IFavoriteView> {
        void onStart();

        void fetchData();
    }
}
