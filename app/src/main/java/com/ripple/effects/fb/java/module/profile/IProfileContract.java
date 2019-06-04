package com.ripple.effects.fb.java.module.profile;


import com.base.java.mvp.IBasePresenter;
import com.base.java.mvp.IBaseView;
import com.ripple.effects.fb.java.models.profile.Profile;

public interface IProfileContract {

    interface IProfileView extends IBaseView {
        void onGetProfileSuccess(Profile profile);

        void onError();

    }

    interface IProfilePresenter extends IBasePresenter<IProfileView> {
        void getProfile();
    }
}
