package com.ripple.effects.fb.java.module.main;

import com.base.java.mvp.IBasePresenter;
import com.base.java.mvp.IBaseView;

public interface IMainContract {

    interface IMainView extends IBaseView {

    }

    interface IMainPresenter extends IBasePresenter {
        void onStart();

        void onStop();
    }
}
