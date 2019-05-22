package com.ripple.effects.fb.java.module.detail;

import com.base.java.mvp.IBasePresenter;
import com.base.java.mvp.IBaseView;
import com.ripple.effects.fb.java.models.detailhomestay.Homestay;

public interface IDetailContract {

    interface IDetailView extends IBaseView {
        void onSucces(Homestay homestay);

        void onError();

        void onShowloading();

    }

    interface IDetailPresenter extends IBasePresenter<IDetailView> {
        void loadData(String homestayId);
    }
}
