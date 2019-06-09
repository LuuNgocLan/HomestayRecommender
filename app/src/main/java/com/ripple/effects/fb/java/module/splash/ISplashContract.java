package com.ripple.effects.fb.java.module.splash;

import com.base.java.mvp.IBasePresenter;

public interface ISplashContract {

    interface ISplashView {

        void goToMainActivity();

        void showErrorView(String message);
    }

    interface ISplashPresenter
            extends IBasePresenter<ISplashView> {

        void callApiRegister();

        void callApiRegisterFirebase();

        void checkEplusConnectStatus();
    }
}
