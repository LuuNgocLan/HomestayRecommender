package com.ripple.effects.fb.java.module.discover.allSpots;

import com.base.java.mvp.IBasePresenter;
import com.base.java.mvp.IBaseView;
import com.ripple.effects.fb.java.models.homestay.Homestay;

import java.util.List;

public interface IAllSpotsContract {
    interface View extends IBaseView {
        void onLoading();

        void onGetDataSuccess(List<Homestay> homestays);
    }

    interface Presenter extends IBasePresenter<View> {
        void fetchData();
    }
}
