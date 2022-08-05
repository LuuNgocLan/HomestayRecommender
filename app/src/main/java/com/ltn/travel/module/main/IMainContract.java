package com.ltn.travel.module.main;

import com.base.java.mvp.IBasePresenter;
import com.base.java.mvp.IBaseView;

public interface IMainContract {

    interface IMainView extends IBaseView {

    }

    interface IMainPresenter extends IBasePresenter<IMainView> {
        void onStart();

        void onStop();
    }
}
