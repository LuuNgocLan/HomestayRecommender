package com.ltn.travel.module.favorite;


import com.base.java.mvp.IBasePresenter;
import com.base.java.mvp.IBaseView;
import com.ltn.travel.models.favorite.Favorite;

import java.util.List;

public interface IFavoriteContract {

    interface IFavoriteView extends IBaseView {
        void showLoading();

        void hideLoading();

        void showMessage();

        void onSuccess(List<Favorite> favorites);

        void onError();
    }

    interface IFavoritePresenter extends IBasePresenter<IFavoriteView> {
        void onStart();

        void fetchData();
    }
}
