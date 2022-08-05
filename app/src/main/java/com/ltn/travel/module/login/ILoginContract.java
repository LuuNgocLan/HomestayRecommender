package com.ltn.travel.module.login;

import com.base.java.mvp.IBasePresenter;
import com.base.java.mvp.IBaseView;

public interface ILoginContract {
    interface ILoginView extends IBaseView {
        void onLoginSucces();

        void onLoginFailed();
    }

    interface ILoginPresenter extends IBasePresenter<ILoginView>{

        void callLogin(String username, String password);

    }
}
