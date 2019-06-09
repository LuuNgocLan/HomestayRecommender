package com.ripple.effects.fb.java.module.tutorial;

import com.base.java.mvp.IBasePresenter;
import com.base.java.mvp.IBaseView;

public interface ITutorialContract {

    interface ITutorialView extends IBaseView {

    }

    interface ITutorialPresenter extends IBasePresenter<ITutorialView> {

    }
}
