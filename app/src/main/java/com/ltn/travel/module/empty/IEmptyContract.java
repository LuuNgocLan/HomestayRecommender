package com.ltn.travel.module.empty;

import com.base.java.mvp.IBasePresenter;
import com.base.java.mvp.IBaseView;

public interface IEmptyContract {

    interface IEmptyView extends IBaseView {

    }

    interface IEmptyPresenter extends IBasePresenter<IEmptyView> {

    }
}
