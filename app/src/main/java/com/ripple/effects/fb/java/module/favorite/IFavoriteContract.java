package com.ripple.effects.fb.java.module.favorite;


import com.base.java.mvp.IBasePresenter;
import com.base.java.mvp.IBaseView;
import com.ripple.effects.fb.java.models.favorite.Favorite;

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
