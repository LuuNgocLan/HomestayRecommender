package com.ltn.travel.module.profile;


import com.base.java.mvp.IBasePresenter;
import com.base.java.mvp.IBaseView;
import com.ltn.travel.models.profile.Profile;

public interface IProfileContract {

    interface IProfileView extends IBaseView {
        void onGetProfileSuccess(Profile profile);

        void onError();

    }

    interface IProfilePresenter extends IBasePresenter<IProfileView> {
        void getProfile();
    }
}
