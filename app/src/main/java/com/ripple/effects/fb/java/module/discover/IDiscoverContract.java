package com.ripple.effects.fb.java.module.discover;

import com.base.java.mvp.IBasePresenter;
import com.base.java.mvp.IBaseView;
import com.ripple.effects.fb.java.models.homestay.Homestay;

import java.util.List;

public interface IDiscoverContract {

    interface IDiscoverView extends IBaseView {
        void showLoading();

        void hideLoading();

        void showMessage();

        void onSuccesSpots(List<Homestay> homestayList);

        void onSuccessRecommend(List<Homestay> homestayList);

        void onSuccessDestinations();

        void onUpdateFavoriteSuccess();

        void onUpdateFavoriteFailed();

    }

    interface IDiscoverPresenter extends IBasePresenter<IDiscoverView> {

        void onStart();

        void loadDataStatic();

        void loadDataRecommendation();

        void updateFavorite(String idHomestay);
    }
}
